package TheGreatHashtable;

import TheGreatHashtable.enums.Bounds;
import TheGreatHashtable.enums.ENode;

public class Node {
	//Our Nodes Variables
	int u; //upper bound
	int l; //lower bound
	
	// yNode and xNode are the nodes which are "pointed to", or more appropriately birthed or stemming from this Node.
	Node dwnNode = null; //the adjacent node connection (if there is any)
	Node adjNode = null; //the down node connection		   '' 
	
	//Our Node's Constructors
	public Node(int l, int u, Node adj, Node dwn){ // node constructor.
		Set(l,u);
		adjNode = adj;
		dwnNode = dwn;
	}
	
	//Getting information from our Node
	public Node Ret(ENode a){ 
		//Ret will return the value of of the upper bound (if given char 'u') or the lower bound (if given char 'l'). 
		switch(a){
			case adj: 
				return adjNode; 
			case dwn: 
				return dwnNode;
		}
		return null;
	}
	
	public int Ret(Bounds a){
		switch(a){
			case u: 
				return u;
			case l:
				return l;
		}
		return 0; 
	}
	
	public int[] Ret(){
		//returns the upper and lower bound in the form of an array of int[2]
		int[] a = {l,u};
		return a;
	}
	
	//Giving information to our Node
	/*Alt() alters something about the node. There are several types of this method
	 * which alters our node in different ways depending upon our input. */
	
	public void ClearLink(){
		adjNode = null;
		dwnNode = null;
	}
	
	public void Set(Node adj, Node dwn){
		//This type of our Alt method requires two nodes to be input, and both x&y Node
		adjNode = adj;
		dwnNode = dwn;
	}
	
	public void Set(Node o, ENode c){
		/* This type of our Alt method alters one of the Nodes, 
		 depending on which char is input. */
		switch(c){
		case dwn:
			dwnNode = o; 
			break;
		case adj: 
			adjNode = o;
			break;
		default:
			System.out.print("Warning: improper entry into Node Alter Method. Node has not been altered.");
		}
	}

	public void Set(int a, Bounds c){
		/* This type of our Alt method changed one of the bounds of the node, 
		depending on which character is input. */
		switch(c){
		case u: //alters upper bound
			u = a;
			break;
		case l:  //alters lower bound
			l = a;
			break;
		default: 
			System.out.print("Warning: improper entry into Node Alter Method. Node has not been altered.");
		}
	}
	
	public void Set(int a, int b){
		l = a;
		u = b;
	}

	public Node Adj(){
		return adjNode;
	}
	
	public void Adj(Node n){
		adjNode = n;
	}
	
	
}
