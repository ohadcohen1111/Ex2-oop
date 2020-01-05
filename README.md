# Ex2-oop
this project represent a directional weighted graph, and A number of algorithms on the graph.  

**package dataStructure:**  

**nodeData**: this class implements node_data interface.   
nodeData represents the set of operations applicable on a node (vertex) in a (directional) weighted graph.  

**edgeData**: this class implements edge_data interface.   
edgrData represents the set of operations applicable on a directional edge(src,dest) in a (directional) weighted graph.  

**DGraph**: this class implemens graph interface.  
DGraph represents a directional weighted graph,  should support a large number of nodes (over 100,000).  

**package algorithems:**    

**Graph-Algo**: this class implements graph-algorithems interface.  
Graph-Algo represents the "regular" Graph Theory algorithms including:
 0. clone();
 1. init(String file_name);
 2. save(String file_name);
 3. isConnected();
 5. double shortestPathDist(int src, int dest);
 6. List<Node> shortestPath(int src, int dest);
  
  **package gui:**    

  **GUI**: displays a graph, saves it, init , add/remove vertex, add/remove edge and runs algorithms on it.
