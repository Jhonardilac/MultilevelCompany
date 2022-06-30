package view.components;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.Controller;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -8353685387402474325L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JMenu mnFile, mnHelp;
	private JMenu mnMenu;
	private JMenuItem mntmExit;
	
	public MenuBar(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
		loadTexts();
	}

	public void loadTexts() {
		mnFile.setText(propertiesTitles.getProperty(ConstantGUI.T_FILE));
		mnHelp.setText(propertiesTitles.getProperty(ConstantGUI.T_HELP));
		mntmExit.setText(propertiesTitles.getProperty(ConstantGUI.T_EXIT));
	}

	private void initProperties() {
		// TODO Auto-generated method stub
		
	}

	private void initComponents() {
		mnMenu = new JMenu();
		mnMenu.setIcon(new ImageIcon("src\\media\\img\\extras\\menuX16.png"));
		this.add(mnMenu);
		
		mntmExit = new JMenuItem();
		mntmExit.addActionListener(controller);
		mntmExit.setActionCommand(ConstantGUI.C_EXIT);
		mntmExit.setIcon(new ImageIcon("src\\media\\img\\extras\\exitX16.png"));
		mnMenu.add(mntmExit);
		
		mnFile = new JMenu();
		this.add(mnFile);
		
		mnHelp = new JMenu();
		this.add(mnHelp);
	}

}
