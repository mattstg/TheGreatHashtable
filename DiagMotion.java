package TheGreatHashtable;

public class DiagMotion { //diagonal motion
		private int x,y;
		double slope;
		private int distx,disty = 0; //this will be the distance covered so far.
		private Entity Et;
		
	
	//this will be the constructor we use the most. Input x velocity(a), y velocity(b), 
	//and the Entity doing the motion to get the locations needing to be checked for collision
	public DiagMotion(int a, int b, Entity tE){
		x = a;
		y = b;
		Et = new Entity(tE); //so we aren't going to have PRIVACY ISSUES AHAHAHHAHAHA
		if(x==0||y==0){
			if(x!=y){
			slope = (x>y)? x : y;
			}else{
				slope = 0;
			}
		}else{
			slope = x/y;
		}
	}
	
	//default constructor will give a velocity of [1,1] to a new Entity (with size 1x1, @ (0,0))
	//used primarily for testing.
	public DiagMotion(){ 
		x = 1;
		y = 1;
		Et = new Entity(); //see default constructor in Entity Class
		slope = x/y;
	}
	
	public String toString(){
		return "velocity: [" + x + " , " + y +"] & slope: " + slope;
	}

	
	//this returns two Collision Space objects which tell us the next location which we need to check...
	public CollisionSpace[] RetColSpaces(){
		int a = 0,b = 0,c = 0,d = 0;
		CollisionSpace i[] = new CollisionSpace[2];
		if(x>0){
			a = 0;
			c = Et.size[0];
			d = c;
		}else if(x<0){
			a = -1; // got one case
			d = -1;
			c = Et.size[0]-1;
		}else{
			a = 0;
			c = Et.size[0]-1 ;
		}
		if(y>0){
			b = 1;
		}else if(y<0){
			b = (-1)*(Et.size[1]-1)-1;
		}
		i[0] = new CollisionSpace(Et.loc[0]+a,Et.loc[1]+b,Et.loc[0]+c,Et.loc[1]+b);
		i[1] = new CollisionSpace(Et.loc[0]+d,Et.loc[1],Et.loc[0]+d,Et.loc[1]-(Et.size[1]-1));
		i[1] = (x==0) ? null: i[1];
		i[0] = (y==0) ? null: i[0];
		if(i[1] == null){
			//note we need to remove a bit off of the a in cases where y == 0
		}
		
		System.out.println("ret overlap successful");
		return i;
	}
	
	
	//this method moves the copy of the object you are collision testing forward one step in its motion.
	//must be done to get the next successive Collision Spaces.
	public void fantomMotion(int times){
		for(int i = times; i > 0; i--){
		int tx = 0,ty = 0; 
		if((distx-x)!=0){
			if(x>0)
				tx = 1;
			else
				tx = -1;
		}
		if((disty-y)!=0){
			if(y>0)
				ty = 1;
			else
				ty = -1;
		}	
		Et.setLoc(Et.getLoc()[0]+tx,Et.getLoc()[1]+ty);
		distx+=tx;
		disty+=ty;
		}
	}
	
	public void fantomMotion(){
		//same as above method, just without an argument.
		int tx = 0,ty = 0;
		if((distx-x)!=0){
			if(x>0)
				tx = 1;
			else
				tx = -1;
		}
		if((disty-y)!=0){
			if(y>0)
				ty = 1;
			else
				ty = -1;
		}	
		Et.setLoc(Et.getLoc()[0]+tx,Et.getLoc()[1]+ty);
		distx+=tx;
		disty+=ty;
	}
	
}
