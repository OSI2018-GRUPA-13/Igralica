package igralica.controller;

import igralica.dialogs.ObavjestenjaDijalog;
import igralica.model.Kljuc.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import static igralica.controller.GlavnaStranaKontroler.tipIgre;
import static igralica.controller.GlavnaStranaKontroler.mapaKljuceva;

public class UnosKljucaKontroler {

	@FXML
	private TitledPane tpVrstaIgre;

	@FXML
	private TextField tfKljuc;

	@FXML
	private Button btnPotvrdi;

	@FXML
	private Button btnOtkazi;

	@FXML
	void initialize(ActionEvent event) {
		tpVrstaIgre.setText(tipIgre.toUpperCase());
	}

	@FXML
	void potvrdi(ActionEvent event) {
		String obavjestenje = "";
		String kljuc = tfKljuc.getText();
		if (mapaKljuceva.containsKey(kljuc)) {
			if (mapaKljuceva.get(kljuc).getStatusKLjuca().equals(StatusKljuca.DOSTUPAN)
					&& mapaKljuceva.get(kljuc).getTipIgre().equals(tipIgre)) {

				mapaKljuceva.get(kljuc).aktivirajKljuc();
				obavjestenje = "Uspješno se aktivirali igru " + tipIgre + "!";
				ObavjestenjaDijalog.showInfoDialog("Obavjestenje", "Uneseni kljuÄ� je ispravan!", obavjestenje);
				close();
			} else {
				obavjestenje = "Niste aktivirali igru " + tipIgre + "!";
				ObavjestenjaDijalog.showInfoDialog("Obavjestenje", "Uneseni kljuÄ� je prethodno iskoriÅ¡ten!",
						obavjestenje);
			}
		} else {
			obavjestenje = "Niste aktivirali igru " + tipIgre + "!";
			ObavjestenjaDijalog.showInfoDialog("Obavjestenje", "Uneseni kljuÄ� nije ispravan!", obavjestenje);
		}
	}

	@FXML
	void otkazi(ActionEvent event) {
		close();
	}

	/**
	 * Zatvara scenu datog kontrolera
	 */
	private void close() {
		Stage stage = (Stage) btnOtkazi.getScene().getWindow();
		stage.close();
	}
}
