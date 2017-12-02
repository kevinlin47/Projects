#include<iostream>
#include<stdexcept>
#include "DynamicArray.h"

//Function returns number of elements in the array
int DynamicArray::getSize()
{
    return size;
}

//Adds integer into the end of the array
void DynamicArray::add(int toAdd)
{
    //Check if the array capacity has been reached

    //if capacity has been reached we need to increase the capacity of the array and copy the values over
    if (size==capacity)
    {
        capacity*=2;

        int *tempArray=new int [capacity];

        for (int i=0;i<size;++i)
        {
            tempArray[i]=a[i];
        }

        //delete the old array, set array pointer to the new double capacity array
        delete[] a;

        a=tempArray;

        //increment size
        ++size;

        a[size-1]=toAdd;
    }
    //if capacity has not been reached
    else
    {
        //increment size variable
        ++size;

        //add integer into the end of the array
        a[size-1]=toAdd;

    }
}

//returns integer at given index
//returns -1 if array is empty
//throws exception if index is out of range or index is negative
int DynamicArray::get(int index)
{
    if (size==0)
    {
        std::cout<<"Array is empty"<<std::endl;
        return -1;
    }
    else if (index<0)
    {
        throw std::invalid_argument("Negative Index");
    }
    else if (index>size-1)
    {
        throw std::out_of_range("Index out of range");
    }

    return a[index];
}

//returns array index of key if found
//return -1 if key is not found
int DynamicArray::search(int key)
{
    if(size==0)
    {
        std::cout<<"Array is empty"<<std::endl;
        return -1;
    }
    else
    {
        //linear search, easier implementation for demonstration purpose
         for (int i=0;i<size;++i)
         {
             if (a[i]==key)
             {
                 return i;
             }
         }
    }

    return -1;
}

//function prints array to the console
void DynamicArray::print()
{
    for (int i=0;i<size;++i)
    {
        std::cout<<a[i]<<" ";
        if ((i+1)%10==0)
        {
            std::cout<<"\n";
        }
    }

    std::cout<<"\n";
}

//removes specified value from the array
//returns false if removal was not successful, because the integer was not found
//return true if the integer was removed from the array
bool DynamicArray::remove(int toRemove)
{
    int location=search(toRemove);

    if (location<0)
    {
        std::cout<<"Integer not found, removal failed"<<std::endl;
        return false;
    }
    else
    {
        for (int i=location;i<size;++i)
        {
            a[i]=a[i+1];
        }
        --size;

        if (size<=capacity/4)
        {
            capacity=capacity/2;

            int *tempArray=new int [capacity];

            for (int i=0;i<size;++i)
            {
                tempArray[i]=a[i];
            }

            delete[]a;

            a=tempArray;
        }
    }

    return true;
}

