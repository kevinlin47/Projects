/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Kevin Lin
 */
public class Graph {
    
    //variable to hold number of vertices
    int v;
    
    //ArrayList of LinkedLists
    ArrayList<LinkedList<Integer>> adjListArray;
    
    //Construct that takes the number of vertices
    //as a parameter
    Graph(int v)
    {
        this.v=v;
        adjListArray=new ArrayList();
        
        //Create a list for each vertex
        for (int i=0;i<v;++i)
        {
            adjListArray.add(new LinkedList());
        }
    }
    
    //Default Constructor
    
    public void addEdge(int src, int dest)
    {   
        //Create and edge between the source node
        //and the destination node
        adjListArray.get(src).addFirst(dest);
        
        //Undirected grapg, so we need to add an 
        //edge from destination to source as well
        adjListArray.get(dest).addFirst(src);
    }
    
}
