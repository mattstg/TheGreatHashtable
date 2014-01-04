package TheGreatHashtable;

import TheGreatHashtable.enums.*;

public class ASCIIWorldGen {

	final int SIZEX = 50;
	final int SIZEY = 25;
	char[][] world = new char[SIZEX][SIZEY];
	
	public ASCIIWorldGen(){
		for(int x= 0; x < SIZEX; x++)
			for(int y= 0; y < SIZEY; y++)
				world[x][y] = '*';
		
	}
	
	public void ResetWorld()
	{
		for(int x= 0; x < SIZEX; x++)
			for(int y= 0; y < SIZEY; y++)
				world[x][y] = '*';
	}
	
	public String DrawWorld()
	{
		String toRet = "";
		for(int y= 0; y < SIZEY; y++)
		{
			for(int x = 0; x < SIZEX; x++)
			{
				toRet += world[x][y];				
			}
			toRet+= '\n';
		}
		return toRet;
	}
	
	public void LoadNodeIntoWorld(Hashtable hasht)
	{
		
		
		Node Xit = hasht.mainNode;
		Node Yit;
		
		
		while(Xit != null)
		{
			Yit = Xit.Dwn();
			while(Yit != null)
			{
				for(int x = Xit.Ret(Bounds.l);x <= Xit.Ret(Bounds.u);x++)
				for(int y = Yit.Ret(Bounds.l);y <= Yit.Ret(Bounds.u);y++)
				{
					world[x][y] = '/';				
					
				}
				Yit = Yit.Adj();
				
			}
			Xit = Xit.Adj();
		}
		
		
		
	}
	
	
}
