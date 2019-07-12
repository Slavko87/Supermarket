package forme;

public class OsveziTabelu extends Thread 
{
	private ServerskaForma sf;

	public OsveziTabelu (ServerskaForma sf)
	{
		this.sf = sf;
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			sf.refresujTabeluSaRacunima();
		
			try
			{
				Thread.sleep(5000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
