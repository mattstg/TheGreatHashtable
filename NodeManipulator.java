package TheGreatHashtable;

import TheGreatHashtable.enums.Bounds;
import TheGreatHashtable.enums.OverlapType;
import TheGreatHashtable.enums.copyTypes;

public class NodeManipulator {
	OverlapType[][] overlapTable = {{OverlapType.Right,OverlapType.AEO,OverlapType.AEO},{OverlapType.OEA,OverlapType.Equals,OverlapType.AEO},{OverlapType.OEA,OverlapType.OEA,OverlapType.Left}};
	public NodeManipulator(){}
	
	
	public Node Inverser(Node n)
	{
		//Node O = new Node(n,true); //Copy of the Orginal, 
		//Node toRetX = new Node(O,false);
		Node OHead = n.CopySelf(copyTypes.copyBoth);  //oit points to copy of full node to be inersed
		Node ox = OHead;
		Node oy;
		
		Node TRHead;
		Node TRx = null;
		
		
		
		//Node TRit;
		//Node Oit = O.Dwn();
		
		//do first case
		if(ox.Ret(Bounds.l) > 0)	
		{
			TRHead = new Node(0,ox.l-1,null,null);	
			TRHead.Dwn(new Node(0,Constants.WORLD_SIZE_Y));
			TRx = TRHead;
			
		}
		else 
		{
			TRHead = new Node(ox);
			TRx = TRHead; ////////////////////ADDED THIS WHEN REALLY TIRED,NT SURE IF CORRECT
			TRHead.Dwn(_InverseAdjList(ox.Dwn()));
			if(ox.Adj() != null)
			{
				ox = ox.Adj();	
				if(_GapExists(TRHead,ox))
				{
					TRHead.Adj(_Gap(TRHead,ox));
					TRx = TRHead.Adj();
					TRx.Dwn(new Node(0,Constants.WORLD_SIZE_Y,null,null));
				}
			} else {
				if(TRHead.Ret(Bounds.u) < Constants.WORLD_SIZE_X) //case that list is one element big, and does not strecth to end
				{
					TRHead.Adj(new Node(TRHead.Ret(Bounds.u),Constants.WORLD_SIZE_X,null,null));
					TRx = TRHead.Adj();
					TRx.Dwn(new Node(0,Constants.WORLD_SIZE_Y,null,null));
				} //if it is one element big starting from 0 and strecthing to end, then the inverse above already took care of it
				
			}
			
		}		
		
		
		while(ox != null)
		{
			//inverse the node
			TRx.Adj(new Node(ox));
			TRx = TRx.Adj();
			TRx.Dwn(_InverseAdjList(ox.Dwn()));
			
			//now gap the holes
			ox = ox.Adj();
			if(ox != null && _GapExists(TRx,ox))
			{
				TRx.Adj(_Gap(TRx,ox));
				TRx = TRx.Adj();
				TRx.Dwn(new Node(0,Constants.WORLD_SIZE_Y,null,null));
			}
			
			
		}
		
		//done, check for upper bound to end
		
		if(TRx.Ret(Bounds.u) < Constants.WORLD_SIZE_X)
		{
			
			TRx.Adj(new Node(TRx.Ret(Bounds.u)+1,Constants.WORLD_SIZE_X,null,null));
			TRx = TRx.Adj();
			TRx.Dwn(new Node(0,Constants.WORLD_SIZE_Y,null,null));		
		}
		
		//Check through for nodes to be deleted//////////////////////////////////
		//Possible TRHead.adj being null? if possible, error can occur, please look into this, although its very unlikely, or TRHead being null will cause fatal crash
		Node tx = TRHead;
		
		while(TRHead.Dwn().Ret(Bounds.l) == -1)
		{
			
			TRHead = TRHead.Adj();	
			tx = TRHead;
		}
		
		while(tx.Adj() != null)
		{
			
			
			if(tx.Adj().Dwn() == null || tx.Adj().Dwn().Ret(Bounds.l) == -1)			
				tx.Adj(tx.Adj().Adj());				
			else
				tx = tx.Adj();
		}
				
		
		return TRHead;
	}
	
