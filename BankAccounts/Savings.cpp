#include"Savings.h"
#include<iostream>
#include<iomanip>
#include<cstdio>

Savings::Savings(int a,double b)
:Account(a,b)
{

    interestRate=.01;
    type="Saving";
}

void Savings::deposit(double d)
{
    double deposit=d;
    double newBalance=balance*interestRate+deposit;
    balance=balance+newBalance;
    std::cout<<"\nDeposited $"<<std::fixed<<std::setprecision(2)<<d<<" to "<<type<<" account "
    <<accountNumber<<", the new balance is $"<<getBalance()<<std::endl;

}

void Savings::withdraw(double w)
{
    if(w>balance)
    {
        std::cout<<"Insufficient funds"<<std::endl;
    }
    else
    {
    double withdraw=w;
    double newBalance=balance-withdraw;
    balance=newBalance;
    std::cout<<"\nWithdrawn $"<<std::fixed<<std::setprecision(2)<<w<<" from "<<type<<" account "
    <<accountNumber<<", the new balance is $"<<getBalance()<<std::endl;


    }
}

double Savings::getBalance()
{
    return balance;
}

std::string Savings::getType()
{
    return type;
}

int Savings::getNumber()
{
    return accountNumber;
}

void Savings::print()
{
std::cout<<getNumber()<<std::setw(20)<<getType()<<" ";
printf("%8.2f\n",getBalance());

}
