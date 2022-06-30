package view.persons;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import models.entities.Person;
import structures.Queeue;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class PersonChooserDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = -1719195415590794407L;
	private PropertiesTitles propertiesTitles;
	private JLabel lblMessage;
	private JComboBox<Person> boxPersons;
	private JPanel panel;
	private JPanel panelControls;
	private JButton btnAccept;
	private JButton btnCancel;
	private static Person personToReturn;

	public PersonChooserDialog(PropertiesTitles propertiesTitles, String message, Queeue<Person> listPersons) {
		this.propertiesTitles = propertiesTitles;
		initProperties();
		initComponents();
		loadTexts();
		lblMessage.setText(message);
		setPersons(listPersons);
	}
	
	public void loadTexts() {
		btnAccept.setText(propertiesTitles.getProperty(ConstantGUI.T_ACCEPT));
		btnCancel.setText(propertiesTitles.getProperty(ConstantGUI.T_CANCEL));
	}

	private void initProperties() {
		setModal(true);
		setSize(442, 140);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

	private void initComponents() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panelMessage = new JPanel();
		panelMessage.setLayout(new BorderLayout(0, 0));
		panel.add(panelMessage);
		
		lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setHorizontalTextPosition(SwingConstants.CENTER);
		panelMessage.add(lblMessage, BorderLayout.SOUTH);
		
		JPanel panelOptions = new JPanel();
		panel.add(panelOptions);
		
		boxPersons = new JComboBox<Person>();
		panelOptions.add(boxPersons);
		
		panelControls = new JPanel();
		getContentPane().add(panelControls, BorderLayout.SOUTH);
		
		btnAccept = new JButton();
		btnAccept.addActionListener(this);
		panelControls.add(btnAccept);
		
		btnCancel = new JButton();
		btnCancel.addActionListener(this);
		panelControls.add(btnCancel);
	}
	
	private void setPersons(Queeue<Person> listPersons){
		Person person = listPersons.get();
		while (person != null) {
			boxPersons.addItem(person);
			person = listPersons.get();
		}
	}
	
	private Person getPerson(){
		return (Person)boxPersons.getSelectedItem();
	}
	
	public static Person open(PropertiesTitles propertiesTitles, String message, Queeue<Person> listPersons){
		PersonChooserDialog personChooserDialog = new PersonChooserDialog(propertiesTitles, message, listPersons);
		personChooserDialog.setVisible(true);
		return personToReturn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(btnAccept)) {
			personToReturn = getPerson();
		}else if (src.equals(btnCancel)) {
			personToReturn = null;
		}
		setVisible(false);
	}
}