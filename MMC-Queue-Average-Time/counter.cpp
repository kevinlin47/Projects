//
//  counter.cpp
//  C
//
//  Created by Kevin Lin on 10/15/16.
//  Copyright © 2016 Kevin Lin. All rights reserved.
//

#include"counter.h"
#include<vector>
#include<iostream>

counter::counter(int s,int st):nTeller(s),serviceTime(st)
{
    t.resize(s);
    for (int i=0;i<t.size();++i)
    {
        t[i].status=0;
    }
}

void counter::assignCounter(customer &custy)
{
    int j=0;



    if (custy.nCustomers<=getnTeller())
    {
        for (int i=0;i<custy.nCustomers;++i)
        {
            t[i].status=1;
        }
    }

    else if (custy.nCustomers>getnTeller())
    {
        for (int z=0;z<getnTeller();++z)
        {
            t[z].status=1;
        }
    }


    for (int i=0;i<custy.tCustomers;++i)
    {
        custy.cQ[i].aC=j+1;
        ++j;

        if (j>getnTeller()-1)
        {
            j=0;
        }
    }




}

int counter::getnTeller()
{
    return nTeller;
}

int counter::getStatus(int i)
{
    return t[i].status;
}

void counter::calcWait(customer &custy)
{
    if (serviceTime==1)
    {
    int t=0;
    int x=1;
    int m=0;
    int j=0;
    int g=custy.nCustomers;


    if (getnTeller()>=custy.nCustomers)
    {
        for (int i=0;i<custy.getSize();++i)
        {
            custy.cQ[i].waitTime=0;
        }
        return;
    }

    if(getnTeller()==1)
    {
        for (int i=0;i<custy.getSize();++i)
        {

                custy.cQ[i].waitTime=t;

                if (i+1>=x*custy.nCustomers)
                {
                    --t;
                    ++x;
                }
                ++t;


        }
    }
    else
    {


        for (int i=0;i<custy.getSize();++i)
        {




            for (int l=0;l<getnTeller();++l)
            {
                custy.cQ[m].waitTime=t;

                if (m==custy.getSize())
                {
                    break;
                }
                ++m;
            }

            ++j;
            if (j==x*custy.nCustomers/getnTeller())
            {
                --t;
                ++x;
            }
            ++t;




        }
    }
    }

    else
    {
        int t=0;
        int x=1;
        int m=0;
        int j=0;

        if(getnTeller()==1)
        {
        for (int i=0;i<custy.getSize();++i)
        {

                custy.cQ[i].waitTime=t;



                if (i+1>=x*custy.nCustomers)
                {
                    t=(t+(serviceTime-1));

                }
                t=t+serviceTime;
                if (i+1>=x*custy.nCustomers)
                {
                    t=t-serviceTime;
                    ++x;
                }
        }
                return;
        }

       if (getnTeller()/custy.nCustomers>=serviceTime)
       {
            for (int i=0;i<custy.getSize();++i)
            {
              custy.cQ[i].waitTime=0;
            }
            return;
       }
       else
       {
            for (int i=0;i<custy.getSize();++i)
            {
                for (int l=0;l<getnTeller();++l)
                {
                custy.cQ[m].waitTime=t;

                    if (m==custy.getSize())
                    {
                     break;
                    }
                    ++m;
                }

                    ++j;
                if (j==x*custy.nCustomers/getnTeller())
                {
                    t=t+(serviceTime-1);
                }
                 t=t+serviceTime;
                 if (j==x*custy.nCustomers/getnTeller())
                 {
                     t=t-serviceTime;
                     ++x;
                 }

            }
       }
    }
}














void counter::setComplete(customer &custy)
{
    for (int i=0;i<custy.getSize();++i)
    {
        custy.cQ[i].completionTime=custy.cQ[i].waitTime+serviceTime;
    }
}

void counter::processC(customer &custy)
{
    calcWait(custy);
    setComplete(custy);

    int a=0;
    int m=1;
    for (int i=0;i<custy.tCustomers;++i)
    {
        std::cout<<"Customer "<<a+1<<" arrived at min"<<custy.cQ[i].arrivalTime<<std::endl;
        std::cout<<"Customer "<<a+1<<" is "<<custy.cQ[i].service<<std::endl;
        std::cout<<"Customer "<<a+1<<" waited "<<custy.cQ[i].waitTime<<" minute(s)"<<std::endl;
        std::cout<<"Customer "<<a+1<<" was served for "<<serviceTime<<"minute(s)"<<std::endl;
        std::cout<<"Customer "<<a+1<<" finished after "<<custy.cQ[i].completionTime<<" minute(s)"<<std::endl;
        std::cout<<"\n";
        ++a;
    }
        std::cout<<"Average wait time: "<<averageTime(custy)<<"minute(s)"<<std::endl;
}

double counter::averageTime(customer &custy)
{
    double sum=0;


    for (int i=0;i<custy.tCustomers;++i)
    {
        sum=sum+custy.cQ[i].waitTime;
    }

    double average=sum/custy.tCustomers;

    return average;
}
