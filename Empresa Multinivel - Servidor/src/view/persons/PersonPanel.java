package view.persons;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import controllers.Controller;
import models.entities.Person;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import view.components.ButtonOpaquer;
import view.components.JPanelBackground;
import view.properties.ConstantGUI;
import view.properties.PropertiesTitles;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

public class PersonPanel extends JPanel {

	private static final long serialVersionUID = -6726574966736902589L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JButton btnEdit, btnLogOut;
	private JTextField lblName;
	private JPanelBackground panelImage;
	private JPanel panelControls;
	private JLabel lblStatus;
	private JPanel panelControlsSouth;

	public PersonPanel(Controller controller, Person person) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
		setPerson(person);
	}

	private void initProperties() {
		setLayout(new BorderLayout(0, 0));
	}
	
	public void loadText() {
		btnEdit.setToolTipText(propertiesTitles.getProperty(ConstantGUI.T_EDIT));
		btnLogOut.setToolTipText(propertiesTitles.getProperty(ConstantGUI.T_LOGOUT));
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelData = new JPanel();
		panelData.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panelData);
		panelData.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblName = new JTextField();
		lblName.setColumns(8);
		lblName.setDisabledTextColor(Color.BLACK);
		lblName.setBorder(null);
		lblName.setOpaque(false);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setEnabled(false);
		panelData.add(lblName);
		
		lblStatus = new JLabel();
		lblStatus.setForeground(SystemColor.activeCaption);
		lblStatus.setVerticalAlignment(SwingConstants.TOP);
		panelData.add(lblStatus);
		
		panelControls = new JPanel();
		add(panelControls, BorderLayout.EAST);
		panelControls.setLayout(new BorderLayout(0, 0));
		
		panelControlsSouth = new JPanel();
		panelControls.add(panelControlsSouth, BorderLayout.SOUTH);
		panelControlsSouth.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnEdit = new ButtonOpaquer();
		btnEdit.addActionListener(controller);
		btnEdit.setActionCommand(ConstantGUI.C_PERSON_EDITOR_OPEN);
		btnEdit.setIcon(new ImageIcon("src\\media\\img\\extras\\editX16.png"));
		panelControlsSouth.add(btnEdit);
		
		btnLogOut = new ButtonOpaquer();
		btnLogOut.addActionListener(controller);
		btnLogOut.setActionCommand(ConstantGUI.C_LOGOUT);
		btnLogOut.setIcon(new ImageIcon("src\\media\\img\\extras\\logoutX16.png"));
		panelControlsSouth.add(btnLogOut);
		
		panelImage = new JPanelBackground("src\\media\\img\\users\\userX512.png");
		panelImage.setPreferredSize(new Dimension(70, 10));
		add(panelImage, BorderLayout.WEST);
	}
	
	public void setSimpleMode(){
		remove(panelControls);
	}

	public void setPerson(Person person){
		if (person != null) {
			lblName.setText(person.getName());
			lblStatus.setText(propertiesTitles.getProperty("T_"+person.getStatus()));
		}
	}
}