package structures;

public class Stack<T> {

	private Node<T> top;
	
	public Stack() {
		this.top = null;
	}
	
	public boolean isEmpty(){
		return this.top == null;
	}
	
	public T pop(){
		Node<T> node = this.top;
		T aux = null;
		if (node != null) {
			aux = this.top.info;
			this.top = this.top.next;
		}
		return aux;
	}
	
	public void push(T info){
		this.top = new Node<T>(info, null, this.top);
	}

	public Stack(Node<T> top) {
		this.top = top;
	}
	
	public Node<T> getTop() {
		return top;
	}
	public void setTop(Node<T> top) {
		this.top = top;
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		Integer num = stack.pop();
		while (num != null) {
			System.out.println(num);
			num = stack.pop();
		}
	}
}