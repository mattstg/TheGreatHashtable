package TheGreatHashtable.enums;

public class S_Box {
		 
		//S_Box moo = new S_Box(new S_XY(),new S_XY());   how to init shortcut
		
		public S_XY loc;
		public S_XY size;

		public S_Box(){loc = new S_XY(); size = new S_XY();}
		public S_Box(S_XY tLoc, S_XY tSize){ loc = tLoc; size = tSize; }
		
		
	}


