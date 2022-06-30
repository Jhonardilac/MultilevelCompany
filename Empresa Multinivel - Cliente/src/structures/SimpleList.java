package structures;

public class SimpleList<T> {
	
	private SNode<T> head;
	
	public SimpleList() {
		this.head = null;
	}
	
	/**
	 * Agrega al final de la lista, buscando desde el inicio hasta el final de esta
	 * @param info
	 */
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
	
	public boolean isEmpty(){
		return (head == null);
	}

	public SNode<T> getHead() {
		return head;
	}

	public void setHead(SNode<T> head) {
		this.head = head;
	}

	public void remove(T info) {
		SNode<T> node = this.head;
		SNode<T> previous = null;
		while (node != null) {
			if (node.info == info) {
				if (previous != null) {
					previous.next = node.next;
				}else{
					this.head = node.next;
				}
			}
			previous = node;
			node = node.next;
		}
	}
}
