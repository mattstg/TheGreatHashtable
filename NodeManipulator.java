package TheGreatHashtable;

import TheGreatHashtable.enums.Bounds;
import TheGreatHashtable.enums.copyTypes;

public class NodeManipulator {

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
