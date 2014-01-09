package TheGreatHashtable;

import TheGreatHashtable.enums.*;

public class ASCIIWorldGen {

	
	char[][] world = new char[Constants.WORLD_SIZE_X+1][Constants.WORLD_SIZE_Y+1];
	
	public ASCIIWorldGen(){
		for(int x= 0; x <= Constants.WORLD_SIZE_X; x++)
			for(int y= 0; y <= Constants.WORLD_SIZE_Y; y++)
				world[x][y] = '*';
		
	}
	
	public void ResetWorld()
	{
		for(int x= 0; x <= Constants.WORLD_SIZE_X; x++)
			for(int y= 0; y <= Constants.WORLD_SIZE_Y; y++)
				world[x][y] = '*';
	}
	
	public String DrawWorld()
	{
		String toRet = "";
		for(int y= 0; y <= Constants.WORLD_SIZE_Y; y++)
		{
			for(int x = 0; x <= Constants.WORLD_SIZE_X; x++)
			{
				toRet += world[x][y];				
			}
			toRet+= '\n';
		}
		return toRet;
	}
	
	public void LoadNodeIntoWorld(Hashtable hasht)
	{
		ResetWorld();
		
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
