package structures;

public class Queeue<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public Queeue() {
		this.head = null;
		this.tail = null;
	}

	/** 
	 * Retorna el primer elemento de la cola y quita ese elemento
	 * @return
	 */
	public T get(){
		T aux = null;
		if (this.head != null) {
			aux = this.head.getInfo();
			this.head = this.head.next;
		}
		return aux;
	}

	/**
	 * Agrega un elemento a la cola (al final)
	 * @param info
	 */
	public void put(T info){
		size++;
		if (head == null && tail == null) {
			this.head = this.tail = new Node<T>(info);
		}else{
			this.tail.next = new Node<T>(info);
			this.tail = this.tail.next;
		}
	}
	
	public void print(){
		Node<T> head = this.head;
		while (head != null) {
			System.out.println(head);
			head = head.next;
		}
	}

	public boolean isEmpty(){
		return head == null;
	}

}