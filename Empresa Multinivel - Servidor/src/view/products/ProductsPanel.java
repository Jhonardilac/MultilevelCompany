package view.products;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import structures.Queeue;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;
import controllers.Controller;
import models.entities.Product;

import javax.swing.JButton;
import javax.swing.JSpinner;

import javax.swing.JSeparator;

public class ProductsPanel extends JPanel {
	
	private static final long serialVersionUID = -8392141829890330508L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private TableProducts tableProducts;
	private JSpinner spnReturnProduct;
	private JButton btnReturnProduct;

	public ProductsPanel(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
	}
	
	public void loadTexts(){
		btnReturnProduct.setText(propertiesTitles.getProperty(ConstantGUI.T_RETURN_PRODUCT));
	}

	private void initComponents() {
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tableProducts = new TableProducts();
		scrollPane.setViewportView(tableProducts);
		
		JPanel panelHeader = new JPanel();
		add(panelHeader, BorderLayout.NORTH);
		
		JSeparator separator = new JSeparator();
		panelHeader.add(separator);
		
		spnReturnProduct = new JSpinner();
		panelHeader.add(spnReturnProduct);
		
		btnReturnProduct = new JButton();
		btnReturnProduct.addActionListener(controller);
		btnReturnProduct.setActionCommand(ConstantGUI.C_RETURN_PRODUCT);
		panelHeader.add(btnReturnProduct);
	}

	private void initProperties() {
		setLayout(new BorderLayout(0, 0));
	}
	
	public void addProduct(Product product) {
		tableProducts.addProduct(product);
	}
	
	public void setProducts(Queeue<Product> products){
		tableProducts.setProducts(products);
	}
	
	public void removeProducts(){
		this.tableProducts.removeRows();
	}
	
	public int getReturnProductValue(){
		return (int) spnReturnProduct.getValue();
	}
}