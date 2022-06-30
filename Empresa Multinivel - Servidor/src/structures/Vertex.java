package structures;

import java.util.Comparator;

public class Vertex<T> {

	protected T info;
	protected SimpleList<Vertex<T>> adjacentVertices;
	
	public Vertex(T info, Comparator<Vertex<T>> comparatorVertexs) {
		this.info = info;
		this.adjacentVertices = new SimpleList<Vertex<T>>(comparatorVertexs);
	}
	
	public void add(Vertex<T> vertex){
		adjacentVertices.insert(vertex);
	}
	
	public void remove(Vertex<T> vertex){
		this.adjacentVertices.remove(vertex);
	}
	
	public SimpleList<Vertex<T>> getAdjacentVertices() {
		return adjacentVertices;
	}
	
	public T getInfo() {
		return info;
	}
	public void setInfo(T info) {
		this.info = info;
	}
}