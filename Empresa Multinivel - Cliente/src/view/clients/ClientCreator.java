package view.clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import models.entities.Gender;
import persistence.PropertiesTitles;
import view.components.DatePanel;
import view.properties.ConstantGUI;

public class ClientCreator extends JDialog {

	private static final long serialVersionUID = 2978371672516077033L;
	private ActionListener actionListener;
	private PropertiesTitles propertiesTitles;
	private JButton btnCreate, btnCancel;
	private JPanel panelData;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblLastname;
	private JTextField txtLastName;
	private JLabel lblGender;
	private JComboBox<Gender> boxGenders;
	private JLabel lblBirthdate;
	private DatePanel panelBirthDate;
	private JButton btnImage;
	private JLabel lblIdLegal;
	private JTextField txtIdLegal;
	
	public ClientCreator(ActionListener actionListener, PropertiesTitles propertiesTitles) {
		this.actionListener = actionListener;
		this.propertiesTitles = propertiesTitles;
		initProperties();
		initComponents();
	}

	public void loadTexts() {
		lblName.setText(propertiesTitles.getProperty(ConstantGUI.T_NAME));
		setTitle(propertiesTitles.getProperty(ConstantGUI.T_USER_CREATOR));
		lblGender.setText(propertiesTitles.getProperty(ConstantGUI.T_GENDER));
		btnCancel.setText(propertiesTitles.getProperty(ConstantGUI.T_CANCEL));
		btnCreate.setText(propertiesTitles.getProperty(ConstantGUI.T_REGISTER));
		lblLastname.setText(propertiesTitles.getProperty(ConstantGUI.T_LAST_NAME));
		lblBirthdate.setText(propertiesTitles.getProperty(ConstantGUI.T_BIRTH_DATE));
		lblIdLegal.setText(propertiesTitles.getProperty(ConstantGUI.T_IDENTIFICATION_DOCUMENT));
	}

	private void initProperties() {
		setModal(true);
		setSize(300, 400);
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		panelData = new JPanel();
		panelData.setPreferredSize(new Dimension(100, 10));
		panelData.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelData.setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().add(panelData, BorderLayout.CENTER);
		
		lblIdLegal = new JLabel();
		panelData.add(lblIdLegal);
		
		txtIdLegal = new JTextField();
		panelData.add(txtIdLegal);
		
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
		
		btnImage = new JButton();
		btnImage.setBorder(null);
		btnImage.setFocusable(false);
		btnImage.setIcon(ConstantGUI.ICON_CLIENT_EMPTY);
		btnImage.setContentAreaFilled(false);
		getContentPane().add(btnImage, BorderLayout.NORTH);
		
		JPanel panelControls = new JPanel();
		getContentPane().add(panelControls, BorderLayout.SOUTH);
		
		btnCreate = new JButton();
		btnCreate.addActionListener(actionListener);
		btnCreate.setActionCommand(ConstantGUI.C_REGISTER);
		panelControls.add(btnCreate);
		
		btnCancel = new JButton();
		btnCancel.addActionListener(actionListener);
		btnCancel.setActionCommand(ConstantGUI.C_CLIENT_CREATOR_CLOSE);
		panelControls.add(btnCancel);
	}

}