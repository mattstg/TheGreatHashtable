package TheGreatHashtable;

import TheGreatHashtable.enums.Bounds;
import TheGreatHashtable.enums.OverlapType;





public class Hashtable {
	Node mainNode = null;
	
	//The overlap table
	OverlapType[][] overlapTable = {{OverlapType.Right,OverlapType.AEO,OverlapType.AEO},{OverlapType.OEA,OverlapType.Equals,OverlapType.AEO},{OverlapType.OEA,OverlapType.OEA,OverlapType.Left}};
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
	
	
//true connects 5-6, false would return Before
public OverlapType RetOverlap(Node O,Node A, boolean tbool)
{
	int bool = (tbool) ? 1:0;
	
	if(Compare(O.Ret(Bounds.l)-bool,A.Ret(Bounds.u)) == 2)
		return OverlapType.Before;
	if(Compare(A.Ret(Bounds.l)-bool,O.Ret(Bounds.u)) == 2)
		return OverlapType.After;
	
			
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
