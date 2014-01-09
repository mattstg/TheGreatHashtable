package TheGreatHashtable.testDrivers;

import TheGreatHashtable.ASCIIWorldGen;
import TheGreatHashtable.Hashtable;
import TheGreatHashtable.Node;
import TheGreatHashtable.NodeManipulator;
import TheGreatHashtable.ShapeGenerator;
import TheGreatHashtable.enums.*;

public class ASCIIWorldDriver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		ASCIIWorldGen World = new ASCIIWorldGen();
		World.ResetWorld();
		ShapeGenerator shapeGen = new ShapeGenerator();
		//NodeManipulator nami = new NodeManipulator();
		
		
		Node hashNode = shapeGen.GenShape(Shape.Square, new S_XY(7,7), new S_XY(7,7));
		Node addNode = shapeGen.GenShape(Shape.Square, new S_XY(5,5), new S_XY(3,3));
		
		
		System.out.println("World New:" + '\n' + World.DrawWorld());
		Hashtable table = new Hashtable(hashNode);
		
		World.LoadNodeIntoWorld(table);
		System.out.println("World with hashtable:" + '\n' + World.DrawWorld());
		
		//System.out.println(table.RetOverlap(Oy00, a3, true));
		//System.out.print(table.RetOverlap(Ox0, added, false));
		
		
		 table.HashAdder(addNode);
		 
		 World.LoadNodeIntoWorld(table);
		 System.out.println("World After Dirt-splosion:" + '\n' + World.DrawWorld());
		/*
		 Node y3 = new Node(18,20,null,null);
		 Node y2 = new Node(12,12,null,null);
		 Node y1 = new Node(6,10,null,null);
		 Node y0 = new Node(0,4,null,null);
			Node x0 = new Node(0,0,null,y0);
		 
			
		 table = new Hashtable(x0);
		 World.LoadNodeIntoWorld(table);
		 System.out.println("World Before the Inverse:" + '\n' + World.DrawWorld());
			
			
			
		Node inversed = nami._InverseYList(x0);
		table = new Hashtable(inversed);
		World.LoadNodeIntoWorld(table);
		System.out.println("World After Inverse:" + '\n' + World.DrawWorld());
		*/
		System.out.println(table.ToString());
		
		
}
}
