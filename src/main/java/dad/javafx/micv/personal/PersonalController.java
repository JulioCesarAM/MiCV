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
	private TextField identificacionText;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField apellidosText;

	@FXML
	private DatePicker fechaNacimientoDate;

	@FXML
	private TextArea direccionText;

	@FXML
	private TextField codigoPostalText;

	@FXML
	private TextField localidadText;

	@FXML
	private ListView<Nacionalidad> nacionalidadesList;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private Button nuevaNacionalidadButton;

	@FXML
	private Button quitarNacionalidadButton;

	private List<Nacionalidad> listaElecciones;

	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personal.addListener((o, ov, nv) -> onPersonalChanged(o, ov, nv));

		listaElecciones = new ArrayList<>();
		quitarNacionalidadButton.setDisable(true);

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/csv/nacionalidades.csv"));
			String row = "";

			while ((row = csvReader.readLine()) != null) {
				Nacionalidad nac = new Nacionalidad(row);
				listaElecciones.add(nac);
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

	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {

		System.out.println("ov=" + ov + "/nv=" + nv);

		if (ov != null) {

			identificacionText.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			fechaNacimientoDate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			direccionText.textProperty().unbindBidirectional(ov.direccionProperty());
			localidadText.textProperty().unbindBidirectional(ov.localidadProperty());
			codigoPostalText.textProperty().unbindBidirectional(ov.codigoPostalProperty());
			paisCombo.valueProperty().unbindBidirectional(ov.paisProperty());
			nacionalidadesList.itemsProperty().unbindBidirectional(ov.nacionalidadesProperty());
			// TODO desbindear el resto de propiedades

		}

		if (nv != null) {

			identificacionText.textProperty().bindBidirectional(nv.identificacionProperty());
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
			fechaNacimientoDate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
			direccionText.textProperty().bindBidirectional(nv.direccionProperty());
			localidadText.textProperty().bindBidirectional(nv.localidadProperty());
			codigoPostalText.textProperty().bindBidirectional(nv.codigoPostalProperty());
			paisCombo.valueProperty().bindBidirectional(nv.paisProperty());
			nacionalidadesList.itemsProperty().bindBidirectional(nv.nacionalidadesProperty());
			// TODO bindear el resto de propiedades

		}

	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onNuevaNacionalidadAction(ActionEvent event) {

		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<>(listaElecciones.get(0), listaElecciones);
		dialog.setHeaderText("Nueva nacionalidad");
		dialog.setTitle("Añadir nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");

		Optional<Nacionalidad> eleccion = dialog.showAndWait();
		if (eleccion.isPresent()) {
				if (!eleccion.isEmpty()) {
				nacionalidadesList.getItems().add(eleccion.get());
				quitarNacionalidadButton.setDisable(false);
				}
			}
		
		}
		
	

	@FXML
	void onQuitarNacionalidadAction(ActionEvent event) {
		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<>(nacionalidadesList.getItems().get(0),
				nacionalidadesList.getItems());
		dialog.setHeaderText("Quitar nacionalidad");
		dialog.setTitle("Quitar nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");
		Optional<Nacionalidad> eleccion = dialog.showAndWait();

		if (eleccion.isPresent()) {
			nacionalidadesList.getItems().remove(eleccion.get());

			if (nacionalidadesList.getItems().isEmpty()) {
				quitarNacionalidadButton.setDisable(true);
			} else {
				quitarNacionalidadButton.setDisable(false);
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
