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
public class Driver {
    
    public static void main (String [] args)
    {
        BinaryTree myTree=new BinaryTree();
        
        myTree.insert(3);
        myTree.insert(19);
        myTree.insert(1995);
        myTree.insert(1997);
        
        System.out.println(myTree.search(0));
        System.out.println(myTree.search(19));
        
        if (myTree.isEmpty())
        {
            System.out.println("Binary Tree is empty");
        }
        else
        {
            System.out.println("Binary Tree is not empty");
        }
        
        myTree.inOrder();
        System.out.println();
        myTree.remove(0);
        
    }
    
}
