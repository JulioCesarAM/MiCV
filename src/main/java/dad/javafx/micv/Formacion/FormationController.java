package dad.javafx.micv.Formacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.formacion.Formation;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FormationController implements Initializable {
	//modelo
	ListProperty<Formation>formacion=new SimpleListProperty<>(FXCollections.observableArrayList());

    @FXML
    private GridPane view;

    @FXML
    private TableView<Formation> tableFormacion;

    @FXML
    private TableColumn<Formation, String> cDesde;

    @FXML
    private TableColumn<Formation, String> cHasta;

    @FXML
    private TableColumn<Formation, String> cDenominacion;

    @FXML
    private TableColumn<Formation, String> cOrganizador;

    @FXML
    private Button bAgregar;

    @FXML
    private Button bEliminar;

    @FXML
    void agregarFormacion(ActionEvent event) {
    	//Borrar  todo esto
//    	Dialog<ButtonType> agregarFormacion=new Dialog<>();
//		agregarFormacion.setTitle("nuevo titulo");
//		//Creamos botones
//		ButtonType crearFormaciono =new ButtonType("crear",ButtonBar.ButtonData.RIGHT);
//		ButtonType cancelar=new ButtonType("cancelar",ButtonBar.ButtonData.RIGHT);
//		agregarFormacion.getDialogPane().getButtonTypes().addAll(crearFormaciono,cancelar);
//		GridPane nuevoTitulo=new GridPane();
//		nuevoTitulo.setHgap(10);
//		nuevoTitulo.setVgap(10);
//		TextField Denominacion=new TextField();
//		TextField organizador=new TextField();
//		DatePicker desde =new DatePicker();
//		DatePicker hasta=new DatePicker();
//		nuevoTitulo.add(new Label("Denominacion"), 0, 0);
//		nuevoTitulo.add(Denominacion, 1, 0);
//		nuevoTitulo.add(new Label("Organizador"), 0, 1);
//		nuevoTitulo.add(organizador, 1, 1);
//		nuevoTitulo.add(new Label("Desde"), 0, 2);
//		nuevoTitulo.add(desde,1,2);
//		nuevoTitulo.add(new Label("Hasta"), 0,3);
//		nuevoTitulo.add(hasta, 1, 3);
//		agregarFormacion.getDialogPane().setContent(nuevoTitulo);
//		
//		
		
    }

    @FXML
    void eliminarFormacion(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.formacion.addListener((o,ov,nv)->onFormationChange(o,ov,nv));
	
		
	}
	private void onFormationChange(ObservableValue<? extends ObservableList<Formation>> o,
			ObservableList<Formation> ov, ObservableList<Formation> nv) {
			tableFormacion.itemsProperty().bindBidirectional(formacion);

	}


	
	

	public FormationController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/formationView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	public void setView(GridPane view) {
		this.view = view;
	}

}
