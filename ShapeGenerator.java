package TheGreatHashtable;
import TheGreatHashtable.enums.*;

public class ShapeGenerator {

	
	public ShapeGenerator()
	{
		
		
	}
	
	
	
	public Node GenShape(Shape shape, S_XY size, S_XY loc)
	{
		Node toRet = new Node(); //Dud node to make compiler happy..
		
		switch(shape)
		{
		case Circle:
			toRet = _MakeCircle(size,loc);  //Not functional atm
			break;
		case Square:
			toRet = _MakeSquare(size,loc);
			break;
		default:
			break;
		
		
		}
				
		
		
		
		return toRet;
	}
	

	
	
	//Need to complete 
	private Node _MakeCircle(S_XY size,S_XY loc)
	{
		Node toRet = new Node(1,1,null,null);
		
		
		return toRet;
	}
	
	private Node _MakeSquare(S_XY size,S_XY loc)
	{
			
		Node yCompenent = new Node(size.y,loc.y+size.y,null,null);
		Node toRet = new Node(loc.x,loc.x+size.x,null,yCompenent);
		return toRet;
		
	}

}
