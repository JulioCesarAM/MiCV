package dad.javafx.micv.Experiencia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Experiencia.Experiencia;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.converter.LocalDateStringConverter;

public class ExperienciaController implements Initializable {
	//modelo
	ListProperty<Experiencia>expformacion=new SimpleListProperty<>(FXCollections.observableArrayList());

    @FXML
    private GridPane view;

    @FXML
    private TableView<Experiencia> tableFormacion;

    @FXML
    private TableColumn<Experiencia, LocalDate> cDesde;

    @FXML
    private TableColumn<Experiencia, LocalDate> cHasta;

    @FXML
    private TableColumn<Experiencia, String> cDenominacion;

    @FXML
    private TableColumn<Experiencia, String> cOrganizador;

    @FXML
    private Button bAgregar;

    @FXML
    private Button bEliminar;

    @FXML
    void agregarFormacion(ActionEvent event) {
    	
    	Dialog<ButtonType> agregarFormacion=new Dialog<>();
		agregarFormacion.setTitle("nueva experiencia");
		//Creamos botones
		ButtonType crearFormaciono =new ButtonType("crear");
		agregarFormacion.getDialogPane().getButtonTypes().addAll(crearFormaciono,ButtonType.CANCEL);
		GridPane nuevoTitulo=new GridPane();
		nuevoTitulo.setHgap(10);
		nuevoTitulo.setVgap(10);
		TextField Denominacion=new TextField();
		TextField organizador=new TextField();
		DatePicker desde =new DatePicker();
		DatePicker hasta=new DatePicker();
		nuevoTitulo.add(new Label("Denominacion"), 0, 0);
		nuevoTitulo.add(Denominacion, 1, 0);
		nuevoTitulo.add(new Label("Organizador"), 0, 1);
		nuevoTitulo.add(organizador, 1, 1);
		nuevoTitulo.add(new Label("Desde"), 0, 2);
		nuevoTitulo.add(desde,1,2);
		nuevoTitulo.add(new Label("Hasta"), 0,3);
		nuevoTitulo.add(hasta, 1, 3);
		agregarFormacion.getDialogPane().setContent(nuevoTitulo);
		Optional<ButtonType> result=agregarFormacion.showAndWait();
		if(result.isPresent()) {
			Experiencia auxFormation=new Experiencia();
			if (result.get()!=ButtonType.CANCEL) {
				auxFormation.setDenominacion(Denominacion.textProperty().get());
				auxFormation.desdeProperty().set(desde.getValue());
				auxFormation.hastaProperty().set(hasta.getValue());
				auxFormation.setOrganizador(organizador.textProperty().get());
	
			
			
				expformacion.add(auxFormation);
			}
			
		}
		
		
    }

    @FXML
    void eliminarFormacion(ActionEvent event) {
    	if (!tableFormacion.getSelectionModel().isEmpty())
		{
			Dialog<ButtonType> borrarTitulo=new Dialog<>();
			borrarTitulo.setTitle("Eliminar");
			borrarTitulo.setHeaderText("vas a borrar la experiencia");
			ButtonType borrarFormation =new ButtonType("borrar");
			borrarTitulo.getDialogPane().getButtonTypes().addAll(borrarFormation,ButtonType.CANCEL);
			Optional<ButtonType>result=borrarTitulo.showAndWait();
			if (result.isPresent()) {
				if (result.get()!=ButtonType.CANCEL) {
					//tableTelefono.getItems().remove(tableTelefono.getSelectionModel().getSelectedItem());
					expformacion.get().remove(tableFormacion.getSelectionModel().getSelectedItem());
				}
			}
			
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//cambiar con local date y usar object property y tal
		cDesde.setCellValueFactory(cellData->cellData.getValue().desdeProperty());
		cDesde.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		cHasta.setCellValueFactory(cellData->cellData.getValue().hastaProperty());
		cHasta.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		cDenominacion.setCellValueFactory(cellData->cellData.getValue().denominacionProperty());
		cDenominacion.setCellFactory(TextFieldTableCell.forTableColumn());
		cOrganizador.setCellValueFactory(cellData->cellData.getValue().organizadorProperty());
		cOrganizador.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
		this.expformacion.addListener((o,ov,nv)->onFormationChange(o,ov,nv));
	
		
	}
	private void onFormationChange(ObservableValue<? extends ObservableList<Experiencia>> o,
			ObservableList<Experiencia> ov, ObservableList<Experiencia> nv) {
			if (ov!=null)
				tableFormacion.itemsProperty().unbindBidirectional(expformacion);
			if (nv!=null)
				tableFormacion.itemsProperty().bindBidirectional(expformacion);

	}


	
	

	public ExperienciaController() throws IOException {
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

	public ListProperty<Experiencia> expformacionProperty() {
		return this.expformacion;
	}
	

	public ObservableList<Experiencia> getFormacion() {
		return this.expformacionProperty().get();
	}
	

	public void setFormacion(final ObservableList<Experiencia> formacion) {
		this.expformacionProperty().set(formacion);
	}
	

}
