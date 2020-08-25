/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finals;

import java.util.*; 
import java.lang.*; 
import java.io.*;

/**
 *
 * @author Hardik
 */
public class Graph {
    
    int distance;
    int ed;
    int[] source= new int[6];
    int[] destination= new int[6];

    
	// Creates a graph with V vertices and E edges 
	Graph(int v, int e) 
	{ 
		V = v; 
		E = e; 
		edge = new Edge[E]; 
		for (int i=0; i<e; ++i) 
			edge[i] = new Edge(); 
	}
		
	class Edge implements Comparable<Edge>
	{
	    //String src;
	    //String dest;
		int src, dest;
	    int weight = 0;

	    // Comparator function used for sorting edges
	    // based on their weight
	    public int compareTo(Edge compareEdge)
	    {
	        return this.weight-compareEdge.weight;
	    }
	};
	
	class Subset
	{
	    int parent, rank;
	}

	
	int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges

    // A utility function to find set of an element i
    // (uses path compression technique)
    public int find(Subset subsets[], int i) 
    {
    	//System.out.println(""+i+" "+subsets[i]);
        // find root and make root as parent of i (path compression)
	        if (subsets[i].parent != i)
	        {	        	
	            subsets[i].parent = find(subsets, subsets[i].parent);
	        }
	        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y
    // (uses union by rank)
    public void Union(Subset subsets[], int x, int y) 
    {        
    	int xroot = find(subsets, x);
    	int yroot = find(subsets, y);

    	// Attach smaller rank tree under root of high rank tree
    	// (Union by Rank)
	    if (subsets[xroot].rank < subsets[yroot].rank)
	    {
	    	subsets[xroot].parent = yroot;
	    }
	    
	    else if (subsets[xroot].rank > subsets[yroot].rank)
	    {
	    	subsets[yroot].parent = xroot;
	    }
	    
	    // If ranks are same, then make one as root and increment
	    // its rank by one
	    else
	    {
	    	subsets[yroot].parent = xroot;
	        subsets[xroot].rank++;
	    }
    }
    
    void Kruskals_Algo() {
    	
    	Edge result[] = new Edge[V];  // This will store the resultant MST 
        int e = 0;  // An index variable, used for result[] 
        int i = 0;  // An index variable, used for sorted edges 
        
        for (i=0; i<V; ++i) 
            result[i] = new Edge(); 

        // Step 1:  Sort all the edges in non-decreasing order of their 
        // weight.  If we are not allowed to change the given graph, we 
        // can create a copy of array of edges 
        Arrays.sort(edge); 

        // Allocate memory for creating V ssubsets 
        Subset subsets[] = new Subset[V]; 
        for(i=0; i<V; ++i )
            subsets[i] = new Subset(); 

        // Create V subsets with single elements 
        for (int v = 0; v < V; ++v) 
        { 
            subsets[v].parent = v; 
            subsets[v].rank = 0; 
        } 

        i = 0;  // Index used to pick next edge 

        // Number of edges to be taken is equal to V-1 
        while (e < V - 1) 
        { 
            // Step 2: Pick the smallest edge. And increment  
            // the index for next iteration 
            Edge next_edge = new Edge(); 
            next_edge = edge[i++]; 

            int x = find(subsets, next_edge.src); 
            int y = find(subsets, next_edge.dest); 

            // If including this edge does't cause cycle, 
            // include it in result and increment the index  
            // of result for next edge 
            if (x != y) 
            { 
                result[e++] = next_edge; 
                Union(subsets, x, y); 
            } 
            // Else discard the next_edge 
        } 

        
        // print the contents of result[] to display 
        // the built MST 
        System.out.println("Using kruskal's Algorithm we've been able to construct a minimum spanning tree"
    			+ "\nwith all the information from user we've calculted that how many edges should be connected "
    			+ "\nso that you will have to travel least distance"); 
        int total_weight = 0;
        
        for (i = 0; i < e ; ++i)
        {
        	
            total_weight = total_weight + result[i].weight ;
            System.out.println(result[i].src+" -- " +result[i].dest);
                
            source[i]= result[i].src; 
            destination[i]= result[i].dest;
        }
        ed = e;
        distance = total_weight;
        System.out.println("Total Distance that you will travel = "+total_weight);
    }   
    int[] function_for_source(){

     return source;   
    }
    int[] function_for_destination(){

        return destination;
    }
    int function_for_e(){

        return ed;
    }
    int function_for_total_dist(){

        return distance;
    }}

