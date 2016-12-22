#include<iostream>
#include<vector>
#include<algorithm>

struct node
{
    int n;
    node *next;
};
node *head=NULL;

int getCurrentSize();
bool isEmpty();
void add(int);
bool remove(int);
void clear();
bool contains(int);
int getFrequency(int);
void print();
std::vector<int> toVector();
void sortList();

int main()
{
    int option=0;

    while (true)
    {
    std::cout<<"Select option:  "<<std::endl;
    std::cout<<"1.Add item"<<std::endl;
    std::cout<<"2.Get current size"<<std::endl;
    std::cout<<"3.Print list"<<std::endl;
    std::cout<<"4.Remove item"<<std::endl;
    std::cout<<"5.Quit"<<std::endl;
    std::cout<<"6.Clear list"<<std::endl;
    std::cout<<"7.Contains"<<std::endl;
    std::cout<<"8.Convert to vector"<<std::endl;
    std::cout<<"9.Sort current list"<<std::endl;


    std::cin>>option;

    if (option==1)
    {
        int n;
        std::cout<<"\nEnter number: ";
        std::cin>>n;
        add(n);
    }
    else if (option==2)
    {
        std::cout<<"\nSize of current list is -> "<<getCurrentSize()<<std::endl;
    }
    else if (option==3)
    {
        print();
    }
    else if (option==4)
    {
        int number;
        std::cout<<"Enter number to remove: ";
        std::cin>>number;
        int sucess=remove(number);

        if (sucess==1)
        {
            std::cout<<"\nRemoved successfully"<<std::endl;
        }
        else
        {
            std::cout<<"Number was not found"<<std::endl;
        }

    }
    else if (option==5)
    {
        return 0;
    }
    else if (option==6)
    {
        clear();
    }
    else if (option==7)
    {
        int checkNumber;
        std::cout<<"Enter number to check: ";
        std::cin>>checkNumber;

        if (contains(checkNumber)==true)
        {
            std::cout<<"\nList does contain the number"<<std::endl;
        }

        else
        {
            std::cout<<"List does NOT contain the number"<<std::endl;
        }
    }
    else if (option==8)
    {
        std::vector<int>toPrint=toVector();
        std::cout<<"\n";
        for (int i=0;i<toPrint.size();++i)
        {
            std::cout<<toPrint[i]<<" ";
        }
        std::cout<<"\n";
    }
    else if (option==9)
    {
        sortList();
    }
    else
    {
        std::cout<<"\nEntered invalid option\n"<<std::endl;
    }
    }
}

int getCurrentSize()
{
    int size=0;
    node* curr=head;

    while (curr!=NULL)
    {
        ++size;
        curr=curr->next;
    }

    return size;
}

bool isEmpty()
{
    if (head==NULL)
    {
        return true;
    }

    else
        return false;
}

void add(int number)
{

        node *newNodeptr=new node;
        newNodeptr->n=number;
        newNodeptr->next=head;
        head=newNodeptr;

}

bool remove(int number)
{
        node* currptr=head;;
        node* prevptr=head;
        node *temp=head;

        if (number==head->n)
        {
            head=temp->next;
            temp->next=NULL;
            delete temp;
            temp=NULL;
            return true;
        }

        else
        {


            while (currptr!=NULL)
            {

                if (currptr->n==number)
                {
                    prevptr->next=currptr->next;
                    currptr->next=NULL;
                    delete currptr;
                    currptr=NULL;
                    return true;

                }
                else
                {
                    prevptr=currptr;
                    currptr=currptr->next;
                }
            }

        }


}

void print()
{
    node *curr=head;

    while (curr!=NULL)
    {
        std::cout<<"\n"<<curr->n<<" ";
        curr=curr->next;
    }
    std::cout<<"\n\n";
}

void clear()
{
    node* currptr=head;


    while (currptr!=NULL)
    {
        head=head->next;
        currptr->next=NULL;
        delete currptr;
        currptr=head;

    }
}

bool contains(int number)
{
    node *currptr=head;

    while (currptr!=NULL)
    {
        if (currptr->n==number)
        {
            return true;
        }
        else
        {
            currptr=currptr->next;
        }
    }

    return false;
}

std::vector<int> toVector()
{
    std::vector<int>list;
    node* curr=head;

    while (curr!=NULL)
    {
      int tempN=curr->n;
      list.push_back(tempN);
      curr=curr->next;
    }

    return list;
}

void sortList()
{
    std::vector<int>vectorToSort=toVector();

    std::sort(vectorToSort.begin(),vectorToSort.end());
    std::cout<<"\n";

    for (int i=0;i<vectorToSort.size();++i)
    {
        std::cout<<vectorToSort[i]<<" ";
    }
    std::cout<<"\n"<<std::endl;

}
