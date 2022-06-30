package structures;

public class Node<T> {

	protected T info;
	protected Node<T> first;
	protected Node<T> next;
	
	public Node() {
		this.info = null;
	}
	
	public Node(T info) {
		this.info = info;
	}
	
	public Node(T info, Node<T> first) {
		this.info = info;
		this.first = first;
	}

	public Node(T info, Node<T> first, Node<T> next) {
		this.info = info;
		this.first = first;
		this.next = next;
	}

	public T getInfo() {
		return info;
	}
	
	public void setInfo(T info) {
		this.info = info;
	}

	public Node<T> getFirst() {
		return first;
	}

	public void setFirst(Node<T> first) {
		this.first = first;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}