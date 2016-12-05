#ifndef SAVINGS_H_INCLUDED
#define SAVINGS_H_INCLUDED
#include"Account.h"

class Savings : public Account
{
public:
    Savings(int,double);
    void deposit(double);
    void withdraw(double);
    double getBalance();
    std::string getType();
    int getNumber();
    void print();
    ~Savings(){};

private:
    float interestRate;



};



#endif // SAVINGS_H_INCLUDED
