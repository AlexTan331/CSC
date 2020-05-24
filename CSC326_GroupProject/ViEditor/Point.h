#pragma once
class Point
{
public:
	Point();
	void setX(int);
	void setY(int);
	int getX()const;
	int getY()const;
	void up();      //move cursor up one line
	void down();		//move cursor down one line
	void left();	    //move cursor left one character
	void right();	//move cursor right one character
private:
	int mX;
	int mY;
};