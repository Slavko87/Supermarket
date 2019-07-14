package forme;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import komunikacija.KlijentskiZahtev;
import komunikacija.KomunikacijaSaServerom;
import komunikacija.ServerskiOdgovor;
import konstante.Operacije;
import supermarket.Artikal;
import supermarket.Magacin;
import supermarket.StavkaZaRacun;

@SuppressWarnings("serial")
public class KlijentskaForma extends JFrame 
{
	private Scene scena;
	private TextField sifraArtikla = new TextField();
	private TextField nazivArtikla = new TextField();;
	private TextField barKodArtikla = new TextField();
	private TextField kolicina = new TextField();
	
	private ComboBox<Magacin> comboListaMagacina = new ComboBox<>();
	private ComboBox<Artikal> comboListaArtikla = new ComboBox<>();
	
	private List<Artikal> listaArtikla = new ArrayList<>();
	private List<Magacin> listaMagacina = new ArrayList<>();
	private List<StavkaZaRacun> listaStavki = new ArrayList<>();
	
	private Button dodaj = new Button("Dodaj");
	private Button ukloni = new Button("Ukloni");
	private Button izdajRacun = new Button("Izdaj Racun");
	
	private TabelaArtikla tabelaArtikla;
	
	private Label trenutnoZaduzenje = new Label("Trenutno zaduzenje iznosi: 0.0");
		
	public KlijentskaForma ()
	{
		super("KASA");
		popuniListuMagacina();
		popuniListuArtikla();
		tabelaArtikla = new TabelaArtikla(listaStavki);
		
		VBox glavniBox = new VBox(10);
		glavniBox.setPadding(new Insets(10, 10, 10, 10));
		
		GridPane paneMagacin = new GridPane();
		paneMagacin.setVgap(10);
		paneMagacin.setHgap(10);
		paneMagacin.add(comboListaMagacina, 0, 0);
		
		Button zameniMagacin = new Button("Zameni magacin");
		zameniMagacin.setOnAction(e -> popuniListuArtikla());
		paneMagacin.add(zameniMagacin, 1, 0);
		
		glavniBox.getChildren().add(paneMagacin);
		
		GridPane panePretragaArtikla = new GridPane();
		panePretragaArtikla.setVgap(10);
		panePretragaArtikla.setHgap(20);
		panePretragaArtikla.add(new Label("Sifra artikla"), 0, 0);
		panePretragaArtikla.add(new Label("Naziv artikla"), 1, 0);
		panePretragaArtikla.add(new Label("Bar kod artikla"), 2, 0);
		
		sifraArtikla.setMaxWidth(60);
		panePretragaArtikla.add(sifraArtikla, 0, 1);
		sifraArtikla.setOnKeyReleased(e -> {
			if (sifraArtikla.getText().equals("") == false)
				filtrirajPoSifriArtikla(Integer.valueOf(sifraArtikla.getText()));
			else
				popuniListuArtikla();
		});
		
		nazivArtikla.setMaxWidth(300);
		nazivArtikla.setMinWidth(300);
		nazivArtikla.setOnKeyReleased(e -> {
			if (nazivArtikla.getText().equals("") == false)
				filtrirajPoNazivuArtikla(nazivArtikla.getText());
			else
				popuniListuArtikla();
		});
		
		panePretragaArtikla.add(nazivArtikla, 1, 1);
		barKodArtikla.setMaxWidth(150);
		barKodArtikla.setMinWidth(150);
		barKodArtikla.setOnKeyReleased(e -> {
			if (barKodArtikla.getText().equals("") == false)
				filtrirajPoBarKodu(barKodArtikla.getText());
			else
				popuniListuArtikla();
		});
		
		panePretragaArtikla.add(barKodArtikla, 2, 1);
		glavniBox.getChildren().add(panePretragaArtikla);
		
		GridPane paneComboIKolicina = new GridPane();
		paneComboIKolicina.setVgap(10);
		paneComboIKolicina.setHgap(20);
		comboListaArtikla.setMinWidth(450);
		comboListaArtikla.setMaxWidth(450);
		paneComboIKolicina.add(comboListaArtikla, 0, 0);
		paneComboIKolicina.add(new Label("Kol:"), 1, 0);
		kolicina.setMaxWidth(40);
		paneComboIKolicina.add(kolicina, 2, 0);
		glavniBox.getChildren().add(paneComboIKolicina);
		
		GridPane paneDodajUkloni = new GridPane();
		paneDodajUkloni.setVgap(10);
		paneDodajUkloni.setHgap(20);
		dodaj.setMaxWidth(150);
		ukloni.setMaxWidth(150);
		dodaj.setMinWidth(150);
		ukloni.setMinWidth(150);
		
		dodaj.setOnAction(e -> dodajStavkuUTabelu());
		ukloni.setOnAction(e -> ukloniStavkuIzTabele());
		
		paneDodajUkloni.setPadding(new Insets(0, 0, 0, 120));
		paneDodajUkloni.add(dodaj, 0, 0);
		paneDodajUkloni.add(ukloni, 1, 0);
		glavniBox.getChildren().add(paneDodajUkloni);
		
		tabelaArtikla.setMinHeight(360);
		tabelaArtikla.setMaxHeight(360);
		tabelaArtikla.autosize();
		glavniBox.getChildren().add(tabelaArtikla);
		
		GridPane paneIzdajRacun = new GridPane();
		paneIzdajRacun.setVgap(10);
		paneIzdajRacun.setHgap(20);
		paneIzdajRacun.setPadding(new Insets(10, 10, 10, 10));
		trenutnoZaduzenje.setMinWidth(300);
		trenutnoZaduzenje.setMinWidth(300);
		
		paneIzdajRacun.add(trenutnoZaduzenje, 0, 0);
		izdajRacun.setMinWidth(200);
		izdajRacun.setMaxWidth(200);
		izdajRacun.setOnAction(e -> izdajRacun());
		paneIzdajRacun.add(izdajRacun, 1, 0);
		glavniBox.getChildren().add(paneIzdajRacun);
		
		scena = new Scene(glavniBox, 590, 600);
	}
	
