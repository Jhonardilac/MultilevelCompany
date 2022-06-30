package view;

import javax.swing.JFrame;

import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import structures.Node;
import structures.Queeue;
import view.company.CompanyPanel;
import view.components.MenuBar;
import view.orders.OrdersPanel;
import view.persons.PersonCreatorDialog;
import view.persons.PersonPanel;
import view.products.ProductsPanel;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;
import controllers.Controller;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.Dimension;

public class HomeFrame extends JFrame {

	private static final long serialVersionUID = 1958941995814465217L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private MenuBar menuBar;
	private PersonPanel panelPerson;
	private JTabbedPane tabbedPane, tabbedMembers;
	private PersonCreatorDialog personCreatorDialog;
	private JScrollPane scrollPaneMembers;
	private JPanel panelAuxMembers;
	private JPanel panelMembers;
	private ProductsPanel productsPanel;
	private CompanyPanel companyPanel;
	private OrdersPanel ordersPanel;
	
	public HomeFrame(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		personCreatorDialog = new PersonCreatorDialog(controller);
		initProperties();
		initComponents();
		loadTexts();
	}

	public void loadTexts() {
		menuBar.loadTexts();
		panelPerson.loadText();
		ordersPanel.loadTexts();
		productsPanel.loadTexts();
		personCreatorDialog.loadTexts();
		setTitle(propertiesTitles.getProperty(ConstantGUI.T_HOME));
		tabbedPane.setTitleAt(0, propertiesTitles.getProperty(ConstantGUI.T_PRODUCTS));
		tabbedPane.setTitleAt(1, propertiesTitles.getProperty(ConstantGUI.T_ORDERS));
		tabbedPane.setTitleAt(2, propertiesTitles.getProperty(ConstantGUI.T_COMPANY_INFO));
		tabbedMembers.setTitleAt(0, propertiesTitles.getProperty(ConstantGUI.T_MEMBERS));
	}

	private void initProperties() {
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initComponents() {
		menuBar = new MenuBar(controller);
		setJMenuBar(menuBar);
		
		JPanel panelHeader = new JPanel();
		getContentPane().add(panelHeader, BorderLayout.WEST);
		panelHeader.setLayout(new BorderLayout(0, 0));
		
		panelPerson = new PersonPanel(controller, null);
		panelHeader.add(panelPerson, BorderLayout.NORTH);
		
		tabbedMembers = new JTabbedPane(JTabbedPane.TOP);
		tabbedMembers.setFocusable(false);
		panelHeader.add(tabbedMembers, BorderLayout.CENTER);
		
		scrollPaneMembers = new JScrollPane();
		scrollPaneMembers.setPreferredSize(new Dimension(50, 2));
		scrollPaneMembers.getVerticalScrollBar().setUnitIncrement(32);
		tabbedMembers.addTab(null, null, scrollPaneMembers, null);
		
		panelAuxMembers = new JPanel();
		panelAuxMembers.setLayout(new BorderLayout(0, 0));
		scrollPaneMembers.setViewportView(panelAuxMembers);
		
		panelMembers = new JPanel();
		panelMembers.setLayout(new GridLayout(0, 1, 0, 0));
		panelAuxMembers.add(panelMembers, BorderLayout.NORTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setFocusable(false);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		productsPanel = new ProductsPanel(controller);
		tabbedPane.addTab(null, null, productsPanel, null);
		
		ordersPanel = new OrdersPanel(controller);
		tabbedPane.addTab(null, null, ordersPanel, null);
		
		companyPanel = new CompanyPanel();
		tabbedPane.addTab(null, null, companyPanel, null);
	}
	
	public void setNode(Node<Person> personNode){
		panelMembers.removeAll();
		productsPanel.removeProducts();
		ordersPanel.removeOrders();
		ordersPanel.setOrders(personNode.getInfo().getOrders());
		refreshPerson(personNode.getInfo());
		Node<Person> nodeMember = personNode.getFirst();
		while (nodeMember != null) {
			Person person = nodeMember.getInfo();
			PersonPanel personPanel = new PersonPanel(controller, person);
			personPanel.setSimpleMode();
			panelMembers.add(personPanel);
			nodeMember = nodeMember.getNext();
		}
	}
	
	public void companyMode(boolean enabled){
		if (enabled) {
			tabbedPane.addTab(propertiesTitles.getProperty(ConstantGUI.T_COMPANY_INFO), null, companyPanel, null);
		}else{
			tabbedPane.remove(companyPanel);;
		}
	}
	
	public void setCompanyInfo(Queeue<Product> products) {
		Product product = products.get();
		while (product != null) {
			productsPanel.addProduct(product);
			product = products.get();
			ordersPanel.setCompanyMode();
		}
		tabbedPane.addTab(null, null, companyPanel, null);
	}
	
	public ProductsPanel getProductsPanel() {
		return productsPanel;
	}

	public PersonCreatorDialog getPersonCreatorDialog() {
		return personCreatorDialog;
	}

	public void refreshPerson(Person person) {
		panelPerson.setPerson(person);
	}

	public void refreshOrders(Queeue<Order> simpleList) {
		ordersPanel.setOrders(simpleList);
	}

	public void setProducts(Queeue<Product> products) {
		productsPanel.setProducts(products);
	}
}