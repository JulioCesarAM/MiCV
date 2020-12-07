package dad.javafx.micv.model.contacto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

enum tipoTelefono {
	Domicilio, Movil;
}

public class Telefono {
	private StringProperty tTelefono = new SimpleStringProperty();
	private StringProperty numeroTelefono = new SimpleStringProperty();

	tipoTelefono tipo;

	public Telefono(String _numero, tipoTelefono _tipo) {
		numeroTelefono.set(_numero);
		tipo = _tipo;
	}

	public Telefono() {
	}

	public StringProperty numeroTelefonoProperty() {
		return this.numeroTelefono;
	}

	public String getNumeroTelefono() {
		return this.numeroTelefonoProperty().get();
	}

	public void setNumeroTelefono(final String numeroTelefono) {
		this.numeroTelefonoProperty().set(numeroTelefono);
	}

	public String getTipoTelefono() {
		if (tipo == tipoTelefono.Domicilio)
			return "Domicilio";
		else
			return "Movil";
	}

	public void setTipoTelefono(String _d) {
		if (_d.toLowerCase().equals("d")) {
			tipo = tipoTelefono.Domicilio;
			this.tTelefono.set("Domicilio");
		} else {
			tipo = tipoTelefono.Movil;
			this.tTelefono.set("Movil");
		}

	}

	public String toString() {
		return this.getNumeroTelefono() + "| |" + this.getTipoTelefono();
	}

	public StringProperty tTelefonoProperty() {
		return this.tTelefono;
	}

	public String getTTelefono() {
		return this.tTelefonoProperty().get();
	}

	public void setTTelefono(final String tTelefono) {
		this.tTelefonoProperty().set(tTelefono);
	}

}
