package hw4;

import java.io.*; 
import java.util.*; 
  
// This class represents a directed graph using adjacency list 
// representation 
class Graph 
{ 
    private HashMap<PointNode, LinkedList<PointNode>> adj;
  
    // Constructor 
    Graph(int v) 
    { 
    	adj = new HashMap<>();
    } 
  
    // Function to add an edge into the graph 
    void addEdge(PointNode v, PointNode w) 
    { 
    	//done twice so its undirected
    	LinkedList<PointNode> tmp1 = adj.get(v);
    	tmp1.add(w);
        adj.put(v,tmp1);
        
        LinkedList<PointNode> tmp2 = adj.get(w);
    	tmp2.add(v);
        adj.put(w,tmp2);
    } 
  
   
    void BFS(PointNode point, PointNode Dest) 
    { 
        Queue<PointNode> queue = new LinkedList<>();
        queue.add(point);
        
        while(!queue.isEmpty())
        {
        	PointNode current = queue.poll();
        	
        	if(current.isVisited())
        		continue;
        	
        	current.visit();
        	
        	LinkedList<PointNode> neighbors = adj.get(current);
        	
        	if(neighbors == null)
        		continue;
        	
        	for(PointNode neighbor : neighbors)
        	{
        		if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
        	}
        }
    } 
  
} 