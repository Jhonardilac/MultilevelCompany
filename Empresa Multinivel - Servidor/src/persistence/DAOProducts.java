package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import models.entities.Product;
import structures.SimpleList;
import utilities.Strings;

public class DAOProducts {

	private static final String PRODUCT_PATH = "src\\data\\MultinivelProductos.csv";
	
	public SimpleList<Product> loadProducts() throws IOException{
		FileReader fileReader = new FileReader(new File(PRODUCT_PATH));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		SimpleList<Product> products = new SimpleList<Product>(null);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			Product product = Strings.toProduct(line);
			if (product != null) {
				products.insert(product);
			}
		}
		bufferedReader.close();
		fileReader.close();
		return products;
	}
}