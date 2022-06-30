package view.components;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JSpinner;
import javax.swing.JComboBox;

import java.awt.GridLayout;


import models.entities.Month;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Date;
import java.awt.Dimension;

public class DatePanel extends JPanel {

	private static final long serialVersionUID = 7351833915526271506L;
	private JSpinner spnYears;
	private JComboBox<Month> boxMonths;
	private JComboBox<Integer> boxDays;
	
	public DatePanel() {
		setPreferredSize(new Dimension(200, 50));
		initProperties();
		initComponents();
	}

	private void initProperties() {
		setLayout(new BorderLayout(0, 0));
	}

	private void initComponents() {
		JPanel panelValues = new JPanel();
		add(panelValues, BorderLayout.CENTER);
		panelValues.setLayout(new GridLayout(0, 3, 0, 0));
		
		spnYears = new JSpinner();
		panelValues.add(spnYears);
		
		boxMonths = new JComboBox<Month>();
		boxMonths.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int days = ((Month)boxMonths.getSelectedItem()).getDays((int)spnYears.getValue());
				boxDays.removeAllItems();
				for (int i = 1; i <= days; i++) {
					boxDays.addItem(i);
				}
			}
		});
		boxDays = new JComboBox<Integer>();
		Month[] months = Month.values();
		for (Month month : months) {
			boxMonths.addItem(month);
		}
		panelValues.add(boxMonths);
		panelValues.add(boxDays);
	}
	
	@SuppressWarnings("deprecation")
	public Date getDate(){
		int year = (int) spnYears.getValue();
		int month = boxMonths.getSelectedIndex() + 1;
		int day = boxDays.getSelectedIndex();
		return new Date(year, month, day);
	}

}