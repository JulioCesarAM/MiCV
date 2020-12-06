package dad.javafx.micv.model.contacto;

import javafx.beans.property.StringProperty;

public class Web {
	private StringProperty url;
	Web(){}
	public StringProperty urlProperty() {
		return this.url;
	}
	
	public String getUrl() {
		return this.urlProperty().get();
	}
	
	public void setUrl(final String url) {
		this.urlProperty().set(url);
	}
	

}
