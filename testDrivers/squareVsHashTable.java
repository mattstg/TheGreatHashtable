package TheGreatHashtable.testDrivers;
import TheGreatHashtable.enums.*;
import TheGreatHashtable.*;

public class squareVsHashTable {
	

	//import TheGreatHashtable.enums.Bounds;
	//import TheGreatHashtable.enums.S_XY;

		public static void main(String[] args) {
			// TODO Auto-generated method stub

			Node Oy21 = new Node(4,4,null,null);
			Node Oy20 = new Node (1,2,Oy21,null);
			Node Ox2 = new Node(3,3,null,Oy20);
			
			Node Oy10 = new Node(3,4,null,null);
			Node Ox1 = new Node(2,2,Ox2,Oy10);
			
			Node Oy00 = new Node(4,4,null,null);
			Node Ox0 = new Node(0,0,Ox1,Oy00);
			
			
			S_Box box = new S_Box(2,2,1,1);
			
			Hashtable table = new Hashtable(Ox0);
			
			System.out.print(table.Coli(box));
			
			
			/* From example above
			 * is dirt from hashtable tree  
			 { box
			 
			  * - * * - - - - -
			  - - * - - - - - -
			  - - { *}- - - - -
			  - - { *}- - - - -
			  - - - - - - - - -
			  */
			
			
			//Always read A is *** of O
			//System.out.print(table.RetOverlap(OX,AX,false).toString());	
			
		}

}


