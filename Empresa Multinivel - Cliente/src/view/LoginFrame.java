package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.Controller;
import persistence.PropertiesTitles;
import view.components.MenuBarLogin;
import view.components.NumberField;
import view.properties.ConstantGUI;
import java.awt.Font;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = -7798292689167507569L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JPanel panel;
	private JLabel lblID;
	private JButton btnLogin;
	private JButton btnClose;
	private JPanel panelData;
	private JTextField txtID;
	private JButton panelImage;
	private JButton buttonCreate;
	private MenuBarLogin menuBar;

	public LoginFrame(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
	}

	public void loadTexts() {
		menuBar.loadTexts();
		setTitle(propertiesTitles.getProperty(ConstantGUI.T_LOGIN));
		lblID.setText(propertiesTitles.getProperty(ConstantGUI.T_ID));
		btnClose.setText(propertiesTitles.getProperty(ConstantGUI.T_CLOSE));
		btnLogin.setText(propertiesTitles.getProperty(ConstantGUI.T_LOGIN));
		buttonCreate.setText(propertiesTitles.getProperty(ConstantGUI.T_CREATE_USER));
	}

	private void initProperties() {
		setSize(350, 275);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setIconImage(ConstantGUI.ICON_LOGIN_24.getImage());
	}

	private void initComponents() {
		menuBar = new MenuBarLogin(controller, propertiesTitles);
		setJMenuBar(menuBar);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panelImage = new JButton();
		panelImage.setBorder(null);
		panelImage.setFocusable(false);
		panelImage.setContentAreaFilled(false);
		panelImage.setIcon(ConstantGUI.ICON_CLIENT);
		panel.add(panelImage);
		
		panelData = new JPanel();
		panelData.setOpaque(false);
		panelData.setBorder(new EmptyBorder(10, 20, 10, 20));
		panelData.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(panelData, BorderLayout.SOUTH);
		
		lblID = new JLabel();
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		panelData.add(lblID);
		
		txtID = new NumberField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtID.setHorizontalAlignment(SwingConstants.CENTER);
		txtID.addKeyListener(controller);
		panelData.add(txtID);
		
		JPanel panelControls = new JPanel();
		panelControls.setOpaque(false);
		getContentPane().add(panelControls, BorderLayout.SOUTH);
		
		btnLogin = new JButton();
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(controller);
		btnLogin.setActionCommand(ConstantGUI.C_LOGIN);
		panelControls.add(btnLogin);
		
		buttonCreate = new JButton();
		buttonCreate.setFocusable(false);
		buttonCreate.addActionListener(controller);
		buttonCreate.setActionCommand(ConstantGUI.C_CLIENT_CREATOR_OPEN);
		panelControls.add(buttonCreate);
		
		btnClose = new JButton();
		btnClose.setFocusable(false);
		btnClose.addActionListener(controller);
		btnClose.setActionCommand(ConstantGUI.C_EXIT);
		panelControls.add(btnClose);
	}
	
	public void clean(){
		txtID.setText("");
	}
	
	public String getClientName(){
		return txtID.getText();
	}
	
	public void setAuthenticationMode() {
		txtID.setEnabled(false);
		btnLogin.setEnabled(false);
		buttonCreate.setEnabled(false);
		btnLogin.setIcon(ConstantGUI.ICON_LOADING_16);
		btnLogin.setText(propertiesTitles.getProperty(ConstantGUI.T_AUTHENTICATING) + "...");
	}
	
	public void setAddress(String address) {
		menuBar.setAddress(address);
	}
	
	public void setLoadingMode(){
		txtID.setEnabled(false);
		btnLogin.setEnabled(false);
		buttonCreate.setEnabled(false);
		btnLogin.setIcon(ConstantGUI.ICON_LOADING_16);
		btnLogin.setText(propertiesTitles.getProperty(ConstantGUI.T_LOADING) + "...");
	}
	
	public void setNormalMode(){
		txtID.setEnabled(true);
		btnLogin.setIcon(null);
		buttonCreate.setEnabled(true);
		btnLogin.setEnabled(true);
		btnLogin.setText(propertiesTitles.getProperty(ConstantGUI.T_LOGIN));
	}

	public void setPort(int port) {
		menuBar.setPort(port);
	}
	
	@Override
	public void setVisible(boolean b) {
		clean();
		super.setVisible(b);
	}
}