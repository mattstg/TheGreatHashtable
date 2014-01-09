package TheGreatHashtable;

import TheGreatHashtable.enums.Bounds;
import TheGreatHashtable.enums.ENode;
import TheGreatHashtable.enums.copyTypes;

public class Node {
	//Our Nodes Variables
	int u; //upper bound
	int l; //lower bound
	
	// yNode and xNode are the nodes which are "pointed to", or more appropriately birthed or stemming from this Node.
	Node dwnNode = null; //the adjacent node connection (if there is any)
	Node adjNode = null; //the down node connection		   '' 
	
	//Our Node's Constructors
	public Node(){
		Set(0,0);		
		
	} //useful for creating dud nodes to make compiler happy
	
	public Node(int l, int u){
		Set(l,u);		
	}
	
	public Node(int l, int u, Node adj, Node dwn){ // node constructor.
		Set(l,u);
		adjNode = adj;
		dwnNode = dwn;
	}
	
	public Node(Node toCopy){ // node constructor.
		Set(toCopy.Ret(Bounds.l),toCopy.Ret(Bounds.u));
		adjNode = null;
		dwnNode = null;
	}
	
	///////////////replace these with the copy
	/*
	public Node(int l, int u, Node toCopy){ // node constructor.
		Set(l,u);
		adjNode = null;
		copyList(toCopy.Dwn());
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
		
		
		
	}*/
	////////////////////////////////////////////////////////////////////
	
	
	
	public Node CopySelf(copyTypes type)
	{
		Node toRet = new Node();
		switch(type)
		{
		case copyNode:	
			toRet.Set(l,u);
			toRet.adjNode = null;
			toRet.dwnNode = null;
			break;
			
		case copyAdj:
			toRet = CopyListAdj(this);
			break;
			
		case copyDwn:
			toRet.Set(l,u);
			toRet.Dwn(CopyListAdj(this.Dwn()));
			toRet.Adj(null);
			break;
			
		case copyBoth:
			toRet = CopyListFull(this);
			break;	
			
		default:
			return null;
		}	
		
		return toRet;
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
	
	
	
	//O is the orginal node passed to be copied
	private Node CopyListFull(Node O)
	{
		Node toRet = null;
		
		Node Oit = O; //stops messing with O ptr
		Node it;
		
		if(Oit != null)
		{
			toRet = new Node(Oit);  //create a copy of O --just a node copy--
			toRet.Dwn(CopyListAdj(Oit.Dwn())); //set the dwn node to be the first node
			it = toRet; //save toRet at main to return later
			Oit = Oit.Adj();
			
			//if(Oit != null)
				//Oit = Oit.Adj(); //use O as the iterator for the original list
			
			while(Oit != null)
			{
				Node newNode = new Node(Oit);
				newNode.Dwn(CopyListAdj(Oit.Dwn()));
				it.Adj(newNode);
				Oit = Oit.Adj(); //cycle to next node to be copied
				it = it.Adj(); //cycle to next node to place
			}
		} 		
		return toRet;
	}
	
	
	
	private Node CopyListAdj(Node O) //given the O
	{
		Node toRet = null;
		Node Oit = O; //stops messing with O ptr
		Node it;
		
		if(Oit != null)
		{
			toRet = new Node(Oit); //use O as the iterator for the original list
			it = toRet;
			Oit = Oit.Adj();
		
		
			while(Oit != null)
			{
				Node newNode = new Node(Oit);
				it.Adj(newNode);
				Oit = Oit.Adj(); //cycle to next node to be copied
				it = it.Adj(); //cycle to next node to place
			}
		}
		
		
		return toRet;
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
