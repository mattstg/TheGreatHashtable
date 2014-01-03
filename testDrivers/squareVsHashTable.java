package TheGreatHashtable.testDrivers;

import TheGreatHashtable.*;
//import TheGreatHashtable.enums.Bounds;
//import TheGreatHashtable.enums.S_XY;
import TheGreatHashtable.enums.*;

public class squareVsHashTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node Oy21 = new Node(4,4,null,null);
		Node Oy20 = new Node (1,2,Oy21,null);
		Node Ox2 = new Node(3,3,null,Oy20);
		
		Node Oy10 = new Node(3,4,null,null);
		Node Ox1 = new Node(2,2,Ox2,Oy10);
		
		Node Oy00 = new Node(4,4,null,null);
		Node Ox0 = new Node(0,0,Ox1,Oy00);
		
		
		S_Box box = new S_Box(0,1,2,2);
		
		Hashtable table = new Hashtable(Ox0);
		
		//System.out.print(table.Coli(box));
		//coli test of static?
		
		
		//System.out.print(table.Coli(box));
		
		
		DiagMotion mov1 = new DiagMotion(2,0,box);
		
		S_Box s = new S_Box();
		int counter = 0;
		while(s != null)
		{
			
			s = mov1.RetNextBox();
			//System.out.println(mov1.E.toString());
		
			
			if(s != null)
				{
					System.out.println("s:" + s.toString());
					System.out.println(table.Coli(s));		
				}
			//System.out.print("COUNTER{" + counter + "}");
			//counter++;
		};
		
		//Always read A is *** of O
		//System.out.print(table.RetOverlap(OX,AX,false).toString());

		
		
		
	}

}
