package structures;

import java.util.Comparator;

public class NTree<T> {

	private Node<T> root;
	private Comparator<T> comparator;
	
	public NTree(Comparator<T> comparator) {
		this.comparator = comparator;
		this.root = null;
	}
	
	public boolean add(T info){
		if (this.root == null) {
			this.root = new Node<T>(info);
			return true;
		}
		return false;
	}
	
	/**
	 * Agrega una informacion
	 * @param info
	 */
	public boolean add(T info, T parent){
		if (this.root == null || parent == null || info == null) {
			this.root = new Node<T>(info);
			return false;
		}else{
			Node<T> finded = search(parent);
			if (finded != null) {
				finded.first = new Node<T>(info, null, finded.first);
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * Busca el nodo que contenga la misma información pedida como parámetro,
	 * iniciando desde el nodo raiz. Este metodo utiliza una sobrecarga de metodo search
	 * para hacer una busqueda recursiva desde el nodo raiz. 
	 * Ver mas en el metodo search(Node<T>, T).
	 * @param info Informacion pedida para buscar el nodo que la contenga
	 * @return Nodo encontrado con la misma informacion
	 */
	private Node<T> search(T info){
		return search(this.root, info);
	}
	
	/**
	 * Busca el nodo que contenga la misma información pedida como parámetro.
	 * Basandose en el nodo pedido como parámetro, buscara dentro de sus nodos hasta encontrarlo
	 * usando una sobrecarga recursiva del mismo metodo.
	 * @param info Informacion pedida para buscar el nodo que la contenga
	 * @return Nodo encontrado con la misma informacion
	 */
	public Node<T> search(Node<T> node, T info){
		if (node != null) {
			if (comparator.compare(node.getInfo(), info) == 0) {
				return node;
			}else{
				if (node.first != null) {
					return search(node.first, info);
				}else{
					if (node.next != null) {
						return search(node.next, info); 
					}
				}
			}
		}
		return null;
	}
	
	public void print(){
		print(this.root);
	}
	
	public Node<T> getNode(Node<T> node, T info){
		if (node != null) {
			getNode(node.first, info);
			if (comparator.compare(node.getInfo(), info) == 0) {
				return node;
			}
			getNode(node.next, info);
		}
		return node;
	}
	
	public Queeue<T> getItems(Node<T> node, Queeue<T> list){
		if (node != null) {
			getItems(node.first, list);
			list.put(node.getInfo());
			getItems(node.next, list);
		}
		return list;
	}
	
	public void print(Node<T> node){
		if (node != null) {
			print(node.first);
			System.out.println(node.getInfo());
			print(node.next);
		}
	}
	
	public boolean isEmpty(){
		return this.root == null;
	}
	
	public Node<T> getRoot() {
		return root;
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}
	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
}