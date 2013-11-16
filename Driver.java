package TheGreatHashtable;

public class Driver {
	int meow;
	public enum OverlapType{Right,Left,AEB,BEA,Equals};
	
	
	OverlapType[][] overlapTable = {{OverlapType.Right,OverlapType.BEA,OverlapType.BEA},{OverlapType.AEB,OverlapType.Equals,OverlapType.BEA},{OverlapType.AEB,OverlapType.AEB,OverlapType.Left}};
	/*
	  R   BeA  BeA 
	  AeB Eq   BeA
	  AeB Aeb  L
	 
	 */
	
	
	public static void main(String[] args) {
<<<<<<< Updated upstream
		
		Node Node1 = new Node(1,2, null, null);
		Node Node2 = new Node(0,1, Node1, null);
		System.out.println("Attempting to acquire Node1's xNode:");
		Node o = (Node) Node2.Acq('x');
		System.out.println("This should print the lower bound of Node1 (should be 1):" + (Node) o.Acq('l'));
=======
	
	

		
		
>>>>>>> Stashed changes
		
		
	}
	
	
	
	
	public overlapType RetOverlap(Node O,Node A)
	{
		OverlapType moo = OverlapType.Right;
		
		return overlapTable[][]
		
				
	}
	
	public int Compare(int a, int b)
	{
		if(a < b)
			return 0;
		if(a > b)
			return 2;
		if(a == b)
			return 1;
		
		return 1;
	}
	
	
}
