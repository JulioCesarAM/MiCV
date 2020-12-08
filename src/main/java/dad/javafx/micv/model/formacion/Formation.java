package dad.javafx.micv.model.formacion;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Formation {
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty organizador =new SimpleStringProperty();
	private Date desde=new Date();
	private Date hasta=new Date();
	
	private StringProperty desdeFecha = new SimpleStringProperty();
	private StringProperty hastaFecha =new SimpleStringProperty();
	public Formation(){
		desdeFecha.addListener((e)->{
			try {
				onCambiosDesde(e);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		hastaFecha.addListener((e)->{
			try {
				onCambiosHasta(e);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	private void onCambiosHasta(Observable e) throws ParseException {
		hasta=new SimpleDateFormat("DD/MM/yyyy").parse(e.toString());
	}

	private void onCambiosDesde(Observable e) throws ParseException {
		desde=new SimpleDateFormat("DD/MM/yyyy").parse(e.toString());
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
	
	public StringProperty desdeFechaProperty() {
		return this.desdeFecha;
	}
	
	public String getDesdeFecha() {
		return this.desdeFechaProperty().get();
	}
	
	public void setDesdeFecha(final String desdeFecha) {
		this.desdeFechaProperty().set(desdeFecha);
	}
	
	public StringProperty hastaFechaProperty() {
		return this.hastaFecha;
	}
	
	public String getHastaFecha() {
		return this.hastaFechaProperty().get();
	}
	
	public void setHastaFecha(final String hastaFechar) {
		this.hastaFechaProperty().set(hastaFechar);
	}
	
	
	
	
	
	
	
	
}
