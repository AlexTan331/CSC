#pragma once
#ifndef HEAP_SORT_
#define HEALP_SORT_

template <class T>
class MinHeap {
public:
    MinHeap(int MSize);
    ~MinHeap();
    MinHeap<T>& Insert(T& x);
    MinHeap<T>& Delete(T& x);
    int Size;

private:
    int MaxSize;
    T *heap;
};

#endif