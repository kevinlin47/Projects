package pkg.qq;

public class QueueDriver {

	//Driver program to test our queue class
	public static void main(String[] args) {
		
		//Instantiate a new queue object
		Queue queue=new Queue();
		
		//Add the alphabet into our queue
		for (int i=97;i<=122;++i)
		{	
			char toAdd=(char)i;
			queue.add(toAdd);
		}
		
		//Look at first value using peek
		System.out.println("Top of queue: "+queue.peek());
		
		//Test isEmpty()
		if (queue.isEmpty())
		{
			System.out.println("Queue is empty");
		}
		else
		{
			System.out.println("Queue is not empty");
		}
		
		//We will print our queue using remove and isEmpty() as our while loop condition
		while(!queue.isEmpty())
		{
			System.out.print(queue.remove()+" ");
		}
		

	}

}
