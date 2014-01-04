package TheGreatHashtable.testDrivers;

import TheGreatHashtable.ASCIIWorldGen;
import TheGreatHashtable.Hashtable;
import TheGreatHashtable.Node;
import TheGreatHashtable.enums.S_Box;

public class ASCIIWorldDriver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		ASCIIWorldGen World = new ASCIIWorldGen();
		World.ResetWorld();
		
		
		
		//Addition "DirtSplosion"
		/*	Node Ay6 = new Node(3,3,null,null);
		Node Ax6 = new Node(6,6,null,Ay6);
			Node Ay5 = new Node(2,4,null,null);					
		Node Ax5 = new Node(5,5,Ax6,Ay5);
			Node Ay4 = new Node(1,5,null,null);
		Node Ax4 = new Node(4,4,Ax5,Ay4);
			Node Ay3 = new Node(0,6,null,null);
		Node Ax3 = new Node(3,3,Ax4,Ay3);
			Node Ay2 = new Node(1,5,null,null);
		Node Ax2 = new Node(2,2,Ax3,Ay2);*/
		//	Node Ay1 = new Node(2,4,null,null);	
		//Node Ax1 = new Node(1,1,null,Ay1);
			Node Ay0 = new Node(10,20,null,null);
		Node Ax0 = new Node(1,3,null,Ay0);
		
		
		
			//Original Node
		
		/*	Node Oy51 = new Node(4,4,null,null);	
			Node Oy5 = new Node(1,2,Oy51,null);					
		Node Ox5 = new Node(5,5,null,Oy5);
			Node Oy41 = new Node(3,3,null,null);
			Node Oy4 = new Node(1,1,Oy41,null);
		Node Ox4 = new Node(4,4,Ox5,Oy4);
			Node Oy31 = new Node(5,7,null,null);
			Node Oy3 = new Node(2,2,Oy31,null);
		Node Ox3 = new Node(3,3,Ox4,Oy3);
			Node Oy22 = new Node(5,5,null,null);
			Node Oy21 = new Node(3,3,Oy22,null);
			Node Oy2 = new Node(1,1,Oy21,null);
		Node Ox2 = new Node(2,2,Ox3,Oy2);*/
			Node Oy11 = new Node(16,19,null,null);	
			Node Oy1 = new Node(0,11,Oy11,null);	
		Node Ox1 = new Node(2,2,null,Oy1);		
			Node Oy01 = new Node(15,20,null,null);			
			Node Oy0 = new Node(1,10,Oy01,null);
		Node Ox0 = new Node(1,1,Ox1,Oy0);
		
		S_Box box = new S_Box(2,2,1,1);
		System.out.println("World New:" + '\n' + World.DrawWorld());
		Hashtable table = new Hashtable(Ox0);
		
		World.LoadNodeIntoWorld(table);
		System.out.println("World with hashtable:" + '\n' + World.DrawWorld());
		
		//System.out.println(table.RetOverlap(Oy00, a3, true));
		//System.out.print(table.RetOverlap(Ox0, added, false));
		
		
		 table.HashAdder(Ax0);
		 
		 World.LoadNodeIntoWorld(table);
		 System.out.println("World After Explosion:" + '\n' + World.DrawWorld());
		
			
		 
		System.out.println(table.ToString());
}
}
