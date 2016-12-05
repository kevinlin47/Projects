//
//  customer.cpp
//  C
//
//  Created by Kevin Lin on 10/15/16.
//  Copyright © 2016 Kevin Lin. All rights reserved.
//

#include "customer.h"
#include<time.h>
#include<random>
#include<vector>



customer::customer(int n, int t)
{
    timeLength=t;
    nCustomers=n;
    tCustomers=n*t;
    srand(time(NULL));
    cQ.resize(tCustomers);


    for (int i=0;i<cQ.size();++i)
    {
        int s=rand()%10+1;

        if (s<5)
        {
                cQ[i].service="Withdrawing";
        }
        else
        {
            cQ[i].service="Depositing";
        }
    }


}

std::string customer::getService(int i)
{
    return cQ[i].service;
}

int customer::getSize()
{
    return cQ.size();
}


void customer::setArrival()
{
    int m=2;
    int n=2;
    int z=1;
    int k=nCustomers;

    for (int i=0;i<nCustomers;++i)
    {
        cQ[i].arrivalTime=1;
    }
    for (int i=1;i<timeLength;++i)
    {
        for (int j=z*nCustomers;j<n*nCustomers;++j)
        {cQ[k].arrivalTime=m;
        ++k;
        }
        ++z;
        ++m;
        ++n;




    }
}

int customer::getArrival(int i)
{
    return cQ[i].arrivalTime;
}

int customer::getWait(int i)
{
    return cQ[i].waitTime;
}

int customer::getComplete(int i)
{
    return cQ[i].completionTime;
}
