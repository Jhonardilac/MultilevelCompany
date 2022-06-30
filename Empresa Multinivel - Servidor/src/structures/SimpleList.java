package structures;

import java.util.Comparator;

public class SimpleList<T> {
	
	protected SNode<T> head;
	private Comparator<T> comparator;
	
	public SimpleList(Comparator<T> comparator) {
		this.head = null;
		this.comparator = comparator;
	}
	
	public void add(T info){
		SNode<T> head = null;
		if (this.head == null) {
			this.head = new SNode<>(info);
		}else{
			head = this.head;
			while (head.getNext() != null) {
				head = head.getNext();
			}
			head.setNext(new SNode<>(info)); 
		}
	}
	
	/**
	 * Agrega al inicio, moviendo los demas nodos
	 * @param info
	 */
	public void insert(T info){
		this.head = new SNode<>(info, this.head);
	}
	
	public SNode<T> search(T info){
		SNode<T> node = this.head;
		while (node != null) {
			if (comparator.compare(node.info, info) == 0) {
				return node;
			}
		}
		return null;
	}
	
	public void remove(T info){
		SNode<T> node = this.head;
		SNode<T> previousNode = null;
		while (node != null) {
			if (comparator.compare(node.getInfo(), info) == 0) {
				if (previousNode == null) {
					if (this.head.next != null) {
						this.head = this.head.next;
					}else{
						this.head = null;
					}
				}else{
					previousNode.next = node.next;
				}
				return;
			}else{
				previousNode = node;
				node = node.next;
			}
		}
	}
	
	public boolean isEmpty(){
		return (head == null);
	}
	
	public void print(){
		SNode<T> node = this.head;
		while (node != null) {
			System.out.println(node.getInfo());
			node = node.next;
		}
	}

	public SNode<T> getHead() {
		return head;
	}

	public void setHead(SNode<T> head) {
		this.head = head;
	}
}