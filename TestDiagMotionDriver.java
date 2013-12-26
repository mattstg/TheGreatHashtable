package TheGreatHashtable;

public class TestDiagMotionDriver {
	public static void main(String[] args) {
		
		//diagonal motion test going @ velocity [1,1] with object of size 2,2 @ origion (0,0)
		Entity E1 = new Entity(); //default constructor Entity
		DiagMotion s = new DiagMotion(1,1,E1); //creating a new motion, of velocity [1,1] on object E1
		CollisionSpace[] i = new CollisionSpace[2]; //initializing our two CollisionSpaces (our A & B in our diagrams) 
		/*
		System.out.println("Example 1:");
		System.out.println("__________________________________");
		i = s.RetColSpaces(); // RetColSpaces returns the spaces to be checked, and places them in i 
		System.out.println(E1.toString());
		System.out.println(s.toString());
		System.out.println("A: " + i[0].toString());
		System.out.println("B: " + i[1].toString());
			So this prints:
		 * 
				width:2  height:2  & @:(0 , 0)
				velocity: [1 , 1] & slope: 1.0
				A: (0 , 1) to (2 , 1)
				B: (2 , 0) to (2 , -1)
		  
		 * 	Our A square starts on square 0,1 and continues by (0+1,1) ... (0+2,1) ... (0+3,1) etc 
		 *  until it reaches end point. In this case its (2,1).
		 * 	 so A: (0,1) -> (2,1) horizontally
		 * 	Our B square starts at (2,0) and travels downwards until (2,-1) 
		 * 	 so B: (2,0) -> (2,-1) vertically
		 * 	
		 *  This happens to be correct. I looked into it :) 
		 *  So up and to the right works!
		 * 
		 *  So lets test another one!
		 
		System.out.println();
		System.out.println();
		System.out.println("Example 2:");
		System.out.println("__________________________________");
		
		s = new DiagMotion(-1,1,E1); //creating a new motion, of velocity [1,1] on object E1
		i = s.RetColSpaces(); //recycling our existing collision spaces
		System.out.println(E1.toString());
		System.out.println(s.toString());
		System.out.println("A: " + i[0].toString());
		System.out.println("B: " + i[1].toString());
		
		 So in this case the output is:
		 *
				width:2  hight:2  & @:(0 , 0)
				velocity: [-1 , 1] & slope: -1.0
				A: (-1 , 1) to (1 , 1)
				B: (-1 , 0) to (-1 , -1)
			
		 * This is correct too!
		 * So up and left works!
		
		
		System.out.println();
		System.out.println();
		System.out.println("Example 3:");
		System.out.println("__________________________________");
		
		s = new DiagMotion(1,-1,E1); //creating a new motion, of velocity [1,1] on object E1
		i = s.RetColSpaces(); //recycling our existing collision spaces
		System.out.println(E1.toString());
		System.out.println(s.toString());
		System.out.println("A: " + i[0].toString());
		System.out.println("B: " + i[1].toString());

		
		System.out.println();
		System.out.println();
		System.out.println("Example 4:");
		System.out.println("__________________________________");
		
		s = new DiagMotion(-1,-1,E1); //creating a new motion, of velocity [1,1] on object E1
		i = s.RetColSpaces(); //recycling our existing collision spaces
		System.out.println(E1.toString());
		System.out.println(s.toString());
		System.out.println("A: " + i[0].toString());
		System.out.println("B: " + i[1].toString());
		*/
		
		//ok, so all four directions work! Now I need to test when x = 0 and when y = 0...
		
		System.out.println();
		System.out.println();
		System.out.println("Example 5:");
		System.out.println("__________________________________");
		
		s = new DiagMotion(0,1,E1); //creating a new motion, of velocity [1,1] on object E1
		i = s.RetColSpaces(); //recycling our existing collision spaces
		System.out.println(E1.toString());
		System.out.println(s.toString());
		if(i[0] == null){
			System.out.println("A: is null");
		}else{
			System.out.println("A: " + i[0].toString());
		}
		if(i[1] == null){
			System.out.println("B: is null");
		}else{
			System.out.println("B: " + i[1].toString());
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Example 6:");
		System.out.println("__________________________________");
		
		s = new DiagMotion(0,-1,E1); 
		i = s.RetColSpaces(); 
		System.out.println(E1.toString());
		System.out.println(s.toString());
		if(i[0] == null){
			System.out.println("A: is null");
		}else{
			System.out.println("A: " + i[0].toString());
		}
		if(i[1] == null){
			System.out.println("B: is null");
		}else{
			System.out.println("B: " + i[1].toString());
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Example 6:");
		System.out.println("__________________________________");
		
		s = new DiagMotion(1,0,E1); 
		i = s.RetColSpaces(); 
		System.out.println(E1.toString());
		System.out.println(s.toString());
		if(i[0] == null){
			System.out.println("A: is null");
		}else{
			System.out.println("A: " + i[0].toString());
		}
		if(i[1] == null){
			System.out.println("B: is null");
		}else{
			System.out.println("B: " + i[1].toString());
		}
		
		System.out.println();
		System.out.println();
		System.out.println("Example 6:");
		System.out.println("__________________________________");
		
		s = new DiagMotion(-1,0,E1); 
		i = s.RetColSpaces(); 
		System.out.println(E1.toString());
		System.out.println(s.toString());
		if(i[0] == null){
			System.out.println("A: is null");
		}else{
			System.out.println("A: " + i[0].toString());
		}
		if(i[1] == null){
			System.out.println("B: is null");
		}else{
			System.out.println("B: " + i[1].toString());
		}
	}
}
