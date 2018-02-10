/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

/**
 *
 * @author kevinlin
 */
public class BinaryTree {

    private Node root=null;
    
    //returns true if tree is empty, returns false otherwise
    public boolean isEmpty()
    {
        if (root==null)
        {
            return true;
        }
        
        return false;
    }
    
    //insert a new node into the binary tree with value d
    public void insert(int d)
    {
        root=insert(root,d);
    }
    
    private Node insert(Node head,int d)
    {
        if (head==null)
        {
            head=new Node(d);
            
        }
        else
        {
            if (head.right==null)
            {
                head.right=insert(head.right,d);
            }
            else
            {
                head.left=insert(head.left,d);
            }
        }
        
        return head;
    }
    
    //Return number of nodes in the binary tree
    public int size()
    {
        return size(root);
    }
    
    private int size(Node head)
    {   
        if (head==null)
        {
            return 0;
        }
        else
        {
            int count=1;
            
            count+=size(head.left);
            count+=size(head.right);
            
            return count;
        }
        
    }
    
    //Search if a node with the int value of key is contained 
    //in the tree
    public boolean search(int key)
    {
            return search(root,key);
    }
        
    private boolean search(Node head,int key)
    {
        if (head.getData()==key)
        {
            return true;
        }   
        
        if (head.left!=null)
        {
            if (search(head.left,key))
            {
                return true;
            }
        }
        
        if(head.right!=null)
        {
            if (search(head.right,key))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public void inOrder()
    {
        inOrder(root);
    }
    
    private void inOrder(Node head)
    {   
        if (head!=null)
        {
            inOrder(head.left);
            System.out.print(head.getData()+" ");
            inOrder(head.right);
        }
    }
    
    public void remove(int d)
    {
        if (search(root,d))
        {
            root=remove(root,d);
        }
        else
        {
            System.out.println("Tree does not contain a node with the value "+d);
        }
    }
    
    private Node remove(Node head,int d)
    {   
        if (head.getData()==d)
        {   
            //check to see if it is a leaf node
            if (head.right==null && head.left==null)
            {
                Node temp=getParent(root,head);
            }
            //check to see if the node has two children
            else if (head.right!=null && head.left!=null)
            {
                
            }
            //Otherwise node has one child
            else
            {
                if (head.right!=null)
                {
                    Node temp=getParent(root,head);
                    
                    if (temp.left==head)
                    {
                        temp.left=head.right;
                    }
                    else
                    {
                        temp.right=head.right;
                    }
                }
                else 
                {
                    Node temp=getParent(root,head);
                    
                    if (temp.left==head)
                    {
                        temp.left=head.left;
                    }
                    else
                    {
                        temp.right=head.left;
                    }
                }
            }
            /*
            Node temp=head.left;
            Node pre=null;
            while (temp.right!=null)
            {   
                pre=temp;
                temp=temp.right;
            }
            head.setData(temp.getData());
            pre.right=null;*/
         }
        else
        {
            if (head.left!=null)
            {
                remove(head.left,d);
            }
            else
            {
                remove(head.right,d);
            }
        }
                
        return head;
    }
    
    
    private Node getParent(Node root,Node p)
    {   
        if (root==null || p==this.root)
        {
            return null;
        }
        else
        {
            if (root.left==p || root.right==p)
            {
                return root;
            }
            else
            {   
                if (root.left!=null)
                {
                    return getParent(root.left,p);
                }
                if (root.right!=null)
                {
                    return getParent(root.right,p);
                }
            }
        }
        
        return null;
    }
}
