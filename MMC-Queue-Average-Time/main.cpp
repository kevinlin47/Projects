//
//  main.cpp
//  C
//
//  Created by Kevin Lin on 10/15/16.
//  Copyright © 2016 Kevin Lin. All rights reserved.
//

#include <iostream>
#include<vector>
#include<string>
#include"customer.h"
#include"counter.h"

int main()
{
    int nC; //number of customers per minute
    int time; //duration of customers arriving per minute
    int nT; //number of tellers
    int serviceTime; //service time for each customer

    std::cout<<"Enter number of customers per minute:  ";
    std::cin>>nC;

    std::cout<<"Enter time length:  ";
    std::cin>>time;

    std::cout<<"Enter number of tellers:  ";
    std::cin>>nT;

    std::cout<<"Enter service time length: ";
    std::cin>>serviceTime;

    customer c(nC,time);        //creates customer object with given
                                //number of customers per minute and for how long
    counter t(nT,serviceTime); //creates counter/teller object with given number of tellers and service time


    c.setArrival(); //sets arrival time for each customer
    t.assignCounter(c); //assigns a counter/teller to each customer
    t.processC(c); //displays time for each event in simulation





}

