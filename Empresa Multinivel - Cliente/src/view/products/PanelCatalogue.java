package view.products;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.Controller;
import models.entities.Product;
import persistence.PropertiesTitles;
import view.properties.ConstantGUI;
import javax.swing.JButton;

public class PanelCatalogue extends JPanel {
	
	private static final long serialVersionUID = 659896178851704764L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnSellProduct;

	public PanelCatalogue(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
	}
	
	public void loadTexts(){
		btnSellProduct.setText(propertiesTitles.getProperty(ConstantGUI.T_SELL_PRODUCT));
		model.setColumnIdentifiers(new String[]{
				propertiesTitles.getProperty(ConstantGUI.T_REF),
				propertiesTitles.getProperty(ConstantGUI.T_LINE),
				propertiesTitles.getProperty(ConstantGUI.T_REFERENCE),
				propertiesTitles.getProperty(ConstantGUI.T_TARGET),
				propertiesTitles.getProperty(ConstantGUI.T_PRICE)});
	}

	private void initProperties() {
		setLayout(new BorderLayout(0, 0));
	}

	private void initComponents() {
		
		JPanel panelNorth = new JPanel();
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelControls = new JPanel();
		panelNorth.add(panelControls, BorderLayout.WEST);
		
		btnSellProduct = new JButton();
		btnSellProduct.setFocusable(false);
		btnSellProduct.addActionListener(controller);
		btnSellProduct.setActionCommand(ConstantGUI.C_SELL_PRODUCT);
		panelControls.add(btnSellProduct);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel();
		table.setModel(model);
	}

	public void addProduct(Product product){
		model.addRow(product.getData());
	}

	public void clean() {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
}
