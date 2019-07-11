package forme;

import javax.swing.JFrame;

import javafx.scene.Scene;
import serverskiDeo.PokreniServer;

@SuppressWarnings("serial")
public class ServerskaForma extends JFrame 
{
	private Scene scena;
	
	public ServerskaForma()
	{
		super("SERVER");
		PokreniServer ps = new PokreniServer();
		ps.start();
	}

	public Scene getScena() {return scena;}
	
}
