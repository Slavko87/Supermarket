package db;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import supermarket.Artikal;
import supermarket.Magacin;
import supermarket.StavkaZaRacun;

public class Database {

	private static Database database = null;
	public Connection conn;

	private Database() {
		String connString = "jdbc:mysql://localhost:3306/supermarket?user=root&password=";
		try 
		{
			conn = DriverManager.getConnection(connString);
		} catch (SQLException e)
		{
			System.out.println("Neka greska sa bazom!");
		}
	}
	
	public void otvoriKonekciju()
	{
		String connString = "jdbc:mysql://localhost:3306/supermarket?user=root&password=";
		try 
		{
			conn = DriverManager.getConnection(connString);
		} catch (SQLException e)
		{
			System.out.println("Neka greska sa bazom!");
		}
	}
	
	public void zatvoriKonekciju()
	{
		try 
		{
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void rollBack()
	{
		try 
		{
			conn.rollback();
		}
		catch (SQLException e) {
			System.out.println("Nije uspeo rollback");
		}
	}

	public static Database getKonekcijaNaBazu() 
	{
		if (database == null)
			database = new Database();

		return database;
	}

	public ArrayList<Artikal> dajListuArtikla(Integer brojMagacina)
	{
		ArrayList<Artikal> lista = new ArrayList<>();
		String upit = "select * from artikal a JOIN magacinkolicina mk on a.sifraArtikla = mk.sifraArtikla where mk.idMagacina = " + brojMagacina + " ORDER by a.nazivArtikla";
		try 
		{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(upit);
			
			while (rs.next())
			{
				int sifraArtikla = rs.getInt("sifraArtikla");
				String barKodArtikla = rs.getString("barKodArtikla");
				String nazivArtikla = rs.getString("nazivArtikla");
				double cenaArtikla = rs.getDouble("cenaArtikla");
				Artikal a = new Artikal(sifraArtikla, barKodArtikla, nazivArtikla, cenaArtikla);
				lista.add(a);
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public ArrayList<Magacin> dajListuMagacina() 
	{
		ArrayList<Magacin> lista = new ArrayList<>();
		String upit = "SELECT * FROM magacin";
		try 
		{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(upit);
			
			while (rs.next())
			{
				int idMagacina = rs.getInt("idMagacina");
				String nazivMagacina = rs.getString("nazivMagacina");
				
				String upit2 = "SELECT * FROM magacin m join magacinkolicina mk on m.idMagacina = mk.idMagacina join artikal a on mk.sifraArtikla = a.sifraArtikla where m.idMagacina=" + idMagacina;
				
				try
				{
					Statement s2 = conn.createStatement();
					ResultSet rs2 = s2.executeQuery(upit2);
					
					HashMap<Artikal, Integer> listaArtikla = new HashMap<>();
					while (rs2.next())
					{
						Artikal a = new Artikal(rs2.getInt("a.sifraArtikla"), rs2.getString("a.barKodArtikla"), rs2.getString("a.nazivArtikla"), rs2.getDouble("a.cenaArtikla"));
						int kolicina = rs2.getInt("mk.kolicina");
						listaArtikla.put(a, kolicina);
					}
					Magacin m = new Magacin(idMagacina, nazivMagacina, listaArtikla);
					lista.add(m);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void zapamtiRacunUBazu (List<StavkaZaRacun> listaStavki)
	{
		int idRacuna = UtilDB.getLastElement("racun") + 1;
		for (StavkaZaRacun szr : listaStavki) 
		{
			//upisivanje stavki u tabelu racun
			try
			{
				String query = "INSERT INTO racun (idRacuna, sifraArtikla, kolicina, cenaArtikla, idMagacina, datum) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = database.conn.prepareStatement(query);
				ps.setInt(1, idRacuna);
				ps.setInt(2, szr.getSifraArtikla());
				ps.setInt(3, szr.getKolicina());
				ps.setDouble(4, szr.getCenaArtikla());
				ps.setInt(5, szr.getIdMagacina());
				ps.setDate(6, Date.valueOf(LocalDate.now()));
				ps.executeUpdate();
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void umanjiBrojArtiklaUMagacinu(List<StavkaZaRacun> listaStavki) 
	{
		//upisivanje stavki u tabelu racun
		zapamtiRacunUBazu(listaStavki);
		
		//smanjivanje stavki iz magacina
		try 
		{
			String query = "select * from magacinkolicina";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			while (rs.next())
			{
				int idMagacina = rs.getInt("idMagacina");
				int sifraArtikla = rs.getInt("sifraArtikla");
				int kolicina = rs.getInt("kolicina");
				
				for (StavkaZaRacun szr : listaStavki) 
				{
					if (szr.getIdMagacina() == idMagacina && szr.getSifraArtikla() == sifraArtikla)
					{
						int novaKolicina = kolicina - szr.getKolicina();
						if (novaKolicina == 0)
						{
							String upit3 = "DELETE FROM magacinkolicina WHERE idMagacina = " + idMagacina + " AND sifraArtikla = " + sifraArtikla;
							Statement s3 = conn.createStatement();
							s3.executeUpdate(upit3);
							s3.close();
						}
						else
						{
						String upit2 = "UPDATE magacinkolicina SET kolicina = " + novaKolicina + " WHERE idMagacina = " + idMagacina + " AND sifraArtikla = " + sifraArtikla;
						Statement s2 = conn.createStatement();
						s2.executeUpdate(upit2);
						s2.close();
						}
					}
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
		
}
