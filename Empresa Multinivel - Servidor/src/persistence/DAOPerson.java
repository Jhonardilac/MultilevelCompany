package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import models.dao.CompanyManager;
import models.entities.Person;
import structures.Node;
import utilities.Strings;

public class DAOPerson {

	private static final String PERSONS_PATH = "src\\data\\multinivelSocios.csv";
	
	public Node<Person> loadPerson(int id){
		Node<Person> node = new Node<>(new Person(0, 0, null, null, null, null, null, 0, null));
		FileReader fileReader;
		try {
			fileReader = new FileReader(new File(PERSONS_PATH));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			try {
				bufferedReader.readLine();
				line = bufferedReader.readLine();
				while (line != null) {
					Person person = Strings.toPerson(line);
					if (person.getId() == id) {
						node.setInfo(person);
					}else if(person.getParent() == id) {
						node.setFirst(new Node<Person>(person, null, node.getFirst()));
					}
					line = bufferedReader.readLine();
				}
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return node;
	}

	public void loadPersons(CompanyManager companyManager) throws IOException {
		FileReader fileReader = new FileReader(new File(PERSONS_PATH));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		while (line != null) {
			Person person = Strings.toPerson(line);
			if (person != null) {
				companyManager.addPerson(person);
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		fileReader.close();
	}
}