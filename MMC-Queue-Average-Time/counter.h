//
//  counter.h
//  C
//
//  Created by Kevin Lin on 10/15/16.
//  Copyright © 2016 Kevin Lin. All rights reserved.
//

#ifndef counter_h
#define counter_h
#include<vector>
#include"customer.h"


struct teller
{
    int status;
};

class counter
{
friend class customer;

public:
    counter(int,int);
    void assignCounter(customer&); //assigns a teller to each customer
    int getnTeller(); //retrieves the number of tellers
    int getStatus(int); //retrieves teller's status
    void processC(customer&); //displays time of each event in simulation
    void calcWait(customer &); //calculates each customers wait time
    void setComplete(customer &); //calculates the time the customer is done
    double averageTime(customer &); //calculates the average wait time

private:
    std::vector<teller>t; //vector that holds the tellers
    int nTeller; //number of tellers
    int serviceTime; //service time per customer



};


#endif /* counter_h */
