package pks.bst;

public class BST {
	
	private Node root;
	
	BST()
	{
		this.root=null;
	}
	
	public void add(int d)
	{
		Node newNode=new Node(d);
		
		if (root==null)
		{
			root=newNode;
		}
		else
		{
			Node currNode=root;
			Node parent=null;
			
			while(true)
			{
				parent=currNode;
				
				if (d<currNode.data)
				{
					currNode=currNode.left;
					if (currNode==null)
					{
						parent.left=newNode;
						return;
					}
				}
				else
				{
					currNode=currNode.right;
					if (currNode==null)
					{
						parent.right=newNode;
						return;
					}
				}
			}
		}
	}
	
	public boolean remove(int d)
	{
		Node currNode=root;
		Node parent=currNode;
		
		boolean isALeftChild=true;
		
		while(currNode.data!=d)
		{	
			parent=currNode;
			if (d<currNode.data)
			{
				currNode=currNode.left;
				isALeftChild=true;
			}
			else
			{
				currNode=currNode.right;
				isALeftChild=false;
			}
			
			if (currNode==null)
			{
				return false;
			}
		}
		
		//check to see if its a lead node or a root node with no children
		if (currNode.left==null && currNode.right==null)
		{
			if (currNode==root)
			{
				root=null;
			}
			else if (isALeftChild)
			{
				parent.left=null;
			}
			else
			{
				parent.right=null;
			}
			
			return true;
		}
		//check to see if the node has no right child
		else if (currNode.right==null)
		{
			if (currNode==root)
			{
				root=currNode.left;
			}
			else if (isALeftChild)
			{
				parent.left=currNode.left;
			}
			else
			{
				parent.right=currNode.left;
			}
		}
		//check to see if the node has no left child
		else if (currNode.left==null)
		{
			if (currNode==root)
			{
				root=currNode.right;
			}
			else if (isALeftChild)
			{
				parent.left=currNode.right;
			}
			else
			{
				parent.right=currNode.right;
			}
		}
		//node has two children
		else
		{
			Node replacement=getReplacementNode(currNode);
			
			if (currNode==root)
			{
				root=replacement;
			}
			else if (isALeftChild)
			{
				parent.left=replacement;
			}
			else
			{
				parent.right=replacement;
			}
			
			replacement.left=currNode.left;
		}
		
		return true;
	}
	
	public Node getReplacementNode(Node replacedNode)
	{
		Node replacementParent=replacedNode;
		Node replacement=replacedNode;
		
		Node currNode=replacedNode.right;
		
		while(currNode!=null)
		{
			replacementParent=replacement;
			replacement=currNode;
			currNode=currNode.left;
		}
		
		if (replacement != replacedNode.right)
		{
			replacementParent.left=replacement.right;
			replacement.right=replacedNode.right;
		}
		
		return replacement;
	}
	
	public void inOrder()
	{
		inOrder(root);
	}
	
	public void inOrder(Node head)
	{	
		if (head!=null)
		{
			inOrder(head.left);
			System.out.println(head.data);
			inOrder(head.right);
		}
	}
	
	public void preOrder()
	{
		preOrder(root);
	}
	
	public void preOrder(Node head)
	{
		if (head!=null)
		{
			System.out.println(head.data);
			preOrder(head.left);
			preOrder(head.right);
		}
	}
	
	public void postOrder()
	{
		postOrder(root);
	}
	
	public void postOrder(Node head)
	{
		if (head!=null)
		{
			postOrder(head.left);
			postOrder(head.right);
			System.out.println(head.data);
		}
	}
	
	public Node search(int key)
	{
		return search(root,key);
	}
	
	public Node search(Node head, int key)
	{

		Node currNode=head;
			
		while(key!=currNode.data)
		{
			if (key<currNode.data)
			{
					currNode=currNode.left;
					
					if (currNode==null)
					{
						return null;
					}
			}
			else
			{
				currNode=currNode.right;
				
				if (currNode==null)
				{
					return null;
				}
			}
		}
		
		return currNode;
	}
}

class Node
{	
	int data;
	Node left;
	Node right;
	
	Node(int d)
	{	
		this.left=null;
		this.right=null;
		this.data=d;
	}
}