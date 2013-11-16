package TheGreatHashtable;

import TheGreatHashtable.enums.Bounds;
import TheGreatHashtable.enums.ENode;
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

private Node _pop(Node node)
{
	if(node == null)
		return null;
	
	Node toRet = node;
	node.Adj(node.Adj());
	return toRet;
}


public void YAdder(Node OyList,Node AyList)
{
	Node Oit = OyList;
	Node Ait = AyList;
	
	while(true)
	{
		if(Oit == null)
		{
			Oit = _pop(AyList);
			return; //ditto			
			
		}
		
		
		OverlapType compared = RetOverlap(OyList,AyList,true);
		
		if(compared == OverlapType.Before)
		{
			AyList = AyList.Ret(ENode.adj);
			Ait.ClearLink();
			Ait.Set(Oit.Ret(ENode.adj),ENode.adj);
			Oit.Set(Ait, ENode.adj);
			return; //ditto	
			
		}
		
		 
		if(compared == OverlapType.After)
		{
			//In this case, After has been called, which means A comes after O, so it through O
			if(Oit.Ret(ENode.adj) == null)
			{
				AyList = AyList.Ret(ENode.adj);
				Ait.ClearLink();
				Oit.Set(Ait, ENode.adj);
				return;		//Ait has been added
			} else if (RetOverlap(Oit.Ret(ENode.adj),Ait,false) == OverlapType.Before){
				
				AyList = AyList.Ret(ENode.adj);
				Ait.ClearLink();
				Ait.Set(Oit.Ret(ENode.adj),ENode.adj);
				Oit.Set(Ait, ENode.adj);
				return; //ditto		
					
			}
				
			
			
			Oit = Oit.Ret(ENode.adj);
		
			//there is a chance we have reached the end of O, which means we should add the new A node here
			
		}
		
		Node temp = Oit.Adj();
		Oit = yMerger(Oit,Ait);
		Oit.Adj(temp);	
		
	}	
}


	
public Node yMerger(Node O, Node A){	
	Node n = null;
	switch(RetOverlap(O,A,true)){
	case Right:
		n.Set(O.Ret(Bounds.l),A.Ret(Bounds.u));
		return n;
	case Left:	
		n.Set(A.Ret(Bounds.l),O.Ret(Bounds.u));
		return n;
	case Equals: 
	case AEO:
		//n.Set(O.Ret(Bounds.l),O.Ret(Bounds.u));
		//return n;
		O.ClearLink();
		return O;
	case OEA:
		//n.Set(A.Ret(Bounds.l),A.Ret(Bounds.u));
		//return n;
		A.ClearLink();
		return A;
	default: 
		System.out.print("Warning: Invalid input into ");
	}

	return O;
}





	
	
	
	
	
}
