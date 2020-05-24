#include "stdafx.h"
#include "Point.h"
Point::Point()
{
	mX = 0;
	mY = 0;
}

void Point::setX(int inX)
{
	mX = inX;
}

void Point::setY(int inY)
{
	mY = inY;
}

int Point::getX()const
{
	return mX;
}

int Point::getY()const
{
	return mY;
}

void Point::up()   
{
	mY = mY - 1;
}

void Point::down()
{
	mY = mY + 1;
}

void Point::left()
{
	mX = mX - 1;
}

void Point::right()
{
	mX = mX + 1;
}