	@SuppressWarnings("unchecked")
	private List<Artikal> popuniListuArtikla() 
	{
		KlijentskiZahtev kz = new KlijentskiZahtev();
		kz.setOperacija(Operacije.POPUNI_LISTU_ARTIKLA);
		int idMagacina = comboListaMagacina.getSelectionModel().getSelectedItem().getIdMagacina();
		kz.setObjekat(idMagacina);
		KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
		ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
		
		comboListaArtikla.getItems().clear();
		listaArtikla = new ArrayList<>();
		List<Artikal> lista2 = (List<Artikal>) so.getObjekat();
		listaArtikla.addAll(lista2);
		for (Artikal artikal : listaArtikla) 
		{
			comboListaArtikla.getItems().add(artikal);
		}
		return listaArtikla;
	}
	
	private ArrayList<Magacin> popuniListuMagacina()
	{
		KlijentskiZahtev kz = new KlijentskiZahtev();
		kz.setOperacija(Operacije.POPUNI_LISTU_MAGACINA);
		KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
		
		ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
		comboListaMagacina.getItems().clear();
		listaMagacina.clear();
		
		@SuppressWarnings("unchecked")
		ArrayList<Magacin> lista = (ArrayList<Magacin>) so.getObjekat();
		listaMagacina.addAll(lista);
		for (Magacin magacin : lista) 
		{
			comboListaMagacina.getItems().add(magacin);
		}
		comboListaMagacina.getSelectionModel().selectFirst();
		return lista;
	}

		
	private void filtrirajPoSifriArtikla(int filter)
	{
		comboListaArtikla.getItems().clear();
		for (Artikal artikal : listaArtikla)
		{
			if (artikal.getSifraArtikla() == filter)
			{
				comboListaArtikla.getItems().add(artikal);
				comboListaArtikla.getSelectionModel().selectFirst();
			}
		}
	}
	
	private void filtrirajPoNazivuArtikla(String filter)
	{
		comboListaArtikla.getItems().clear();
		for (Artikal artikal : listaArtikla)
		{
			if (artikal.getNazivArtikla().toUpperCase().contains(filter.toUpperCase()))
			{
				comboListaArtikla.getItems().add(artikal);
				comboListaArtikla.getSelectionModel().selectFirst();
			}
		}
	}
	
	private void filtrirajPoBarKodu(String filter)
	{
		comboListaArtikla.getItems().clear();
		for (Artikal artikal : listaArtikla)
		{
			if (artikal.getBarKodArtikla().toUpperCase().contains(filter.toUpperCase()))
			{
				comboListaArtikla.getItems().add(artikal);
				comboListaArtikla.getSelectionModel().selectFirst();
			}
		}
	}

	private void dodajStavkuUTabelu()
	{
		
		if (kolicina.getText().matches("^[a-zA-Z]+$"))
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Niste lepo uneli kolicinu");
			alert.showAndWait();
			return;
		}
		
		if (kolicina.getText().equals(""))
			kolicina.setText("1");
		
		Artikal a = comboListaArtikla.getValue();
		if (a == null)
			return;
		
