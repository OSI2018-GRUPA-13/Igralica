package igralica.controller;

import static igralica.controller.PocetnaStranaKontroler.korisnik;
import static igralica.dialogs.RegistracijaKorisnikaDijalog.mapaBodovaNaProfilu;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.logging.Level;

import igralica.dialogs.ObavjestenjaDijalog;
import igralica.utility.FileLogger;
import igralica.utility.Putanje;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class GlavnaStranaKontroler implements Putanje {

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

	public static Label lblBrojBodovaNaProfilu;

	@FXML
	void initialize() throws MalformedURLException {

		mapaBodovaNaProfilu = ucitajBodoveNaProfilu(PUTANJA_DO_BODOVA_KORISNIKA);
		korisnik.setBrojPoenaNaProfilu(mapaBodovaNaProfilu.get(korisnik.getKorisnickoIme()));

		krerijaLabeluZaBodoveNaProfilu();
		lblBrojBodovaNaProfilu.setText(Integer.toString(korisnik.getBrojPoenaNaProfilu()));

		lblIme.setText(korisnik.getKorisnickoIme());

		prikazSlike();

	}

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
		mapaBodovaNaProfilu.put(korisnik.getKorisnickoIme(), korisnik.getBrojPoenaNaProfilu());
		sacuvajBodoveNaProfilu(mapaBodovaNaProfilu, PUTANJA_DO_BODOVA_KORISNIKA);
		System.exit(0);
	}

	private void prikazSlike() throws MalformedURLException {
		ivAvatar.setVisible(true);
		File file = korisnik.getFotografija();
		String slikaKorisnika = file.toURI().toURL().toString();

		Image slika = new Image(slikaKorisnika, true);
		ivAvatar.setSmooth(true);
		ivAvatar.setCache(true);
		ivAvatar.setImage(slika);
	}

	private void krerijaLabeluZaBodoveNaProfilu() {
		lblBrojBodovaNaProfilu = new Label(Integer.toString(korisnik.getBrojPoenaNaProfilu()));
		lblBrojBodovaNaProfilu.setFont(new Font("System", 17));
		lblBrojBodovaNaProfilu.setTextFill(Color.web("#e42f06"));
		lblBrojBodovaNaProfilu.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
		lblBrojBodovaNaProfilu.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		hbKontejnerZaLabelu.getChildren().add(lblBrojBodovaNaProfilu);
	}

	/*
	 * Serijalizacija broja bodova na profilu
	 */
	public static boolean sacuvajBodoveNaProfilu(HashMap<String, Integer> mapaBodova, String putanja) {
		File putanjaFile = new File(putanja);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(putanjaFile))) {
			oos.writeObject(new HashMap<String, Integer>(mapaBodova));
			oos.close();
			return true;
		} catch (IOException ex) {
			FileLogger.log(Level.SEVERE, null, ex);
			ObavjestenjaDijalog.showErrorDialog("Greška", "Greška tokom serijalizacije bodova na profilu korisnika.",
					"Nije moguće sačuvati bodove na sljedećoj putanji: \n" + putanjaFile.getAbsolutePath());
		}
		return false;
	}

	/*
	 * Deserijalizacija broja bodova na profilu
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Integer> ucitajBodoveNaProfilu(String putanja) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(putanja)))) {
			HashMap<String, Integer> mapa = (HashMap<String, Integer>) ois.readObject();
			return mapa;
		} catch (EOFException ex) {
			// izuzetak se baca ako je na pocetku data datoteka prazna, sto je i
			// slucaj za prvog registrovanog korisnika
			FileLogger.log(Level.WARNING, null, ex);
			return new HashMap<String, Integer>();
		} catch (ClassNotFoundException | IOException ex) {
			FileLogger.log(Level.WARNING, null, ex);
			ObavjestenjaDijalog.showWarningDialog("Upozorenje",
					"Upozorenje tokom deserijalizacije bodova na profilu korisnika.",
					"Nije moguće učitati bodove sa sljedeće putanje: \n" + new File(putanja).getAbsolutePath());
		}
		return null;
	}

}
