package persistence;

import java.io.IOException;
import java.util.Properties;

public class PropertiesTitles extends Properties{

	private static final long serialVersionUID = 815499522311784108L;
	private Language language;

	public PropertiesTitles() {
	}
	
	public void loadLanguage(Language language) throws IOException{
		switch (language) {
		case ENGLISH: getProperties("English.properties"); break;
		case SPANISH: getProperties("Spanish.properties"); break;
		default: break;
		}
		this.language = language;
	}
	
	private void getProperties(String file) throws IOException{
		load(getClass().getResourceAsStream(file));
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public enum Language{
		ENGLISH, SPANISH;

		public static int indexOf(Language language) {
			Language[] languages = values();
			for (int i = 0; i < languages.length; i++) {
				if (languages[i].equals(language)) {
					return i;
				}
			}
			return -1;
		}
	}
}
