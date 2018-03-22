/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;


/**
 *
 * @author Kevin Lin
 */
public class Driver {
    
        public static void main(String args[])
        {   
            //Test graph, passing in number of verticies 
            Graph graph=new Graph(5);
            graph.addEdge(0,1);
            graph.addEdge(0,3);
            
            graph.addEdge(1,3);
            graph.addEdge(1,4);
            graph.addEdge(1,2);
            
            graph.addEdge(2,3);
            
            graph.printGraph();
            
            //Test graph using default constructor
            Graph graph2=new Graph();
            graph2.addEdge(1,3);
            graph2.addEdge(1,19);
            
            graph2.addEdge(3,5);
            graph2.addEdge(3,4);
            
            graph2.addEdge(0,3);
            
            graph2.printGraph();
            
        }
        
}
