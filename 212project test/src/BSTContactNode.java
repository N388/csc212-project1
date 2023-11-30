
public class BSTContactNode <T>{
	public String key;
	public T data;
	public BSTContactNode<T> left, right;

	public BSTContactNode(String k, T val) {
		key = k;
		data = val;
		left = right = null;
	}

	public BSTContactNode(String k, T val, BSTContactNode<T> l, BSTContactNode<T> r) {
		key=k;
		data=val;
		left=l;
		right=r;
	}
}
