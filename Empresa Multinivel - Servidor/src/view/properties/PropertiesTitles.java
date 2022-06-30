package view.properties;

import java.io.IOException;
import java.util.Properties;

public class PropertiesTitles extends Properties{

	private static final long serialVersionUID = 815499522311784108L;
	private Language language;

	public PropertiesTitles() {
	}
	
	public void loadLanguage(Language language) throws IOException{
		this.language = language;
		switch (language) {
		case ENGLISH: getProperties("English.properties"); break;
		case ESPANIOL: getProperties("Espaniol.properties"); break;
		default: break;
		}
	}
	
	private void getProperties(String file) throws IOException{
		load(getClass().getResourceAsStream(file));
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public enum Language{
		ENGLISH, ESPANIOL;
	}
}
