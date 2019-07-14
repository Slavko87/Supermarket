package forme;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import db.Kontroler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import supermarket.Racun;
import tabela.TabelaRacunaZaPazar;

@SuppressWarnings({ "serial", "restriction" })
public class PregledPazara extends JFrame 
{

	private Stage stage = new Stage();
	private Scene scene;
	private DatePicker datumOD = new DatePicker();
	private DatePicker datumDO = new DatePicker();
	private DatePickerSkin datumODskin = new DatePickerSkin(datumOD);
	private DatePickerSkin datumDOskin = new DatePickerSkin(datumDO);
	private ArrayList<Racun> listaRacuna = new ArrayList<>();
	private TabelaRacunaZaPazar tr = new TabelaRacunaZaPazar(listaRacuna);
	private Label zbirPazara = new Label();
	
	public PregledPazara ()
	{
		super("Pregled pazara");
		stage.setTitle("Pregled pazara");
		VBox glavniBox = new VBox(10);
		glavniBox.setPadding(new Insets(10, 10, 10, 10));
		
		HBox naslovKalendar = new HBox();
		Label labelaOD = new Label("DATUM OD");
		Label labelaDO = new Label("DATUM DO");
		HBox.setMargin(labelaOD, new Insets(0, 0, 0, 80));
		HBox.setMargin(labelaDO, new Insets(0, 0, 0, 200));
		naslovKalendar.getChildren().add(labelaOD);
		naslovKalendar.getChildren().add(labelaDO);
		
		HBox kalendari = new HBox();
		Node node1 = datumODskin.getPopupContent();
		HBox.setMargin(node1, new Insets(5, 0, 5, 10));
		Node node2 = datumDOskin.getPopupContent();
		HBox.setMargin(node2, new Insets(5, 0, 5, 55));
		datumOD.setValue(LocalDate.now());
		datumDO.setValue(LocalDate.now());
		kalendari.getChildren().add(node1);
		kalendari.getChildren().add(node2);
		
		VBox zajednoNaslovIKalendar = new VBox();
		zajednoNaslovIKalendar.getChildren().add(naslovKalendar);
		zajednoNaslovIKalendar.getChildren().add(kalendari);
		zajednoNaslovIKalendar.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		glavniBox.getChildren().add(zajednoNaslovIKalendar);
		
		VBox prikaziBox = new VBox();
		prikaziBox.setAlignment(Pos.CENTER);
		Button prikazi = new Button("Prikazi");
		prikazi.setPrefSize(250, 40);
		prikaziBox.getChildren().add(prikazi);
		glavniBox.getChildren().add(prikaziBox);
		
		VBox racunUzivoBox = new VBox(10);
		racunUzivoBox.setMaxWidth(495);
		racunUzivoBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
		tr.setMinHeight(300);
		tr.setMaxHeight(300);
		tr.autosize();
		racunUzivoBox.getChildren().add(tr);
		prikazi.setOnAction(e -> popuniTabelu());
		glavniBox.getChildren().add(racunUzivoBox);
		zbirPazara.setMaxWidth(500);
		zbirPazara.setAlignment(Pos.CENTER);
		zbirPazara.setBorder(new Border(new BorderStroke(Color.RED, Color.BLUE, Color.RED, Color.BLUE, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, new CornerRadii(5), new BorderWidths(2), new Insets(0))));
		glavniBox.getChildren().add(zbirPazara);
				
		scene = new Scene(glavniBox, 510, 650);
		stage.setScene(scene);
		stage.show();
	}
	
	private void popuniTabelu()
	{
		String vremeOD = datumOD.getValue().toString() + " 00:00:00";
		String vremeDO = datumDO.getValue().toString() + " 23:59:59";
		listaRacuna = Kontroler.getInstanca().dajListuRacuna(vremeOD, vremeDO);
		tr.getItems().clear();
		tr.getItems().addAll(listaRacuna);
		tr.refresh();
		double zbirRacuna = 0;
		int brojIzdatihRacuna = 0;
		for (Racun racun : listaRacuna) 
		{
			zbirRacuna += racun.getUkupanIznosRacuna();
			brojIzdatihRacuna++;
		}
		zbirPazara.setStyle("-fx-font-size: 14px; -fx-text-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, blue 0%, red 50%);");
		zbirPazara.setText("Ukupan pazar za trazeni period iznosi: " + zbirRacuna + ", broj izdatih racuna je " + brojIzdatihRacuna);
	}

	public Scene getScene() 
	{
		return scene;
	}
	
	
}
