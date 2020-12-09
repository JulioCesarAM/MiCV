package dad.javafx.micv.model.formacion;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Formation {
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty organizador =new SimpleStringProperty();
	private ObjectProperty<LocalDate>desde=new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate>hasta=new SimpleObjectProperty<>();

	
	
	public Formation(){
		
	}
	
		
	

	

	public StringProperty denominacionProperty() {
		return this.denominacion;
	}
	
	public String getDenominacion() {
		return this.denominacionProperty().get();
	}
	
	public void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}
	
	public StringProperty organizadorProperty() {
		return this.organizador;
	}
	
	public String getOrganizador() {
		return this.organizadorProperty().get();
	}
	
	public void setOrganizador(final String organizador) {
		this.organizadorProperty().set(organizador);
	}






	public ObjectProperty<LocalDate> desdeProperty() {
		return this.desde;
	}
	






	public LocalDate getDesde() {
		return this.desdeProperty().get();
	}
	






	public void setDesde(final LocalDate desde) {
		this.desdeProperty().set(desde);
	}
	






	public ObjectProperty<LocalDate> hastaProperty() {
		return this.hasta;
	}
	






	public LocalDate getHasta() {
		return this.hastaProperty().get();
	}
	






	public void setHasta(final LocalDate hasta) {
		this.hastaProperty().set(hasta);
	}
	
	
	

	

	
	
	
	
	
	
	
	
}
