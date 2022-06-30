package structures;

import java.util.Comparator;

/**
 * 
 * @author Fredy
 *
 * @param <T>
 */
public class BTree<T> {

    protected Node<T> root;
    protected Comparator<T> comparator;

    public BTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Agrega informacion in-Order
     * AVL
     * @param info
     */
    public void add(T info) {
        if (this.root == null) {
            this.root = new Node<>(info);
        } else {
            Node<T> aux = this.root;
            while (true) {
                if (comparator.compare(aux.info, info) > 0) {
                    if (aux.first == null) {
                        aux.first = new Node<>(info);
                        return;
                    } else {
                        aux = aux.first;
                    }
                } else {
                    if (aux.next == null) {
                        aux.next = new Node<>(info);
                        return;
                    } else {
                        aux = aux.next;
                    }
                }
            }
        }
    }
    
    public void remove(T info){
    	
    }
    
	/**
     * Este metodo sirve para buscar el {@link Node} que contiene la info igual a la pedida como parámetro.
     * Utiliza una sobrecarga del metodo search, el cual se diferencia por que este pide un nodo en el que se buscara
     * sus ramas. Ver mas en el metodo search(Node<T>, T);
     * @param info Aquella infomación con la cual se basa para buscar aquel nodo que la contiene dentro de un arbol binario.
     * @return Devuelve el nodo que contiene su info igual a la pedida como parámetro.
     */
    public Node<T> search(T info){
    	return search(this.root, info);
    }
    
    /**
     * Este método sirve para buscar el {@link Node} que contiene la info igual a la pedida como parámetro.
     * Este metodo usa una recursividad del mismo metodo buscando los nodos que se encuentran dentro de los nodos.
     * Para encontrar el nodo que se pide buscar, el utliza la interface {@link Comparator} establecida desde la creacion
     * del arbol binario, para comparar los valores de los nodos que encuentre.
     * @param root Nodo en el cual se buscara sus la informacion.
     * @param info Aquella infomación con la cual se basa para buscar aquel nodo que la contiene dentro de un arbol binario.
     * @return Devuelve el nodo que contiene su info igual a la pedida como parámetro.
     */
    public Node<T> search(Node<T> root, T info){
    	if (root != null) {
    		int comparation = comparator.compare(info, root.getInfo());
    		if (comparation == 0) {
				return root;
			}else if (comparation < 0) {
				return search(root.first, info);
			}else {
				return search(root.next, info);
			}
		}
    	return root;
    }
    
    public Queeue<T> getItems(){
    	return getItems(getRoot(), new Queeue<>());
    }
    public Queeue<T> getItems(Node<T> node, Queeue<T> queeue){
    	if (node != null) {
			getItems(node.first, queeue);
			queeue.put(node.getInfo());
			getItems(node.next, queeue);
		}
    	return queeue;
    }
    
    public Node<T> getRoot() {
		return root;
	}

	public void removeAll() {
		this.root = null;
	}
}
