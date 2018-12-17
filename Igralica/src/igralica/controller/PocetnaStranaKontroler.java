package igralica.controller;

import igralica.dialogs.RegistracijaKorisnikaDijalog;
import igralica.model.Korisnik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PocetnaStranaKontroler {

	@FXML
	private Label lblStatus;

	@FXML
	private TextField txtKorisnickoIme;

	@FXML
	private PasswordField txtLozinka;

	@FXML
	private Button btnPrijava;

	@FXML
	private Button btnRegistracija;

	@FXML
	void Prijava(ActionEvent event) {

	}

	@FXML
	void Registracija(ActionEvent event) {
		dodajKorisnika(new Korisnik());
	}

	private void dodajKorisnika(Korisnik korisnik) {
		RegistracijaKorisnikaDijalog dialog = new RegistracijaKorisnikaDijalog("Dodaj ", korisnik);
		dialog.kreirajIPrikazi();
	}

}
