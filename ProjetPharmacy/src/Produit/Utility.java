package Produit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Utility {

	public static boolean ajouterProduit(String nom, float prix, String date_expiration) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO bdproduit (nom, prix, date_expiration) VALUES (?,?,?)");

			ps.setString(1, nom);
			ps.setFloat(2, prix);
			ps.setString(3, date_expiration);

			int r = ps.executeUpdate();

			System.out.println("ps= " + ps);

			con.close();
			ps.close();

			if (r > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	public static void afficherListeProduit() {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bdproduit");

			while (rs.next()) {
				System.out.println(
						rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getFloat(3) + "  " + rs.getString(4));
			}

			con.close();
			stmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static boolean supprimerProduit(int id) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			PreparedStatement ps = con.prepareStatement("DELETE FROM bdproduit WHERE id = ?");

			ps.setInt(1, id);
			int r = ps.executeUpdate();

			System.out.println("ps= " + ps);

			con.close();
			ps.close();

			if (r > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	public static boolean modifierProduit(int id, int x, String v) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

			String query = "";

			if (x == 1) {
				query = "UPDATE bdproduit set nom = ? WHERE id = ?";

			} else if (x == 2) {
				query = "UPDATE bdproduit set prix = ? WHERE id = ?";
				
			} else if (x == 3) {
				query = "UPDATE bdproduit set date_expiration = ? WHERE id = ?";

			}
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, v);
			ps.setInt(2, id);
			

			int r = ps.executeUpdate();

			System.out.println("ps= " + ps);

			con.close();
			ps.close();

			if (r > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println(e);
		}

		return false;

	}

	    // ... autres méthodes ...

	    public static boolean ajouterAdmin(String nom, String prenom, String email, String password) {
	        try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
	            PreparedStatement ps = con.prepareStatement("INSERT INTO bdadmin (nom, prenom, email, password) VALUES (?,?,?,?)");

	            ps.setString(1, nom);
	            ps.setString(2, prenom);
	            ps.setString(3, email);
	            ps.setString(4, password);

	            int r = ps.executeUpdate();

	            con.close();
	            ps.close();

	            if (r > 0)
	                return true;
	            else
	                return false;

	        } catch (Exception e) {
	            System.out.println(e);
	        }

	        return false;
	    }

	    // ... autres méthodes ...
	}

	   