package TheGreatHashtable;

import TheGreatHashtable.enums.Bounds;
import TheGreatHashtable.enums.ENode;

public class Node {
	//Our Nodes Variables
	int u; //upper bound
	int l; //lower bound
	
	// yNode and xNode are the nodes which are "pointed to", or more appropriately birthed or stemming from this Node.
	Node yNode = null; //the y node connection (if there is any)
	Node xNode = null; //the x node connection		   '' 
	
	//Our Node's Constructors
	public Node(int l, int u, Object x, Object y){ // node constructor.
		Set(l,u);
		xNode = (Node) x;
		yNode = (Node) y;
	}
	
	//Getting information from our Node
	public Object Ret(ENode a){ 
		//Ret will return the value of of the upper bound (if given char 'u') or the lower bound (if given char 'l'). 
		switch(a){
			case y: 
				return yNode; 
			case x: 
				return xNode;
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
	
	public void Set(Object x, Object y){
		//This type of our Alt method requires two nodes to be input, and both x&y Node
		xNode = (Node) x;
		yNode = (Node) y;
	}
	
	public void Set(Object x, ENode c){
		/* This type of our Alt method alters one of the Nodes, 
		 depending on which char is input. */
		switch(c){
		case y:
			yNode = (Node) x; 
			break;
		case x: 
			xNode = (Node) x;
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

	
}
