package Produit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ListProduit {
    private static final String URL = "jdbc:mysql://localhost:3306/test"; // Remplacez "test" par le nom de votre base de données
    private static final String USER = "root"; // Remplacez par votre nom d'utilisateur MySQL
    private static final String PASSWORD = ""; // Remplacez par votre mot de passe MySQL

    // Méthode pour ajouter un produit dans la base de données
    public static boolean ajouterProduit(String nom, float prix, String date_expiration) {
        String query = "INSERT INTO bdproduit (nom, prix, date_expiration) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, nom);
            ps.setFloat(2, prix);;
            ps.setString(3, date_expiration);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retourne true si l'insertion a réussi

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du produit : " + e.getMessage());
            return false;
        }
    }

    // Méthode pour afficher tous les produits de la base de données
    public static void afficherListeProduit() {
        String query = "SELECT * FROM bdproduit";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    ", Nom: " + rs.getString("nom") +
                    ", Prix: " + rs.getFloat("prix") +
                    ", Date d'expiration: " + rs.getString("date_expiration")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des produits : " + e.getMessage());
        }
    }

    // Méthode pour modifier un produit dans la base de données
    public static boolean modifierProduit(int id, String nouveauNom, float nouveauPrix, String nouvelleDate) {
        String query = "UPDATE bdproduit SET nom = ?, prix = ?, date_expiration = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, nouveauNom);
            ps.setFloat(2, nouveauPrix);
            ps.setString(3, nouvelleDate);
            ps.setInt(4, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retourne true si la modification a réussi

        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification du produit : " + e.getMessage());
            return false;
        }
    }

    // Méthode pour supprimer un produit de la base de données
    public static boolean supprimerProduit(int id) {
        String query = "DELETE FROM bdproduit WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retourne true si la suppression a réussi

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du produit : " + e.getMessage());
            return false;
        }
    }

    // Méthode pour gérer les produits
    public static void gestionProduits() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Gestion des Produits ☻☻ ===");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Afficher tous les produits");
            System.out.println("3. Modifier un produit");
            System.out.println("4. Supprimer un produit");
            System.out.println("5. Retour au menu principal");
            System.out.print("Choisissez une option : ");

            int choix = input.nextInt();
            input.nextLine(); // Pour consommer la nouvelle ligne après nextInt()

            switch (choix) {
                case 1:
                    System.out.println("\n=== Ajouter un produit ☻☻ ===");
                    System.out.print("Entrez le nom du produit : ");
                    String nom = input.nextLine();
                    System.out.print("Entrez le prix du produit : ");
                    float prix = input.nextFloat();
                    input.nextLine(); // Pour consommer la nouvelle ligne après nextFloat()
                    System.out.print("Entrez la date d'expiration (YYYY-MM-DD) : ");
                    String dateExpiration = input.nextLine();

                    if (ajouterProduit(nom, prix, dateExpiration)) {
                        System.out.println("Produit ajouté avec succès ☻☻ ");
                    } else {
                        System.out.println("Erreur lors de l'ajout du produit.");
                    }
                    break;

                case 2:
                    System.out.println("\n=== Liste des produits ☻☻ ===");
                    afficherListeProduit();
                    break;

                case 3:
                    System.out.println("\n=== Modifier un produit ☻☻ ===");
                    System.out.print("Entrez l'ID du produit à modifier : ");
                    int idModifier = input.nextInt();
                    input.nextLine(); // Pour consommer la nouvelle ligne après nextInt()
                    System.out.print("Entrez le nouveau nom : ");
                    String nouveauNom = input.nextLine();
                    System.out.print("Entrez le nouveau prix : ");
                    float nouveauPrix = input.nextFloat();
                    input.nextLine(); // Pour consommer la nouvelle ligne après nextFloat()
                    System.out.print("Entrez la nouvelle date d'expiration (YYYY-MM-DD) : ");
                    String nouvelleDate = input.nextLine();

                    if (modifierProduit(idModifier, nouveauNom, nouveauPrix, nouvelleDate)) {
                        System.out.println("Produit modifié avec succès ☻☻");
                    } else {
                        System.out.println("Erreur lors de la modification du produit.");
                    }
                    break;

                case 4:
                    System.out.println("\n=== Supprimer un produit ===");
                    System.out.print("Entrez l'ID du produit à supprimer : ");
                    int idSupprimer = input.nextInt();
                    input.nextLine(); // Pour consommer la nouvelle ligne après nextInt()

                    if (supprimerProduit(idSupprimer)) {
                        System.out.println("Produit supprimé avec succès ☻☻");
                    } else {
                        System.out.println("Erreur lors de la suppression du produit.");
                    }
                    break;

                case 5:
                    System.out.println("Retour au menu principal...");
                    return;

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }
}