package g52977.sortingRace.view;

import g52977.sortingRace.controller.Controller;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import g52977.sortingRace.model.Sort;

/**
 * The fx view of the application.
 *
 * @author 52977
 */
public class FXMLController implements PropertyChangeListener {

	private Controller controller;
	private final Stage stage;
	private final XYChart.Series bubbleSeries = new XYChart.Series();
	private final XYChart.Series mergeSeries = new XYChart.Series();
	private final XYChart.Series quickSeries = new XYChart.Series();
	private final XYChart.Series insertionSeries = new XYChart.Series();
	private double barProgression;
	LocalTime begin;
	LocalTime end;
	Duration executionTime;

	@FXML
	private ChoiceBox configurationChoice;
	@FXML
	private ChoiceBox sortChoice;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label leftStatus;
	@FXML
	private Label rightStatus;
	@FXML
	private Spinner threadSpinner;
	@FXML
	private LineChart chart;
	@FXML
	private TableView table;
	@FXML
	private TableColumn nameCol;
	@FXML
	private TableColumn lengthCol;
	@FXML
	private TableColumn operationsCol;
	@FXML
	private TableColumn durationCol;

	/**
	 * Constructor of FXMLController.
	 *
	 * @param stage The application stage.
	 * @throws IOException An exception thrown if there is an error during
	 * the load of the fxml view.
	 */
	public FXMLController(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sort.fxml"));
		loader.setController(this);
		VBox root = loader.load();
		Scene scene = new Scene(root);
		this.stage = stage;
		stage.setScene(scene);
	}

	/**
	 * Initialize the view elements.
	 */
	public void initialize() {
		initConfigurations();
		initSorts();
		initTable();
		initChart();

		leftStatus.setText("Nombre de threads actifs : "
			+ String.valueOf(Thread.activeCount()));		
		threadSpinner.setValueFactory(
			new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
	}
	
	/**
	 * Initializes the configurationChoice choiceBox.
	 */
	private void initConfigurations() {
		String[] configurations = {
			"Very easy : 0 - 100",
			"Easy : 0 - 1000",
			"Hard : 0 - 10000",
			"Very Hard : 0 - 100000"
		};
		configurationChoice.setItems(FXCollections.observableArrayList(
			configurations));
		configurationChoice.setValue("Very easy : 0 - 100");
	}
	
	/**
	 * Initializes the sortChoice choiceBox.
	 */
	private void initSorts() {
		String[] sorts = {
			"Tri bulle",
			"Tri fusion",
			"Tri rapide",
			"Tri insertion"
		};
		sortChoice.setItems(FXCollections.observableArrayList(sorts));
		sortChoice.setValue("Tri bulle");
	}
	
	/**
	 * Initializes the tableView.
	 */
	private void initTable() {
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		lengthCol.setCellValueFactory(new PropertyValueFactory<>("length"));
		operationsCol.setCellValueFactory(new PropertyValueFactory<>("operations"));
		durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
	}
	
	/**
	 * Initializes the lineChart.
	 */
	private void initChart() {
		chart.getData().add(bubbleSeries);
		chart.getData().add(mergeSeries);
		chart.getData().add(quickSeries);
		chart.getData().add(insertionSeries);
		bubbleSeries.setName("Tri Bulle");
		mergeSeries.setName("Tri Fusion");
		quickSeries.setName("Tri Rapide");
		insertionSeries.setName("Tri Insertion");
	}

	/**
	 * Shows the stage of the view.
	 */
	public void showStage() {
		stage.show();
	}

	/**
	 * Setter of controller.
	 *
	 * @param controller The controller of the application.
	 */
	public void setController(Controller controller) {
		this.controller = controller;
		controller.addModelListener(this);
	}

	/**
	 * Asks to the controller to start the sorting when the start button is
	 * clicked.
	 *
	 * @param event The click event.
	 */
	@FXML
	public void handleStartButton(ActionEvent event) {
		begin = LocalTime.now();
		String arrayLength = (String) configurationChoice.getValue();
		String[] lengths = arrayLength.split("- ");
		barProgression = 0;

		progressBar.setProgress(barProgression);
		controller.startSorting(Integer.parseInt(lengths[1]),
			(String) sortChoice.getValue(), (int) threadSpinner.getValue());
	}

	/**
	 * Quits the application when quit button is clicked.
	 *
	 * @param event The click event.
	 */
	@FXML
	public void handleQuitItem(ActionEvent event) {
		Platform.exit();
	}

	/**
	 * Updates the view whenever a sorting is done.
	 *
	 * @param pce An object of type Sort.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent pce) {

		Platform.runLater(() -> {
			Sort sort = (Sort) pce.getNewValue();
			table.getItems().add(sort);

			LineChartUpdate(sort);

			leftStatus.setText("Nombre de threads actifs : "
				+ String.valueOf(Thread.activeCount()));
			barProgression += 1.0 / 11;
			progressBar.setProgress(barProgression);
			if (barProgression > 0.99) {
				end = LocalTime.now();
				executionTime = Duration.between(begin, end);
				rightStatus.setText("Dérnière exécution | Début : "
					+ String.valueOf(begin) + " - Fin : " + String.valueOf(end)
					+ " - Durée : " + executionTime.toMillis() + " ms");
			}
		});
	}

	/**
	 * Updates the linechart according the the sorting data.
	 * 
	 * @param sort The sorting data.
	 */
	private void LineChartUpdate(Sort sort) {
		switch (sort.getName()) {
			case "BUBBLE" -> bubbleSeries.getData().add(new XYChart.Data(
					sort.getLength(), sort.getOperations()));
			case "MERGE" -> mergeSeries.getData().add(new XYChart.Data(
					sort.getLength(), sort.getOperations()));
			case "QUICK" -> quickSeries.getData().add(new XYChart.Data(
					sort.getLength(), sort.getOperations()));
			case "INSERTION" -> insertionSeries.getData().add(new XYChart.Data(
					sort.getLength(), sort.getOperations()));
		}
	}

}
