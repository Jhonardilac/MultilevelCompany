package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

import comunication.ClientSocket;
import comunication.Host;
import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import structures.Node;
import utilities.Strings;
import view.properties.ConstantGUI;

public class ThreadUserListener extends Thread{

	private Host host;
	private int idUser;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	
	public ThreadUserListener(Host host, ClientSocket clientSocket) {
		this.host = host;
		this.idUser = clientSocket.getId();
		this.dataInputStream = clientSocket.getDataInputStream();
		this.dataOutputStream = clientSocket.getDataOutputStream();
	}
	
	@Override
	public void run() {
		super.run();
		while (true) {
			try {
				if (this.dataInputStream.available() != 0) {
					switch (this.dataInputStream.readUTF()) {
					case ConstantGUI.CC_AUTHORIZE_SALE: authorizeSale(); break;
					case ConstantGUI.CC_GET_CLIENT_NAME: getClientName(); break;
					case ConstantGUI.CC_SEND_MESSAGE: sendMessage(); break;
					case ConstantGUI.CC_REQUEST_ORDERS: sendProducts(); break;
					case ConstantGUI.CC_RETURN_PRODUCT: returnProduct(); break;
					default: break;
					}
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private void authorizeSale() throws IOException {
		try {
			int ref = Integer.parseInt(this.dataInputStream.readUTF());
			int amount = Integer.parseInt(this.dataInputStream.readUTF());
			Product product = host.getProduct(ref);
			if (product != null) {
				Order order = new Order(host.generateOrderRegisterID(), new Date(), idUser, ref, amount, 1);
				dataOutputStream.writeUTF(ConstantGUI.CH_ACCEPT_ORDER);
				dataOutputStream.writeUTF(Strings.toString(order));
//				host.addOrder(order);
			}else{
				dataOutputStream.writeUTF(ConstantGUI.CH_PETITION_DENIED);
			}
		} catch (Exception e) {
			dataOutputStream.writeUTF(ConstantGUI.CH_PETITION_DENIED);
		}
	}

	private void getClientName() {
		int idClient;
		try {
			idClient = Integer.parseInt(this.dataInputStream.readUTF());
			Node<Person> person = host.getClient(idClient);
			String nameClient = null;
			if (person != null) {
				nameClient = person.getInfo().getName();
			}else{
				nameClient = "null";
			}
			dataOutputStream.writeUTF(nameClient);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void returnProduct() throws IOException {
		try {
			int ref = Integer.parseInt(dataInputStream.readUTF());
			boolean wasPossible = host.returnProduct(ref);
			if (wasPossible) {
				dataOutputStream.writeUTF(ConstantGUI.CH_RETURN_PRODUCT_TRUE);
				dataOutputStream.writeUTF(""+ref);
			}else{
				dataOutputStream.writeUTF(ConstantGUI.CH_RETURN_PRODUCT_FALSE);
			}
		} catch (Exception e) {
			dataOutputStream.writeUTF(ConstantGUI.CH_PETITION_DENIED);
		}
	}

	private void sendMessage() {
		try {
			int focus = Integer.parseInt(this.dataInputStream.readUTF());
			String message = this.dataInputStream.readUTF();
			host.sendMessage(idUser, focus, message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendProducts() throws IOException {
		String products = host.getProducts(idUser);
		this.dataOutputStream.writeUTF(ConstantGUI.CH_SEND_PRODUCTS);
		if (products != null) {
			this.dataOutputStream.writeUTF(products);
		}else{
			this.dataOutputStream.writeUTF("null");
		}
	}
}