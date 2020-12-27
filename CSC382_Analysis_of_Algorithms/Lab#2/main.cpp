#include <iostream>
#include <time.h>
#include <stdlib.h>
#include <fstream>
#include <vector>
using namespace std;

int Find(int x, int A[], int n);

int main() {

    int boundArrIndex = 0;
    int bound[50];

    ifstream myfile("bound.txt");
    if (!myfile) {
        cout << "unable to open file";
    }

    while (!myfile.eof()) {
        myfile >> bound[boundArrIndex];
        boundArrIndex++;
    }

    myfile.close();

    const int n = 50; //array size
    vector<double> results;  //store the calculated and real average cases in a vector 

    int** sequences = new int*[10000];
    for (int i = 0; i < 10000; i++) { //initiate a 10000 x 50 2D array
        sequences[i] = new int[n];
    }

    srand(time(NULL));

    for (int index = 0; index < boundArrIndex; index++) {
        int x = 25; //let x be a random integer between 0 and bound
        int hits = 0;

        //step 1. calculated average
        //generate random numbers and store into a 10000 x 50 2D array
        for (int i = 0; i < 10000; i++) {
            bool isRepeat = false;
            for (int j = 0; j < n; j++) {
                int num = rand() % (bound[index] + 1);
                sequences[i][j] = num;
                if (!isRepeat && num == x) { //if random number is equal to x, increment hits by one (maximun once every row)
                    hits++;
                    isRepeat = true;
                }

            }
        }

        double q = hits / 10000.0;
        double calculatedAverage = n + q / 2 - q * n / 2;

        results.push_back(calculatedAverage);


        //step 2. real average
        int totalSteps = 0;
        for (int i = 0; i < 10000; i++) {
            if (Find(x, sequences[i], n) == 0)
                totalSteps += n;
            else
                totalSteps += Find(x, sequences[i], n);
        }

        results.push_back(totalSteps / 10000.0);

    }

    
    
    //step 3. compare calculated and read average cases in a table view
    cout << "  Bound" << "\t\tCalculated Average" << "\tReal Average" << endl;
    for (int i = 0; i <= results.size() - 2; i+=2) {
        printf("%10d %15.3f %20.3f \n", bound[i / 2], results.at(i), results.at(i + 1));
    }

    return 0;
}


int Find(int x, int A[], int n) // array of size n
{
    int  j;
    for (j = 0; j < n; j++) {
        if (x == A[j]) {
            return (j + 1); //the position is 1 more than the index
        }
    }
    return 0; // x is not an element of the array
}
