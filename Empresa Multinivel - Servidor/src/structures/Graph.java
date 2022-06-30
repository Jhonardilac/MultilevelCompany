package structures;

import java.util.Comparator;

public class Graph<T> {

	private Comparator<T> comparator;
	private Comparator<Vertex<T>> comparatorVertexs;
	private SimpleList<Vertex<T>> vertexs;
	
	public Graph(Comparator<T> comparator) {
		this.comparator = comparator;
		this.comparatorVertexs = new Comparator<Vertex<T>>() {
			@Override
			public int compare(Vertex<T> vertex1, Vertex<T> vertex2) {
				return comparator.compare(vertex1.info, vertex2.info);
			}
		};
		this.vertexs = new SimpleList<>(comparatorVertexs);
	}

	public void add(T info){
		vertexs.add(new Vertex<T>(info, comparatorVertexs));
	}
	
	public T get(T info){
		Vertex<T> vertex = getVertex(info);
		return vertex != null ? vertex.info : null;
	}
	
	public Vertex<T> getVertex(T info){
		SNode<Vertex<T>> node = vertexs.head;
		Vertex<T> vertex = null;
		while (node != null) {
			vertex = node.info;
				if (comparator.compare(info, vertex.info) == 0) {
					return vertex;
				}
			node = node.next;
		}
		return null;
	}
	
	public SimpleList<Vertex<T>> getVertexs() {
		return vertexs;
	}
}