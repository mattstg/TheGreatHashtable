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
	public Node(){} //useful for creating dud nodes to make compiler happy
	
	public Node(int l, int u, Node adj, Node dwn){ // node constructor.
		Set(l,u);
		adjNode = adj;
		dwnNode = dwn;
	}
	
	public Node(int l, int u, Node toCopy){ // node constructor.
		Set(l,u);
		adjNode = null;
		copyList(toCopy.Dwn());
	}
	
	public Node(Node toCopy){ // node constructor.
		Set(toCopy.Ret(Bounds.l),toCopy.Ret(Bounds.u));
		adjNode = null;
		dwnNode = null;
	}
	
	public Node(Node toCopy, boolean fullCopy){ // node constructor.
		if(fullCopy)
		{
			adjNode = null;
			Set(toCopy.Ret(Bounds.l),toCopy.Ret(Bounds.u));
			copyList(toCopy.Dwn());
		} else {
			Set(toCopy.Ret(Bounds.l),toCopy.Ret(Bounds.u));
			adjNode = null;
			dwnNode = null;
		}
		
		
		
	}
	
	
	
	public String toString()	{
		return " L: " + l + " U: " + u + " adjNode: " + (adjNode != null) + " dwnNode: " + (dwnNode != null);
		
		
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
	
	
	public boolean EqualBounds(Node a)
	{
		//compare if bounds are equal
		if(l == a.Ret(Bounds.l) && u == a.Ret(Bounds.u))
			return true;
		return false;
		
	}
	
	private void copyList(Node O)
	{
		//O is the orginal node passed to be copied
		if(O != null)
		{
			
			Dwn(new Node(O,false)); //set the dwn node to be the first node
			Node it = this.dwnNode; //pointing at the new dwnNode
			
			
			if(O != null)
				O = O.Adj(); //use O as the iterator for the original list
			
			while(O != null)
			{
				Node newNode = new Node(O,false);
				it.Adj(newNode);
				O = O.Adj(); //cycle to next node to be copied
				it = it.Adj(); //cycle to next node to place
			}
		} else {
			Dwn(null);
		}
		
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

	public Node Dwn(){
		return dwnNode;
	}
	
	public void Dwn(Node n){
		dwnNode = n;
	}
	
	public Node Adj(){
		return adjNode;
	}
	
	public void Adj(Node n){
		adjNode = n;
	}
	
	
}
