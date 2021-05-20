/** 
 * @author Xabier, Catarina, Anthonny
 */

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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

		VBox panel = new VBox();

		txtBuscar = new TextField("Buscar");
		txtBuscar.getStyleClass().add("text-field");
		txtBuscar.setMinHeight(40);
		VBox.setMargin(txtBuscar, new Insets(0, 0, 40, 0));

		rbtListarTodo = new RadioButton("Listar toda la agenda");
		rbtListarTodo.getStyleClass().add("radio-button");

		rbtListarSoloNumero = new RadioButton("Listar nº contactos");
		rbtListarSoloNumero.getStyleClass().add("radio-button");

		ToggleGroup grupo = new ToggleGroup();
		rbtListarTodo.setToggleGroup(grupo);
		rbtListarSoloNumero.setToggleGroup(grupo);

		btnListar = new Button("Listar");
		btnListar.getStyleClass().add("botones");
		btnListar.setPrefWidth(250);
		VBox.setMargin(btnListar, new Insets(0, 0, 40, 0));

		btnPersonalesEnLetra = new Button("Contactos personales en letra");
		btnPersonalesEnLetra.getStyleClass().add("botones");
		btnPersonalesEnLetra.setPrefWidth(250);

		btnPersonalesOrdenadosPorFecha = new Button("Contactos personales\n ordenados por fecha");
		btnPersonalesOrdenadosPorFecha.getStyleClass().add("botones");
		btnPersonalesOrdenadosPorFecha.setPrefWidth(250);

		btnClear = new Button("Clear");
		btnClear.getStyleClass().add("botones");
		btnClear.setPrefWidth(250);
		VBox.setMargin(btnClear, new Insets(40, 0, 0, 0));

		btnSalir = new Button("Salir");
		btnSalir.getStyleClass().add("botones");
		btnSalir.setPrefWidth(250);

		txtBuscar = new TextField("Buscar");
		txtBuscar.getStyleClass().add("text-field");
		txtBuscar.setMinHeight(40);
		VBox.setMargin(txtBuscar, new Insets(0, 0, 40, 0));

		rbtListarTodo = new RadioButton("Listar toda la agenda");
		rbtListarTodo.getStyleClass().add("radio-button");

		rbtListarSoloNumero = new RadioButton("Listar nº contactos");
		rbtListarSoloNumero.getStyleClass().add("radio-button");

		btnListar = new Button("Listar");
		btnListar.getStyleClass().add("botones");
		btnListar.setPrefWidth(250);
		btnListar.setOnAction(e -> listar());
		VBox.setMargin(btnListar, new Insets(0, 0, 40, 0));

		btnPersonalesEnLetra = new Button("Contactos personales en letra");
		btnPersonalesEnLetra.getStyleClass().add("botones");
		btnPersonalesEnLetra.setPrefWidth(250);
		btnPersonalesEnLetra.setOnAction(e -> contactosPersonalesEnLetra());

		btnPersonalesOrdenadosPorFecha = new Button("Contactos personales\n ordenados por fecha");
		btnPersonalesOrdenadosPorFecha.getStyleClass().add("botones");
		btnPersonalesOrdenadosPorFecha.setPrefWidth(250);
		btnPersonalesOrdenadosPorFecha.setOnAction(e -> personalesOrdenadosPorFecha());

		btnClear = new Button("Clear");
		btnClear.getStyleClass().add("botones");
		btnClear.setPrefWidth(250);
		btnClear.setOnAction(e -> clear());
		VBox.setMargin(btnClear, new Insets(40, 0, 0, 0));

		btnSalir = new Button("Salir");
		btnSalir.getStyleClass().add("botones");
		btnSalir.setPrefWidth(250);
		btnSalir.setOnAction(e -> salir());
		btnSalir.setPrefWidth(250);

		txtBuscar = new TextField("Buscar");
		txtBuscar.getStyleClass().add("text-field");
		txtBuscar.setMinHeight(40);
		VBox.setMargin(txtBuscar, new Insets(0, 0, 40, 0));

		rbtListarTodo = new RadioButton("Listar toda la agenda");
		rbtListarTodo.getStyleClass().add("radio-button");

		rbtListarSoloNumero = new RadioButton("Listar nº contactos");
		rbtListarSoloNumero.getStyleClass().add("radio-button");

		btnListar = new Button("Listar");
		btnListar.getStyleClass().add("botones");
		btnListar.setPrefWidth(250);
		btnListar.setOnAction(e -> listar());
		VBox.setMargin(btnListar, new Insets(0, 0, 40, 0));

		btnPersonalesEnLetra = new Button("Contactos personales en letra");
		btnPersonalesEnLetra.getStyleClass().add("botones");
		btnPersonalesEnLetra.setPrefWidth(250);
		btnPersonalesEnLetra.setOnAction(e -> contactosPersonalesEnLetra());

		btnPersonalesOrdenadosPorFecha = new Button("Contactos personales\n ordenados por fecha");
		btnPersonalesOrdenadosPorFecha.getStyleClass().add("botones");
		btnPersonalesOrdenadosPorFecha.setPrefWidth(250);
		btnPersonalesOrdenadosPorFecha.setOnAction(e -> personalesOrdenadosPorFecha());

		btnClear = new Button("Clear");
		btnClear.getStyleClass().add("botones");
		btnClear.setPrefWidth(250);
		btnClear.setOnAction(e -> clear());
		VBox.setMargin(btnClear, new Insets(40, 0, 0, 0));

		btnSalir = new Button("Salir");
		btnSalir.getStyleClass().add("botones");
		btnSalir.setPrefWidth(250);
		btnSalir.setOnAction(e -> salir());

		panel.setSpacing(10);
		panel.setPadding(new Insets(10));
		panel.getChildren().addAll(txtBuscar, rbtListarTodo, rbtListarSoloNumero, btnListar, btnPersonalesEnLetra,
				btnPersonalesOrdenadosPorFecha, btnClear, btnSalir);
		return panel;

	}

	private GridPane crearPanelLetras() {
		GridPane panel = new GridPane();
//		panel.setGridLinesVisible(true);
		panel.setPadding(new Insets(10));
		panel.setHgap(5);
		panel.setVgap(5);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(10);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(10);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(10);
		;
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setPercentWidth(10);
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setPercentWidth(10);
		ColumnConstraints col6 = new ColumnConstraints();
		col6.setPercentWidth(10);
		ColumnConstraints col7 = new ColumnConstraints();
		col7.setPercentWidth(10);
		ColumnConstraints col8 = new ColumnConstraints();
		col8.setPercentWidth(10);
		ColumnConstraints col9 = new ColumnConstraints();
		col9.setPercentWidth(10);
		ColumnConstraints col10 = new ColumnConstraints();
		col10.setPercentWidth(10);
		ColumnConstraints col11 = new ColumnConstraints();
		col11.setPercentWidth(10);
		ColumnConstraints col12 = new ColumnConstraints();
		col12.setPercentWidth(10);
		ColumnConstraints col13 = new ColumnConstraints();
		col13.setPercentWidth(10);
		ColumnConstraints col14 = new ColumnConstraints();
		col14.setPercentWidth(10);

		RowConstraints fila1 = new RowConstraints();
		fila1.setPercentHeight(100);
		RowConstraints fila2 = new RowConstraints();
		fila2.setPercentHeight(100);

		panel.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12,
				col13, col14);
		panel.getRowConstraints().addAll(fila1, fila2);

		String abecedario = "abcdefghijklmnñopqrstuvwxyz";
		Button btnLetra;
		int c = 0;
		for (int i = 0; i < 28; i++) {
			int f = 0;
			while (f < 14 && c <= abecedario.length() - 1) {
				char letra = abecedario.charAt(c);
				panel.add(btnLetra = new Button(String.valueOf(letra).toUpperCase()), f, i);
				btnLetra.getStyleClass().add("botonletra");
				btnLetra.setMaxWidth(Double.MAX_VALUE);
				btnLetra.setOnAction(e -> contactosEnLetra(letra));
				f++;
				c++;
			}
		}
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
		if (agenda.felicitar().isEmpty()) {
			areaTexto.appendText("No hay nadie para felicitar");
		} else {
			areaTexto.appendText("Hoy felicidamos a:\n" + agenda.felicitar());
		}

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
