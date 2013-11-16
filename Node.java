
public class Node {
	//Our Nodes Variables
	int u; //upper bound
	int l; //lower bound
	// yNode and xNode are the nodes which are "pointed to", or more appropriately birthed or stemming from this Node.
	Node yNode = null; //the y node connection (if there is any)
	Node xNode = null; //the x node connection		   '' 
	
	//Our Node's Constructors
	public Node(int l, int u, Object x, Object y){ // node constructor.
		Alt(l,u);
		xNode = (Node) x;
		yNode = (Node) y;
	}
	
	//Getting information from our Node
	public int Acq(char c){ 
		//Acq will return the value of of the upper bound (if given char 'u') or the lower bound (if given char 'l'). 
		switch(c){
		case 'u': 
			return u;
		case 'l': 
			return l;
		default: 
			System.out.print("Warning: improper entry into Acquire Method. Returning 0.");
			return 0;
		}
	}
	public int[] Acq(){
		//returns the upper and lower bound in the form of an array of int[2]
		int[] a = {l,u};
		return a;
	}
	public Object Acq(char c){//for acquiring attached nodes
		switch(c){
		case 'y': 
			return yNode; 
		case 'x': 
			return xNode;
		default:
			System.out.print("Warning: improper entry into Node Aqcuire Method. Returning xNode.");
			return xNode;
		}
	}
	
	//Giving information to our Node
	/*Alt() alters something about the node. There are several types of this method
	 * which alters our node in different ways depending upon our input. */
	
	public void Alt(Object x, Object y){
		//This type of our Alt method requires two nodes to be input, and both x&y Node
		xNode = (Node) x;
		yNode = (Node) y;
	}
	
	public void Alt(Object x, char c){
		/* This type of our Alt method alters one of the Nodes, 
		 depending on which char is input. */
		switch(c){
		case 'y': //alters the yNode
			yNode = (Node) x; 
			break;
		case 'x': //alters the xNode
			xNode = (Node) x;
			break;
		default:
			System.out.print("Warning: improper entry into Node Alter Method. Node has not been altered.");
		}
	}

	public void Alt(int a, char c){
		/* This type of our Alt method changed one of the bounds of the node, 
		depending on which character is input. */
		switch(c){
		case 'u': //alters upper bound
			u = a;
			break;
		case 'l':  //alters lower bound
			l = a;
			break;
		default: 
			System.out.print("Warning: improper entry into Node Alter Method. Node has not been altered.");
		}
	}
	
	public void Alt(int a, int b){
		l = a;
		u = b;
	}

	
}
