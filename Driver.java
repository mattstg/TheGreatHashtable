package TheGreatHashtable;

public class Driver {
	public static void main(String[] args) {
		
		Node Node1 = new Node(1,2, null, null);
		Node Node2 = new Node(0,1, Node1, null);
		System.out.println("Attempting to acquire Node1's xNode:");
		Node o = (Node) Node2.Acq('x');
		System.out.println("This should print the lower bound of Node1 (should be 1):" + (Node) o.Acq('l'));
		
		
	}
}
