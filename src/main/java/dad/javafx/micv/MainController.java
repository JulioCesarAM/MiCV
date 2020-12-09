package dad.javafx.micv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.JsonSyntaxException;

import dad.javafx.micv.Conocimientos.ConocimientoController;
import dad.javafx.micv.Experiencia.ExperienciaController;
import dad.javafx.micv.Formacion.FormationController;
import dad.javafx.micv.contacto.contactoController;
import dad.javafx.micv.model.CV;
import dad.javafx.micv.personal.PersonalController;
import dad.javafx.micv.utils.JSONUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	// controllers
	
	private PersonalController personalController = new PersonalController();
	// TODO resto de controladores
	private contactoController contactoController=new contactoController();
	private FormationController formationController=new FormationController();
	private ExperienciaController experienciaController=new ExperienciaController();
	private ConocimientoController cononocimientoController=new ConocimientoController();
	// model
	
	private ObjectProperty<CV> cv = new SimpleObjectProperty<>();
	
	// view
	private Stage auxCerrar;
	
    @FXML
    private BorderPane view;

    @FXML
    private Tab personalTab;

    @FXML
    private Tab contactoTab;

    @FXML
    private Tab formacionTab;

    @FXML
    private Tab experienciaTab;

    @FXML
    private Tab conocimientosTab;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personalTab.setContent(personalController.getView());
		contactoTab.setContent(contactoController.getView());
		this.formacionTab.setContent(formationController.getView());
		this.experienciaTab.setContent(experienciaController.getView());
		this.conocimientosTab.setContent(cononocimientoController.getView());
		
		cv.addListener((o, ov, nv) -> onCVChanged(o, ov, nv));
		
		cv.set(new CV());
	}
	
	private void onCVChanged(ObservableValue<? extends CV> o, CV ov, CV nv) {

		if (ov != null) {
			personalController.personalProperty().unbind();
			contactoController.contactoPropertyProperty().unbind();
			formationController.formacionProperty().unbind();
			experienciaController.expformacionProperty().unbind();
			cononocimientoController.conocimientosModeloProperty().unbind();
		}
		
		if (nv != null) {
			personalController.personalProperty().bind(nv.personalProperty());
			contactoController.contactoPropertyProperty().bind(nv.contactoProperty());
			formationController.formacionProperty().bind(nv.formacionesProperty());
			experienciaController.expformacionProperty().bind(nv.experienciaProperty());
			cononocimientoController.conocimientosModeloProperty().bind(nv.conocimientoProperty());
		}
		
	}

	public BorderPane getView() {
		return view;
	}

    @FXML
    void onAbrirAction(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Abrir un currículum");
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Currículum (*.cv)", "*.cv"));
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos (*.*)", "*.*"));
    	File cvFile = fileChooser.showOpenDialog(App.getPrimaryStage());
    	if (cvFile != null) {
    		
    		try {
    			cv.set(JSONUtils.fromJson(cvFile, CV.class));
    			App.info("Se ha abierto el fichero " + cvFile.getName() + " correctamente.", "Pues eso...");
			} catch (JsonSyntaxException|IOException e) {
				App.error("Ha ocurrido un error al abrir " + cvFile, e.getMessage());
			}
    		
    	}
    	
    }

    @FXML
    void onAcercaDeAction(ActionEvent event) {
    	Stage stage=(Stage)view.getScene().getWindow();

    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(stage);
    	alert.setTitle("Acerca de MiCV");
    	alert.setHeaderText("Proyecto MiCV");
    	alert.setContentText("que dios se apiade ");   
    	alert.showAndWait();
    }

    @FXML
    void onCerrarAction(ActionEvent event) {
    	this.auxCerrar.close();
    }

    @FXML
    void onGuardarAction(ActionEvent event) {
    	onGuardarComoAction(event);
    }
   public void setAuxCerrar(Stage u) {
    	auxCerrar=u;
    }

    @FXML
    void onGuardarComoAction(ActionEvent event) {

	    	FileChooser fileChooser = new FileChooser();
	    	fileChooser.setTitle("Guardar un currículum");
	    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Currículum (*.cv)", "*.cv"));
	    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos (*.*)", "*.*"));
	    	File cvFile = fileChooser.showSaveDialog(App.getPrimaryStage());
	    	if (cvFile != null) {

	    		try {
	    			JSONUtils.toJson(cvFile, cv.get());
				} catch (JsonSyntaxException|IOException e) {
					App.error("Ha ocurrido un error al guardar " + cvFile, e.getMessage());
				}
	    		
	    	}
	    	else {
	    		try {
					JSONUtils.toJson(cvFile, cv.get());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	
    }

    @FXML
    void onNuevoAction(ActionEvent event) {
    	try {

    	cv.set(new CV());
    	}catch(Exception e) {
    		System.out.println("Nuevo");
    	}
    }
    
}
