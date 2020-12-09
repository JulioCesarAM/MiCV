package dad.javafx.micv.model.Conocimientos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

enum nivelConocimiento{
		BASICO,MEDIO,AVANZADO;
		}
public class Conocimientos {
	private StringProperty denominacion =new SimpleStringProperty();
	private StringProperty certificacion=new SimpleStringProperty();
	private StringProperty nivelTipo=new SimpleStringProperty();
	private nivelConocimiento nivel;
	
	
	public void setNivel(String a) {
		if (a.toLowerCase().equals("basico"))
			this.nivel=nivelConocimiento.BASICO;
		else if (a.toLowerCase().equals("medio"))
			this.nivel=nivelConocimiento.MEDIO;
		else if (a.toLowerCase().equals("avanzado"))
			this.nivel=nivelConocimiento.AVANZADO;
		else
			throw new IllegalArgumentException("deja de inventar");
		enlaceStringProperty();
				
		
	}
	private void enlaceStringProperty() {
		nivelTipo.set(this.nivel.toString());
		
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
	
	public StringProperty certificacionProperty() {
		return this.certificacion;
	}
	
	public String getCertificacion() {
		return this.certificacionProperty().get();
	}
	
	public void setCertificacion(final String certificacion) {
		this.certificacionProperty().set(certificacion);
	}
	
	public StringProperty nivelTipoProperty() {
		return this.nivelTipo;
	}
	
	public String getNivelTipo() {
		return this.nivelTipoProperty().get();
	}
	
	public void setNivelTipo(final String nivelTipo) {
		this.nivelTipoProperty().set(nivelTipo);
	}
	
	
	

	

}
