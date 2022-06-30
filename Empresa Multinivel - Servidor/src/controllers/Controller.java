package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import comunication.ClientSocket;
import comunication.Host;
import models.entities.Message;
import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import persistence.DAOMessages;
import persistence.DAOOrders;
import persistence.DAOPerson;
import persistence.DAOProducts;
import rules.Rules;
import structures.Node;
import structures.SNode;
import structures.SimpleList;
import structures.Stack;
import utilities.Strings;
import view.HomeFrame;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;
import view.properties.PropertiesTitles.Language;

public class Controller implements ActionListener{

	private HomeFrame homeFrame;
	private PropertiesTitles propertiesTitles;
	private Node<Person> loggedNode;
	private DAOMessages daoMessages;
	private DAOOrders daoOrders;
	private DAOPerson daoPerson;
	private DAOProducts daoProducts;
	private Host host;
	private int port = 12345;
	private SimpleList<Product> products;

	public Controller() {
		propertiesTitles = new PropertiesTitles();
		homeFrame = new HomeFrame(this);
		daoMessages = new DAOMessages();
		daoOrders = new DAOOrders();
		daoPerson = new DAOPerson();
		daoProducts = new DAOProducts();
		try {
			products = daoProducts.loadProducts();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			host = new Host(this, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ConstantGUI.C_EXIT: System.exit(0); break;
		default: break;
		}
	}

	public Node<Person> getClient(int id) {
		return daoPerson.loadPerson(id);
	}

	public Message[] getMessages(int personID) {
		return daoMessages.loadMessages(personID);
	}

	public Stack<Order> getOrders(int id) {
		Stack<Order> orders = null;
		try {
			orders = daoOrders.loadOrders(id);
		} catch (IOException e) {
		}
		return orders;
	}
	
	public Product getProduct(int ref) {
		SNode<Product> node = products.getHead();
		Product product = null;
		while (node != null) {
			product = node.getInfo();
			if (product.getRef() == ref) {
				return product;
			}
			node = node.getNext();
		}
		return null;
	}
	
	public SimpleList<Product> getProducts() {
		return products;
	}
	
	public String getProducts(int id) {
		Stack<Order> orders = getOrders(id);
		Order order = orders.pop();
		String producs = "";
		while (order != null) {
			if (Rules.validateOrderDelivery(order)) {
				Product product = getProduct(order.getCodeProduct());
				if (producs != null) {
					producs += Strings.toString(product) + "\n";
				}
			}
			order = orders.pop();
		}
		return producs.equals("") ? null : producs;
	}

	public void initApp(){
		try {
			propertiesTitles.loadLanguage(Language.ENGLISH);
			homeFrame.loadTexts();
			loadCompany();
			host.start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantGUI.D_LANGUAGE_FILE_NOT_FOUND);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void loadCompany(){
		loggedNode = daoPerson.loadPerson(0);
		if (loggedNode != null) {
			homeFrame.setNode(loggedNode);
			homeFrame.setVisible(true);
		}else{
			System.exit(0);
		}
	}
	
	public int generateOrderRegisterID() {
		return daoOrders.getMayorID() + 1;
	}
	
	public boolean returnProduct(int ref) {
		Order order = searchOrderWithProductCode(ref);
		if (order != null) {
			return Rules.validateReturnProduct(order);
		}
		return false;
	}

	public void sendMessage(int from, int to, String message) {
		ClientSocket clientSocket = host.getClientSocket(to);
		if (clientSocket != null) {
			try {
				Node<Person> person = getClient(from);
				if (person != null) {
					clientSocket.sendMessage(ConstantGUI.CH_SEND_MESSAGE);
					clientSocket.sendMessage(""+from);
					clientSocket.sendMessage(""+person.getInfo().getName());
					clientSocket.sendMessage(""+message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				daoMessages.saveMessage(to, new Message(from, message));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Order searchOrderWithProductCode(int ref) {
		try {
			return daoOrders.loadOrderWithProductCode(ref);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public PropertiesTitles getPropertiesTitles() {
		return propertiesTitles;
	}

}