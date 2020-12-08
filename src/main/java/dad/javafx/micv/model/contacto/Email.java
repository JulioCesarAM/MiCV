package dad.javafx.micv.model.contacto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email {
	private StringProperty direccionCorreo=new SimpleStringProperty();
	
	public Email(){}

	public StringProperty direccionCorreoProperty() {
		return this.direccionCorreo;
	}
	

	public String getDireccionCorreo() {
		return this.direccionCorreoProperty().get();
	}
	

	public void setDireccionCorreo(final String direccionCorreo) {
		this.direccionCorreoProperty().set(direccionCorreo);
	}
	
	
}
