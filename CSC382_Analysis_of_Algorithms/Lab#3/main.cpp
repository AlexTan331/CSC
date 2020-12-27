#include <iostream>
#include <time.h>
#include <stdlib.h>
#include <fstream>
#include <vector>
#include <cmath>
using namespace std;

int Insertion_Mod(int A[], int n);

int main() {

    vector<int> inputSize;

    ifstream myfile("sizes.txt");
    if (!myfile) {
        cout << "unable to open file";
    }

    while (!myfile.eof()) {
        int input;
        myfile >> input;
        inputSize.push_back(input);
    }

    myfile.close();

    for (int i = 0; i < inputSize.size(); i++) {
        cout << inputSize.at(i) << " ";
    }

    const int bound = 10000;
    vector<double> results;
    srand(time(NULL));


    for (int i = 0; i < inputSize.size(); i++) {
        double A1; //calculated average-case A1(n)
        double A2; //real average-case A2(n)
        int totNumberSteps = 0;

        A1 = (pow(inputSize.at(i), 2)  + 3 * inputSize.at(i)) / 4.0;
        results.push_back(A1);

        int **sequence = new int*[1000];
        for (int j = 0; j < 1000; j++) {
            sequence[j] = new int[inputSize.at(i)];
            for (int z = 0; z < inputSize.at(i); z++) {
                sequence[j][z] = rand() % (bound + 1);
            }
            totNumberSteps += Insertion_Mod(sequence[j], inputSize.at(i) - 1);
        }

        A2 = totNumberSteps / 1000.0;
        results.push_back(A2);

    }

    cout << "\nInput size" << "\tCalculated Average" << "\tReal Average" << endl;
    for (int i = 0; i <= results.size() - 2; i += 2) {
        printf("%7d %20.3f %20.3f \n", inputSize.at(i / 2), results.at(i), results.at(i + 1));
    }

    return 0;
}


int Insertion_Mod(int A[], int n) // array of size n
{
    int steps = 0;
    int i, j, temp;
    A[0] = -32768; //smallest possible integer using 2 bytes integer representation
    for (i = 1; i <= n; i++) {
        j = i;
        while (A[j] < A[j - 1]) { // swap 
            temp = A[j];
            A[j] = A[j - 1];
            A[j - 1] = temp;
            j--;
            steps++;
        }
    }

    return steps;
}
