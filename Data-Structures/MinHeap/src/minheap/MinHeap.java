/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minheap;
import java.util.ArrayList;

/**
 *
 * @author Kevin Lin
 */
public class MinHeap {

    ArrayList<Integer> minHeap;
    private int size;
    
    MinHeap()
    {
        this.minHeap=new ArrayList();
        this.size=0;
    }
    
    public int getSize()
    {
        return size;
    }
    
    private int getLeftChildIndex(int parentIndex)
    {
        return 2*parentIndex+1;
    }
    
    private int getRightChildIndex(int parentIndex)
    {
        return 2*parentIndex+2;
    }
    
    private int getParentIndex(int childIndex)
    {
        return (childIndex-1)/2;
    }
    
    private boolean hasLeftChild(int index)
    {
        return getLeftChildIndex(index)<size;
    }
    
    private boolean hasRightChild(int index)
    {
        return getRightChildIndex(index)<size;
    }
    
    private boolean hasParent(int index)
    {
        return getParentIndex(index)>=0;
    }
    
    private int leftChild(int index)
    {
        return minHeap.get(getLeftChildIndex(index));
    }
    
    private int rightChild(int index)
    {
        return minHeap.get(getRightChildIndex(index));
    }
    
    private int parent(int index)
    {
        return minHeap.get(getParentIndex(index));
    }
    
    private void swap(int indexOne,int indexTwo)
    {
        int temp=minHeap.get(indexOne);
        minHeap.set(indexOne,minHeap.get(indexTwo));
        minHeap.set(indexTwo,temp);
    }
    
    public int peek()
    {
        if (size==0)
        {
            throw new IllegalStateException();
        }
        else
        {
            return minHeap.get(0);
        }
    }
    
    public int poll()
    {
        if (size==0)
        {
            throw new IllegalStateException();
        }
        int value=minHeap.get(0);
        minHeap.set(0,minHeap.get(size-1));
        minHeap.remove(size-1);
        size--;
        heapifyDown();
        return value;
    }
    
    public void add(int value)
    {
        minHeap.add(value);
        size++;
        heapifyUp();
    }
    
    public void heapifyUp()
    {   
        int index=size-1;
        while(hasParent(index) && parent(index)>minHeap.get(index))
        {
            swap(getParentIndex(index),index);
            index=getParentIndex(index);
        }
    }
    
    public void heapifyDown()
    {
        int index=0;
        while(hasLeftChild(index))
        {
            int smallerChildIndex=getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index)<leftChild(index))
            {
                smallerChildIndex=getRightChildIndex(index);
            }
            
            if (minHeap.get(index)<minHeap.get(smallerChildIndex))
            {
                break;
            }
            else
            {
                swap(index,smallerChildIndex);
                index=smallerChildIndex;
            }
        }
    }

}
