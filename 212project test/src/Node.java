public class Node<T> {
	public T data;
	public Node<T> next;

	public Node() {
		data = null;
		next = null;
	}

	public Node(T value) {
		data = value;
		next = null;
	}

	// to use in insertEvent *******************
	public void setNext(Node<T> head) {
		
		
	}
}