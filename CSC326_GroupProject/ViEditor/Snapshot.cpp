#include "stdafx.h"
#include "Snapshot.h"
Snapshot::Snapshot()
{
	StoredCommand = "";
	StoredValue = "";
	StoredPosition.setX(0);
	StoredPosition.setY(0);
}

void Snapshot::setStoredValue(const string& value)
{
	StoredValue = value;
}

void Snapshot::setStoredPosition(const Point& position)
{
	StoredPosition = position;
}

void Snapshot::setStoredCommand(const string& command)
{
	StoredCommand = command;
}

string Snapshot::getStoredValue()const
{
	return StoredValue;
}

Point Snapshot::getStoredPosition()const
{
	return StoredPosition;
}

string Snapshot::getStoredCommand()const
{
	return StoredCommand;
}