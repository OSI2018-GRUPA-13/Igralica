package igralica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GlavnaStranaKontroler {

	@FXML
	private ImageView ivAvatar;

	@FXML
	private HBox hbKontejnerZaLabelu;

	@FXML
	private Label lblIme;

	@FXML
	private Button btnPogodi;

	@FXML
	private Button btnInfoPogodi;

	@FXML
	private Button btnKviz;

	@FXML
	private Button btnInfoKviz;

	@FXML
	private Button btnLoto;

	@FXML
	private Button btnInfoLoto;

	@FXML
	private Button btnMojBroj;

	@FXML
	private Button btnInfoMojBroj;

	@FXML
	private ComboBox<?> cbRangLista;

	@FXML
	private TableView<?> tblRangLista;

	@FXML
	private TableColumn<?, ?> tcPozicija;

	@FXML
	private TableColumn<?, ?> tcImeIgraca;

	@FXML
	private TableColumn<?, ?> tcDatumIgranja;

	@FXML
	private TableColumn<?, ?> tcBrojBodova;

	@FXML
	private Button btnSacuvajRezultat;

	@FXML
	private Button btnIzadji;

	@FXML
	void akcijaPogodi(ActionEvent event) {

	}

	@FXML
	void infoPogodi(ActionEvent event) {

	}

	@FXML
	void akcijaKviz(ActionEvent event) {

	}

	@FXML
	void infoKviz(ActionEvent event) {

	}

	@FXML
	void akcijaLoto(ActionEvent event) {

	}

	@FXML
	void infoLoto(ActionEvent event) {

	}

	@FXML
	void akcijaMojBroj(ActionEvent event) {

	}

	@FXML
	void infoMojBroj(ActionEvent event) {

	}

	@FXML
	void izaberiRangListu(ActionEvent event) {

	}

	@FXML
	void sacuvajRezultat(ActionEvent event) {

	}

	@FXML
	void izadji(ActionEvent event) {

	}

}
