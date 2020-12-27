#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <ctime>
#include <iomanip>
#include <stdlib.h>
#include <algorithm>
#include <string>
#include "HeapSort.inl"
using namespace std;

int * original_array;   //store random number  
int * insertionsort_array;  //copied data from original_array to be sorted by insertion sort
int * mergesort_array;  //copied data from original_array to be sorted by merge sort
int * temp;     //global array for storing data temporarily in merge sort
int * heapsort_array;   //copied data from original_array to be sorted by heap sort
const int max_range = 20000;     //max range to generate random number
int arr_index = 0;      //used to reset the size of bounds[]

void InitNCopyArrays(int size);
void Insertion(int A[], int n); 
void Merge(int A[], int low, int mid, int high);
void MergeSort(int A[], int low, int high);
string FindBestTime(float insert_time, float merge_time, float heap_time);

int main()
{
    //store all the bounds from the text file
    int bounds[50];

    //store each sorting execution time into array
    float insertion_sort_time[50];
    float merge_sort_time[50];
    float heap_sort_time[50];

    //load data from file to bounds[]
    ifstream myfile("bounds.txt");
    if (!myfile) {
        cout << "unable to open file";
    }

    while (!myfile.eof() && arr_index < 50) {
        myfile >> bounds[arr_index];
        arr_index++;
    }

    myfile.close();

    srand(time(NULL));


    std::cout << "Input_Length | Insertion_Sort(s) | Merge_Sort(s) | Heap_Sort(s) | Best_Time\n";
    //record executing time of each sorting method. 
    //repeat the steps for inputs of different size
    //this case, we have input size of 1000, 10000, 25000, 50000, 150000, 250000
    for (int i = 0; i < arr_index; i++) { 

        InitNCopyArrays(bounds[i]);

        //record timing for executing insertion sort
        clock_t start_insertion_sort = clock();

        Insertion(insertionsort_array, bounds[i] - 1);

        clock_t end_insertion_sort = clock();

        insertion_sort_time[i] = ((float)(end_insertion_sort - start_insertion_sort)) / CLOCKS_PER_SEC;


        //record timing for executing merge sort 
        clock_t start_merge_sort = clock();

        MergeSort(mergesort_array, 0, bounds[i] - 1);

        clock_t end_merge_sort = clock();

        merge_sort_time[i] = ((float)(end_merge_sort - start_merge_sort)) / CLOCKS_PER_SEC;


        
        int b = bounds[i];  //input size
        MinHeap<int>* h = new MinHeap<int>(b);

        //record timing for executing heap sort 
        clock_t start_heap_sort = clock();

        for (int i = 0; i < b; i++) {
            h->Insert(heapsort_array[i]);
        }
        for (int i = 0; i < b; i++) {
            h->Delete(heapsort_array[i]);
        }

        clock_t end_heap_sort = clock();
        h->~MinHeap();
        heap_sort_time[i] = ((float)(end_heap_sort - start_heap_sort)) / CLOCKS_PER_SEC;


        std::string best_time = FindBestTime(insertion_sort_time[i], merge_sort_time[i], heap_sort_time[i]);

        //print out the result and compare with their execution times
        printf("%d\t\t  %4.2f\t\t    %4.2f\t      %4.2f\t   %s\n", bounds[i], insertion_sort_time[i], 
            merge_sort_time[i], heap_sort_time[i], best_time.c_str());

    }

    delete[] temp;
    delete[] original_array;
    delete[] insertionsort_array;
    delete[] heapsort_array;
    delete[] mergesort_array;

    return 0;
}

void InitNCopyArrays(int size) 
{
    original_array = new (nothrow) int[size];   //dynamically allocate original_array with input size 
    insertionsort_array = new (nothrow) int[size];  //dynamically allocate insertionsort_array with input size 
    mergesort_array = new (nothrow) int[size];  //dynamically allocate mergesort_array with input size 
    temp = new (nothrow) int[size];     //dynamically allocate temp with input size 
    heapsort_array = new (nothrow) int[size];   //dynamically allocate heapsort_array with input size 

    if (original_array == nullptr || insertionsort_array == nullptr || mergesort_array == nullptr || heapsort_array == nullptr)
        cout << "Error! Memory could not be allocated!\n";

    //generate random numbers and store into original array
    for (int i = 0; i < size; i++) {
        original_array[i] = rand() % (max_range + 1);
    }

    //copy from original array to insertion sort array
    for (int i = 0; i < size; i++) {
        insertionsort_array[i] = original_array[i];
    }

    //copy from original array to merge sort array
    for (int i = 0; i < size; i++) {
        mergesort_array[i] = original_array[i];
    }

    //copy from original array to heap sort array
    for (int i = 0; i < size; i++) {
        heapsort_array[i] = original_array[i];
    }

}

void Insertion(int A[], int n) // in reality the elements to be sorted are indexed from
                               // index 1 to index n
{
    int i, j, temp;
    A[0] = -32768; //smallest possible integer using 2 bytes integer representation

    for (i = 1; i <= n; i++) {
        j = i;
        while (A[j] < A[j - 1]) { // swap 
            temp = A[j];
            A[j] = A[j - 1];
            A[j - 1] = temp;
            j--;
        }
    }
}

void Merge(int A[], int low, int mid, int high)    // we assume that temp is a global variable                                                                       // variable
{
    int l = low, i = low, h = mid + 1, k; // i is the index corresponding to array temp
    while ((l <= mid) && (h <= high)) { // exactly one of the pointers will reach its limit
        if (A[l] <= A[h]) {
            temp[i] = A[l];      // push A[l] to temp
            l++;                //increment l
        }
        else {                 // we must A[h] to temp
            temp[i] = A[h];
            h++;
        }
        i++;
    }  //end while 
       // now one of the pointer has reached its limit so we push the remaining  
       // elements into temp.
    if (l > mid) {  // we pushed the remaining elements starting at A[h]
        for (k = h; k <= high; k++) {
            temp[i] = A[k];
            i++;
        } // end for
    }
    else {  // we push remaining elements starting at A[l]
        for (k = l; k <= mid; k++) {
            temp[i] = A[k];
            i++;
        }
    } // end else

      // Next we move temp[low:high] to A[low:high] 
    for (k = low; k <= high; k++) {
        A[k] = temp[k];
    } // end for
} // end algorithm

void MergeSort(int A[], int low, int high)
{
    if (low < high) { // if the sub-list has more than one element
        int mid = (low + high) / 2;
        MergeSort(A, low, mid); // we call Mergesort for the first half
        MergeSort(A, mid + 1, high); //we call Mergesort for the second half
            // at this point the two halves are sorted
        Merge(A, low, mid, high);
    }
} // end algorithm

string FindBestTime(float insert_time, float merge_time, float heap_time) 
{
    string best_time;
    if (insert_time <= merge_time && insert_time <= heap_time)
        return "Insert";
    else if (merge_time <= insert_time && merge_time <= heap_time)
        return "Merge";
    else if (heap_time <= insert_time && heap_time <= merge_time)
        return "Heap";
    else
        return "Invalid";

}