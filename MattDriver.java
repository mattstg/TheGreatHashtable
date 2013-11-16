package TheGreatHashtable;

public class MattDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node nodeO = new Node(6,10,null,null);
		Node nodeA = new Node(10,26,null,null);
		Hashtable table = new Hashtable();
		
		//Always read A is *** of O
		System.out.print(table.RetOverlap(nodeO,nodeA,false).toString());
		
		
		
	}

}
