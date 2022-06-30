package view.messages;

import javax.swing.JDialog;

import controllers.Controller;
import persistence.PropertiesTitles;
import structures.SNode;
import structures.SimpleList;
import view.properties.ConstantGUI;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class MessageTray extends JDialog {

	private static final long serialVersionUID = 6968420677514918157L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JButton btnClose;
	private JPanel panelMessages;
	private SimpleList<MessagePanel> messagePanels;

	public MessageTray(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		messagePanels = new SimpleList<MessagePanel>();
		initProperties();
		initComponents();
	}
	
	public void loadTexts(){
		setTitle(propertiesTitles.getProperty(ConstantGUI.T_MESSAGE_TRAY));
		btnClose.setText(propertiesTitles.getProperty(ConstantGUI.T_CLOSE));
	}

	private void initProperties() {
		setModal(true);
		setIconImage(ConstantGUI.ICON_MESSAGES.getImage());
	}

	private void initComponents() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelMessages = new JPanel();
		panelMessages.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panelMessages, BorderLayout.NORTH);
		panelMessages.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelControls = new JPanel();
		getContentPane().add(panelControls, BorderLayout.SOUTH);
		
		btnClose = new JButton();
		btnClose.setFocusable(false);
		btnClose.addActionListener(controller);
		btnClose.setActionCommand(ConstantGUI.C_MESSAGE_TRAY_CLOSE);
		panelControls.add(btnClose);
	}
	
	public void addMessage(int id, String from, String message){
		MessagePanel messagePanel = searchMessage(id);
		if (messagePanel == null) {
			messagePanel = new MessagePanel(controller);
			Component[] components = panelMessages.getComponents();
			panelMessages.removeAll();
			panelMessages.add(messagePanel);
			for (Component component : components) {
				panelMessages.add(component);
			}
			messagePanels.insert(messagePanel);
		}
		messagePanel.addMessage(id, from, message);
	}
	
	public void clean() {
		panelMessages.removeAll();
		messagePanels = new SimpleList<MessagePanel>();
	}
	
	public MessagePanel searchMessage(int id){
		SNode<MessagePanel> node = messagePanels.getHead();
		MessagePanel messagePanel = null;
		while (node != null) {
			messagePanel = node.getInfo();
			if (messagePanel.getId() == id) {
				return messagePanel;
			}
			node = node.getNext();
		}
		return null;
	}
	
	@Override
	public void setVisible(boolean b) {
		setSize(350, 350);
		setLocationRelativeTo(null);
		super.setVisible(b);
	}
}
