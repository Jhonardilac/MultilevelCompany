package view.orders;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import structures.Queeue;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;
import models.entities.Order;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import controllers.Controller;

public class OrdersPanel extends JPanel {

	private static final long serialVersionUID = 4519914638431142950L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnBtnrequestAllOrders;
	private JButton btnRequestOrder;

	public OrdersPanel(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
	}

	public void loadTexts() {
		btnRequestOrder.setText(propertiesTitles.getProperty(ConstantGUI.T_REQUEST_ORDER));
		btnBtnrequestAllOrders.setText(propertiesTitles.getProperty(ConstantGUI.T_REQUEST_ALL_ORDERS));
	}

	private void initProperties() {
		setLayout(new BorderLayout(0, 0));
	}

	private void initComponents() {
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[]{"ID", "Date", "ID member", "Code product", "Amount", "State"});
		table.setModel(model);
		
		JPanel panelControls = new JPanel();
		add(panelControls, BorderLayout.NORTH);
		
		btnRequestOrder = new JButton();
		btnRequestOrder.addActionListener(controller);
		btnRequestOrder.setActionCommand(ConstantGUI.C_OPEN_CATALOGUE_CHOOSER);
		panelControls.add(btnRequestOrder);
		
		btnBtnrequestAllOrders = new JButton();
		btnBtnrequestAllOrders.addActionListener(controller);
		btnBtnrequestAllOrders.setActionCommand(ConstantGUI.C_REQUEST_ALL_ORDERS);
		panelControls.add(btnBtnrequestAllOrders);
	}
	
	public void addOrder(Order order){
		model.addRow(order.getData());
	}
	
	public void setOrders(Queeue<Order> orders){
		removeOrders();
		Order order = orders.get();
		while (order != null) {
			addOrder(order);
			order = orders.get();
		}
	}

	public void removeOrders() {
		int rows = model.getRowCount();
		for (int i = rows - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	public void setCompanyMode() {
		btnBtnrequestAllOrders.setEnabled(false);
	}
}