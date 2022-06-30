package view.company;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
public class CompanyPanel extends JPanel {

	private static final long serialVersionUID = 2440430280286908597L;

	public CompanyPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSales = new JPanel();
		panel_1.add(panelSales);
		panelSales.setLayout(new BorderLayout(0, 0));
		
		JPanel panelDates = new JPanel();
		panelSales.add(panelDates, BorderLayout.SOUTH);
		
		JSpinner spnSalesYear = new JSpinner();
		panelDates.add(spnSalesYear);
	}

}
