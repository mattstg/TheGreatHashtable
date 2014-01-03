package TheGreatHashtable.testDrivers;

import TheGreatHashtable.DiagMotion;
import TheGreatHashtable.Hashtable;
import TheGreatHashtable.Node;
import TheGreatHashtable.enums.S_Box;

public class NodeListCopyCnstrDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Node Oy21 = new Node(4,4,null,null);
		//Node Oy20 = new Node (1,2,Oy21,null);
		//Node Ox2 = new Node(3,3,null,Oy20);
		
		//Node Oy10 = new Node(3,4,null,null);
		//Node Ox1 = new Node(2,2,Ox2,Oy10);
		
		Node Oy02 = new Node(5,4,null,null);
		Node Oy01 = new Node(3,4,Oy02,null);
		Node Oy00 = new Node(4,2,Oy01,null);
		Node Ox0 = new Node(5,10,null,Oy00);
		
		Node Ox1 = new Node(2,9,null,null);
		
		
		S_Box box = new S_Box(0,1,2,2);
		
		Hashtable table = new Hashtable(Ox0);
		//System.out.println(table.ToString());
		
		//Node testCopy = new Node(5,5,Ox0);
		//Hashtable tableCopy = new Hashtable(testCopy);
		//System.out.println(tableCopy.ToString());
		
		//subsplitter tester
		
		//table._OverlapSplitter(Ox0, Ox1);

		System.out.println(Ox1.toString());
		System.out.println(Ox1.Adj().toString());
		//System.out.println(Ox0.Adj().Adj().toString());
		//System.out.println(Ox0.Adj().Adj().Dwn().Adj().Adj().toString());
		
		
	}
}
