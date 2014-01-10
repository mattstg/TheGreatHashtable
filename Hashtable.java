package TheGreatHashtable;

import TheGreatHashtable.enums.*;





public class Hashtable {
	Node mainNode = null;
	NodeManipulator nami = new NodeManipulator();
	
	//The overlap table
	OverlapType[][] overlapTable = {{OverlapType.Right,OverlapType.AEO,OverlapType.AEO},{OverlapType.OEA,OverlapType.Equals,OverlapType.AEO},{OverlapType.OEA,OverlapType.OEA,OverlapType.Left}};
	/*  compareTable
	R   BeA  BeA 
	AeB Eq   BeA
	AeB Aeb  L

	*/
	
public Hashtable(Node hashTreeHead){
	mainNode = hashTreeHead;
}

public Hashtable(){
	mainNode = null;
}


public Node RetMainNode()  //Delete this function later, mainNode should stay in hashtable to avoid security/consistency issues
{
	return mainNode;
}


public String ToString()
{
	String toRet = "";
	Node it = mainNode;
	while(it != null)
	{
		
		Node yit = it.Dwn();
		toRet += it.toString();
		toRet += '\n';
		
		while(yit != null)
		{
			toRet += "   " + yit.toString() + '\n';
			yit = yit.Adj();
			
		}
		it = it.Adj();
				
	}
	
	return toRet;
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
	node = node.Adj(); //points to next node
	toRet.ClearLink(); //isolate the toRet node
	
	return toRet;
}

private boolean Coli(Node a, Node b)
{
	if(RetOverlap(a,b,false) == OverlapType.After || RetOverlap(a,b,false) == OverlapType.Before)
	{		
		return false;
	}
	return true;
	
	
}

public boolean Coli(S_Box box)
{
	//turn the passed box into a hashtable
	//Node x = new Node(l,u,adj,dwn);
	
	Node nodeY = new Node(box.loc.y-box.size.y+1,box.loc.y,null,null);
	Node nodeX = new Node(box.loc.x,box.loc.x+box.size.x-1,null,nodeY);
	//////System.Out.println("The NodeY: " + '\n' + nodeY.toString() + '\n' + "The NodeX: " + '\n' +  nodeX.Dwn().toString());
	//now have a tree for box 
	
	
	return Coli(nodeX); //given the nodeX trees
	
	
}


private boolean Coli(Node hashTree)  //WORKS UNDER ASSUMPTION THAT hashTree CONTAINS 1 x and 1 y!!!!!, please grow algo if needed
{
	Node mainITX = mainNode;  //Iterator to main node in x plane
	
	while(mainITX != null)
	{
		//////System.Out.println("main" + mainITX.toString());
		//////System.Out.println("hashTree" + hashTree.toString());
		
		
		if(Coli(mainITX,hashTree)) //if coli in x
		{
			
			//an overlap of a kind has occured, now loop through for the y laps
			Node mainITY = mainITX.Dwn(); //retrieve the dwn node
			
			while(mainITY != null)
			{
				//////System.Out.println("hashTree.dwn" + hashTree.Dwn().toString());
				//////System.Out.println("main.y" + mainITY.toString());
				
				if(Coli(mainITY,hashTree.Dwn())) //if a coli occurs between the y from local hash and y from given tree			
				{
					//////System.Out.println("The main: " + '\n' + mainITY.toString() + '\n' + "Moving object: " + '\n' +  hashTree.Dwn().toString());
					return true;//A coli has occured, this means there is a colision between these two trees
				}					
				else				
					mainITY = mainITY.Adj();//iterate to the next mainITY				
			}
			//no coli in y, go down one more x,
			
		}
		mainITX = mainITX.Adj(); //iterate to next x		
		
	}
	return false; //no coli occured
	
}



private void YMerger(Node Ox, Node Ax)
{
	
	
	
	//Given the two higher scopes to be combined
	Node O = Ox.Dwn();
	Node A = Ax.Dwn();
	Node OLast = null;
	//////System.Out.println("Enter YMerger");

		while(O != null && A != null)
		{
			
			OverlapType compared = RetOverlap(O,A,false);	
			//////System.Out.println("Start YMergerLoop");
			
			switch(compared)
			{
			case Equals: 
				//If identical, no need to merge, increase A
				//////System.Out.println("YMerger: Equals");
				A = A.Adj();				
				break;
				
			case Before:
				//////System.Out.println("YMerger: Before");
				//Should be taken care of already in after below, unless it is the first one
				if(O == Ox.Dwn())
				{
					Node newNode = new Node(A);
					newNode.Adj(Ox.Dwn());  //new node sets its adj to the it's
					Ox.Dwn(newNode);
					A = A.Adj();
					
				}
				break;
				
			case After:
				////System.Out.println("YMerger: After");		
				if(O.Adj() != null)
				{
					if(RetOverlap(O.Adj(),A,false) == OverlapType.Before)
					{
						////System.Out.println("YMerge: After-MergeNeghb");
						//then insert
						Node newNode = new Node(A); //non full copy of node (no dwn should exist)
						newNode.Adj(O.Adj());  //new node sets its adj to the it's
						O.Adj(newNode);	
						A = A.Adj(); //iterate the A
						MergeNeighbour(O);
					}	
				} else {
					OLast = O; //save last for second loop
				}
				
				O = O.Adj();
				break;
				
			case Right:		
			case Left:			
			case AEO:				
			case OEA:		
				
				_MergeNodes(O,A);
				A = A.Adj();				
				MergeNeighbour(O); //merge with neighbours possibly
				break;
				
			}
			
		}
		
		//while(A != null) //if its in here, its because Oit reached null, so just add the rest of the A in
		//{
		if(A != null)
		{
			Node newNode = A.CopySelf(copyTypes.copyAdj);	//this new node points to remaining list of A				
			OLast.Adj(newNode);
			//A = A.Adj();
			MergeNeighbour(OLast);
		
			//O = OLast.Adj();	
		}	
			
		//}
	
	
}


private void MergeNeighbour(Node O)
{
	//after insertion of LOWEST scope possible, check to merge with neighbours
	
	boolean mergeNeighbour = true;
	while(mergeNeighbour)
	{
		mergeNeighbour = false;
		if(O.Adj() != null)
		{
			
			OverlapType c = RetOverlap(O,O.Adj(),true);
			//////System.Out.println(O.toString() + '\n' + O.Adj().toString() + "  " + c);
			if(c != OverlapType.Before && c != OverlapType.After) //should never be after, but just to be sure
			{
				//needs to be merged with neighbor
				mergeNeighbour = true;
				_MergeNodes(O,O.Adj());
				Node toDel = O.Adj();
				O.Adj(O.Adj().Adj());
				toDel.ClearLink(); //clear links so will be deleted				
				
			}
		}
		////System.Out.println("DEBUG TEST: " + '\n' + this.ToString() + '\n');
	}
	
	
}

public void HashAdder(Node A)
{
	//given another hashTable head A, add to current hashtable
	
	
	Node Oit = this.mainNode;
	Node OLast = this.mainNode;
	
	
	
	//Scope 1 (X)
	
		while(Oit != null && A != null)
		{
			
			
			OverlapType compared = RetOverlap(Oit,A,false);	
			
			//System.out.println(A.toString());
			//System.out.println(compared);
			//try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
			
					
			
			switch(compared)
			{
			case Equals: 
				
				////System.Out.println("Before Equal:");
				////System.Out.println(ToString());					
				//good case, do Y ADDER
				YMerger(Oit,A); //merge the Ys of A into Oit
					
				
				Oit = Oit.Adj();			
				
				//OLast is null for some reason
				if(OLast.Adj() != Oit)
					OLast = OLast.Adj(); //increase the trail it
				A = A.Adj();				
				
				break;
				
			case Before:
				
				//Should be taken care of already in after below, unless is first case
				if(Oit == this.mainNode)
				{					
					Node newNode = A.CopySelf(copyTypes.copyDwn);							
					newNode.Adj(this.mainNode);  //new node sets its adj to the it's
					this.mainNode = newNode;
					Oit = this.mainNode;
					OLast = this.mainNode;
					A = A.Adj();					
				} else {
					//an A was created to the Before via Left or AEO
					////System.out.println("Step1, whats coming in:" + A.toString());try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
					
					Node newNode = A.CopySelf(copyTypes.copyDwn);	
					newNode.Adj(Oit);
					////System.out.println("NewNode:" + newNode.toString());try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
					////System.out.println("Its new ADJ:" + Oit.toString());try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
					OLast.Adj(newNode);
					////System.out.println("OLast Adj adding newNode:" + OLast.toString());try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
					A = A.Adj();
					Oit = newNode;
					
					/*////System.out.println("Adj: " + A.toString());try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

					////System.out.println('\n' + "Final Test:");
					Node t = mainNode;
					while(t != null)
					{
						////System.out.println('\n' + t.toString());
						t = t.Adj();						
					}
					////System.out.println("END RESULT: " + '\n' + '\n');try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
*/
				}
				
				break;
				
			case After:
				
				
				if(Oit.Adj() != null)
					if(RetOverlap(Oit.Adj(),A,false) == OverlapType.Before)
					{
						
						//then insert
						Node newNode = A.CopySelf(copyTypes.copyDwn);	
						newNode.Adj(Oit.Adj());  //new node sets its adj to the it's
						Oit.Adj(newNode);	
						A = A.Adj(); //iterate the A
					}					
				Oit = Oit.Adj();
				if(OLast.Adj() != Oit)
					OLast = OLast.Adj(); //increase the trail it
				break;
				
			case Right:		
			case Left:	
				_OverlapSplitter(Oit,A);
				break;
				
			case OEA:	
				_SubsetSplitter(A,Oit);
				break;
				
			case AEO:			
				_SubsetSplitter(Oit,A);
				break;
				
			}

			
		
		}
		
		
		if(A != null)
		{
			Node newNode = A.CopySelf(copyTypes.copyBoth);
			OLast.Adj(newNode);
		}
		
		/*while(A != null) //if its in here, its because Oit reached null, so just add the rest of the A in
		{
			Node newNode = new Node(A,true);
			OLast.Adj(newNode);
			A = A.Adj();
			OLast = newNode;
			
			
			
			
			
			
		}*/
	
		
		_FinalXMerger();
	
}



public void HashSubtractor(Node Ax)
{
	//given another hashTable head A, add to current hashtable
	
	
	Node Oit = this.mainNode;
	
		while(Oit != null && Ax != null)
		{
			
			
			OverlapType compared = RetOverlap(Oit,Ax,false);	
			
			
			switch(compared)
			{
			case Equals: 
				
				////System.Out.println(ToString());					
				//good case, do Y ADDER
				Oit.Dwn(nami.YSubtractor(Oit.Dwn(),Ax.Dwn())); //merge the Ys of A into Oit
							
				Oit = Oit.Adj();					
				Ax = Ax.Adj();				
				
				break;
				
			case Before:
				
				//If it comes before, no need to subtract it
				Ax = Ax.Adj();
				
				break;
				
			case After:
				
				Oit = Oit.Adj();
				break;
				
			case Right:		
			case Left:	
				_OverlapSplitter(Oit,Ax);
				break;
				
			case OEA:	
				_SubsetSplitter(Ax,Oit);
				break;
				
			case AEO:			
				_SubsetSplitter(Oit,Ax);
				break;
				
			}		
		
		}		
		//if A!= null, then there is sitll some A left, since nothing to subtract, a becomes null
		Ax = null;
				
			
		
		
		_FinalXMerger();  //remove when fix nami
		mainNode = nami.CleanNode(mainNode);
		//_NodeDeletor();
	
}





private void _FinalXMerger()
{
	//iterates through the list and checks if adj X are identical and merges them
	Node it = this.mainNode;
	if(it == null)
		return;
	Node itt = it.Adj();
	if(itt == null)
		return;
	
	Node xit;
	Node xitt;
	
	
	
	while(it != null && itt != null)
	{
		boolean valid = true;
		if(it.Ret(Bounds.u) == itt.Ret(Bounds.l)-1)
		{
			//if connected side by side 3-4 ex
			xit = it.Dwn();  //should not be null
			xitt = itt.Dwn();//should not be null
			
			
			while(valid)
			{		
				if(xit == null || xitt == null)
				{
					valid = false;				
				} else {
					if(xit.EqualBounds(xitt))
					{
						//good, iterate to the enxt
						xit = xit.Adj();
						xitt = xitt.Adj();
					} else {
						valid = false;
					}
				
				}			
				
			}
			
			if(xit == null && xitt == null) //this should only occur when they exit simotanouesly through all
			{
				it.Adj(itt.Adj()); //overwrite the one in front since its identical
				it.Set(itt.Ret(Bounds.l),Bounds.u);			
			}
			
			
			
		}
		it = it.Adj();
		itt = itt.Adj();
	}
	
		
	
	
	
}
	
/*
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
		////System.Out.print("Warning: Invalid input into ");
	}

	return O;
}
*/

public void NodeSplitter(Node ONode, Node ANode)
{
	OverlapType compared = RetOverlap(ONode,ANode,false);
	switch(compared)
	{
	case Equals:
		break; //no split needed
	case OEA:
		
	
	
	
	
	}
	
}


//Possible Refactor
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


//REFACTOR  I think the cases where the bounds match is useless since that is technically a subset, please refactor
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

	
	
	
	
	
}
