package view.persons;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import controllers.Controller;
import view.components.DatePanel;
import view.components.JPanelBackground;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;
import models.entities.Gender;
import models.entities.Person;
import models.entities.Status;

import java.awt.Dimension;
import java.util.Date;

public class PersonCreatorDialog extends JDialog {

	private static final long serialVersionUID = 1886055394738118633L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JButton btnCreate, btnCancel;
	private JPanel panelImage;
	private JPanel panelData;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblLastname;
	private JTextField txtLastName;
	private JLabel lblGender;
	private JComboBox<Gender> boxGenders;
	private JLabel lblBirthdate;
	private DatePanel panelBirthDate;
	private JScrollPane scrollPane;
	private Person auxPerson;
	
	public PersonCreatorDialog(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
	}

	public void loadTexts() {
		setTitle(propertiesTitles.getProperty(ConstantGUI.T_PERSON_CREATOR));
		btnCreate.setText(propertiesTitles.getProperty(ConstantGUI.T_EDIT));
		btnCancel.setText(propertiesTitles.getProperty(ConstantGUI.T_CANCEL));
		lblName.setText(propertiesTitles.getProperty(ConstantGUI.T_NAME));
		lblLastname.setText(propertiesTitles.getProperty(ConstantGUI.T_LAST_NAME));
		lblGender.setText(propertiesTitles.getProperty(ConstantGUI.T_GENDER));
		lblBirthdate.setText(propertiesTitles.getProperty(ConstantGUI.T_BIRTH_DATE));
	}

	private void initProperties() {
		setModal(true);
		setSize(450, 300);
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		panelImage = new JPanelBackground("src\\media\\img\\users\\userX512.png");
		panel.add(panelImage);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		panel.add(scrollPane);
		
		panelData = new JPanel();
		panelData.setPreferredSize(new Dimension(100, 10));
		panelData.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelData.setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane.setViewportView(panelData);
		
		lblName = new JLabel();
		panelData.add(lblName);
		
		txtName = new JTextField();
		panelData.add(txtName);
		
		lblLastname = new JLabel();
		panelData.add(lblLastname);
		
		txtLastName = new JTextField();
		panelData.add(txtLastName);
		
		lblGender = new JLabel();
		panelData.add(lblGender);
		
		boxGenders = new JComboBox<Gender>();
		Gender[] listGenders = Gender.values();
		for (Gender gender : listGenders) {
			boxGenders.addItem(gender);
		}
		panelData.add(boxGenders);
		
		lblBirthdate = new JLabel();
		panelData.add(lblBirthdate);
		
		panelBirthDate = new DatePanel();
		panelData.add(panelBirthDate);
		
		JPanel panelControls = new JPanel();
		getContentPane().add(panelControls, BorderLayout.SOUTH);
		
		btnCreate = new JButton();
		btnCreate.addActionListener(controller);
		btnCreate.setActionCommand(ConstantGUI.C_EDIT_USER);
		panelControls.add(btnCreate);
		
		btnCancel = new JButton();
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panelControls.add(btnCancel);
	}

	public void setPerson(Person person) {
		this.auxPerson = person;
		txtName.setText(person.getName());
		txtLastName.setText(person.getLastName());
		boxGenders.setSelectedItem(person.getGender());
	}

	public Person getPerson() {
		int idParent = 0;
		if (this.auxPerson != null) {
			idParent = this.auxPerson.getParent();
		}
		return new Person(0, 0, txtName.getText(), txtLastName.getText(), (Gender)boxGenders.getSelectedItem(),
				panelBirthDate.getDate(), Status.ENABLED, idParent, new Date());
	}

	public void setEditorMode() {
		btnCreate.setActionCommand(ConstantGUI.C_EDIT_USER);
		btnCreate.setText(propertiesTitles.getProperty(ConstantGUI.T_EDIT));
	}

	public void setCreatorMode() {
		btnCreate.setActionCommand(ConstantGUI.C_CREATE_USER);
		btnCreate.setText(propertiesTitles.getProperty(ConstantGUI.T_CREATE));
	}	

}