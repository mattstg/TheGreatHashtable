package TheGreatHashtable;

import TheGreatHashtable.enums.*;

public class TestDiagMotionDriver {
	public static void main(String[] args) {
		
		//diagonal motion test going @ velocity [1,1] with object of size 2,2 @ origion (0,0)
		S_Box E = new S_Box(0,0,2,2); //default constructor Entity
		DiagMotion s = new DiagMotion(0,-1,E); //creating a new motion, of velocity [1,1] on object E
		S_Box ColBox;
		
		S_Box w = s.RetNextBox();
		
		System.out.println("Entity moving attributes: " + s.E.toString());
		System.out.println("box to be checked for collision: " + w.toString());
		
		
		
	}
}

