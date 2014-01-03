
package TheGreatHashtable.testDrivers;
import TheGreatHashtable.enums.*;
import TheGreatHashtable.*;

public class AdditionOfTables {
	

	//import TheGreatHashtable.enums.Bounds;
	//import TheGreatHashtable.enums.S_XY;

		public static void main(String[] args) {
			// TODO Auto-generated method stub

				//Node Oy21 = new Node(1,1,null,null);
				Node Oy20 = new Node (6,6,null,null);
			Node Ox2 = new Node(7,7,null,Oy20);
			
				Node Oy10 = new Node(1,2,null,null);
			Node Ox1 = new Node(3,4,Ox2,Oy10);			
				
				Node Oy04 = new Node(37,40,null,null);
				Node Oy03 = new Node(28,35,Oy04,null);
				Node Oy02 = new Node(23,25,Oy03,null);
				Node Oy01 = new Node(17,20,Oy02,null);
				Node Oy00 = new Node(10,15,Oy01,null);
			Node Ox0 = new Node(1,1,Ox1,Oy00);
			
				//Node addedSuby3 = new Node(6,6,null,null);
			//Node added3 = new Node(8,8,null,addedSuby3);
				Node addedSuby2 = new Node(6,6,null,null);
			Node added2 = new Node(6,6,null,addedSuby2);
				Node a6 = new Node(50,55,null,null);
				Node a5 = new Node(45,47,a6,null);
				Node a4 = new Node(22,36,a5,null);
				Node a3 = new Node(16,16,a4,null);
				Node a2 = new Node(8,11,a3,null);
				Node a1 = new Node(0,1,a2,null);
			Node added = new Node(1,4,added2,a1);
			
			S_Box box = new S_Box(2,2,1,1);
			
			Hashtable table = new Hashtable(Ox0);
			
			
			//System.out.println(table.RetOverlap(Oy00, a3, true));
			//System.out.print(table.RetOverlap(Ox0, added, false));
			
			//System.out.print(table.Coli(box));
			 table.HashAdder(added);
			
				
			 
			System.out.println(table.ToString());
			 
			 
			
			 //Know problems. 11 is not equal to 11
			 
			 
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


