package structures;

public class SNode<T> {

	protected T info;
	protected SNode<T> next;
	
	public SNode() {
		this.info = null;
		this.next = null;
	}
	
	public SNode(T info) {
		this.info = info;
		this.next = null;
	}
	
	public SNode(T info, SNode<T> next) {
		this.info = info;
		this.next = next;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public SNode<T> getNext() {
		return next;
	}

	public void setNext(SNode<T> next) {
		this.next = next;
	}
}
