package igralica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LotoKontroler {

	@FXML
	private TextField tf1;

	@FXML
	private TextField tf2;

	@FXML
	private TextField tf3;

	@FXML
	private TextField tf4;

	@FXML
	private TextField tf5;

	@FXML
	private TextField tf6;

	@FXML
	private TextField tf7;

	@FXML
	private TextArea taIzvuceniBrojevi;

	@FXML
	private Button btnProvjeriUnos;

	@FXML
	private Button btnPokreniSimulaciju;

	@FXML
	private Button btnIzadji;

	@FXML
	private Label lblBrojPogodaka;

	@FXML
	private Label lblBrojPoena;

	@FXML
	void initialize() {
		btnPokreniSimulaciju.setDisable(true);
		btnIzadji.setDisable(true);
	}

	@FXML
	void provjeriUnos(ActionEvent event) {

	}

	@FXML
	void pokreniSimulaciju(ActionEvent event) {

	}

	@FXML
	void izadji(ActionEvent event) {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