		if (proveriStanjeArtikla(a, Integer.valueOf(kolicina.getText()), comboListaMagacina.getValue().getIdMagacina()) == false)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Nema trazena kolicina, u magacinu ima ukupno " + comboListaMagacina.getValue().proveriArtikalUMagacinu(a));
			alert.showAndWait();
			return;
		}
		
		StavkaZaRacun szr = new StavkaZaRacun(a, Integer.valueOf(kolicina.getText()), comboListaMagacina.getValue().getIdMagacina());
		
			for (StavkaZaRacun szr1 : listaStavki) 
			{
				if (szr1.equals(szr))
				{
					if (proveriStanjeArtikla(a, (szr1.getKolicina() + szr.getKolicina()), szr1.getIdMagacina()) == true)
					{
						szr1.setKolicina(szr1.getKolicina() + szr.getKolicina());
						szr1.setUkupnaCena(szr1.getArtikal().getCena() * szr1.getKolicina());
						izracunajUkupanRacun();
						obrisiPoljaZaUnosIRefreshTabele();
						return;
					}
					else
					{
						Alert alert2 = new Alert(Alert.AlertType.WARNING);
						alert2.setHeaderText("Nema trazena kolicina, u magacinu ima ukupno " + comboListaMagacina.getValue().proveriArtikalUMagacinu(a) + " " + szr.getNazivArtikla());
						alert2.showAndWait();
						return;
					}
				}
			}
		
		listaStavki.add(szr);
		izracunajUkupanRacun();
		obrisiPoljaZaUnosIRefreshTabele();
	}
	
	private void ukloniStavkuIzTabele()
	{
		StavkaZaRacun szr = tabelaArtikla.getSelectionModel().getSelectedItem();
		listaStavki.remove(szr);
		obrisiPoljaZaUnosIRefreshTabele();
		izracunajUkupanRacun();
	}
	
	private boolean proveriStanjeArtikla (Artikal artikal, int kolicina, int idMagacina)
	{
		Magacin magacin = null;
		
		for (Magacin m : listaMagacina) 
		{
			if (m.getIdMagacina() == idMagacina)
				magacin = m;
		}
		
		if (magacin == null)
			return false;
		
		int kolicinaUMagacinu = magacin.proveriArtikalUMagacinu(artikal);
		
		if (kolicinaUMagacinu >= kolicina)
			return true;
		else
			return false;
	}
	
	private void izracunajUkupanRacun()
	{
		double ukupanRacun = 0;
		for (StavkaZaRacun stavkaZaRacun : listaStavki) 
		{
			ukupanRacun += stavkaZaRacun.getUkupnaCena();
		}
		trenutnoZaduzenje.setText("Trenutno zaduzenje iznosi: " + ukupanRacun);
	}
	
	public boolean izdajRacun()
	{
		boolean izdatRacun = true;
		boolean ubacenoUbazu;
		popuniListuMagacina();
		
		try 
		{
			for (StavkaZaRacun szr : listaStavki) 
			{
				if (proveriStanjeArtikla(szr.getArtikal(), szr.getKolicina(), szr.getIdMagacina()) == false)
				{
					izdatRacun = false;
					Alert alert2 = new Alert(Alert.AlertType.WARNING);
					alert2.setHeaderText("Nema trazena kolicina, u magacinu ima ukupno " + comboListaMagacina.getValue().proveriArtikalUMagacinu(szr.getArtikal()) + " " + szr.getNazivArtikla());
					alert2.showAndWait();
					
				}
			}
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		
		if (izdatRacun == true)
		{
			ubacenoUbazu = umanjiBrojArtiklaUMagacinu(listaStavki);
			if (ubacenoUbazu == true)
			{
				listaStavki.clear();
				trenutnoZaduzenje.setText("Trenutno zaduzenje iznosi: ");
				obrisiPoljaZaUnosIRefreshTabele();
				popuniListuMagacina();
				popuniListuArtikla();
			}
			else
			{
				Alert alert2 = new Alert(Alert.AlertType.WARNING);
				alert2.setHeaderText("Doslo je do greske prilikom upisa u bazu, racun nije izdat");
				alert2.showAndWait();
			}
		}
		else
		{
			Alert alert2 = new Alert(Alert.AlertType.WARNING);
			alert2.setHeaderText("Doslo je do greske prilikom upisa u bazu, racun nije izdat");
			alert2.showAndWait();
		}
		return izdatRacun;
	}
	
	//ako je izdatRacun = true onda se poziva ova metoda
	public boolean umanjiBrojArtiklaUMagacinu(List<StavkaZaRacun> listaStavki)
	{
		boolean ubaceno;
		KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.SMANJI_ZALIHE_U_MAGACINU, listaStavki);
		KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
		ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
		ubaceno = (boolean) so.getObjekat();
		return ubaceno;
	}
	
	private void obrisiPoljaZaUnosIRefreshTabele ()
	{
		kolicina.setText("");
		sifraArtikla.setText("");
		nazivArtikla.setText("");
		barKodArtikla.setText("");
		filtrirajPoNazivuArtikla("");
		comboListaArtikla.getSelectionModel().clearSelection();
		tabelaArtikla.getItems().clear();
		tabelaArtikla.getItems().addAll(listaStavki);
		tabelaArtikla.refresh();
		
	}
	
	public Scene getScena() {return scena;}
	
}
