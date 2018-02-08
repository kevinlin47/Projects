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
public class Node {
    
    private int data;
    
    public Node left;
    public Node right;
    
    Node()
    {   
        left=null;
        right=null;
    }
    
    Node(int d)
    {
        this.data=d;
        left=null;
        right=null;
    }
    
    public void setData(int data)
    {
        this.data=data;
    }
    
    public int getData()
    {
        return this.data;
    }
    
}
