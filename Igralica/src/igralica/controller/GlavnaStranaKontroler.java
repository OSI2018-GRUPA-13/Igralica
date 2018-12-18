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

import igralica.model.Kljuc;
import igralica.dialogs.ObavjestenjaDijalog;
import igralica.utility.FileLogger;
import igralica.utility.FxmlLoader;
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

	public static String tipIgre;
	public static Label lblBrojBodovaNaProfilu;
	public static HashMap<String, Kljuc> mapaKljuceva = new HashMap<String, Kljuc>();

	@FXML
	void initialize() throws MalformedURLException {

		mapaBodovaNaProfilu = ucitajBodoveNaProfilu(PUTANJA_DO_BODOVA_KORISNIKA);
		korisnik.setBrojPoenaNaProfilu(mapaBodovaNaProfilu.get(korisnik.getKorisnickoIme()));

		krerijaLabeluZaBodoveNaProfilu();
		lblBrojBodovaNaProfilu.setText(Integer.toString(korisnik.getBrojPoenaNaProfilu()));

		mapaKljuceva = ucitajKljuceve(PUTANJA_DO_LISTE_KLJUCEVA);

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
		if (daLiJeIgraAktivirana("Loto")) {
			if (daLiImaDovoljnoBodova("Loto")) {
				kreirajIgru("Loto");
				FxmlLoader.load(getClass(), "/igralica/view/Loto.fxml", "Loto");
			} else {
				ObavjestenjaDijalog.showWarningDialog("Upozorenje", "Upozorenje tokom pokretanja igre.",
						"Nije moguće pokrenuti igru \"Loto\" \nNemate doboljno bodova na profilu!");
			}
		} else {
			setTipIgre("Loto");
			ObavjestenjaDijalog.showWarningDialog("Upozorenje", "Upozorenje tokom pokretanja igre.",
					"Nije moguće pokrenuti igru \"Loto\" \nIgra do sada nije aktivirana ili je trajanje ključa isteklo!");
			FxmlLoader.load(getClass(), "/igralica/view/UnosKljuca.fxml", "Unos kljuca");
		}
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
		sacuvajKljuceve(mapaKljuceva, PUTANJA_DO_LISTE_KLJUCEVA);
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

	private boolean daLiJeIgraAktivirana(String tipIgre) {
		// NE ZAVRSENO
		return true;
	}

	private boolean daLiImaDovoljnoBodova(String tipIgre) {
		// NE ZAVRSENO
		return true;
	}

	private void kreirajIgru(String tipIgre) {
		// NE ZAVRSENO
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

	/*
	 * Serijalizacija kljuceva
	 */
	private static boolean sacuvajKljuceve(HashMap<String, Kljuc> mapaKljuceva, String putanja) {
		File putanjaFile = new File(putanja);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(putanjaFile))) {
			oos.writeObject(new HashMap<String, Kljuc>(mapaKljuceva));
			oos.close();
			return true;
		} catch (IOException ex) {
			FileLogger.log(Level.SEVERE, null, ex);
			ObavjestenjaDijalog.showErrorDialog("Greška", "Greška tokom serijalizacije ključeva.",
					"Nije moguće sačuvati ključeve na sljedećoj putanji: \n" + putanjaFile.getAbsolutePath());
		}
		return false;
	}

	/*
	 * Deserijalizacija kljuceva
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Kljuc> ucitajKljuceve(String putanja) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(putanja)))) {
			HashMap<String, Kljuc> mapa = (HashMap<String, Kljuc>) ois.readObject();
			return mapa;
		} catch (EOFException ex) {
			FileLogger.log(Level.WARNING, null, ex);
			return new HashMap<String, Kljuc>();
		} catch (ClassNotFoundException | IOException ex) {
			FileLogger.log(Level.WARNING, null, ex);
			ObavjestenjaDijalog.showWarningDialog("Upozorenje", "Upozorenje tokom deserijalizacije kljuceva.",
					"Nije moguće učitati kljuceve sa sljedeće putanje: \n" + new File(putanja).getAbsolutePath());
		}
		return null;
	}

	public static String getTipIgre() {
		return tipIgre;
	}

	public static void setTipIgre(String tipIgre) {
		GlavnaStranaKontroler.tipIgre = tipIgre;
	}

}
