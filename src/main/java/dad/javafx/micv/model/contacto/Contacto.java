package dad.javafx.micv.model.contacto;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class Contacto {
	private ListProperty<Telefono> listadoTelefonos = new SimpleListProperty<Telefono>(FXCollections.observableArrayList());
	private ListProperty<Email> correoElectronico= new SimpleListProperty<Email>(FXCollections.observableArrayList());
	private ListProperty<Web> urlWeb= new SimpleListProperty<Web>(FXCollections.observableArrayList());
	
	public Contacto(){}
	public void addTelefono(Telefono uno) {
		this.listadoTelefonos.add(uno);
	}

	public ListProperty<Telefono> getListadoTelefonos() {
		return listadoTelefonos;
	}

	public void setListadoTelefonos(ListProperty<Telefono> listadoTelefonos) {
		this.listadoTelefonos = listadoTelefonos;
	}

	public ListProperty<Email> getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(ListProperty<Email> correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public ListProperty<Web> getUrlWeb() {
		return urlWeb;
	}

	public void setUrlWeb(ListProperty<Web> urlWeb) {
		this.urlWeb = urlWeb;
	}
	
	

}
