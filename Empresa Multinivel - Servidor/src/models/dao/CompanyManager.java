package models.dao;

import java.util.Comparator;

import models.entities.Person;
import persistence.DAOPerson;
import structures.Graph;
import structures.Node;
import structures.SNode;
import structures.SimpleList;
import structures.Vertex;

public class CompanyManager{

	private Graph<Person> persons;
	
	public CompanyManager() {
		this.persons = new Graph<Person>(new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return person1.getId() - person2.getId();
			}
		});
	}

	public void addPerson(Person person){
		persons.add(person);
	}
	
	public void addMembers(Person person, Node<Person> nodeMembers){
		Vertex<Person> vertex = persons.getVertex(person);
		Node<Person> auxNode = nodeMembers;
		while (auxNode != null) {
			Vertex<Person> auxVertex = persons.getVertex(auxNode.getInfo());
			if (auxVertex != null) {
				vertex.add(auxVertex);
			}
			auxNode = auxNode.getNext();
		}
	}
	
	public static void main(String[] args) {
		DAOPerson daoPerson = new DAOPerson();
		Node<Person> node = daoPerson.loadPerson(123);
		CompanyManager companyManager = new CompanyManager();
		Person person = node.getInfo();
		companyManager.addPerson(person);
		Node<Person> nodeMember = node.getFirst();
		Person member = null;
		while (nodeMember != null) {
			member = nodeMember.getInfo();
			companyManager.addPerson(member);
			nodeMember = nodeMember.getNext();
		}
		companyManager.addMembers(person, node.getFirst());
	}
	
	public void printPersons(){
		SimpleList<Vertex<Person>> list = persons.getVertexs();
		SNode<Vertex<Person>> nodeVertex = list.getHead();
		System.out.println("hola");
		while (nodeVertex != null) {
			System.out.println(nodeVertex.getInfo().getInfo());
			nodeVertex = nodeVertex.getNext();
		}
	}
}