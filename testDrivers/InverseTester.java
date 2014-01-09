package TheGreatHashtable.testDrivers;

import TheGreatHashtable.ASCIIWorldGen;
import TheGreatHashtable.Hashtable;
import TheGreatHashtable.Node;
import TheGreatHashtable.NodeManipulator;
import TheGreatHashtable.ShapeGenerator;
import TheGreatHashtable.enums.S_XY;
import TheGreatHashtable.enums.Shape;

public class InverseTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ShapeGenerator shapeGen = new ShapeGenerator();
		NodeManipulator nami = new NodeManipulator();
		
		
		Node hashNode = shapeGen.GenShape(Shape.Square, new S_XY(0,0), new S_XY(1,1));		
		Node a1 = shapeGen.GenShape(Shape.Square, new S_XY(2,0), new S_XY(1,26));
		//Node a2 = shapeGen.GenShape(Shape.Square, new S_XY(14,2), new S_XY(2,3));	
		//Node a3 = shapeGen.GenShape(Shape.Square, new S_XY(2,19), new S_XY(4,1));	
		
		Hashtable table = new Hashtable(hashNode);
		table.HashAdder(a1);
		//table.HashAdder(a2);
		//table.HashAdder(a3);
		System.out.println("World Before Inverse:" + '\n' + table.ToString());
	
			
		Node inversed = nami.Inverser(table.RetMainNode());
		table = new Hashtable(inversed);		
		System.out.println("World After Inverse:" + '\n' + table.ToString());
	}

}
