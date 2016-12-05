#include"Checkings.h"
#include<iostream>
#include<iomanip>
#include<cstdio>

Checkings::Checkings(int a, double b)
:Account(a,b)
{
    transactionFee=0.50;
    type="Checking";
}

void Checkings::deposit(double d)
{
    double deposit=d;
    if (deposit>0)
    {
        balance=deposit+balance-transactionFee;
    std::cout<<"\nDeposited $"<<std::fixed<<std::setprecision(2)<<d<<" to "<<type<<" account "
    <<accountNumber<<", the new balance is $"<<getBalance()<<std::endl;

    }
    else
    {
        std::cout<<"Invalid amount"<<std::endl;
    }
}

void Checkings::withdraw(double w)
{
double withdraw=w;
double tempBalance=balance-transactionFee;
if(withdraw>tempBalance)
{
    std::cout<<"Insufficient funds\n"<<std::endl;
}
else
{
    balance=balance-withdraw-transactionFee;
    std::cout<<"\nWithdrawn $"<<std::fixed<<std::setprecision(2)<<w<<" from "<<type<<" account "
    <<accountNumber<<", the new balance is $"<<getBalance()<<std::endl;
}
}

double Checkings::getBalance()
{
    return balance;
}

std::string Checkings::getType()
{
    return type;
}

int Checkings::getNumber()
{
    return accountNumber;
}

void Checkings::print()
{
std::cout<<getNumber()<<std::setw(22)<<getType()<<" ";
printf("%6.2f\n",getBalance());

}


