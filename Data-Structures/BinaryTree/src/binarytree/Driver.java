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
        
        System.out.println(myTree.search(0));
        System.out.println(myTree.search(19));
    }
    
}
