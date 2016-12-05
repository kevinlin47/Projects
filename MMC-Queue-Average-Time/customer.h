//
//  customer.h
//  C
//
//  Created by Kevin Lin on 10/15/16.
//  Copyright © 2016 Kevin Lin. All rights reserved.
//

#ifndef customer_h
#define customer_h
#include<string>
#include<vector>




struct cust
{
    int arrivalTime; //customer arrival time
    int completionTime; //customer completion time
    int aC; //assigned teller number
    int status; //status of customer
    int waitTime; //wait time of customer
    std::string service; //service type of customer
};

class customer
{
friend class counter;

public:
    customer(int, int);
    std::string getService(int); //gets service type of a particular customer
    int getSize(); //gets size of the customer vector
    void setArrival(); //sets arrival time for each customer
    int getArrival(int);
    int getWait(int);
    int getComplete(int);




private:
    int nCustomers; //number of customers per minute
    int tCustomers; //total amount of customers during the whole duration
    int timeLength; //the time length duration example x amount of customers per minute for timeLength time
    std::vector <cust> cQ; //vector to hold customers


};


#endif /* customer_h */
