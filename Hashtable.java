package TheGreatHashtable;

import TheGreatHashtable.enums.Bounds;
import TheGreatHashtable.enums.OverlapType;





public class Hashtable {
	Node mainNode = null;
	
	//The overlap table
	OverlapType[][] overlapTable = {{OverlapType.Right,OverlapType.BEA,OverlapType.BEA},{OverlapType.AEB,OverlapType.Equals,OverlapType.BEA},{OverlapType.AEB,OverlapType.AEB,OverlapType.Left}};
	/*  compareTable
	R   BeA  BeA 
	AeB Eq   BeA
	AeB Aeb  L

	*/
	
public Hashtable(Object o){
	mainNode = (Node) o;
}

public Hashtable(){
	mainNode = null;
}
	
	
public OverlapType RetOverlap(Node O,Node A)
{
		
	return overlapTable[Compare(O.Ret(Bounds.l),A.Ret(Bounds.l))][Compare(O.Ret(Bounds.u),A.Ret(Bounds.u))];	
			
}

public int Compare(int a, int b)
{
	if(a < b)
		return 0;
	if(a > b)
		return 2;
	if(a == b)
		return 1;
	
	return -1;
}
	






	
	
	
	
	
}
