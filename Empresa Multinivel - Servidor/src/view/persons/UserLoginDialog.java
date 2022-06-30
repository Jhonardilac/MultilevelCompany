package view.persons;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JLabel;

import structures.Queeue;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;
import controllers.Controller;

import javax.swing.JComboBox;

import models.entities.Person;

import java.awt.Cursor;

public class UserLoginDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = -2503874898565687795L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JLabel lblDialog;
	private JButton btnAccept, btnCancel;
	private JToggleButton btnCompany, btnUsers;
	private JPanel panel;
	private JPanel panelOptions;
	private JPanel panelExtra;
	private JComboBox<Person> boxUsers;
	private Person itemCompany;
	private JButton btnCreateUser;
	
	public UserLoginDialog(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
		loadTexts();
	}

	public void loadTexts() {
		setTitle(propertiesTitles.getProperty(ConstantGUI.T_USER_LOGIN));
		lblDialog.setText(propertiesTitles.getProperty(ConstantGUI.D_USER_LOGIN) + ":");
		btnAccept.setText(propertiesTitles.getProperty(ConstantGUI.T_ACCEPT));
		btnCancel.setText(propertiesTitles.getProperty(ConstantGUI.T_CANCEL));
	}

	private void initProperties() {
		setModal(true);
		setSize(640, 281);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

	private void initComponents() {
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		panel.setLayout(new BorderLayout(0, 0));
		
		panelExtra = new JPanel();
		panel.add(panelExtra, BorderLayout.SOUTH);
		
		boxUsers = new JComboBox<Person>();
		
		panelOptions = new JPanel();
		panel.add(panelOptions);
		
		btnCompany = new JToggleButton();
		btnCompany.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCompany.setFocusable(false);
		btnCompany.addActionListener(this);
		btnCompany.setIcon(new ImageIcon("src\\media\\img\\company\\companyX128.png"));
		buttonGroup.add(btnCompany);
		panelOptions.add(btnCompany);
		
		btnUsers = new JToggleButton();
		btnUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsers.setFocusable(false);
		btnUsers.addActionListener(this);
		btnUsers.setIcon(new ImageIcon("src\\media\\img\\users\\userX128.png"));
		buttonGroup.add(btnUsers);
		panelOptions.add(btnUsers);
		
		btnCreateUser = new JButton();
		btnCreateUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreateUser.setFocusable(false);
		btnCreateUser.setFocusable(false);
		btnCreateUser.setIcon(new ImageIcon("src\\media\\img\\extras\\addX128.png"));
		btnCreateUser.addActionListener(controller);
		btnCreateUser.setActionCommand(ConstantGUI.C_PERSON_CREATOR_OPEN);
		panelOptions.add(btnCreateUser);
		
		JPanel panelControls = new JPanel();
		getContentPane().add(panelControls, BorderLayout.SOUTH);
		
		btnAccept = new JButton();
		btnAccept.addActionListener(controller);
		btnAccept.setActionCommand(ConstantGUI.C_LOAD_USER);
		btnAccept.setEnabled(false);
		panelControls.add(btnAccept);
		
		btnCancel = new JButton();
		btnCancel.addActionListener(controller);
		btnCancel.setActionCommand(ConstantGUI.C_EXIT);
		panelControls.add(btnCancel);
		
		JPanel panelHeader = new JPanel();
		getContentPane().add(panelHeader, BorderLayout.NORTH);
		
		lblDialog = new JLabel();
		panelHeader.add(lblDialog);
	}
	
	public void rePaint(){
		int width = getWidth();
		int height = getHeight();
		setSize(width + 1, height);
		setSize(width, height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(btnCompany)) {
			panelExtra.remove(boxUsers);
		}else if (src.equals(btnUsers)) {
			panelExtra.add(boxUsers);
		}
		btnAccept.setEnabled(true);
		rePaint();
	}
	
	public Person getPerson(){
		Person person = null;
		if (btnCompany.isSelected()) {
			person = itemCompany;
		}else if (btnUsers.isSelected()) {
			person = (Person) boxUsers.getSelectedItem();
		}
		return person;
	}

	public void setUsers(Person company, Queeue<Person> members) {
		boxUsers.removeAllItems();
		this.itemCompany = company;
		while (!members.isEmpty()) {
			Person person = members.get();
			boxUsers.addItem(person);
		}
	}

	public void addUser(Person person) {
//		boxUsers.addItem(person);
//		boxUsers.setSelectedItem(person);
	}
}