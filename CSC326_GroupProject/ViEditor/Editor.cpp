#include "stdafx.h"
#include "Editor.h"
#include "Snapshot.h"
#include "BinarySearchTree.h"
#include <fstream>
#include <string>
#include <iostream>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include "windows.h"
#include <conio.h>
using namespace std;

const int ESC = 27;
const int backspace = 8;
void placeCursorAt(Point coordinate) {
	COORD coord;
	coord.X = coordinate.getX();
	coord.Y = coordinate.getY();
	SetConsoleCursorPosition(
		GetStdHandle(STD_OUTPUT_HANDLE),
		coord);
	
}
Editor::Editor()
{
	Position.setX(0);
	Position.setY(0);
}

void Editor::readfile(string afile)
{
	int curPosition = 1;
	string aline;
	ifstream infile;
	infile.open(afile);
	if (!infile)
	{
		cout << "Error!" << endl;
		system("pause");
	}

	while (!infile.eof())
	{
		getline(infile, aline);
		lines.insert(curPosition, aline);
		curPosition++;
	}
	infile.close();
}

void Editor::run()
{
	display();
	placeCursorAt(Position);    //Cursor at (0,0) when display the file at the beginning.
	int preSize;					//record the size of an entry within the list before move up or down 
	string EraseAChar;			//used for erasing a character of an entry

	char ch;						// for 
	int UpdateX;					// insert
	int UpdateY;					// characters 
	string InsertAChar=" ";		// into 
	string whole;				// entry

	char Usercommand;			
	string precommand;			
	Snapshot copy;				

	do
	{
		Usercommand = _getch();
		switch (Usercommand)
		{
		case 'j':											//limits position Y not greater than the length of the list
			if (Position.getY() <lines.getLength()-1 )		//position Y counts from 0, lines counts from 1, thus -1
			{
				preSize = Position.getX() + 1;;			//position X counts from 0, thus +1
				Position.down();
				if (preSize > CurSize(Position.getY()))		//compare lengths between two entries
				{
					Position.setX(CurSize(Position.getY()) - 1);   
					placeCursorAt(Position);
				}
				else
					placeCursorAt(Position);			
			}
			break;
		case 'k':									//limits position Y not smaller than the length of the list
			if (Position.getY() > 0)
			{
				preSize = Position.getX() + 1;
				Position.up();
				if (preSize > CurSize(Position.getY()))		//compare lengths between two entries
				{
					Position.setX(CurSize(Position.getY()) - 1);		
					placeCursorAt(Position);
				}
				else
					placeCursorAt(Position);
			}
			break;
		case 'h':											//limits position X not smaller than 0
			if (Position.getX() > 0)			 
			{
				Position.left();
				placeCursorAt(Position);
			}
			break;
		case 'l':
			if (Position.getX() < CurSize(Position.getY())-1)	//limits position X not greater than the size of an entry
			{
				Position.right();
				placeCursorAt(Position);
			}
			break;
		case 'd':									
			Usercommand = _getwch();					//double tap dd to successfully excute than delete function
			if (Usercommand == 'd')
			{
				copy.setStoredCommand("dd");									//copy the command
				copy.setStoredPosition(Position);							//copy the position	
				copy.setStoredValue(lines.getEntry(Position.getY() + 1));   //copy the entry
				undoStack.push(copy);										//push all three of them to a stack

				lines.remove(Position.getY() + 1);
				display();
				placeCursorAt(Position);
			}
			break;
		case 'x':
			copy.setStoredCommand("x");										//copy the command
			copy.setStoredPosition(Position);								//copy the position
			copy.setStoredValue(lines.getEntry(Position.getY() + 1));		//copy the entry
			undoStack.push(copy);											//push all three of them to a stack

			EraseAChar = lines.getEntry(Position.getY() + 1);				
			EraseAChar.erase(EraseAChar.begin() + Position.getX());		//erase a single character within an entry
			lines.replace(Position.getY() + 1,EraseAChar);			//replace the entry in the list by a modified 'EraseAChar'
			display();
			placeCursorAt(Position);
			break;
		case 'u':
			if (!undoStack.isEmpty())			//check if there is any recorded command in the stack before undo
			{
				precommand = undoStack.peek().getStoredCommand();		//set precommand to be the most previous command
				if (precommand == "dd")			//insert the most previous entry in respond to the right position in the list
											
	  			{					//position of the most previous entry		   //value of the most previous entry
					lines.insert(undoStack.peek().getStoredPosition().getY() + 1, undoStack.peek().getStoredValue());
					display();
					Position = undoStack.peek().getStoredPosition();
					placeCursorAt(Position);      //place the cursor where it was
					undoStack.pop();				 
				}
				else if (precommand == "x")	//replace the most previous entry in respond to the right position in the list
											
				{					//position of the most previous entry			//value of the most previous entry
					lines.replace(undoStack.peek().getStoredPosition().getY() + 1, undoStack.peek().getStoredValue());	
					display();
					Position = undoStack.peek().getStoredPosition();
					placeCursorAt(Position);			//place the cursor where it was
					undoStack.pop();
				}
				else if (precommand == "I")	//replace the most previous entry in respond to the right position in the list
				{
					lines.replace(undoStack.peek().getStoredPosition().getY() + 1, undoStack.peek().getStoredValue());
					display();
					Position = undoStack.peek().getStoredPosition();
					placeCursorAt(Position);			//place the cursor where it was
					undoStack.pop();
				}
				else if (precommand == "i") //replace the most previous entry in respond to the right position in the list
				{
					lines.replace(undoStack.peek().getStoredPosition().getY() + 1, undoStack.peek().getStoredValue());
					display();
					Position = undoStack.peek().getStoredPosition();
					placeCursorAt(Position);			//place the cursor where it was
					undoStack.pop();
				}
			}
			break;
		case 'I':
			UpdateX = 0;
			Position.setX(0);												//Insert content at the begining of the entry
			copy.setStoredCommand("I");										//copy the command
			copy.setStoredPosition(Position);								//copy the position
			copy.setStoredValue(lines.getEntry(Position.getY() + 1));		//copy the entry
			undoStack.push(copy);											//push all three of them to a stack
			placeCursorAt(Position);
			InsertAChar = lines.getEntry(Position.getY() + 1);
			
			do
			{
				ch = _getche();
				switch (ch)
				{
				case ESC:		
					break;
				case backspace:
					if (Position.getX() == 0 &&Position.getY()==0 )       //cursor at the origin (0,0)
					{
						display();
						placeCursorAt(Position);
					}
					else if (Position.getX() > 0 && Position.getY()>=0)     //cursor at somewhere within an entry 
					{
						EraseAChar = lines.getEntry(Position.getY() + 1);
						EraseAChar.erase(EraseAChar.begin() + Position.getX());
						lines.replace(Position.getY() + 1, EraseAChar);
						UpdateX--;
						Position.setX(UpdateX);
						display();
						placeCursorAt(Position);
					}
					else if (Position.getX() == 0 && Position.getY() > 0)       //cursor at the begining of the entry 
					{
						copy.setStoredCommand("I");										//copy the command
						copy.setStoredPosition(Position);								//copy the position
						copy.setStoredValue(lines.getEntry(Position.getY() + 1));		//copy the entry
						undoStack.push(copy);									//push all three of them to a stack

						UpdateY = Position.getY();
						InsertAChar = lines.getEntry(Position.getY() + 1);
						lines.remove(Position.getY() + 1);
						UpdateY--;
						Position.setY(UpdateY);
						UpdateX = CurSize(Position.getY());
						Position.setX(UpdateX);
						whole = lines.getEntry(Position.getY() + 1);
						whole.insert(Position.getX(), InsertAChar);
						lines.replace(Position.getY() + 1, whole);
						display();
						placeCursorAt(Position);
					}
					break;
				case '\r':

				default:
					UpdateX++;
					InsertAChar.insert(Position.getX(), 1, ch);			//string& insert(size_t pos, size_t n, char c);
					lines.replace(Position.getY() + 1, InsertAChar);
					Position.setX(UpdateX);
					display();
					placeCursorAt(Position);
					break;
				}
			} while (ch != ESC); 
			break;
		case 'i':
			UpdateX = Position.getX();
			copy.setStoredCommand("i");										//copy the command
			copy.setStoredPosition(Position);								//copy the position
			copy.setStoredValue(lines.getEntry(Position.getY() + 1));		//copy the entry
			undoStack.push(copy);											//push all three of them to a stack
			InsertAChar = lines.getEntry(Position.getY() + 1);

			do
			{
				ch = _getche();
				switch (ch)
				{
				case ESC:
					break;
				case backspace:
					UpdateX--;
					InsertAChar.insert(Position.getX(), 1, ch);				//string& insert(size_t pos, size_t n, char c);
					lines.replace(Position.getY() + 1, InsertAChar);
					Position.setX(UpdateX);
					display();
					placeCursorAt(Position);
					break;
				default:
					UpdateX++;
					InsertAChar.insert(Position.getX(), 1, ch);				//string& insert(size_t pos, size_t n, char c);
					lines.replace(Position.getY() + 1, InsertAChar);
					Position.setX(UpdateX);
					display();
					placeCursorAt(Position);
					break;
				}
			} while (ch != ESC);
			break;
		default:
			break;
		}
	} while (Usercommand!='E');
}

void Editor::display()const
{
	system("CLS");
	for (int i = 1; i <= lines.getLength(); i++)
	{
		cout << lines.getEntry(i) << endl;
	}
}

int Editor::CurSize(int Position)     //return the size of an entry within the linkedlist
{										//Parameter 'Position' is the Y coordinate
	string aEntry;
	int size;
	aEntry = lines.getEntry(Position + 1);
	size = aEntry.size();
	return size;
}
