package TheGreatHashtable.testDrivers;

import TheGreatHashtable.ASCIIWorldGen;
import TheGreatHashtable.Hashtable;
import TheGreatHashtable.Node;
import TheGreatHashtable.NodeManipulator;
import TheGreatHashtable.ShapeGenerator;
import TheGreatHashtable.enums.S_XY;
import TheGreatHashtable.enums.Shape;

public class SubtractionTester {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ASCIIWorldGen World = new ASCIIWorldGen();
		World.ResetWorld();
		ShapeGenerator shapeGen = new ShapeGenerator();
		
		
		Node hashNode = shapeGen.GenShape(Shape.Square, new S_XY(0,0), new S_XY(15,15));		
		Node s1 = shapeGen.GenShape(Shape.Square, new S_XY(10,10), new S_XY(7,7));
		//Node a2 = shapeGen.GenShape(Shape.Square, new S_XY(14,2), new S_XY(2,3));	
		//Node a3 = shapeGen.GenShape(Shape.Square, new S_XY(2,19), new S_XY(4,1));	
		
		Hashtable table = new Hashtable(hashNode);	
		World.LoadNodeIntoWorld(table);
		System.out.println("World Before Subtraction:" + '\n' + World.DrawWorld() + '\n' + table.ToString());			
	
		
		table.HashSubtractor(s1);
		World.LoadNodeIntoWorld(table);
		System.out.println("World Afer Subtraction:" + '\n' + World.DrawWorld() + '\n' + table.ToString());
		
		
				
		
		
		
	}

}
