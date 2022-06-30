package comunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controllers.Controller;
import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import structures.Node;
import structures.SNode;
import structures.SimpleList;
import structures.Stack;
import threads.ThreadAuthenticator;
import threads.ThreadUserListener;

public class Host extends Thread {

	private ServerSocket serverSocket;
	private Controller controller;
	private SimpleList<ClientSocket> clientSockets;
	
	public Host(Controller controller ,int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.controller = controller;
		this.clientSockets = new SimpleList<ClientSocket>(null);
	}
	
	@Override
	public void run() {
		super.run();
		while (true) {
			Socket socket;
			try {
				socket = serverSocket.accept();
				new ThreadAuthenticator(controller, this, socket).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addClient(ClientSocket clientSocket) {
		clientSockets.insert(clientSocket);
		new ThreadUserListener(this, clientSocket).start();
	}

	public Node<Person> getClient(int id) {
		return controller.getClient(id);
	}
	
	public ClientSocket getClientSocket(int idUser) {
		SNode<ClientSocket> node = clientSockets.getHead();
		while (node != null) {
			ClientSocket clientSocket = node.getInfo();
			if (clientSocket.getId() == idUser) {
				return clientSocket;
			}
			node = node.getNext();
		}
		return null;
	}

	public Stack<Order> getOrders(int id) {
		return controller.getOrders(id);
	}
	
	public SimpleList<Product> getProducts() {
		return controller.getProducts();
	}

	public void sendMessage(int from, int to, String message) {
		controller.sendMessage(from, to, message);
	}
	
	public int generateOrderRegisterID() {
		return controller.generateOrderRegisterID();
	}

	public Product getProduct(int ref) {
		return controller.getProduct(ref);
	}

	public String getProducts(int id) {
		return controller.getProducts(id);
	}

	public boolean returnProduct(int ref) {
		return controller.returnProduct(ref);
	}
}