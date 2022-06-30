package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import comunication.ClientSocket;
import comunication.Host;
import controllers.Controller;
import models.entities.Message;
import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import structures.Node;
import structures.SNode;
import structures.SimpleList;
import structures.Stack;
import utilities.Strings;
import view.properties.ConstantGUI;

public class ThreadAuthenticator extends Thread {

	private Host host;
	private Socket socket;
	private Controller controller;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ThreadAuthenticator(Controller controller, Host host, Socket socket) throws IOException {
		this.host = host;
		this.socket = socket;
		this.controller = controller;
		this.dataInputStream = new DataInputStream(socket.getInputStream());
		this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		super.run();
		try {
			String loginType = this.dataInputStream.readUTF();
			switch (loginType) {
			case ConstantGUI.C_LOGINTYPE_LOGIN: validateLogin(); break;
			case ConstantGUI.C_LOGINTYPE_REGISTER: break;
			default: break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void validateLogin() throws IOException{
		try {
			int personID = Integer.parseInt(dataInputStream.readUTF());
			Node<Person> nodePerson = host.getClient(personID);
			if (nodePerson != null) {
				dataOutputStream.writeUTF("true");
				sendUserData(nodePerson);
				sendMembers(nodePerson);
				sendProducts(personID);
				sendUserOrders(personID);
				sendMessages(personID);
				host.addClient(new ClientSocket(personID, socket));
			}else{
				dataOutputStream.writeUTF("false");
			}
		} catch (NumberFormatException e) {
			dataOutputStream.writeUTF("null");
		}
	}

	private void sendMembers(Node<Person> nodePerson) throws IOException {
		String members = "";
		Node<Person> member = nodePerson.getFirst();
		while (member != null) {
			members += Strings.toString(member.getInfo()) + "\n";
			member = member.getNext();
		}
		dataOutputStream.writeUTF((members.equals("") ? "null" : members));
	}
	
	private void sendMessages(int personID) throws IOException {
		Message[] text = controller.getMessages(personID);
		if (text != null) {
			String messages = "";
			for (Message message : text) {
				messages += Strings.toString(message) + "\n";
			}
			dataOutputStream.writeUTF(messages);
		}else{
			dataOutputStream.writeUTF("null");
		}
	}
	
	private void sendProducts(int personID) throws IOException {
		SimpleList<Product> products = host.getProducts();
		if (products != null) {
			String sOrders = "";
			SNode<Product> node = products.getHead();
			while (node != null) {
				sOrders += Strings.toString(node.getInfo()) + "\n";
				node = node.getNext();
			}
			dataOutputStream.writeUTF(sOrders);
		}else{
			dataOutputStream.writeUTF("null");
		}
	}

	
	private void sendUserData(Node<Person> nodePerson) throws IOException {
		Person person = nodePerson.getInfo();
		String sPerson = Strings.toString(person);
		if (sPerson != null) {
			dataOutputStream.writeUTF(sPerson);
		}else{
			dataOutputStream.writeUTF("null");
		}
	}
	
	private void sendUserOrders(int userID) throws IOException {
		Stack<Order> orders = host.getOrders(userID);
		if (orders != null) {
			String sOrders = "";
			Order order = orders.pop();
			while (order != null) {
				sOrders += Strings.toString(order) + "\n";
				order = orders.pop();
			}
			dataOutputStream.writeUTF(sOrders);
		}else{
			dataOutputStream.writeUTF("null");
		}
	}
	
}