package view.products;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.entities.Product;
import structures.Queeue;

public class TableProducts extends JTable {

	private static final long serialVersionUID = 6541091170384262406L;
	private DefaultTableModel model;
	private String[] columnNames;
	
	public TableProducts() {
		model = new DefaultTableModel(null, columnNames);
		model.setColumnIdentifiers(new String[]{"Ref", "Line", "Reference", "Target", "Price"});
		setModel(model);
	}

	public void addProduct(Product product){
		model.addRow(product.getData());
	}
	
	public void setProducts(Queeue<Product> products){
		removeRows();
		if (products != null) {
			Product product = products.get();
			while (product != null) {
				model.addRow(product.getData());
				product = products.get();
			}
		}
	}
	
	public void removeRows(){
		model.setRowCount(0);
	}
	
	public void setSelectedMode(boolean enabled){
		if (enabled) {
			model.addColumn("Selected");
		}
	}
}