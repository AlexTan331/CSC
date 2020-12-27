#pragma once
#ifndef GRAPH
#define GRAPH
#include <stack>

struct node {
    int vertex;
    node * next;
};

class Graph {
public:
    Graph(int nodes);
    ~Graph();
    void create();
    void DFS();
    void DFSHelper(int father, int v);
    int getNmbrcmpnts();
    int getNmbrCycles();
    void showLinkedList();
    node * headnodes;

private:
    int vertices; // number of vertices
    int Nmbrcmpnts = 0; //we initialize the counter for the number of components
    int nmbrCycles = 0;  
    bool * visited;
    std::stack<int> edgesCycle; //store nodes in a cycle
};

#endif