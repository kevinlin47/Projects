#include<iostream>
#include<stdlib.h>
#include<string>



void inorder (std::string [],int); //in-order traversal function prototype

int main()
{
    int size=10; //start with size 10
    int option; //for selection menu
    int index=0; //keep track of location in array that is occupied


    std::string *treeArray= new std::string [size]; //dynamic array

    for (int i=0;i<size;++i) //initialize array entries with empty string
    {
        treeArray[i]=" ";
    }


    while (true)
    {
        std::cout<<"\nSelect option: "<<std::endl;
        std::cout<<"1. Add to tree"<<std::endl;
        std::cout<<"2. Delete from tree"<<std::endl;
        std::cout<<"3. Search tree"<<std::endl;
        std::cout<<"4. Print" <<std::endl;
        std::cout<<"5. Clear tree"<<std::endl;
        std::cout<<"6. Terminate program"<<std::endl;
        std::cin>>option;

        if (option==1)
        {
            std::string name;
            std::cout<<"Enter name: ";
            std::cin>>name;
            treeArray[index]=name;
            ++index;

        }

        if (option==2)
        {
            std::string name;
            std::cout<<"Enter name to delete: ";
            std::cin>>name;
            bool found=false;

            for (int i=0;i<index;++i)
            {
                if (treeArray[i]==name)
                {
                    --index;
                    treeArray[i]=treeArray[index];
                    treeArray[index]=" ";
                    found=true;
                    break;
                }
            }

            if (found==false)
            {
                std::cout<<"\nName not found"<<std::endl;
            }
        }

        if (option==3)
        {
            std::string name;
            std::cout<<"Enter name to search: "<<std::endl;
            std::cin>>name;
            bool found=false;

            for (int i=0;i<index;++i)
            {
                if (treeArray[i]==name)
                {
                    std::cout<<"\nFound at index "<<i<<std::endl;
                    found=true;
                }
            }

            if (found==false)
            {
                std::cout<<"\nName not found"<<std::endl;
            }
        }

        if (option==4)
        {
            if(2*(index-1)+2>size-1) //Increase the size of the array if needed
            {
                int tempSize=size*2;

                std::string *tempArray= new std::string [tempSize];

                for (int i=0;i<tempSize;++i)
                {
                    tempArray[i]=" ";
                }

                for (int i=0;i<size;++i)
                {
                    tempArray[i]=treeArray[i];
                }

                delete [] treeArray;
                treeArray=tempArray;
                tempArray=NULL;
                size=tempSize;
            }



            std::cout<<std::endl;
            inorder(treeArray,0);
            std::cout<<std::endl;
        }

        if(option==5)
        {
                int size=10;
                delete[] treeArray; //delete old array
                treeArray= new std::string [size]; //create new array with original size and empty entries

                for (int i=0;i<size;++i)
                {
                    treeArray[i]=" ";
                }

        }

        if (option==6)
        {
            std::cout<<"\nProgram terminated"<<std::endl;
            return 0;
        }


    }
}

void inorder (std::string treeArray[],int index)
{

    int leftIndex=2*index+1;


    if (treeArray[leftIndex]!=" ")
    {
        inorder(treeArray,leftIndex);
    }

    std::cout<<treeArray[index]<<" ";

    int rightIndex=2*index+2;

    if (treeArray[rightIndex]!=" ")
    {
        inorder(treeArray,rightIndex);
    }
}

