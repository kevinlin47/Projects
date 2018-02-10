package pkg.qq;

public class Node {
	
	private char data;
	public Node next;
	
	public Node(char c) {
		
		this.data=c;
		this.next=null;
	}
	
	public char getData()
	{
		return data;
	}
	
	public void setData(char c)
	{
		this.data=c;
	}

}
