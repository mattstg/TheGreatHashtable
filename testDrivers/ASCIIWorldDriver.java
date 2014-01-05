package TheGreatHashtable.testDrivers;

import TheGreatHashtable.ASCIIWorldGen;
import TheGreatHashtable.Hashtable;
import TheGreatHashtable.Node;
import TheGreatHashtable.ShapeGenerator;
import TheGreatHashtable.enums.*;

public class ASCIIWorldDriver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		ASCIIWorldGen World = new ASCIIWorldGen();
		World.ResetWorld();
		ShapeGenerator shapeGen = new ShapeGenerator();
		
		
		Node hashNode = shapeGen.GenShape(Shape.Square, new S_XY(7,7), new S_XY(7,7));
		Node addNode = shapeGen.GenShape(Shape.Square, new S_XY(3,3), new S_XY(3,3));
		
		
		System.out.println("World New:" + '\n' + World.DrawWorld());
		Hashtable table = new Hashtable(hashNode);
		
		World.LoadNodeIntoWorld(table);
		System.out.println("World with hashtable:" + '\n' + World.DrawWorld());
		
		//System.out.println(table.RetOverlap(Oy00, a3, true));
		//System.out.print(table.RetOverlap(Ox0, added, false));
		
		
		 table.HashAdder(addNode);
		 
		 World.LoadNodeIntoWorld(table);
		 System.out.println("World After Dirt-splosion:" + '\n' + World.DrawWorld());
		
			
		 
		System.out.println(table.ToString());
}
}
