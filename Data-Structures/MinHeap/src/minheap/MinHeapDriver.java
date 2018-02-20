/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minheap;
import java.util.*;

/**
 *
 * @author Kevin Lin
 */
public class MinHeapDriver {
    
    public static void main(String args[])
    {
        MinHeap myHeap=new MinHeap();
        
        //we will add 10 random values from 0-99 into our min heap
        //the value will be shown before placed into the heap
        
        Random rn=new Random();
        for (int i=0;i<10;++i)
        {
            int randomValue=rn.nextInt(100);
            myHeap.add(randomValue);
            System.out.print(randomValue+" ");
        }    
        
        //lets call peek to see the smalled value in our heap
        System.out.println("\n"+myHeap.peek());
        
        //we will iterate and poll our min heap
        while(myHeap.getSize()>0)
        {
            System.out.print(myHeap.poll()+" ");
        }
    }
    
}
