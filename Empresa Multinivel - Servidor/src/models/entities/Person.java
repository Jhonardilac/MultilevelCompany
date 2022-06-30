package models.entities;

import java.util.Comparator;
import java.util.Date;

import structures.Queeue;
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
	private SimpleList<Order> orders;
	private Comparator<Order> comparatorOrders;
	
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
		this.orders = new SimpleList<Order>(null);
	}

	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void addOrders(Queeue<Order> orders){
		Order order = orders.get();
		while (order != null) {
			this.orders.add(order);
			order = orders.get();
		}
	}
	
	public void removeOrders(){
	}

	@Override
	public String toString() {
		return name;
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
	public Queeue<Order> getOrders() {
		Queeue<Order> orders = new Queeue<Order>();
		SNode<Order> order = this.orders.getHead();
		while (order != null) {
			orders.put(order.getInfo());
			order = order.getNext();
		}
		return orders;
	}
	public void setOrders(SimpleList<Order> orders) {
		this.orders = orders;
	}
	public Comparator<Order> getComparatorOrders() {
		return comparatorOrders;
	}
	public void setComparatorOrders(Comparator<Order> comparatorOrders) {
		this.comparatorOrders = comparatorOrders;
	}
	public long getIdlegal() {
		return idlegal;
	}
	public void setIdlegal(long idlegal) {
		this.idlegal = idlegal;
	}

	public void removeOrders(Queeue<Order> auxOrders) {
		SNode<Order> order = this.orders.getHead();
		while (order != null) {
			this.orders.remove(order.getInfo());
			order = order.getNext();
		}
	}
}