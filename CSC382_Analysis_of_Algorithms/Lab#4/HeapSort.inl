#include "HeapSort.h"
#include "stdafx.h"

using namespace std;
template <class T>
MinHeap<T>::MinHeap(int MSize)
{
    MaxSize = MSize;
    heap = new T[MaxSize + 1];
    Size = 0;
}

template <class T>
MinHeap<T>& MinHeap<T>::Insert(T& x)
{
    if (Size == MaxSize)
        throw 20;
    else
    {
        int i = ++Size;
        while (i != 1 && x < heap[i / 2])
        {
            heap[i] = heap[i / 2];
            i /= 2;
        }
        heap[i] = x;
        return *this;
    }
}

template <class T>
MinHeap<T>& MinHeap<T>::Delete(T& x)
{
    if (Size == 0) throw 10;
    x = heap[1];  //root has the smallest key
    T y = heap[Size--]; //last element
    int vacant = 1;
    int child = 2; //make child = left child
    while (child <= Size)
    {
        if (child < Size && heap[child] > heap[child + 1]) ++child;
        //right child < left child
        if (y <= heap[child]) break;
        heap[vacant] = heap[child]; //move smaller child
        vacant = child; //new vacant
        child = child * 2; // new child of vacant

    }
    heap[vacant] = y;
    return *this;
}


template<class T>
MinHeap<T>::~MinHeap()
{
    delete[] heap;
}