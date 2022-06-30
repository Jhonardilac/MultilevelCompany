package models.entities;

import java.util.Date;

import structures.SNode;
import structures.SimpleList;

/**
 * Esta clase encapsula atributos de una persona, como lo es:
 * una identificacion, un nombre, un apellido, un genero, la fecha
 * de nacimiento, un estatus (ver mas en {@link Status}), una 
 * identificacion de un pariente y una fecha de creacion de la misma
 * clase {@link Person}.
 * @author fredy
 *
 */
public class Person {

	private int id;
	private long idlegal;
	private String name;
	private String lastName;
	private Gender gender;
	private Date birthDate;
	private Status status;
	private int parent;
	private Date registerDate;
	private PersonType personType;
	private SimpleList<Person> members;
	private SimpleList<Product> products;
	
	public Person(int id, long idlegal, String name, String lastName,
			Gender gender, Date birthDate, Status status, int parent,
			Date registerDate) {
		super();
		this.id = id;
		this.idlegal = idlegal;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.status = status;
		this.parent = parent;
		this.registerDate = registerDate;
		this.members = new SimpleList<Person>();
		this.products = new SimpleList<Product>();
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", idlegal=" + idlegal + ", name=" + name + ", lastName=" + lastName + ", gender="
				+ gender + ", birthDate=" + birthDate + ", status=" + status + ", parent=" + parent + ", registerDate="
				+ registerDate + ", personType=" + personType + "]";
	}
	
	public void addMember(Person person){
		members.add(person);
	}
	
	public void addProduct(Product product){
		products.add(product);
	}
	
	public Person getMember(int id){
		SNode<Person> node = members.getHead();
		Person person = null;
		while (node != null) {
			person = node.getInfo();
			if (person.getId() == id) {
				return person;
			}
			node = node.getNext();
		}
		return null;
	}
	
	public Product searchProduct(int ref){
		SNode<Product> node = products.getHead();
		Product product = null;
		while (node != null) {
			product = node.getInfo();
			if (product.getRef() == ref) {
				return product;
			}
			node = node.getNext();
		}
		return null;
	}
	
	public void removeProduct(int ref){
		Product product = searchProduct(ref);
		if (product != null) {
			products.remove(product);
		}
	}
	public void removeProduct(Product product){
		products.remove(product);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public PersonType getPersonType() {
		return personType;
	}
	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}
	public long getIdlegal() {
		return idlegal;
	}
	public void setIdlegal(long idlegal) {
		this.idlegal = idlegal;
	}
	public SimpleList<Person> getMembers() {
		return members;
	}
	public void setMembers(SimpleList<Person> members) {
		this.members = members;
	}
	public SimpleList<Product> getProducts() {
		return products;
	}
}