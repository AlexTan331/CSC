#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
#include <iomanip>

using namespace std;

double FiboRec(int n);
double FiboNoRec(int n);

int main(int arg[])
{
    int integers[50];
    int integer;
    int arrIndex = 0;

    float FiboRecTime[50];
    float FiboNoRecTime[50];
    double FiboValue[50];
    int tableIndex = 0;

    ifstream myfile("integers.txt");
    if (!myfile) {
        cout << "unable to open file";
    }
    
    while (!myfile.eof() && arrIndex < 50) {
        myfile >> integer;
        integers[arrIndex] = integer;
        arrIndex++;
    }

    myfile.close();


    for (int i = 0; i < arrIndex; i++) {

        //record timing for fibonacci using recursion 
        clock_t startFiboRec = clock();
        FiboValue[tableIndex] = FiboRec(integers[i]);
        clock_t endFiboRec = clock();
        
        FiboRecTime[tableIndex] = ((float)(endFiboRec - startFiboRec)) / CLOCKS_PER_SEC;

        
        //record timing for fibonacci using dynamic programming 
        clock_t startFiboNoRec = clock();
        FiboValue[tableIndex] = FiboNoRec(integers[i]);
        clock_t endFiboNoRec = clock();
        FiboNoRecTime[tableIndex] = ((float)(endFiboNoRec - startFiboNoRec)) / CLOCKS_PER_SEC;
       
        tableIndex++;
    }


    //display results in a table view
    cout << "Integer\t" << "FiboR(Seconds)\t" << "FiboNR(Seconds)\t" << "Fibo-value\n";
    for (int i = 0; i < arrIndex; i++) {
        printf("%3d %12.3f %18.5f %15.1f \n", integers[i], FiboRecTime[i], FiboNoRecTime[i], FiboValue[i]);
    }


    return 0;
}


double FiboRec(int n) // array of size n
{
    if (n == 0 || n == 1)
        return (n);
    else 
        return ((double)FiboRec(n - 1) + FiboRec(n - 2));
}

double FiboNoRec(int n) // array of size n
{
    double F[500];
    F[0] = 0; F[1] = 1;
    for (int i = 2; i <= n; i++)
    {
        F[i] = (double)F[i - 1] + F[i - 2];
    }
    return (F[n]);
}
