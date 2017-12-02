#ifndef DYNAMICARRAY_H_INCLUDED
#define DYNAMICARRAY_H_INCLUDED

class DynamicArray
{

public:

    //constructor
    DynamicArray()
    {
        //set size to 0, no items
        size=0;

        //set initial array capacity to 10
        capacity=10;

        //create initial array
        a=new int[capacity];
    }

    //class methods
    int getSize();
    void add(int);
    bool remove(int);
    int search(int);
    int get(int);
    void print();



    //Data members
    private:
        int size;
        int capacity;
        int *a;
};


#endif // DYNAMICARRAY_H_INCLUDED
