package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.entities.Order;
import utilities.Strings;

public class FilesManager {

	public static String[] read(String path) throws IOException{
		FileReader fileReader = new FileReader(new File(path));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String text = "";
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			text += line + "\n";
		}
		bufferedReader.close();
		fileReader.close();
		return text.split("\n");
	}
	
	public static String[] searchLine(String path, int id) throws IOException{
		FileReader fileReader = new FileReader(new File(path));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String text = "";
		String auxLine = "";
		while ((auxLine = bufferedReader.readLine()) != null) {
			Order order = Strings.toOrder(auxLine);
			if (order != null) {
				if (order.getIdMember() == id) {
					text += auxLine + "\n";
					System.out.println(auxLine);
				}
			}
		}
		bufferedReader.close();
		fileReader.close();
		return text.split("\n");
	}

	public static void write(String text, String path) throws IOException{
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
		bufferedWriter.write(text);
		bufferedWriter.close();
	}

	public static File searchFile(String pathname) {
		File file = new File(pathname);
		return file.exists() ? file : null;
	}
}