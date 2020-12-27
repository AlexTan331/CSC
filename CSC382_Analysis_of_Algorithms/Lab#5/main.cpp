#include "stdafx.h"
#include <iostream>
#include "Graph.inl"

using namespace std;

char const QUIT = 'q';

int main()
{

    char choice;

    do {
        int vertices;

        std::cout << "Enter number of vertices: ";
        cin >> vertices;

        Graph G(vertices); // constructor
        G.create();

        // comment out the next line to display linked list of the graph
        // G.showLinkedList();

        G.DFS();
        std::cout << "Number of connected components: " << G.getNmbrcmpnts() << endl;
        std::cout << "number of cycles: " << G.getNmbrCycles() << endl;

        
        cout << "enter 'q' to exit, any other key to continue: ";
        cin >> choice;
    } while (tolower(choice) != QUIT);
    
    

    return 0;
}
