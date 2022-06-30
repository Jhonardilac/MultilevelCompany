package view.messages;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controllers.Controller;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Cursor;

public class MessagePanel extends JPanel implements MouseListener{

	private static final long serialVersionUID = -4643908625044542155L;
	private Controller controller;
	private int id;
	private String nameFocus;
	private JLabel lblFrom;
	private JTextArea txtMessage;
	private boolean viewed;
	private int newMessages;

	public MessagePanel(Controller controller) {
		this.controller = controller;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		initProperties();
		initComponents();
	}

	private void initProperties() {
		addMouseListener(this);
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(1, 1, 1, 1));
	}

	private void initComponents() {
		lblFrom = new JLabel();
		lblFrom.addMouseListener(this);
		lblFrom.setForeground(SystemColor.activeCaption);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblFrom, BorderLayout.NORTH);
		
		txtMessage = new JTextArea();
		txtMessage.setOpaque(false);
		txtMessage.setEditable(false);
		txtMessage.addMouseListener(this);
		txtMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(txtMessage, BorderLayout.CENTER);
	}

	public void addMessage(int id, String from, String message){
		this.id = id;
		this.nameFocus = from;
		lblFrom.setText(from);
		String text = txtMessage.getText();
		if (!text.equals("")) {
			message = "\n" + message;
		}
		txtMessage.setText(text + message);
		setBackground(Color.YELLOW);
		viewed = false;
		newMessages++;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isViewed() {
		return viewed;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		controller.openChatWindow(nameFocus, id);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setBackground(null);
		if (viewed == false) {
			controller.reduceNumberOfMessages(newMessages);
			newMessages = 0;
		}
		viewed = true;
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