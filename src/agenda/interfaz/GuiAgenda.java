package agenda.interfaz;

import java.io.File;
import java.io.IOException;
import java.util.List;

import agenda.io.AgendaIO;
import agenda.modelo.AgendaContactos;
import agenda.modelo.Contacto;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class GuiAgenda extends Application {
	private AgendaContactos agenda;
	private MenuItem itemImportar;
	private MenuItem itemExportarPersonales;
	private MenuItem itemSalir;

	private MenuItem itemBuscar;
	private MenuItem itemFelicitar;

	private MenuItem itemAbout;

	private TextArea areaTexto;

	private RadioButton rbtListarTodo;
	private RadioButton rbtListarSoloNumero;
	private Button btnListar;

	private Button btnPersonalesEnLetra;
	private Button btnPersonalesOrdenadosPorFecha;

	private TextField txtBuscar;

	private Button btnClear;
	private Button btnSalir;

	@Override
	public void start(Stage stage) {
		agenda = new AgendaContactos(); // el modelo

		BorderPane root = crearGui();

		Scene scene = new Scene(root, 1100, 700);
		stage.setScene(scene);
		stage.setTitle("Agenda de contactos");
		scene.getStylesheets().add((getClass().getResource("/application.css").toExternalForm()));
		stage.show();

	}

	private BorderPane crearGui() {
		BorderPane panel = new BorderPane();
		panel.setTop(crearBarraMenu());
		panel.setCenter(crearPanelPrincipal());
		return panel;
	}

	private BorderPane crearPanelPrincipal() {
		BorderPane panel = new BorderPane();
		panel.setPadding(new Insets(10));
		panel.setTop(crearPanelLetras());

		areaTexto = new TextArea();
		areaTexto.getStyleClass().add("textarea");
		panel.setCenter(areaTexto);

		panel.setLeft(crearPanelBotones());
		return panel;
	}

	private VBox crearPanelBotones() {
		// a completar
		VBox panel = new VBox();

		return panel;
	}

	private GridPane crearPanelLetras() {
		// a completar
		GridPane panel = new GridPane();
		panel.setGridLinesVisible(true);
		return panel;
	}

	private MenuBar crearBarraMenu() {

		MenuBar barra = new MenuBar();
		Menu menu = new Menu("Archivo");
		itemImportar = new MenuItem("Importar Agenda");
		itemImportar.setAccelerator(KeyCombination.keyCombination("Ctrl + l"));
		itemImportar.setOnAction(ev -> importarAgenda());
		itemImportar.setDisable(false);

		itemExportarPersonales = new MenuItem("Exportar agenda");
		itemExportarPersonales.setAccelerator(KeyCombination.keyCombination("Ctrl + E"));
		itemExportarPersonales.setOnAction(ev -> {
			exportarPersonales();
		});
		itemExportarPersonales.setDisable(true);

		itemSalir = new MenuItem("Salir");
		itemSalir.setAccelerator(KeyCombination.keyCombination("Ctrl + S"));
		itemSalir.setOnAction(salir -> salir());
		menu.getItems().addAll(itemImportar, itemExportarPersonales, itemSalir);
		barra.getMenus().add(menu);

		Menu menu2 = new Menu("Opciones");
		itemBuscar = new MenuItem("Buscar");
		itemBuscar.setAccelerator(KeyCombination.keyCombination("Ctrl + B"));
		itemBuscar.setOnAction(buscar -> buscar());

		itemFelicitar = new MenuItem("Felicitar");
		itemFelicitar.setAccelerator(KeyCombination.keyCombination("Ctrl + F"));
		itemFelicitar.setOnAction(felicitar -> felicitar());

		menu2.getItems().addAll(itemBuscar, itemFelicitar);
		barra.getMenus().add(menu2);

		Menu menu3 = new Menu("Help");
		itemAbout = new MenuItem("About");
		itemAbout.setAccelerator(KeyCombination.keyCombination("Ctrl + A"));
		itemAbout.setOnAction(about -> about());
		menu3.getItems().add(itemAbout);
		barra.getMenus().add(menu3);
		return barra;
	}

	private void importarAgenda() {
		FileChooser fichero = new FileChooser();
		fichero.setTitle("Ingresa el nombre del fichero");
		fichero.getExtensionFilters().addAll(new ExtensionFilter("CSV", "*.csv"));
		fichero.setInitialDirectory(new File("."));
		File f = fichero.showOpenDialog(null);
		if (f != null) {
			System.out.println("Fichero elegido: " + f.getName());
			itemImportar.setDisable(true);
			itemExportarPersonales.setDisable(false);
			// AgendaIO.importar(agenda, f.getAbsolutePath());
			areaTexto.setText("Importada agenda\n\n Número de errores :" + AgendaIO.importar(agenda, f.getName()));
			itemImportar.setDisable(true);
		}

	}

	private void exportarPersonales() {

		FileChooser fichero = new FileChooser();
		fichero.getExtensionFilters().addAll(new ExtensionFilter("txt", "*.txt"));
		File f = fichero.showSaveDialog(null);

		try {
			AgendaIO.exportarPersonales(agenda, f.getName());
			areaTexto.setText("Datos Personales exportados al fichero " + f.getName());
		} catch (IOException e) {
			areaTexto.setText("Error al intentar exportar los datos al fichero" + f.getName() + e.getMessage());

		}

		itemExportarPersonales.setDisable(true);
		itemImportar.setDisable(false);

	}

	/**
	 *  
	 */
	private void listar() {
		clear();
		// a completar

	}

	private void personalesOrdenadosPorFecha() {
		clear();
		// a completar

	}

	private void contactosPersonalesEnLetra() {
		clear();
		// a completar

	}

	private void contactosEnLetra(char letra) {
		clear();
		// a completar
	}

	private void felicitar() {
		clear();
		String str = "";
		str += java.time.LocalDate.now() + "\n";
		agenda.felicitar();
		areaTexto.setText(str.toString());

	}

	private void buscar() {
		clear();
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Mensaje");

		if (txtBuscar.getText().isBlank()) {
			alerta.setContentText("Teclee un valor");

		} else {
			List<Contacto> str;
			str = agenda.buscarContactos(txtBuscar.getText());
			if (str == null) {
				alerta.setContentText("La agenda no tiene contactos");
			} else {
				alerta.setContentText("La Agenda tiene contactos");
			}
		}

		cogerFoco();

	}

	private void about() {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("About de Agenda");
		alerta.setHeaderText("");
		alerta.setContentText("Mi agenda contactos");

		DialogPane dialogPane = alerta.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		alerta.showAndWait();

	}

	private void clear() {
		areaTexto.setText("");
	}

	private void salir() {
		Platform.exit();
	}

	private void cogerFoco() {
		txtBuscar.requestFocus();
		txtBuscar.selectAll();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
