package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import comunication.ClientSocket;
import models.entities.Message;
import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import persistence.PropertiesTitles;
import persistence.PropertiesTitles.Language;
import threads.ThreadLoadData;
import utilities.Strings;
import view.HomeFrame;
import view.LoginFrame;
import view.clients.ClientCreator;
import view.properties.ConstantGUI;

public class Controller implements ActionListener, KeyListener{

	private PropertiesTitles propertiesTitles;
	private LoginFrame loginFrame;
	private ClientCreator clientCreator;
	private ClientSocket clientSocket;
	private String address = "localhost";
	private int port = 12345;
	private HomeFrame homeFrame;
	private Person person;
	
	public Controller() {
		this.propertiesTitles = new PropertiesTitles();
		this.loginFrame = new LoginFrame(this);
		this.clientCreator = new ClientCreator(this, propertiesTitles);
		this.homeFrame = new HomeFrame(this);
		loadLanguage(Language.SPANISH);
		loginFrame.setAddress(address);
		loginFrame.setPort(port);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ConstantGUI.C_CHANGE_ADDRESS: changeAddress(); break;
		case ConstantGUI.C_CHANGE_PORT: changePort(); break;
		case ConstantGUI.C_CLIENT_CREATOR_CLOSE: clientCreator.setVisible(false); break;
		case ConstantGUI.C_CLIENT_CREATOR_OPEN: clientCreator.setVisible(true); break;
		case ConstantGUI.C_EXIT: System.exit(0); break;
		case ConstantGUI.C_LOGIN: login(); break;
		case ConstantGUI.C_LOGOUT: logout(); break;
		case ConstantGUI.C_MESSAGE_TRAY_CLOSE: homeFrame.setMessageTrayVisible(false); break;
		case ConstantGUI.C_MESSAGE_TRAY_OPEN: homeFrame.setMessageTrayVisible(true); break;
		case ConstantGUI.C_REGISTER: register(); break;
		case ConstantGUI.C_SELL_PRODUCT: authorizeSale(); break;
		case ConstantGUI.C_SHOW_ABOUT: showAbout(); break;
		case ConstantGUI.C_REQUEST_ORDERS: requestOrders(); break;
		case ConstantGUI.C_RETURN_PRODUCT: returnProduct(); break;
		default: break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			login();
		}
	}
	
	public void addOrder(Order order) {
		homeFrame.addOrder(order);
		JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_NEW_ORDER));
	}
	
	public void addRealProduct(String answer) {
		String[] products = answer.split("\n");
		for (String string : products) {
			Product product = Strings.toProduct(string);
			if (product != null) {
				try {
					person.addProduct(product);
					homeFrame.addRealProduct(product);
					homeFrame.removeProductFromOrders(product.getRef());
				} catch (Exception e) { }
			}
		}
	}
	
	public Integer askNumber(String message){
		String sNum = JOptionPane.showInputDialog(message);
		if (sNum != null) {
			try {
				return Integer.parseInt(sNum);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_NUMBER_FORMAT));
				askNumber(message);
			}
		}
		return null;
	}
	
	private void authorizeSale() {
		Integer ref = askNumber(propertiesTitles.getProperty(ConstantGUI.D_SELL_PRODUCT_REF));
		if (ref != null) {
			Integer amount = askNumber(propertiesTitles.getProperty(ConstantGUI.D_SELL_PRODUCT_AMOUNT));
			if (amount != null) {
				sendMessage(ConstantGUI.CC_AUTHORIZE_SALE);
				sendMessage(""+ref);
				sendMessage(""+amount);
			}
		}
	}
	
	private void changeAddress() {
		String address = JOptionPane.showInputDialog(propertiesTitles.getProperty(ConstantGUI.D_INSERT_ADDRESS));
		if (address != null) {
			this.address = address;
			loginFrame.setAddress(address);
		}
	}
	
	private void changePort() {
		String sPort = JOptionPane.showInputDialog(propertiesTitles.getProperty(ConstantGUI.D_INSERT_PORT));
		if (sPort != null) {
			try {
				this.port = Integer.parseInt(sPort);
				loginFrame.setPort(port);
			} catch (NumberFormatException e) {
				changePort();
			}
		}
	}

	public void initApp(){
		loginFrame.setVisible(true);
//		homeFrame.setVisible(true);
	}

	private void loadLanguage(Language language) {
		try {
			propertiesTitles.loadLanguage(language);
			loginFrame.loadTexts();
			clientCreator.loadTexts();
			homeFrame.loadTexts();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean loadUser() {
		try {
			String answer = reciveMessage();
			if (!answer.equals("null")) {
				person = Strings.toPerson(answer);
				if (person != null) {
					homeFrame.setUser(person);
					return true;
				}else{
					loginFrame.setNormalMode();
				}
			}else{
				loginFrame.setNormalMode();
				JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_CLIENT_NOT_FOUND));
			}
		} catch (Exception e) {
			loginFrame.setNormalMode();
		}
		return false;
	}
	
	public void loadMembers(){
		String answer = reciveMessage();
		if (!answer.equals("null")) {
			String[] members = answer.split("\n");
			for (int i = 0; i < members.length; i++) {
				if (members[i] != null) {
					Person member = Strings.toPerson(members[i]);
					person.addMember(member);
					homeFrame.addMember(member);
				}
			}
		}
	}
	
	public void loadMessages(){
		String answer1 = reciveMessage();
		if (!answer1.equals("null")) {
			String[] messages = answer1.split("\n");
			for (String string : messages) {
				Message message = Strings.toMessage(string);
				int personID = message.getId();
				Person person = this.person.getMember(personID);
				if (person == null) {
					sendMessage(ConstantGUI.CC_GET_CLIENT_NAME);
					sendMessage(""+personID);
					String answer2 = reciveMessage();
					if (answer2 != "null") {
						homeFrame.addNewMessage(personID, answer2, message.getMessage());
					}
				}
			}
		}
	}
	
	public void loadOrders(){
		String answer = reciveMessage();
		if (!answer.equals("null")) {
			String[] orders = answer.split("\n");
			for (int i = 0; i < orders.length; i++) {
				homeFrame.addOrder(Strings.toOrder(orders[i]));
			}
		}
	}
	
	public void loadCatalogue() {
		String answer = reciveMessage();
		if (!answer.equals("null")) {
			String[] products = answer.split("\n");
			for (int i = 0; i < products.length; i++) {
				homeFrame.addProduct(Strings.toProduct(products[i]));
			}
		}
	}
	
	private void login() {
		try {
			this.clientSocket = new ClientSocket(address, port);
			sendMessage(ConstantGUI.C_LOGINTYPE_LOGIN);
			sendMessage(loginFrame.getClientName());
			String answer = reciveMessage();
			new NumberFormatException();
			switch (answer) {
			case "false": JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_CLIENT_NOT_FOUND)); break;
			case "true": new ThreadLoadData(this, clientSocket).start(); break;
			default: break;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_HOST_UNKNOWN));
		}
	}
	
	public void logout() {
		homeFrame.setVisible(false);
		loginFrame.setVisible(true);
		homeFrame.clean();
	}
	
	public void openChatWindow(String nameMember, int idMember) {
		homeFrame.openChatWindow(nameMember, idMember);
	}
	
	public void petitionDenied() {
		JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_PETITION_DENIED));
	}
	
	private String reciveMessage(){
		String message = null;
		try {
			message = this.clientSocket.reciveMessage();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return message;
	}
	
	public void reduceNumberOfMessages(int numberOfMessages) {
		homeFrame.reduceMessages(numberOfMessages);
	}
	
	private void register() {
		clientCreator.setVisible(false);
	}
	
	private void requestOrders() {
		if (JOptionPane.showConfirmDialog(null, propertiesTitles.getProperty(ConstantGUI.D_REQUEST_ORDERS)) == 0) {
			sendMessage(ConstantGUI.CC_REQUEST_ORDERS);
		}
	}
	
	private void returnProduct() {
		if (!person.getProducts().isEmpty()) {
			Integer productCode = askNumber(propertiesTitles.getProperty(ConstantGUI.D_RETURN_PRODUCT));
			if (person.searchProduct((int)productCode) != null) {
				if (productCode != null) {
					sendMessage(ConstantGUI.CC_RETURN_PRODUCT);
					sendMessage("" + productCode);
				}
			}else{
				JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_PRODUCT_NOT_FOUND));
			}
		}else{
			JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_NO_PRODUCTS));
		}
	}
	
	public void returnProductFalse() {
		JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_RETURN_PRODUCT_FALSE));
	}
	
	public void returnProductTrue(int ref) {
		person.removeProduct(ref);
		homeFrame.removeRealProduct(ref);
		JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_RETURN_PRODUCT_TRUE));
	}
	
	private void sendMessage(String message){
		try {
			this.clientSocket.sendMessage(message);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void sendMessage(int idFocus, String message) {
		sendMessage(ConstantGUI.CC_SEND_MESSAGE);
		sendMessage(""+idFocus);
		sendMessage(message);
	}
	
	public void setHomeFrameVisible(boolean b){
		homeFrame.setVisible(b);
	}
	
	public void setLoginFrameVisible(boolean b){
		loginFrame.setVisible(b);
	}
	
	private void showAbout() {
		JOptionPane.showMessageDialog(null, propertiesTitles.getProperty(ConstantGUI.D_ABOUT));
	}
	
	public void writeMessage(int idFocus, String nameFocus, String message) {
		homeFrame.addNewMessage(idFocus, nameFocus, message);
	}
	
	public PropertiesTitles getPropertiesTitles() {
		return propertiesTitles;
	}
	
	public void setAuthenticationMode() {
		loginFrame.setAuthenticationMode();
	}
	
	public void setLoadingMode() {
		loginFrame.setLoadingMode();
	}
	
	public void setNormalMode() {
		loginFrame.setNormalMode();
	}

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }
}