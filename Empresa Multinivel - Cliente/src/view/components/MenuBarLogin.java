package view.components;

import javax.swing.JMenuBar;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import persistence.PropertiesTitles;
import view.properties.ConstantGUI;

public class MenuBarLogin extends JMenuBar {

	private static final long serialVersionUID = 5677043096941758291L;
	private ActionListener actionListener;
	private PropertiesTitles propertiesTitles;
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JMenu mnConnection;
	private JMenuItem mntmChangePort;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenuItem mntmChangeAddress;
	private String address;
	private int port;

	public MenuBarLogin(ActionListener actionListener, PropertiesTitles propertiesTitles) {
		this.actionListener = actionListener;
		this.propertiesTitles = propertiesTitles;
		initProperties();
		initComponents();
	}

	public void loadTexts() {
		mnFile.setText(propertiesTitles.getProperty(ConstantGUI.T_FILE));
		mnHelp.setText(propertiesTitles.getProperty(ConstantGUI.T_HELP));
		mntmExit.setText(propertiesTitles.getProperty(ConstantGUI.T_EXIT));
		mntmAbout.setText(propertiesTitles.getProperty(ConstantGUI.T_ABOUT) + "...");
		mnConnection.setText(propertiesTitles.getProperty(ConstantGUI.T_CONNECTION));
		mntmChangePort.setText(propertiesTitles.getProperty(ConstantGUI.T_CHANGE_PORT) + " (" + port + ")");
		mntmChangeAddress.setText(propertiesTitles.getProperty(ConstantGUI.T_CHANGE_ADDRESS) + " (" + address + ")");
	}

	private void initProperties() {
		// TODO Auto-generated method stub
		
	}

	private void initComponents() {
		mnFile = new JMenu();
		add(mnFile);
		
		mntmExit = new JMenuItem();
		mntmExit.addActionListener(actionListener);
		mntmExit.setActionCommand(ConstantGUI.C_EXIT);
		mnFile.add(mntmExit);
		
		mnConnection = new JMenu();
		add(mnConnection);
		
		mntmChangeAddress = new JMenuItem();
		mntmChangeAddress.addActionListener(actionListener);
		mntmChangeAddress.setActionCommand(ConstantGUI.C_CHANGE_ADDRESS);
		mnConnection.add(mntmChangeAddress);
		
		mntmChangePort = new JMenuItem();
		mntmChangePort.addActionListener(actionListener);
		mntmChangePort.setActionCommand(ConstantGUI.C_CHANGE_PORT);
		mnConnection.add(mntmChangePort);
		
		mnHelp = new JMenu();
		add(mnHelp);
		
		mntmAbout = new JMenuItem();
		mntmAbout.addActionListener(actionListener);
		mntmAbout.setActionCommand(ConstantGUI.C_SHOW_ABOUT);
		mnHelp.add(mntmAbout);
	}

	public void setAddress(String address) {
		this.address = address;
		mntmChangeAddress.setText(propertiesTitles.getProperty(ConstantGUI.T_CHANGE_ADDRESS) + " (" + address + ")");
	}

	public void setPort(int port) {
		this.port = port;
		mntmChangePort.setText(propertiesTitles.getProperty(ConstantGUI.T_CHANGE_PORT) + " (" + port + ")");
	}

}
