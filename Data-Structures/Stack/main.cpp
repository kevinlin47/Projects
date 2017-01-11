#include"stack.h"
#include<time.h>
#include<random>

int main()
{
    std::cout<<"Testing all stack methods"<<std::endl;

    std::cout<<"\nAdding 20 random numbers into the stack"<<std::endl;

    stack testStack;
    srand(time(NULL));

    for (int i=0;i<20;++i)
    {
        int randomEntry=rand()%99+1;
        testStack.push(randomEntry);
    }

    std::cout<<"\nDisplaying all the items in the current stack:"<<std::endl;
    testStack.printStack();

    std::cout<<"\n\nRemoving the last 10 entries from the stack:"<<std::endl;
    for (int i=0;i<10;++i)
    {
        testStack.pop();
    }
    testStack.printStack();

    std::cout<<"\n\nDisplaying number at the top of the current stack:"<<std::endl;
    std::cout<<testStack.peek()<<std::endl;

    std::cout<<"\n\nDisplaying current number of items in the stack:"<<std::endl;
    std::cout<<testStack.currSize()<<std::endl;

    std::cout<<"\n\nTesting isEmpty function:"<<std::endl;
    std::cout<<testStack.isEmpty()<<std::endl;

    std::cout<<"\n\nClearing the stack:"<<std::endl;
    testStack.clearStack();
    testStack.printStack();
    std::cout<<"\n\nCalling isEmpty on cleared stack:"<<std::endl;
    std::cout<<testStack.isEmpty()<<std::endl;

}
