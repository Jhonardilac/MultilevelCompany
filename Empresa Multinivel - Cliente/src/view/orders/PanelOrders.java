package view.orders;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.Controller;
import models.entities.Order;
import persistence.PropertiesTitles;
import view.properties.ConstantGUI;
import javax.swing.JButton;

public class PanelOrders extends JPanel {
	
	private static final long serialVersionUID = 659896178851704764L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnRequestOrders;

	public PanelOrders(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
	}
	
	public void loadTexts(){
		btnRequestOrders.setText(propertiesTitles.getProperty(ConstantGUI.T_REQUEST_ORDERS));
		model.setColumnIdentifiers(new String[]{
				propertiesTitles.getProperty(ConstantGUI.T_REGISTER_ID),
				propertiesTitles.getProperty(ConstantGUI.T_DATE),
				propertiesTitles.getProperty(ConstantGUI.T_ID_MEMBER),
				propertiesTitles.getProperty(ConstantGUI.T_CODE_PRODUCT),
				propertiesTitles.getProperty(ConstantGUI.T_AMOUNT),
				propertiesTitles.getProperty(ConstantGUI.T_STATE)});
	}

	private void initProperties() {
		setLayout(new BorderLayout(0, 0));
	}

	private void initComponents() {
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel();
		table.setModel(model);
		
		JPanel panelNorth = new JPanel();
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelControls = new JPanel();
		panelNorth.add(panelControls, BorderLayout.WEST);
		
		btnRequestOrders = new JButton();
		btnRequestOrders.setFocusable(false);
		btnRequestOrders.addActionListener(controller);
		btnRequestOrders.setActionCommand(ConstantGUI.C_REQUEST_ORDERS);
		panelControls.add(btnRequestOrders);
	}

	public void addOrder(Order order){
		model.addRow(order.getData());
		table.repaint();
	}

	public void clean() {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			try {
				model.removeRow(i);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void removeOrderWithProductCode(int ref) {
		for (int i = 0; i < model.getRowCount(); i++) {
			try {
				if ((int)model.getValueAt(i, 3) == ref) {
					model.removeRow(i);
					break;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
			}
		}
	}
}
