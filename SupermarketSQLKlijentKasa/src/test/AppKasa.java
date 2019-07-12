package test;

import forme.KlijentskaForma;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppKasa extends Application
{
	
	KlijentskaForma kf = new KlijentskaForma();

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		try
		{
			stage.setMaxHeight(700);
			stage.setMaxWidth(590);
			stage.setMinHeight(600);
			stage.setMinWidth(590);
			stage.setTitle("KASA");
			stage.setScene(kf.getScena());
			stage.show();
			
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() 
			{
				@Override
				public void handle(WindowEvent e) 
				{
					Platform.exit();
					System.exit(0);
				}
			});
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
