package view;

import javax.swing.JFrame;

import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import persistence.PropertiesTitles;
import structures.SNode;
import structures.SimpleList;
import view.components.ChatWindow;
import view.components.MenuBar;
import view.messages.MessageTray;
import view.orders.PanelOrders;
import view.persons.MemberPanel;
import view.persons.PersonPanel;
import view.products.PanelCatalogue;
import view.products.PanelProducts;
import view.properties.ConstantGUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;

import controllers.Controller;

import javax.swing.JScrollPane;

import java.awt.Dimension;

public class HomeFrame extends JFrame {

	private static final long serialVersionUID = 1958941995814465217L;
	private PropertiesTitles propertiesTitles;
	private MenuBar menuBar;
	private PersonPanel panelUser;
	private JTabbedPane tabbedPane, tabbedMembers;
	private JScrollPane scrollPaneMembers;
	private JPanel panelAuxMembers;
	private JPanel panelMembers;
	private PanelCatalogue panelCatalogue;
	private PanelOrders panelOrders;
	private PanelProducts panelProducts;
	private Controller controller;
	private SimpleList<ChatWindow> chatWindows;
	private MessageTray messageTray;
	
	public HomeFrame(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		this.chatWindows = new SimpleList<ChatWindow>();
		this.messageTray = new MessageTray(controller);
		initProperties();
		initComponents();
		loadTexts();
	}

	public void loadTexts() {
		menuBar.loadTexts();
		panelUser.loadText();
		messageTray.loadTexts();
		panelOrders.loadTexts();
		panelCatalogue.loadTexts();
		panelProducts.loadTexts();
		setTitle(propertiesTitles.getProperty(ConstantGUI.D_APP_TITLE));
		tabbedPane.setTitleAt(0, propertiesTitles.getProperty(ConstantGUI.T_CATALOGUE));
		tabbedPane.setTitleAt(1, propertiesTitles.getProperty(ConstantGUI.T_ORDERS));
		tabbedPane.setTitleAt(2, propertiesTitles.getProperty(ConstantGUI.T_PRODUCTS));
		tabbedMembers.setTitleAt(0, propertiesTitles.getProperty(ConstantGUI.T_MEMBERS));
	}

	private void initProperties() {
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(ConstantGUI.ICON_CLIENT_EMPTY.getImage());
	}

	private void initComponents() {
		menuBar = new MenuBar(controller, propertiesTitles);
//		setJMenuBar(menuBar);
		
		JPanel panelHeader = new JPanel();
		getContentPane().add(panelHeader, BorderLayout.WEST);
		panelHeader.setLayout(new BorderLayout(0, 0));
		
		panelUser = new PersonPanel(controller, propertiesTitles);
		panelHeader.add(panelUser, BorderLayout.NORTH);
		
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
		
		panelCatalogue = new PanelCatalogue(controller);
		tabbedPane.addTab(null, null, panelCatalogue, null);
		
		panelOrders = new PanelOrders(controller);
		tabbedPane.addTab(null, null, panelOrders, null);
		
		panelProducts = new PanelProducts(controller);
		tabbedPane.addTab(null, null, panelProducts, null);
	}
	
	public void addMember(Person person) {
		MemberPanel memberPanel = new MemberPanel(controller);
		memberPanel.setPerson(person);
		memberPanel.setSimpleMode();
		panelMembers.add(memberPanel);
	}
	
	public void addNewMessage(int id, String nameMember, String message) {
		ChatWindow chatWindow = searchChatWindow(id);
		if (chatWindow == null) {
			chatWindow = new ChatWindow(controller);
		}
		chatWindow.setFocus(nameMember, id);
		chatWindows.insert(chatWindow);
		chatWindow.addMessage(nameMember + ": " + message);
		messageTray.addMessage(id, nameMember, message);
		panelUser.increaseMessages();
	}
	
	public void addOrder(Order order) {
		panelOrders.addOrder(order);
	}
	
	public void addProduct(Product product) {
		panelCatalogue.addProduct(product);
	}
	
	public void addRealProduct(Product product) {
		panelProducts.addProduct(product);
	}
	
	public void clean() {
		messageTray.clean();
		panelMembers.removeAll();
		panelOrders.clean();
		panelUser.clean();
	}
	
	public void openChatWindow(String nameMember, int idMember) {
		ChatWindow chatWindow = searchChatWindow(idMember);
		messageTray.setVisible(false);
		if (chatWindow == null) {
			chatWindow = new ChatWindow(controller);
			chatWindow.setFocus(nameMember, idMember);
			chatWindows.insert(chatWindow);
		}
		chatWindow.setVisible(true);
	}
	
	public ChatWindow searchChatWindow(int id){
		SNode<ChatWindow> node = chatWindows.getHead();
		ChatWindow chatWindow = null;
		while (node != null) {
			chatWindow = node.getInfo();
			if (chatWindow.getIdFocus() == id) {
				return chatWindow;
			}
			node = node.getNext();
		}
		return null;
	}
	
	public void setMessageTrayVisible(boolean b){
		this.messageTray.setVisible(b);
	}
	
	public void setUser(Person person) {
		panelUser.setPerson(person);
	}

	public void reduceMessages(int numberOfMessages) {
		panelUser.reduceMessage(numberOfMessages);
	}

	public void removeProductFromOrders(int ref) {
		panelOrders.removeOrderWithProductCode(ref);
	}
	
	public void removeRealProduct(int ref) {
		panelProducts.removeProduct(ref);
	}
}