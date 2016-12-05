#ifndef ACCOUNT_H_INCLUDED
#define ACCOUNT_H_INCLUDED
#include<vector>
#include<string>

class Account
{
public:
    Account(int a, double b)
    :accountNumber(a),balance(b)
    {

    }
    virtual void deposit(double)=0;
    virtual void withdraw(double)=0;
    virtual double getBalance()=0;
    virtual std::string getType()=0;
    virtual int getNumber()=0;
    virtual void print()=0;
    virtual ~Account(){};
    double balance;
    int accountNumber;
    std::string type;

private:
    std::vector<Account>bank_accounts;
};

#endif // ACCOUNT_H_INCLUDED
