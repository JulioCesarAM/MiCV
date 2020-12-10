package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.contacto.Contacto;
import dad.javafx.micv.model.contacto.Email;
import dad.javafx.micv.model.contacto.Telefono;
import dad.javafx.micv.model.contacto.Web;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class contactoController implements Initializable {

	// modelo
	Contacto testing = new Contacto();
	private ObjectProperty<Contacto> contactoProperty = new SimpleObjectProperty<>();
	@FXML
	private AnchorPane view;

	public AnchorPane getView() {
		return view;
	}

	public void setView(AnchorPane view) {
		this.view = view;
	}

	@FXML
	private TableView<Telefono> tableTelefono;
	@FXML
	private TableView<Email> tableEmail;
	@FXML
	private TableView<Web> tableUrl;
	@FXML
	private TableColumn<Telefono, String> columnNumero, columnTipo;
	@FXML
	private TableColumn<Email, String> columnEmail;
	@FXML
	private TableColumn<Web, String> columnUrl;
	@FXML
	private Button bAgregarTelfono, bEliminarTelefono, bAgregarEmail, bEliminarEmail, bAgregarUrl, bnEliminarUrl;

	public contactoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		columnNumero.setCellValueFactory(cellData -> cellData.getValue().numeroTelefonoProperty());
		columnNumero.setCellFactory(TextFieldTableCell.forTableColumn());
		columnTipo.setCellValueFactory(cellData -> cellData.getValue().tTelefonoProperty());
		columnTipo.setCellFactory(ComboBoxTableCell.forTableColumn("Domicilio", "Movil"));
		columnEmail.setCellValueFactory(cellData -> cellData.getValue().direccionCorreoProperty());
		columnNumero.setCellFactory(TextFieldTableCell.forTableColumn());
		columnUrl.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
		columnUrl.setCellFactory(TextFieldTableCell.forTableColumn());
		this.contactoProperty.addListener((o, ov, nv) -> onCambiosContacto(o, ov, nv));
		this.contactoProperty.set(new Contacto());
	}

	private void onCambiosContacto(ObservableValue<? extends Contacto> o, Contacto ov, Contacto nv) {
		System.out.println("testing");
		if (ov != null) {
			tableTelefono.itemsProperty().unbindBidirectional(ov.getListadoTelefonos());
			tableEmail.itemsProperty().unbindBidirectional(ov.getCorreoElectronico());
			tableUrl.itemsProperty().unbindBidirectional(ov.getUrlWeb());
			System.out.println(ov);
			System.out.println("testing ov");

		}
		if (nv != null) {
			tableTelefono.itemsProperty().bindBidirectional(nv.getListadoTelefonos());
			tableEmail.itemsProperty().bindBidirectional(nv.getCorreoElectronico());
			tableUrl.itemsProperty().bindBidirectional(nv.getUrlWeb());
			System.out.println("testing nv");
			System.out.println(nv);
		}
	}

	@FXML
	private void agregarTelefono() {

		Dialog<ButtonType> agregarTlf = new Dialog<>();
		agregarTlf.setTitle("nuevo telefono");
		agregarTlf.setHeaderText("Introduzca el nuevo numero de telefono");
		// Creamos botones
		ButtonType añadirTelefono = new ButtonType("añadir");
		agregarTlf.getDialogPane().getButtonTypes().addAll(añadirTelefono, ButtonType.CANCEL);

		// creamos el campo de numero y su tipo
		GridPane auxGridAgregarTelefono = new GridPane();
		auxGridAgregarTelefono.setHgap(10);
		auxGridAgregarTelefono.setVgap(10);
		TextField numero = new TextField();
		ComboBox<String> tipoTlf = new ComboBox<>();
		tipoTlf.getItems().addAll("Domicilio", "Movil");
		auxGridAgregarTelefono.add(new Label("numero telefono"), 0, 0);
		auxGridAgregarTelefono.add(numero, 1, 0);
		auxGridAgregarTelefono.add(new Label("combo"), 0, 1);
		auxGridAgregarTelefono.add(tipoTlf, 1, 1);
		agregarTlf.getDialogPane().setContent(auxGridAgregarTelefono);
		Optional<ButtonType> result = agregarTlf.showAndWait();

		if (result.isPresent()) {
			if (result.get() != ButtonType.CANCEL) {
				Telefono nuevoTlf = new Telefono();
				// nuevoTlf.numeroTelefonoProperty().setValue(numero.textProperty().get());
				nuevoTlf.setNumeroTelefono(numero.textProperty().get());
				// nuevoTlf.tTelefonoProperty().bind(
				// tipoTlf.getSelectionModel().selectedItemProperty());
				nuevoTlf.setTipoTelefono(
						tipoTlf.getSelectionModel().getSelectedItem().toString().toLowerCase().equals("domicilio") ? "d"
								: "m");
				contactoProperty.get().addTelefono(nuevoTlf);

			}

		}

	}

	@FXML
	private void eliminarTelefono() {
		if (!tableTelefono.getSelectionModel().isEmpty()) {
			Dialog<ButtonType> borrarTlf = new Dialog<>();
			borrarTlf.setTitle("Eliminar");
			borrarTlf.setHeaderText("vas a borrar el telefono");
			ButtonType borrarTelefono = new ButtonType("borrar");
			borrarTlf.getDialogPane().getButtonTypes().addAll(borrarTelefono, ButtonType.CANCEL);
			Optional<ButtonType> result = borrarTlf.showAndWait();
			if (result.isPresent()) {
				if (result.get() != ButtonType.CANCEL) {
					// tableTelefono.getItems().remove(tableTelefono.getSelectionModel().getSelectedItem());
					contactoProperty.get().getListadoTelefonos()
							.remove(tableTelefono.getSelectionModel().getSelectedItem());
				}
			}

		}

	}

	@FXML
	private void agregarEmail() {

		Dialog<ButtonType> agregarEmail = new Dialog<>();
		agregarEmail.setTitle("nuevo Email");
		agregarEmail.setHeaderText("crea una nueva direccion de correo");
		// Creamos botones
		ButtonType añadirCorreo = new ButtonType("aceptar");
		agregarEmail.getDialogPane().getButtonTypes().addAll(añadirCorreo, ButtonType.CANCEL);

		// creamos el campo de numero y su tipo
		GridPane auxGridAgregarEmail = new GridPane();
		auxGridAgregarEmail.setHgap(10);
		auxGridAgregarEmail.setVgap(10);
		TextField email = new TextField();
		auxGridAgregarEmail.add(new Label("numero Email"), 0, 0);
		auxGridAgregarEmail.add(email, 1, 0);

		agregarEmail.getDialogPane().setContent(auxGridAgregarEmail);
		Optional<ButtonType> result = agregarEmail.showAndWait();
		if (result.isPresent()) {
			if (result.get() != ButtonType.CANCEL) {
				Email nuevoEmail = new Email();
				nuevoEmail.setDireccionCorreo(email.textProperty().get());
				contactoProperty.get().addEmail(nuevoEmail);

			}
		}

	}

	@FXML
	private void eliminarEmail() {
		if (!tableEmail.getSelectionModel().isEmpty()) {
			Dialog<ButtonType> borrarUrl = new Dialog<>();
			borrarUrl.setTitle("Eliminar");
			borrarUrl.setHeaderText("vas a borrar un correo");
			ButtonType borrarWeb = new ButtonType("borrar");
			borrarUrl.getDialogPane().getButtonTypes().addAll(borrarWeb, ButtonType.CANCEL);
			Optional<ButtonType> result = borrarUrl.showAndWait();
			if (result.isPresent()) {
				if (result.get() != ButtonType.CANCEL) {
					// tableTelefono.getItems().remove(tableTelefono.getSelectionModel().getSelectedItem());
					contactoProperty.get().getCorreoElectronico()
							.remove(tableEmail.getSelectionModel().getSelectedItem());
				}
			}

		}

	}

	@FXML
	private void agregarUrl() {

		Dialog<ButtonType> agregarWeb = new Dialog<>();
		agregarWeb.setTitle("nuevo Web");
		agregarWeb.setHeaderText("crea una nueva direccion web");
		// Creamos botones
		ButtonType añadirCorreo = new ButtonType("aceptar");
		agregarWeb.getDialogPane().getButtonTypes().addAll(añadirCorreo, ButtonType.CANCEL);

		// creamos el campo de numero y su tipo
		GridPane auxGridAgregarEmail = new GridPane();
		auxGridAgregarEmail.setHgap(10);
		auxGridAgregarEmail.setVgap(10);
		TextField url = new TextField();
		auxGridAgregarEmail.add(new Label("numero Url"), 0, 0);
		auxGridAgregarEmail.add(url, 1, 0);

		agregarWeb.getDialogPane().setContent(auxGridAgregarEmail);
		Optional<ButtonType> result = agregarWeb.showAndWait();
		if (result.isPresent()) {
			if (result.get() != ButtonType.CANCEL) {
				Web nuevaUrl = new Web();
				nuevaUrl.setUrl(url.textProperty().get());
				contactoProperty.get().addUrl(nuevaUrl);

			}
		}

	}

	@FXML
	private void eliminarUrl() {
		if (!tableUrl.getSelectionModel().isEmpty()) {
			Dialog<ButtonType> borrarUrl = new Dialog<>();
			borrarUrl.setTitle("Eliminar");
			borrarUrl.setHeaderText("vas a borrar una url");
			ButtonType borrarWeb = new ButtonType("borrar");
			borrarUrl.getDialogPane().getButtonTypes().addAll(borrarWeb, ButtonType.CANCEL);
			Optional<ButtonType> result = borrarUrl.showAndWait();
			if (result.isPresent()) {
				if (result.get() != ButtonType.CANCEL) {
					// tableTelefono.getItems().remove(tableTelefono.getSelectionModel().getSelectedItem());
					contactoProperty.get().getUrlWeb().get().remove(tableUrl.getSelectionModel().getSelectedItem());
				}
			}

		}

	}

	public ObjectProperty<Contacto> contactoPropertyProperty() {
		return this.contactoProperty;
	}

	public Contacto getContactoProperty() {
		return this.contactoPropertyProperty().get();
	}

	public void setContactoProperty(final Contacto contactoProperty) {
		this.contactoPropertyProperty().set(contactoProperty);
	}

}
