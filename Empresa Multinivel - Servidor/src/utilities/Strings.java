package utilities;

import java.util.Date;

import models.entities.Gender;
import models.entities.Message;
import models.entities.Order;
import models.entities.Person;
import models.entities.Product;
import models.entities.Status;

public class Strings {
	
	public static Integer getFirstNum(String string){
		String nums = "0123456789";
		String num = "";
		for (int i = 0; i < string.length(); i++) {
			char charAux = string.charAt(i);
			if (!nums.contains(""+charAux)) {
				break;
			}else{
				num += charAux;
			}
		}
		return (nums.equals("") ? null : Integer.parseInt(num));
	}
	
	public static Message toMessage(String string) {
		String[] data = string.split("_");
		return new Message(Integer.parseInt(data[0]), data[1]);
	}

	public static String toString(Message message){
		return message.getId() + "_" + message.getMessage();
	}
	
	@SuppressWarnings("deprecation")
	public static Order toOrder(String string) {
		try {
			String[] data = string.split(",");
			int registerID = Integer.parseInt(data[0]);
			Date date = new Date(data[1]);
			long idMember = Long.parseLong(data[2]);
			int codeProduct = Integer.parseInt(data[3]);
			int amount = Integer.parseInt(data[4]);
			int state = Integer.parseInt(data[5]);
			return new Order(registerID , date, idMember, codeProduct , amount , state);
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String toString(Order order){
		String registerID = ""+order.getRegisterID();
		Date date = order.getDate();
		String sDate = date.getDate() + "/" + date.getMonth() + "/" + date.getYear();
		String idMember = ""+order.getIdMember();
		String codeProduct = ""+order.getCodeProduct();
		String amount = ""+order.getAmount();
		String state = ""+order.getState();
		return registerID + "," + sDate + "," + idMember + "," + codeProduct + "," + amount + "," + state;
	}
	
	@SuppressWarnings("deprecation")
	public static Person toPerson(String text){
		try {
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
		} catch (Exception e) {
			System.out.println("error");
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String toString(Person person){
		try {
			Date date = person.getRegisterDate();
			String sDate = date.getDate() + "/" + date.getMonth() + "/" + date.getYear();
			Gender[] genders = Gender.values();
			Gender gender = person.getGender();
			int nGender = -1;
			for (int i = 0; i < genders.length; i++) {
				if (genders[i].equals(gender)) {
					nGender = i;
					break;
				}
			}
			Date birthDate = person.getBirthDate();
			String sBirthDate = birthDate.getDate() + "/" + birthDate.getMonth() + "/" + birthDate.getYear();
			Status state = person.getStatus();
			int nStatus = -1;
			Status[] states = Status.values();
			for (int i = 0; i < states.length; i++) {
				if (states[i].equals(state)) {
					nStatus = 0;
					break;
				}
			}
			return person.getId() + "," + sDate + "," + person.getIdlegal() + "," + person.getName() 
			+ "," + person.getLastName() + "," + nGender + "," + sBirthDate + "," + nStatus + "," + person.getParent();
		} catch (Exception e) {
			return null;
		}
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