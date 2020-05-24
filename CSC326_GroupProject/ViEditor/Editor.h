#pragma once
#include "LinkedList.h"
#include "Point.h"
#include "ArrayStack.h"
#include "Snapshot.h"
#include <string>
using namespace std;
class Editor
{
public:
	Editor();
	void readfile(const string);
	void run();
	void display()const;
	//void CheckKeywords()const;
private:
	LinkedList<string> lines;
	Point Position;
	ArrayStack<Snapshot> undoStack;
	int CurSize(int);    //return the size of an entry within the linkedlist
};