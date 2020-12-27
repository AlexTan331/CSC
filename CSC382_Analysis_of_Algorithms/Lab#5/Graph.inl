#include "Graph.h"
#include "stdafx.h"
#include <iterator>
#define FINISHED -1
using namespace std;

Graph::Graph(int nodes)
{
    vertices = nodes;
    headnodes = new node[vertices]; // headnodes is an array of nodes.
    for (int i = 0; i < vertices; i++)
    {
        headnodes[i].vertex = i;
        headnodes[i].next = nullptr;
    }
}

void Graph::create()
{
    int v1, v2;
    std::cout << "Enter a pair of nodes, separated by space (enter -1 to exit):";

    std::cin >> v1;

    do
    {
        std::cin >> v2;
        node * temp1 = new node;
        temp1->next = NULL;
        temp1->vertex = v1;

        node * cur1 = &headnodes[v2];

        while (cur1->next != NULL) {
            cur1 = cur1->next;
        }
        cur1->next = temp1;

         
        node * temp2 = new node;
        temp2->next = NULL;
        temp2->vertex = v2;

        node * cur2 = &headnodes[v1];

        while (cur2->next != NULL) {
            cur2 = cur2->next;
        }
        cur2->next = temp2;

        std::cout << "Enter a pair of nodes, separated by space (enter -1 to exit):";
        std::cin >> v1;

    } while (v1 != FINISHED);

}

void Graph::DFS()
{
    visited = new bool[vertices];

    for (int i = 0; i < vertices; i++)
        visited[i] = false;


    for (int i = 0; i < vertices; i++)
    {
        if (visited[i] == false) {
            DFSHelper(i, i);
            Nmbrcmpnts++;
        }
    }

    
}

void Graph::DFSHelper(int father, int v)
{
    visited[v] = true;
    edgesCycle.push(v);
    node* adjnode = headnodes[v].next;
    while (adjnode) // visit all vertices adjacent to v
    {
        if (visited[adjnode->vertex] == false) { //if adjacent vertex to v was not visited previously            
            DFSHelper(v, adjnode->vertex); // when we call DFS, we pass the father vertex of the call
            return;
        }
        else if (father != adjnode->vertex) // if the vertex adjacent to v is not the father, 
                                            // we have a cycle
        {
            nmbrCycles++;
            cout << "cycle: (" << adjnode->vertex;
            while (edgesCycle.top() != adjnode->vertex) {
                cout << "," << edgesCycle.top() << "), (" << edgesCycle.top();
                edgesCycle.pop();
            }
            cout << "," << edgesCycle.top() << ")" << endl;
            edgesCycle.pop();
        }
        adjnode = adjnode->next;
    }
   
}

int Graph::getNmbrcmpnts()
{
    return Nmbrcmpnts;
}


int Graph::getNmbrCycles() 
{
    return nmbrCycles;
}


void Graph::showLinkedList()
{
    // display linked list of the graph
    for (int i = 0; i < vertices; i++) {
        node *temp = &headnodes[i];
        cout << temp->vertex;
        while (temp->next) {
            temp = temp->next;
            cout << "->" <<temp->vertex;
            
        }
        std::cout << "\n\n";
    }
}


Graph::~Graph()
{
    delete headnodes;
}