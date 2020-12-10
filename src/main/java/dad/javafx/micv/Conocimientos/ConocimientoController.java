package dad.javafx.micv.Conocimientos;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Conocimientos.Conocimientos;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConocimientoController implements Initializable {
	ListProperty<Conocimientos> conocimientosModelo = new SimpleListProperty<>(FXCollections.observableArrayList());
	private Stage stageConocimiento;

	public ConocimientoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public HBox getView() {
		return view;
	}

	@FXML
	private HBox view;

	@FXML
	private TableView<Conocimientos> tableC;

	@FXML
	private TableColumn<Conocimientos, String> denominacionCol;

	@FXML
	private TableColumn<Conocimientos, String> nivelCol;

	@FXML
	private Button btnEliminarConocomiento;

	@FXML
	private TextField tfDenominacionC;

	@FXML
	private ComboBox<String> cbConocimientoCombo = new ComboBox<>();

	@FXML
	private TextField denominacionIdiomaTF;

	@FXML
	private ComboBox<String> cbIdiomaCombo = new ComboBox<>();

	@FXML
	private TextField CertificaciónIdiomaTf;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cbConocimientoCombo.getItems().addAll("basico", "medio", "avanzado");
		cbIdiomaCombo.getItems().addAll("basico", "medio", "avanzado");
		this.conocimientosModelo.addListener((o,ov,nv)->onCambios(o,ov,nv));	
		denominacionCol.setCellValueFactory(cellData -> cellData.getValue().denominacionProperty());
		denominacionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelCol.setCellValueFactory(cellData -> cellData.getValue().nivelTipoProperty());
		nivelCol.setCellFactory(ComboBoxTableCell.forTableColumn("basico", "medio", "avanzado"));

	}

	private void onCambios(ObservableValue<? extends ObservableList<Conocimientos>> o,
			ObservableList<Conocimientos> ov, ObservableList<Conocimientos> nv) {
			if (ov!=null)
				tableC.itemsProperty().unbindBidirectional(conocimientosModelo);
			if(nv!=null)
				tableC.itemsProperty().bindBidirectional(conocimientosModelo);
		
	}

	@FXML
	void onBtnAñadirConocimiento(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aConocimientoView.fxml"));
			loader.setController(this);
			Parent root1 = (Parent) loader.load();
			stageConocimiento = new Stage();
			stageConocimiento.setTitle("Añadir Conocimiento");
			stageConocimiento.initModality(Modality.WINDOW_MODAL);
			stageConocimiento.initOwner(view.getScene().getWindow());
			stageConocimiento.setScene(new Scene(root1));
			stageConocimiento.setResizable(false);
			stageConocimiento.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void oonCancelarConocimiento(ActionEvent event) {
		stageConocimiento.close();
	}

	@FXML
	void onCrearConocimientoM(ActionEvent event) {

		Conocimientos auxConocimiento = new Conocimientos();
		auxConocimiento.setDenominacion(tfDenominacionC.textProperty().get());
		auxConocimiento.setNivel(cbConocimientoCombo.getValue().toLowerCase());
		if (auxConocimiento.getDenominacion().isEmpty() || auxConocimiento.getNivelTipo().isEmpty()) {
			Alert aux = new Alert(AlertType.ERROR);
			aux.setContentText("error ");
			aux.showAndWait();
		} else {
			conocimientosModelo.add(auxConocimiento);
			stageConocimiento.close();
		}

	}

	@FXML
	void onXButtonConocimiento(ActionEvent event) {
		cbConocimientoCombo.valueProperty().set(null);
	}

	@FXML
	void onBtnAñadirIdioma(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aIdiomaConocimientoView.fxml"));
			loader.setController(this);
			Parent root1 = (Parent) loader.load();
			stageConocimiento = new Stage();
			stageConocimiento.setTitle("Añadir idioma");
			stageConocimiento.initModality(Modality.WINDOW_MODAL);
			stageConocimiento.initOwner(view.getScene().getWindow());
			stageConocimiento.setScene(new Scene(root1));
			stageConocimiento.setResizable(false);
			stageConocimiento.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void onEliminar(ActionEvent event) {
		if (!tableC.getSelectionModel().isEmpty()) {
			Dialog<ButtonType> borrarTlf = new Dialog<>();
			borrarTlf.setTitle("Eliminar");
			borrarTlf.setHeaderText("vas a borrar un conocimiento");
			ButtonType borrarTelefono = new ButtonType("borrar");
			borrarTlf.getDialogPane().getButtonTypes().addAll(borrarTelefono, ButtonType.CANCEL);
			Optional<ButtonType> result = borrarTlf.showAndWait();
			if (result.isPresent()) {
				if (result.get() != ButtonType.CANCEL) {
					tableC.getItems().remove(tableC.getSelectionModel().getSelectedItem());
				}
			}

		}

	}

	@FXML
	void onCancelarIdioma(ActionEvent event) {
		stageConocimiento.close();
	}

	@FXML
	void onCrearIdioma(ActionEvent event) {
		Conocimientos auxConocimientoIdioma = new Conocimientos();
		auxConocimientoIdioma.setDenominacion(denominacionIdiomaTF.textProperty().get());
		auxConocimientoIdioma.setNivel(cbIdiomaCombo.getValue().toLowerCase());
		auxConocimientoIdioma.setCertificacion(CertificaciónIdiomaTf.textProperty().get());
		if (auxConocimientoIdioma.getDenominacion().isEmpty() || auxConocimientoIdioma.getNivelTipo().isEmpty()) {
			Alert aux = new Alert(AlertType.ERROR);
			aux.setContentText("error ");
			aux.showAndWait();
		} else {
			conocimientosModelo.add(auxConocimientoIdioma);
			stageConocimiento.close();
		}

	}

	@FXML
	void buttonXIdioma(ActionEvent event) {
		cbIdiomaCombo.valueProperty().set(null);
	}

	public ListProperty<Conocimientos> conocimientosModeloProperty() {
		return this.conocimientosModelo;
	}
	

	public ObservableList<Conocimientos> getConocimientosModelo() {
		return this.conocimientosModeloProperty().get();
	}
	

	public void setConocimientosModelo(final ObservableList<Conocimientos> conocimientosModelo) {
		this.conocimientosModeloProperty().set(conocimientosModelo);
	}
	

}
