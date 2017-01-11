#ifndef STACK_H_INCLUDED
#define STACK_H_INCLUDED
#include<iostream>

class stack{

    public:
    stack()
    {
        stackPtr=new int [10]; //set the pointer to point to a new array size 10
    }

    //stack methods
    int* push(int);
    int* pop();
    int peek();
    bool isEmpty();
    int currSize();
    void printStack();
    void clearStack();

    private:
    int numItems=0; //track number of items in the stack
    int topStack=-1; //index of the top item in the stack
    int arraySize=10; //keep track of the size of the array
    int* stackPtr=NULL; //pointer to the stack (dynamic array)


};

#endif // STACK_H_INCLUDED
