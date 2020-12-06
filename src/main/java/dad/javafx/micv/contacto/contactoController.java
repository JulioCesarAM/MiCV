package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.contacto.Contacto;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class contactoController implements Initializable {
	// modelo
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<>();
	@FXML
	private AnchorPane view;

	public AnchorPane getView() {
		return view;
	}

	public void setView(AnchorPane view) {
		this.view = view;
	}

	@FXML
	private TableView tableTelefono, tableEmail, tableUrl;
	@FXML
	private TableColumn columnNumero, columnTipo, columnEmail, columnUrl;
	@FXML
	private Button bAgregarTelfono, bEliminarTelefono, bAgregarEmail, bEliminarEmail, bAgregarUrl, bnEliminarUrl;

	public contactoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.contacto.addListener((o, ov, nv) -> onCambiosContacto(o, ov, nv));
	}

	private void onCambiosContacto(ObservableValue<? extends Contacto> o, Contacto ov, Contacto nv) {

		if (ov != null) {
			tableTelefono.itemsProperty().unbindBidirectional(ov.getListadoTelefonos());
			tableEmail.itemsProperty().unbindBidirectional(ov.getCorreoElectronico());
			tableUrl.itemsProperty().unbindBidirectional(ov.getUrlWeb());

		}
		if (nv != null) {
			tableTelefono.itemsProperty().bindBidirectional(nv.getListadoTelefonos());
			tableEmail.itemsProperty().bindBidirectional(nv.getCorreoElectronico());
			tableUrl.itemsProperty().bindBidirectional(nv.getUrlWeb());
		}
	}

	@FXML
	private void agregarTelefono() {
		Dialog<Pair<Contacto,Contacto>> agregarTlf=new Dialog<>();
		agregarTlf.setTitle("nuevo telefono");
		agregarTlf.setHeaderText("Introduzca el nuevo numero de telefono");
		//Ccreamos botones
		ButtonType añadirTelefono =new ButtonType("añadir",ButtonData.OK_DONE);
		agregarTlf.getDialogPane().getButtonTypes().addAll(añadirTelefono,ButtonType.CANCEL);
		//creamos el campo de  numero y su tipo
		GridPane auxGridAgregarTelefono=new GridPane();
		auxGridAgregarTelefono.setHgap(10);
		auxGridAgregarTelefono.setVgap(10);
		TextField numero = new TextField();
		ComboBox<String> tipoTlf =new ComboBox<>();
		tipoTlf.getItems().addAll("uni","dos");
		auxGridAgregarTelefono.add(new Label("numero telefono"), 0, 0);
		auxGridAgregarTelefono.add(numero, 1, 0);
		auxGridAgregarTelefono.add(new Label("combo"), 0, 1);
		auxGridAgregarTelefono.add(numero, 1, 1);
		agregarTlf.getDialogPane().setContent(auxGridAgregarTelefono);
		 agregarTlf.showAndWait();
	}

	@FXML
	private void eliminarTelefono() {

	}

	@FXML
	private void agregarEmail() {

	}

	@FXML
	private void eliminarEmail() {

	}

	@FXML
	private void agregarUrl() {

	}

	@FXML
	private void eliminarUrl() {

	}

}
