package dad.javafx.micv.model.contacto;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class Contacto {
	private ListProperty<Telefono> listadoTelefonos = new SimpleListProperty<Telefono>(FXCollections.observableArrayList());
	private ObjectProperty<Email> correoElectronico=new SimpleObjectProperty<>();
	private ObjectProperty<Web>urlWeb = new SimpleObjectProperty<>();
	public Contacto(){}
	public ListProperty<Telefono> getListadoTelefonos() {
		return listadoTelefonos;
	}
	public void addListadoTelefonos(Telefono listadoTelefonos) {
		this.listadoTelefonos.add(listadoTelefonos);
	}
	public ObjectProperty<Email> getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(ObjectProperty<Email> correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public ObjectProperty<Web> getUrlWeb() {
		return urlWeb;
	}
	public void setUrlWeb(ObjectProperty<Web> urlWeb) {
		this.urlWeb = urlWeb;
	}
	
	
	

}
