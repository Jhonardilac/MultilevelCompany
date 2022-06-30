package utilities;

import java.util.Date;

import models.entities.Gender;
import models.entities.Message;
import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import models.entities.Status;

public class Strings {
	
	public static Message toMessage(String string) {
		String[] data = string.split("_");
		return new Message(Integer.parseInt(data[0]), data[1]);
	}
	
	public static String toString(Message message){
		return message.getId() + "_" + message.getMessage();
	}

	@SuppressWarnings("deprecation")
	public static Person toPerson(String text){
		String[] data = text.split(",");
		int id = Integer.parseInt(data[0]);
		Date registerDate = new Date(data[1]);
		long idLegal = Long.parseLong(data[2]);
		String name = data[3];
		String lastName = data[4];
		Gender gender;
		try {
			gender = Gender.values()[Integer.parseInt(data[5])];
		} catch (ArrayIndexOutOfBoundsException e) {
			gender = Gender.MALE;
		}
		Date birthDate = new Date(data[6]);
		Status status;
		try {
			status = Status.values()[Integer.parseInt(data[7])];
		} catch (ArrayIndexOutOfBoundsException e) {
			status = Status.DISABLED;
		}
		int parent = Integer.parseInt(data[8]);
		return new Person(id, idLegal, name, lastName, gender, birthDate, status, parent, registerDate);
	}
	
	@SuppressWarnings("deprecation")
	public static Order toOrder(String string) {
		String[] data = string.split(",");
		int registerID = Integer.parseInt(data[0]);
		Date date = new Date(data[1]);
		long idMember = Long.parseLong(data[2]);
		int codeProduct = Integer.parseInt(data[3]);
		int amount = Integer.parseInt(data[4]);
		int state = Integer.parseInt(data[5]);
		return new Order(registerID , date, idMember, codeProduct , amount , state);
	}
	
	public static Product toProduct(String string) {
		try {
			String[] data = string.split(",");
			int ref = Integer.parseInt(data[0]);
			String line = data[1];
			String reference = data[2];
			Gender target = null;
			switch (data[3].toUpperCase()) {
			case "HOMBRE": target = Gender.MALE; break;
			case "MUJER": target = Gender.FEMALE; break;
			default: target = Gender.FEMALE; break;
			}
			double price = Double.parseDouble(data[4]);
			return new Product(ref, line, reference, target, price);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String toString(Product product){
		if (product != null) {
			int ref = product.getRef();
			String line = product.getLine();
			String reference = product.getReference();
			String target = product.getTarget().equals(Gender.MALE) ? "Hombre" : "Mujer";
			double price = product.getPrice();
			return ref + "," + line + "," + reference + "," + target + "," + price;
		}
		return null;
	}

}