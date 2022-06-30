package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import models.entities.Order;
import structures.Stack;
import utilities.Strings;

public class DAOOrders {

	private static final String ORDERS_PATH = "src\\data\\MultinivelPedidos.csv";
	private int mayorID = 0;
	
	public Stack<Order> loadOrders(int id) throws IOException{
		FileReader fileReader = new FileReader(new File(ORDERS_PATH));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		Stack<Order> orders = new Stack<Order>();
		String auxLine = "";
		while ((auxLine = bufferedReader.readLine()) != null) {
			if (auxLine.contains(""+id)) {
				Order order = Strings.toOrder(auxLine);
				if (order != null) {
					if (order.getIdMember() == id) {
						orders.push(order);
					}
					int registerID = order.getRegisterID();
					if (registerID > mayorID) {
						mayorID = registerID;
					}
				}
			}
		}
		bufferedReader.close();
		fileReader.close();
		return orders;
	}

	public int getMayorID() {
		return mayorID;
	}

	public Order loadOrderWithProductCode(int ref) throws IOException {
		FileReader fileReader = new FileReader(new File(ORDERS_PATH));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		Order order = null;
		String auxLine = "";
		while ((auxLine = bufferedReader.readLine()) != null) {
			if (auxLine.contains(""+ref)) {
				order = Strings.toOrder(auxLine);
				if (order != null) {
					if (order.getCodeProduct() == ref) {
						bufferedReader.close();
						fileReader.close();
						return order;
					}
				}
			}
		}
		bufferedReader.close();
		fileReader.close();
		return null;
	}
}