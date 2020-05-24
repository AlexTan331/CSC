#pragma once
#include "Point.h"
#include <string>
using namespace std;
class Snapshot {
private:
	string StoredValue;
	Point StoredPosition;
	string StoredCommand;
public:
	Snapshot();
	void setStoredValue(const string& value);
	void setStoredPosition(const Point& position);
	void setStoredCommand(const string& command);
	string getStoredValue()const;
	Point getStoredPosition()const;
	string getStoredCommand()const;
};
