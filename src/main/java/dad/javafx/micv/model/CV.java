package dad.javafx.micv.model;

import dad.javafx.micv.model.Conocimientos.Conocimientos;
import dad.javafx.micv.model.Experiencia.Experiencia;
import dad.javafx.micv.model.contacto.Contacto;
import dad.javafx.micv.model.formacion.Formation;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>(new Personal());
	private ObjectProperty<Contacto> contacto=new SimpleObjectProperty<Contacto>(new Contacto());
	private ListProperty <Formation> formaciones=new SimpleListProperty<Formation>(FXCollections.observableArrayList());
	private ListProperty <Conocimientos> conocimiento=new SimpleListProperty<Conocimientos>(FXCollections.observableArrayList()); 
	private ListProperty<Experiencia> experiencia=new SimpleListProperty<Experiencia>(FXCollections.observableArrayList());
	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

	public ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}
	

	public Contacto getContacto() {
		return this.contactoProperty().get();
	}
	

	public void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	

	public ListProperty<Formation> formacionesProperty() {
		return this.formaciones;
	}
	

	public ObservableList<Formation> getFormaciones() {
		return this.formacionesProperty().get();
	}
	

	public void setFormaciones(final ObservableList<Formation> formaciones) {
		this.formacionesProperty().set(formaciones);
	}
	

	public ListProperty<Conocimientos> conocimientoProperty() {
		return this.conocimiento;
	}
	

	public ObservableList<Conocimientos> getConocimiento() {
		return this.conocimientoProperty().get();
	}
	

	public void setConocimiento(final ObservableList<Conocimientos> conocimiento) {
		this.conocimientoProperty().set(conocimiento);
	}
	

	public ListProperty<Experiencia> experienciaProperty() {
		return this.experiencia;
	}
	

	public ObservableList<Experiencia> getExperiencia() {
		return this.experienciaProperty().get();
	}
	

	public void setExperiencia(final ObservableList<Experiencia> experiencia) {
		this.experienciaProperty().set(experiencia);
	}
	
	
}

