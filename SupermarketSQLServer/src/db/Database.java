package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import supermarket.Artikal;
import supermarket.Magacin;
import supermarket.Racun;
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
	
	public boolean zapamtiRacunUBazu (List<StavkaZaRacun> listaStavki)
	{
		boolean zapamceno = true;
		int idRacuna = UtilDB.vratiPoslednjiID("racun", "idRacuna") + 1;
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
				ps.setTimestamp(6, UtilDB.dajTrenutnoTimestampVreme());
				ps.executeUpdate();
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				zapamceno = false;
			}
		}
		return zapamceno;
	}

	public boolean umanjiBrojArtiklaUMagacinu(List<StavkaZaRacun> listaStavki) 
	{
		boolean ubacenoIsmanjeno = zapamtiRacunUBazu(listaStavki);
		//upisivanje stavki u tabelu racun
				
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
			ubacenoIsmanjeno = false;
		}
		return ubacenoIsmanjeno;
	}
	
	public ArrayList<Racun> dajListuRacuna(String vremeOD, String vremeDO)
	{
		ArrayList<Racun> listaRacuna = new ArrayList<>();
		String upit = "SELECT * , COUNT(idRacuna) AS brojStavki, SUM(cenaArtikla * kolicina) AS ukupnaSuma FROM racun WHERE datum >= '" + vremeOD + "' AND datum <= '" + vremeDO + "' GROUP by idRacuna";
		
		try 
		{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(upit);
			
			while (rs.next())
			{
				int idRacuna = rs.getInt("idRacuna");
				double ukupanIznos = rs.getDouble("ukupnaSuma");
				Timestamp datum = rs.getTimestamp("datum");
				
				String upit2 = "SELECT * FROM racun r JOIN artikal a ON r.sifraArtikla = a.sifraArtikla WHERE idRacuna = " + idRacuna;
				
				Statement s2 = conn.createStatement();
				ResultSet rs2 = s2.executeQuery(upit2);
				ArrayList<StavkaZaRacun> listaStavki = new ArrayList<>();
				
				while (rs2.next())
				{
					int sifraArtikla = rs2.getInt("sifraArtikla");
					String barKodArtikla = rs2.getString("barKodArtikla");
					String nazivArtikla = rs2.getString("nazivArtikla");
					double cenaArtikla = rs2.getDouble("cenaArtikla");
					int kolicina = rs2.getInt("kolicina");
					int idMagacina = rs2.getInt("idMagacina");
					Artikal a = new Artikal(sifraArtikla, barKodArtikla, nazivArtikla, cenaArtikla);
					StavkaZaRacun stavka = new StavkaZaRacun(a, kolicina, idMagacina);
					listaStavki.add(stavka);
				}
				
				Racun r = new Racun(idRacuna, listaStavki, ukupanIznos, datum);
				listaRacuna.add(r);
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return listaRacuna;
	}

	public boolean ubaciNovArtikal(Artikal a) 
	{
		boolean ubacen;
		
		try 
		{
			String upit = "INSERT into artikal VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(upit);
			ps.setInt(1, a.getSifraArtikla());
			ps.setString(2, a.getBarKodArtikla());
			ps.setString(3, a.getNazivArtikla());
			ps.setDouble(4, a.getCena());
			ps.executeUpdate();
			ps.close();
			ubacen = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			ubacen = false;
		}
		return ubacen;
		
	}
		
}
