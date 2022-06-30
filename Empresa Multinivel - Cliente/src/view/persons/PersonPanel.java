package view.persons;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import models.entities.Person;
import persistence.PropertiesTitles;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import view.components.ButtonOpaquer;
import view.components.JPanelBackground;
import view.properties.ConstantGUI;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.Color;

public class PersonPanel extends JPanel {

	private static final long serialVersionUID = -6726574966736902589L;
	private ActionListener actionListener;
	private PropertiesTitles propertiesTitles;
	private JButton btnEdit, btnLogOut;
	private JTextField lblName;
	private JPanelBackground panelImage;
	private JPanel panelControls;
	private JLabel lblStatus;
	private JPanel panelControlsSouth;
	private JButton btnNewMessages;
	private int amountMessages;

	public PersonPanel(ActionListener actionListener, PropertiesTitles propertiesTitles) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		this.actionListener = actionListener;
		this.propertiesTitles = propertiesTitles;
		initProperties();
		initComponents();
	}

	private void initProperties() {
		setLayout(new BorderLayout(0, 0));
	}
	
	public void loadText() {
		btnEdit.setToolTipText(propertiesTitles.getProperty(ConstantGUI.T_EDIT));
		btnLogOut.setToolTipText(propertiesTitles.getProperty(ConstantGUI.T_LOGOUT));
		btnNewMessages.setToolTipText(propertiesTitles.getProperty(ConstantGUI.T_MESSAGE_TRAY));
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		add(panel, BorderLayout.CENTER);
		
		JPanel panelData = new JPanel();
		panelData.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelData.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(panelData);
		
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
		btnEdit.addActionListener(actionListener);
		btnEdit.setIcon(ConstantGUI.ICON_EDIT_16);
		panelControlsSouth.add(btnEdit);
		
		btnLogOut = new ButtonOpaquer();
		btnLogOut.addActionListener(actionListener);
		btnLogOut.setIcon(ConstantGUI.ICON_LOGOUT_16);
		btnLogOut.setActionCommand(ConstantGUI.C_LOGOUT);
		panelControlsSouth.add(btnLogOut);
		
		btnNewMessages = new ButtonOpaquer();
		btnNewMessages.setBackground(Color.LIGHT_GRAY);
		btnNewMessages.addActionListener(actionListener);
		btnNewMessages.setIcon(ConstantGUI.ICON_MESSAGES);
		btnNewMessages.setActionCommand(ConstantGUI.C_MESSAGE_TRAY_OPEN);
		panelControls.add(btnNewMessages, BorderLayout.CENTER);
		
		panelImage = new JPanelBackground(ConstantGUI.ICON_CLIENT_EMPTY_PATH);
		panelImage.setPreferredSize(new Dimension(80, 70));
		add(panelImage, BorderLayout.WEST);
	}
	
	public void increaseMessages() {
		amountMessages++;
		btnNewMessages.setText(""+amountMessages);
		btnNewMessages.setBackground(Color.YELLOW);
		btnNewMessages.setContentAreaFilled(true);
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

	public void reduceMessage(int numberOfMessages) {
		amountMessages -= numberOfMessages;
		btnNewMessages.setText(""+amountMessages);
	}

	public void clean() {
		amountMessages = 0;
		btnNewMessages.setText("0");
	}
}