	public Node CleanNode(Node a)
	{
		Node toRet = a.CopySelf(copyTypes.copyBoth);
		//toRet = _MergeAllX(toRet);
		toRet = _DeleteAllEmptyX(toRet);
		
		return toRet;
	}
	
	
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
	
	private Node _MergeAllX(Node a)
	{
		return null;
	}
	
	private Node _DeleteAllEmptyX(Node a)
	{
		Node headNode = a;
		Node tx = null;
		
		while(headNode.Dwn().Ret(Bounds.l) == -1)
			headNode = headNode.Adj();	
				
		
		tx = headNode;
		
		if(tx != null)
		while(tx.Adj() != null)
		{			
			if(tx.Adj().Dwn() == null || tx.Adj().Dwn().Ret(Bounds.l) == -1)			
				tx.Adj(tx.Adj().Adj());				
			else
				tx = tx.Adj();
		}
		
		return headNode;
		
	}

	public Node YSubtractor(Node O,Node a)
	{
		Node TRHead = O.CopySelf(copyTypes.copyAdj);
		Node TRit = TRHead;
		Node TRTrail = TRHead;
		
				
		//Loop not entered for Y Sub
		while(a != null && TRHead != null && TRit != null)
		{
						
			
			switch(RetOverlap(TRit,a,false))
			{
			case Equals: 
				
				if(TRit == TRHead)
					TRHead = TRHead.Adj();
				else	
					TRTrail.Adj(TRTrail.Adj().Adj());
				
					
				a = a.Adj();				
				break;
				
			case Before:
				//if happens before, no need to delete
				a = a.Adj();
				break;
			case After:	
				
				TRit = TRit.Adj();
				if(TRTrail.Adj() != TRit)
					TRTrail = TRTrail.Adj();		
				
				break;
				
			case Right:		
			case Left:	
				_OverlapSplitter(TRit,a);
				break;
				
			case OEA:	
				_SubsetSplitter(a,TRit);
				break;
				
			case AEO:	
				_SubsetSplitter(TRit,a);
				break;	
					
			
			
			}			
		}
		
		return TRHead;
	}
	
	private void _MergeNodes(Node O, Node A)
	{
		//given two nodes, will merge A into node O, O will be modded, not A
		int lb;
		int ub;
		
		if(O.Ret(Bounds.l) <= A.Ret(Bounds.l))
			lb = O.Ret(Bounds.l);
		else
			lb = A.Ret(Bounds.l);
		
		if(O.Ret(Bounds.u) >= A.Ret(Bounds.u))
			ub = O.Ret(Bounds.u);
		else
			ub = A.Ret(Bounds.u);
		
		O.Set(lb, ub);
		
		
	}
	
	public void _OverlapSplitter(Node center, Node overlap)
	{
			
		
		//center node will not be split, overlap one will be
		if(overlap.Ret(Bounds.l) < center.Ret(Bounds.l))
		{
			//overlap is to the left, break apart overlap
			Node oldAdjPtr = overlap.Adj();
			//Node newNode = new Node(center.Ret(Bounds.l),overlap.Ret(Bounds.u),overlap);
			Node newNode = overlap.CopySelf(copyTypes.copyDwn);
			newNode.Set(center.Ret(Bounds.l),overlap.Ret(Bounds.u));
			
			overlap.Adj(newNode);
			newNode.Adj(oldAdjPtr);
			overlap.Set(center.Ret(Bounds.l) - 1, Bounds.u);
		} else {
			
			Node oldAdjPtr = overlap.Adj();
			//Node newNode = new Node(center.Ret(Bounds.u)+1,overlap.Ret(Bounds.u),overlap);
			Node newNode = overlap.CopySelf(copyTypes.copyDwn);
			newNode.Set(center.Ret(Bounds.u)+1,overlap.Ret(Bounds.u));
			
			overlap.Adj(newNode);
			newNode.Adj(oldAdjPtr);
			overlap.Set(center.Ret(Bounds.u), Bounds.u);
			
		}
	}
	
