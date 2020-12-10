package dad.javafx.micv.personal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Nacionalidad;
import dad.javafx.micv.model.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

	// model

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();

	@FXML
	private GridPane view;

	@FXML
	private TextField identificacionTextField;

	@FXML
	private TextField nombreTextField;

	@FXML
	private TextField apellidosTextField;

	@FXML
	private DatePicker fechaNacimientoDate;

	@FXML
	private TextArea textAreaDireccion;

	@FXML
	private TextField codPostalTextField;

	@FXML
	private TextField localidadTextField;

	@FXML
	private ListView<Nacionalidad> listViewNacionalidades;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private Button BttnNacionalidadNuevan,BttnQuitarNacionalidad;



	private List<Nacionalidad> nacionalidadesListas;

	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personal.addListener((o, ov, nv) -> onPersonallControllerChanged(o, ov, nv));

		nacionalidadesListas = new ArrayList<>();
		BttnQuitarNacionalidad.setDisable(true);

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/csv/nacionalidades.csv"));
			String row = "";

			while ((row = csvReader.readLine()) != null) {
				Nacionalidad nac = new Nacionalidad(row);
				nacionalidadesListas.add(nac);
			}
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/csv/paises.csv"));
			String row = "";

			while ((row = csvReader.readLine()) != null) {
				paisCombo.getItems().add(row);
			}
			csvReader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		paisCombo.getSelectionModel().select(0);
	

	}

	private void onPersonallControllerChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {

		System.out.println("ov=" + ov + "/nv=" + nv);

		if (ov != null) {

			identificacionTextField.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreTextField.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosTextField.textProperty().unbindBidirectional(ov.apellidosProperty());
			fechaNacimientoDate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			textAreaDireccion.textProperty().unbindBidirectional(ov.direccionProperty());
			localidadTextField.textProperty().unbindBidirectional(ov.localidadProperty());
			codPostalTextField.textProperty().unbindBidirectional(ov.codigoPostalProperty());
			paisCombo.valueProperty().unbindBidirectional(ov.paisProperty());
			listViewNacionalidades.itemsProperty().unbindBidirectional(ov.nacionalidadesProperty());
			// TODO desbindear el resto de propiedades

		}

		if (nv != null) {

			identificacionTextField.textProperty().bindBidirectional(nv.identificacionProperty());
			nombreTextField.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosTextField.textProperty().bindBidirectional(nv.apellidosProperty());
			fechaNacimientoDate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
			textAreaDireccion.textProperty().bindBidirectional(nv.direccionProperty());
			localidadTextField.textProperty().bindBidirectional(nv.localidadProperty());
			codPostalTextField.textProperty().bindBidirectional(nv.codigoPostalProperty());
			paisCombo.valueProperty().bindBidirectional(nv.paisProperty());
			listViewNacionalidades.itemsProperty().bindBidirectional(nv.nacionalidadesProperty());
			// TODO bindear el resto de propiedades

		}

	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onNuevaNacionalidadAction(ActionEvent event) {

		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<>(nacionalidadesListas.get(0), nacionalidadesListas);
		dialog.setHeaderText("Nueva nacionalidad");
		dialog.setTitle("Añadir nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");

		Optional<Nacionalidad> eleccion = dialog.showAndWait();
		if (eleccion.isPresent()) {
				if (!eleccion.isEmpty()) {	
					
				listViewNacionalidades.getItems().add(eleccion.get());
				BttnQuitarNacionalidad.setDisable(false);
				}
			}
		
		}
		
	

	@FXML
	void onQuitarNacionalidadAction(ActionEvent event) {
		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<>(listViewNacionalidades.getItems().get(0),
				listViewNacionalidades.getItems());
		dialog.setHeaderText("Quitar nacionalidad");
		dialog.setTitle("Quitar nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");
		Optional<Nacionalidad> eleccion = dialog.showAndWait();

		if (eleccion.isPresent()) {
			listViewNacionalidades.getItems().remove(eleccion.get());

			if (listViewNacionalidades.getItems().isEmpty()) {
				BttnQuitarNacionalidad.setDisable(true);
			} else {
				BttnQuitarNacionalidad.setDisable(false);
			}
		}

	}

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

}
