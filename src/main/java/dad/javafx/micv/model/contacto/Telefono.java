package dad.javafx.micv.model.contacto;

import javafx.beans.property.StringProperty;



public class Telefono {
	
	private StringProperty numeroTelefono;
	static public enum tipoTelefono{
		Domicilio,
		Movil;
		
	}
	public void prueba() {
	}
	public Telefono() {}
	public StringProperty numeroTelefonoProperty() {
		return this.numeroTelefono;
	}
	
	public String getNumeroTelefono() {
		return this.numeroTelefonoProperty().get();
	}
	
	public void setNumeroTelefono(final String numeroTelefono) {
		this.numeroTelefonoProperty().set(numeroTelefono);
	}
	
	

}
