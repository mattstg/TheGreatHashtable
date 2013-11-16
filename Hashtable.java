package TheGreatHashtable;

import TheGreatHashtable.enums.OverlapType;

/*  compareTable
R   BeA  BeA 
AeB Eq   BeA
AeB Aeb  L

*/



public class Hashtable {
	Node mainNode = null;
	OverlapType[][] overlapTable = {{OverlapType.Right,OverlapType.BEA,OverlapType.BEA},{OverlapType.AEB,OverlapType.Equals,OverlapType.BEA},{OverlapType.AEB,OverlapType.AEB,OverlapType.Left}};

	
public Hashtable(Object o){
	mainNode = (Node) o;
}
	
	
public OverlapType RetOverlap(Node O,Node A)
{
	OverlapType moo = OverlapType.Right;
	
	//return overlapTable[][]
	return moo;
			
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
