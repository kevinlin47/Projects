#include<iostream>
#include<vector>
#include<iomanip>
#include<cstdlib>
#include<time.h>
#include<string>
#include"Account.h"
#include"Savings.h"
#include"Checkings.h"

int linearSearch(int,std::vector<Account*>);

int main()
{

std::vector<Account*>bank_accounts;
srand(time(NULL));

for (int i=0;i<5;++i)
{
int attribute=rand()%2+1;
if(attribute==1)
{
    int a=rand()%1000+8999;
    double b=0+rand()%100;
    Savings savings1(a,b);
    bank_accounts.push_back(new Savings(a,b));
}

else if(attribute==2)
{
int a=rand()%1000+8999;
double b=0+rand()%100;
Checkings checkings1(a,b);
bank_accounts.push_back(new Checkings(a,b));
}
}

while(true)
{
int option;
std::cout<<"Select the following options"<<std::endl;
std::cout<<"1. Add new account"<<std::endl;
std::cout<<"2. Deposit to an account"<<std::endl;
std::cout<<"3. Withdraw from an account"<<std::endl;
std::cout<<"4. Print all accounts"<<std::endl;
std::cout<<"5. Quit"<<std::setw(15)<<" ";
std::cin>>option;
while(std::cin.fail())
{
    std::cout<<"Entered invalid input"<<std::endl;
    std::cin.clear();
    std::cin.ignore(256,'\n');
    std::cin>>option;
}

if(option==1)
{

    int aN;
    double balancerino;
    std::string type;
    std::cout<<"\n";
    std::cout<<"Enter account number:   ";
    std::cin>>aN;



    while (!std::cin)
    {
        std::cout<<"Invalid input!"<<std::endl;
        std::cin.clear();
        std::cin.ignore(256,'\n');
        std::cin>>aN;
    }
    while(aN<999)
    {
        std::cout<<"Not a four digit number."<<std::endl;
        std::cin>>aN;
        if (!std::cin)
        {
            std::cout<<"Invalid input!"<<std::endl;
            std::cin.clear();
            std::cin.ignore(256,'\n');
            std::cin>>aN;
        }
    }
    std::cout<<"Enter account type:     ";
    std::cin>>type;
    std::cout<<"Enter account balance:  ";
    std::cin>>balancerino;
    while(!std::cin)
    {
        std::cout<<"Invalid input!"<<std::endl;
        std::cin.clear();
        std::cin.ignore(256,'\n');
        std::cin>>balancerino;
    }
    std::cout<<std::endl;
    if (type=="Saving"||type=="saving")
    {
        Savings savings2(aN,balancerino);
        bank_accounts.push_back(new Savings(aN,balancerino));
    }
    else if (type=="Checking"||type=="checking")
    {
        Checkings checkings2(aN,balancerino);
        bank_accounts.push_back(new Checkings(aN,balancerino));
    }
    else
    {
        std::cout<<"Invalid account type!\n"<<std::endl;
    }
std::cout<<"\n"<<"Account number"<<std::setw(8)<<"Type"<<std::setw(13)<<"Balance"<<std::endl;
for (int i=0;i<bank_accounts.size();++i)
{
    bank_accounts[i]->print();
}
std::cout<<std::endl;
}

else if (option==2)
{
double deposit;
int aN;
std::cout<<"\nEnter account number:    ";
std::cin>>aN;
while (std::cin.fail())
{
    std::cout<<"Invalid input!"<<std::endl;
    std::cin.clear();
    std::cin.ignore(256,'\n');
    std::cin>>aN;
}
std::cout<<"Enter amount:            ";
std::cin>>deposit;
while (std::cin.fail())
{
    std::cout<<"Invalid input!"<<std::endl;
    std::cin.clear();
    std::cin.ignore(256,'\n');
    std::cin>>deposit;
}
int location=linearSearch(aN,bank_accounts);
    if(location>=0)
    {
        bank_accounts[location]->deposit(deposit);
        std::cout<<std::endl;
    }
    else
    {
        std::cout<<"\nAccount does not exist!\n"<<std::endl;
    }
}

else if(option==3)
{
    double withdraw;
    int aN;
    std::cout<<"\nEnter account number:     ";
    std::cin>>aN;
    while(std::cin.fail())
    {
        std::cout<<"Invalid input!"<<std::endl;
        std::cin.clear();
        std::cin.ignore(256,'\n');
        std::cin>>aN;
    }
    std::cout<<"Enter amount:             ";
    std::cin>>withdraw;
    while(std::cin.fail())
    {
        std::cout<<"Invalid input!"<<std::endl;
        std::cin.clear();
        std::cin.ignore(256,'\n');
        std::cin>>withdraw;
    }

int location=linearSearch(aN,bank_accounts);

if (location>=0)
{
    bank_accounts[location]->withdraw(withdraw);
    std::cout<<std::endl;
}
else
{
    std::cout<<"\nAccount does not exist!\n"<<std::endl;
}
}

else if (option==4)
{
std::cout<<"\n"<<"Account number"<<std::setw(8)<<"Type"<<std::setw(13)<<"Balance"<<std::endl;
for (int i=0;i<bank_accounts.size();++i)
{
    bank_accounts[i]->print();
}
std::cout<<std::endl;
}

else if (option==5)
{
    std::cout<<"\nProgram terminated"<<std::endl;
    return 0;
}



else
    {
        std::cout<<"\nInvalid choice try again\n"<<std::endl;
    }

}



}

int linearSearch(int aN,std::vector<Account*>bank_accounts)
{
    for (int i=0;i<bank_accounts.size();++i)
    {
        if(aN==bank_accounts[i]->accountNumber)
        {
            return i;
        }
    }
    return -1;
}

