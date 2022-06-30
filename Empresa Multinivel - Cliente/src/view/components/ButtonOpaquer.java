package view.components;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonOpaquer extends JButton {

	private static final long serialVersionUID = -1112138293577375761L;
	
	public ButtonOpaquer() {
		setBorder(new EmptyBorder(4, 4, 4, 4));
		setOpaque(false);
		setFocusable(false);
		setBackground(Color.LIGHT_GRAY);
		setContentAreaFilled(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setOpaque(true);
				setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setContentAreaFilled(false);
				setOpaque(false);
				setBackground(Color.LIGHT_GRAY);
			}
		});

	}

}
