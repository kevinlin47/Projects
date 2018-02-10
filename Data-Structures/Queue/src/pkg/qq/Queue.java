package pkg.qq;

import java.util.*;

public class Queue {
	
	private Node head;
	private Node tail;
	
	
	public Queue() {
		head=null;
		tail=null;
	}
	
	//Method returns true if the queue is empty, false otherwise
	public boolean isEmpty()
	{
		return head==null;
	}
	
	//returns the item at the front of the queue, does not remove it
	public char peek() throws NoSuchElementException
	{
		if (!isEmpty())
		{
			return head.getData();
		}
		else
		{
			throw new NoSuchElementException(); 
		}
	}
	
	//Add a new node to our queue
	public void add(char c)
	{
		if (head==null)
		{
			Node newNode=new Node(c);
			head=newNode;
			tail=newNode;
		}
		else
		{
			Node newNode=new Node(c);
			tail.next=newNode;
			tail=newNode;
		}
	}
	
	//Return value at the head of the list and remove it
	public char remove() throws NoSuchElementException
	{
		if (isEmpty())
		{
			throw new NoSuchElementException("Queue is empty");
		}
		else
		{	
			char nodeValue=head.getData();
			head=head.next;
			
			if (head==null)
			{
				tail=null;
			}
			return nodeValue;
		}
	}

}
