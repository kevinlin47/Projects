#ifndef CHECKINGS_H_INCLUDED
#define CHECKINGS_H_INCLUDED
#include"Account.h"

class Checkings : public Account
{
public:
    Checkings(int,double);
    void deposit(double);
    void withdraw(double);
    double getBalance();
    std::string getType();
    int getNumber();
    void print();
    ~Checkings(){};

private:
    float transactionFee;
};


#endif // CHECKINGS_H_INCLUDED