	private void _SubsetSplitter(Node a, Node b)
	{
		
		//Node B is a subset of node A. A will be the one that always splits
				//Node newNode()
			if(a.Ret(Bounds.l) == b.Ret(Bounds.l))
			{ //lb are equal, splits into two
				
				Node oldAdjPtr = a.Adj();
				//Node newNode = new Node(b.Ret(Bounds.u)+1,a.Ret(Bounds.u),a);
				Node newNode = a.CopySelf(copyTypes.copyDwn);
				newNode.Set(b.Ret(Bounds.u)+1,a.Ret(Bounds.u));
				a.Adj(newNode);
				newNode.Adj(oldAdjPtr);
				a.Set(b.Ret(Bounds.u), Bounds.u);			
				
			} else if(a.Ret(Bounds.u) == b.Ret(Bounds.u)) {
				
				Node oldAdjPtr = a.Adj();
				//Node newNode = new Node(b.Ret(Bounds.l),a.Ret(Bounds.u),a);
				Node newNode = a.CopySelf(copyTypes.copyDwn);
				newNode.Set(b.Ret(Bounds.l),a.Ret(Bounds.u));
				a.Adj(newNode);
				newNode.Adj(oldAdjPtr);
				a.Set(b.Ret(Bounds.l) - 1, Bounds.u);
			
			} else {
				
				//split into 3
				Node oldAdjPtr = a.Adj();
				//Node newNode = new Node(b.Ret(Bounds.l),a.Ret(Bounds.u),a);
				Node newNode = a.CopySelf(copyTypes.copyDwn);
				newNode.Set(b.Ret(Bounds.l),a.Ret(Bounds.u));
				a.Adj(newNode);
				newNode.Adj(oldAdjPtr);
				a.Set(b.Ret(Bounds.l) - 1, Bounds.u);
				_SubsetSplitter(a.Adj(),b);
			}	
			
		
	}
	
	
	private Node _Gap(Node a, Node b)
	{
		Node toRet = new Node(a.Ret(Bounds.u)+1,b.Ret(Bounds.l)-1,null,null);
		return toRet;
	}
	
	private boolean _GapExists(Node a, Node b)
	{
		if(a.Ret(Bounds.u) < b.Ret(Bounds.l)-1)
			return true;
		return false;
	}
	
	public Node _InverseAdjList(Node a)
	{
		//Given an node, copies all adj of it 
		Node O = a.CopySelf(copyTypes.copyAdj);
		
		//Node toRetX = new Node(O,false);
		Node TRHead;// = new Node(O); //CopySelf(copyTypes.copyNode);		
		Node TRit;
		Node Oit = O;
		
		//Dont forget full case!!!
		if(O.Ret(Bounds.l) == 0 && O.Ret(Bounds.u) == Constants.WORLD_SIZE_Y)
		{
			//this means the inverse should cause the scope above (x) to be deleted. 
			TRHead = new Node(-1,0,null,null);
			return TRHead;			
		}
		
		
		if(Oit.Ret(Bounds.l) > 0)
		{
			TRHead = new Node(0,Oit.Ret(Bounds.l)-1,null,null);
					
		} else {
			if(Oit.Adj() != null)
				TRHead = _Gap(Oit,Oit.Adj());
			else
				TRHead = new Node(Oit.Ret(Bounds.u)+1,Constants.WORLD_SIZE_Y,null,null);	
			Oit = Oit.Adj();
		}
		
		TRit = TRHead;
		//toRetX.Dwn(newDwn);
		//TRit = toRetX.Dwn();
		
		
		//Now down node has been taken care of, start with adj
		
		if(Oit != null) //case its null already, which means its all taken care of above
		{
			while(Oit.Adj() != null)
			{
				Node temp = _Gap(Oit,Oit.Adj());
				TRit.Adj(temp);			
				Oit = Oit.Adj();
				if(Oit != null)
					TRit = TRit.Adj();  
							
			}
			//check if last one has reached end
			
			if(Oit.Ret(Bounds.u) < Constants.WORLD_SIZE_Y) //then there is a gap
			{
				Node temp = new Node(Oit.Ret(Bounds.u)+1,Constants.WORLD_SIZE_Y,null,null);
				TRit.Adj(temp);
				
			}
		}
		
		
		//Y List should be inverted by here	
		
		
		return TRHead;
		
	}
	
	
}
