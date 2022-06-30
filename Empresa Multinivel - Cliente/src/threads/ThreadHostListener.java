package threads;

import java.io.DataInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import comunication.ClientSocket;
import controllers.Controller;
import models.entities.Order;
import models.entities.Product;
import utilities.Strings;
import view.properties.ConstantGUI;

public class ThreadHostListener extends Thread {

	private Controller controller;
	private DataInputStream dataInputStream;
	
	public ThreadHostListener(Controller controller, ClientSocket clientSocket) {
		this.controller = controller;
		this.dataInputStream = clientSocket.getDataInputStream();
	}
	
	@Override
	public void run() {
		super.run();
		while (true) {
			try {
				if (dataInputStream.available() != 0) {
					switch (dataInputStream.readUTF()) {
					case ConstantGUI.CH_ACCEPT_ORDER: reciveOrder(); break;
					case ConstantGUI.CH_PETITION_DENIED: controller.petitionDenied(); break;
					case ConstantGUI.CH_RETURN_PRODUCT_FALSE: returnProductFalse(); break;
					case ConstantGUI.CH_RETURN_PRODUCT_TRUE: returnProductTrue(); break;
					case ConstantGUI.CH_SEND_MESSAGE: reciveMessage(); break;
					case ConstantGUI.CH_SEND_PRODUCTS: reciveProducts(); break;
					default: break;
					}
				}
			} catch (NumberFormatException e) {
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, ConstantGUI.D_HOST_UNKNOWN);
				controller.logout();
			}
		}
	}
	
	private void reciveMessage() throws NumberFormatException, IOException {
		int idFocus = Integer.parseInt(dataInputStream.readUTF());
		String nameFocus = dataInputStream.readUTF();
		String message = dataInputStream.readUTF();
		controller.writeMessage(idFocus, nameFocus, message);
	}
	
	private void reciveOrder() throws IOException {
		Order order = Strings.toOrder(dataInputStream.readUTF());
		if (order != null) {
			controller.addOrder(order);
		}
	}
	
	private Product[] reciveProducts() throws IOException {
		String answer = dataInputStream.readUTF();
		if (!answer.equals("null")) {
			controller.addRealProduct(answer);
		}
		return null;
	}
	
	private void returnProductFalse() {
		controller.returnProductFalse();
	}
	
	private void returnProductTrue() throws NumberFormatException, IOException {
		int ref = Integer.parseInt(dataInputStream.readUTF());
		controller.returnProductTrue(ref);
	}
}