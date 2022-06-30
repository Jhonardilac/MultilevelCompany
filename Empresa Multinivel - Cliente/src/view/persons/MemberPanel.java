package view.persons;

import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;

import models.entities.Person;
import persistence.PropertiesTitles;

import java.awt.Dimension;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.Controller;
import view.components.JPanelBackground;
import view.properties.ConstantGUI;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MemberPanel extends JPanel implements MouseListener{

	private static final long serialVersionUID = -6726574966736902589L;
	private Controller controller;
	private PropertiesTitles propertiesTitles;
	private JLabel lblName;
	private JPanelBackground panelImage;
	private JPanel panelControls;
	private JLabel lblStatus;
	private int idMember;
	private String nameMember;

	public MemberPanel(Controller controller) {
		this.controller = controller;
		this.propertiesTitles = controller.getPropertiesTitles();
		initProperties();
		initComponents();
	}

	private void initProperties() {
		addMouseListener(this);
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(1, 1, 1, 1));
	}
	
	public void loadText() {
	}

	private void initComponents() {
		JPanel panelData = new JPanel();
		panelData.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelData.setLayout(new GridLayout(0, 1, 0, 0));
		add(panelData, BorderLayout.CENTER);
		
		lblName = new JLabel();
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
		panelControls.setLayout(new BorderLayout(0, 0));
		add(panelControls, BorderLayout.EAST);
		
		panelImage = new JPanelBackground(ConstantGUI.ICON_CLIENT_PATH);
		panelImage.setPreferredSize(new Dimension(80, 70));
		add(panelImage, BorderLayout.WEST);
	}
	
	public void setSimpleMode(){
		remove(panelControls);
	}

	public void setPerson(Person person){
		if (person != null) {
			this.idMember = person.getId();
			this.nameMember = person.getName();
			lblName.setText(nameMember);
			lblStatus.setText(propertiesTitles.getProperty("T_"+person.getStatus()));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { 
		if (e.getClickCount() == 2) {
			controller.openChatWindow(nameMember, idMember);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBorder(new EmptyBorder(1, 1, 1, 1));
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }
}