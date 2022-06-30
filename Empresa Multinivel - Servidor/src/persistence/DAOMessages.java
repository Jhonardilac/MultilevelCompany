package persistence;

import java.io.File;
import java.io.IOException;

import models.entities.Message;
import utilities.Strings;


public class DAOMessages {

	private static final String MESSAGE_PATH = "src\\data\\";
	
	public void saveMessage(int to, Message message) throws IOException {
		String path = MESSAGE_PATH + to;
		File file = FilesManager.searchFile(path);
		if (file == null) {
			FilesManager.write(Strings.toString(message), path);
		}else{
			String[] lines = FilesManager.read(path);
			String messages = "";
			for (String string : lines) {
				if (!string.equals("")) {
					messages += string + "\n";
				}
			}
			messages += Strings.toString(message);
			FilesManager.write(messages, path);
		}
	}

	public Message[] loadMessages(int id) {
		try {
			String[] lines = FilesManager.read(MESSAGE_PATH + id);
			Message[] messages = new Message[lines.length];
			for (int i = 0; i < lines.length; i++) {
				if (!lines[i].equals("") || !lines[i].equals("\n")) {
					String[] data = lines[i].split("_");
					messages[i] = new Message(Integer.parseInt(data[0]), data[1]);
				}
			}
			return messages;
		} catch (IOException e) {
		}
		return null;
	}
}