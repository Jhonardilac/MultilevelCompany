package view.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class NumberField extends JTextField implements KeyListener{

	private static final long serialVersionUID = 1723703816210584582L;

	public NumberField() {
		initProperties();
	}

	private void initProperties() {
		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) {
		String nums = "0123456789";
		if (!nums.contains("" + e.getKeyChar())) {
			e.consume();
		}
	}

}