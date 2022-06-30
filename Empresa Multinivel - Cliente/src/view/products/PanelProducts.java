package view.products;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.Controller;
import models.entities.Product;
import persistence.PropertiesTitles;
import view.properties.ConstantGUI;
import javax.swing.JButton;

public class PanelProducts extends JPanel {

	private static final long serialVersionUID = -6478883021287877861L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnReturnProduct;

	public PanelProducts(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
	}
	
	public void loadTexts(){
		btnReturnProduct.setText(propertiesTitles.getProperty(ConstantGUI.T_RETURN_PRODUCT));
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
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel();
		table.setModel(model);
		
		JPanel panelNorth = new JPanel();
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelControls = new JPanel();
		panelNorth.add(panelControls, BorderLayout.WEST);
		
		btnReturnProduct = new JButton();
		btnReturnProduct.setFocusable(false);
		btnReturnProduct.addActionListener(controller);
		btnReturnProduct.setActionCommand(ConstantGUI.C_RETURN_PRODUCT);
		panelControls.add(btnReturnProduct);
	}

	public void addProduct(Product product){
		model.addRow(product.getData());
	}

	public void removeProduct(int ref) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if ((int)model.getValueAt(i, 0) == ref) {
				model.removeRow(i);
				break;
			}
		}
	}
}