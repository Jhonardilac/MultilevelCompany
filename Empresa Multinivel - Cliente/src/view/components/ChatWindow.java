package view.components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import controllers.Controller;
import persistence.PropertiesTitles;
import view.properties.ConstantGUI;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatWindow extends JFrame {

	private static final long serialVersionUID = 3396671320601055460L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JTextField txtMessage;
	private JButton btnSendMessage;
	private int idFocus;
	private JScrollPane scrollPane;
	private JTextArea txtMessages;
	
	public ChatWindow(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
		loadTexts();
	}

	public void loadTexts() {
		btnSendMessage.setText(propertiesTitles.getProperty(ConstantGUI.T_SEND));
	}

	private void initProperties() {
		setSize(450, 350);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setIconImage(ConstantGUI.ICON_CLIENT.getImage());
	}

	private void initComponents() {
		JPanel panelMessage = new JPanel();
		panelMessage.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMessage.setLayout(new BorderLayout(2, 0));
		getContentPane().add(panelMessage, BorderLayout.SOUTH);
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});
		txtMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelMessage.add(txtMessage);
		
		btnSendMessage = new JButton();
		btnSendMessage.setFocusable(false);
		btnSendMessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		btnSendMessage.addActionListener(controller);
		panelMessage.add(btnSendMessage, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		txtMessages = new JTextArea();
		txtMessages.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMessages.setBorder(new EmptyBorder(5, 5, 5, 5));
		txtMessages.setEditable(false);
		txtMessages.setWrapStyleWord(true);
		scrollPane.setViewportView(txtMessages);
	}
	
	public void sendMessage(){
		String message = txtMessage.getText();
		if (!message.equals("")) {
			txtMessage.setText("");
			controller.sendMessage(idFocus, message);
			addMessage(propertiesTitles.getProperty(ConstantGUI.T_YOU) + ": " + message);
		}
	}
	
	public void addMessage(String message){
		txtMessages.setText(txtMessages.getText() + message + "\n");
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
	
	public void setFocus(String name, int idFocus){
		setTitle(name);
		this.idFocus = idFocus;
	}
	
	public int getIdFocus() {
		return idFocus;
	}
}
