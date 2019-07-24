package test;

import java.sql.Timestamp;
import forme.ServerskaForma;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppServer extends Application
{
	
	ServerskaForma sf = new ServerskaForma();

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		try
		{
			stage.setMaxHeight(600);
			stage.setMaxWidth(590);
			stage.setMinHeight(600);
			stage.setMinWidth(590);
			stage.setTitle("SERVER");
			stage.setScene(sf.getScena());
			stage.show();
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			System.out.println(ts.toString().substring(0, 19));
			
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
