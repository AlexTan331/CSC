#include "stdafx.h"
#include "ListInterface.h"
#include "LinkedList.h"
#include "StackInterface.h"
#include "ArrayStack.h"
#include "Node.h"
#include "Point.h"
#include "PrecondViolatedExcept.h"
#include "Editor.h"
#include "Snapshot.h"
#include <iostream>
#include <string>
using namespace std;

int main()
{
	Editor xEditor;
	xEditor.readfile("sample.txt");
	xEditor.run();

    return 0;
}
