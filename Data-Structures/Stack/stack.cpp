#include"stack.h"
#include<iostream>

//function to add new item to the top of the stack
//@ parameters int to add to the top of the stack
//@returns pointer to the stack
int* stack::push(int newItem)
{
    ++numItems;

    if (numItems>arraySize)
    {
        ++arraySize;

        int* tempStack = new int [arraySize];

        for (int i=0;i<arraySize;++i)
        {
            tempStack[i]=stackPtr[i];
        }

        stackPtr=tempStack;
        tempStack=NULL;
        delete tempStack;

        topStack=arraySize-1;
        stackPtr[topStack]=newItem;
    }

    else
    {
        ++topStack;
        stackPtr[topStack]=newItem;

    }

    return stackPtr;
}

//function to remove item at the top of the stack
//@parameters none
//@returns pointer to the stack
int* stack::pop()
{
    //check to see if the stack is empty
    if (numItems>0)
    {
        //if the number of items is equal to the size of the stack
        //means there is no more free space, so the stack is resized
        if (numItems==arraySize)
        {
            //create temporary array to hold everything from the stack
            //except for the item on the top
            int* tempArray=new int[arraySize-1];

            for (int i=0;i<arraySize-1;++i)
            {
                tempArray[i]=stackPtr[i];
            }

            //assign the stack pointer to point to the temporary array
            //delete the temporary array
            stackPtr=tempArray;
            tempArray=NULL;
            delete tempArray;

            --arraySize; //decrement stack size
            --numItems; //decrement total number of items in the stack
            topStack=arraySize-1; //assign topStack the index to the top of the stack
        }
        //if the number of items in the stack is less than the size of the stack
        //means there is still free space
        else
        {
            stackPtr[topStack]=0; //set top value to 0
            --topStack; //decrement index to the top of the stack
            --numItems; //decrement number of items in the stack
        }
    }

    //if stack is empty
    else
    {
        std::cout<<"\nStack is empty"<<std::endl;
    }

    return stackPtr;
}

//function to display all values currently in the stack
void stack::printStack()
{
    for (int i=0;i<numItems;++i)
    {
        std::cout<<stackPtr[i]<<" ";
    }
}

//function to get the value at the stop of the stack
//@Parameters none
//@Returns value at the top of the stack if it is not empty
//@Returns -1 if the stack is empty
int stack::peek()
{
    if (numItems==0)
    {
        std::cout<<"\nStack is empty"<<std::endl;
        return -1;
    }

    else
    {
        return stackPtr[topStack];
    }
}

//function to see if the stack is currently empty
//@Parameters none
//@Returns true if the stack has 0 items, false otherwise
bool stack::isEmpty()
{
    if (numItems==0)
    {
        return true;
    }

    else
    {
        return false;
    }
}

//@Parameters none
//@Returns number of items in the stack
int stack::currSize()
{
    return numItems;
}

//function to clear all items in the stack
//@Parameters none
//@Return none
void stack::clearStack()
{
    int *tempArray=new int [10]; //create new empty array of size 10

    delete stackPtr; //delete current stack

    stackPtr=tempArray; //assign pointer to new array

    numItems=0; //set number of items to zero
    topStack=-1; //set index of the top of the stack
    arraySize=10; //set array size back to 10
}
