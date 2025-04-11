package Produit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class listAdmin {
    private static final String URL = "jdbc:mysql://localhost:3306/test"; // Remplacez "test" par le nom de votre base de données
    private static final String USER = "root"; // Remplacez par votre nom d'utilisateur MySQL
    private static final String PASSWORD = ""; // Remplacez par votre mot de passe MySQL

    public static void inscriptionAdmin() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n=== Inscription Administrateur ☻☻ ===");
        System.out.print("Entrez votre nom : ");
        String nom = input.nextLine();
        System.out.print("Entrez votre prénom : ");
        String prenom = input.nextLine();
        System.out.print("Entrez votre email : ");
        String email = input.nextLine();
        System.out.print("Entrez votre mot de passe : ");
        String password = input.nextLine();

        if (Utility.ajouterAdmin(nom, prenom, email, password)) {
            System.out.println("Inscription réussie ☻☻ Bienvenue, " + nom + " " + prenom + "!");
        } else {
            System.out.println("Erreur lors de l'inscription. Veuillez réessayer.");
        }
    }

    public static void inscriptionClient() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n=== Inscription Client ☻☻ ===");
        System.out.print("Entrez votre nom : ");
        String nom = input.nextLine();
        System.out.print("Entrez votre prénom : ");
        String prenom = input.nextLine();
        System.out.print("Entrez votre email : ");
        String email = input.nextLine();
        System.out.print("Entrez votre mot de passe : ");
        String password = input.nextLine();
        System.out.print("Entrez votre numéro de téléphone : ");
        String numero = input.nextLine();
        System.out.print("Entrez votre CIN : ");
        String cin = input.nextLine();
        System.out.print("Entrez votre adresse : ");
        String adresse = input.nextLine();
        System.out.print("Avez-vous une ordonnance ? (oui/non) : ");
        String ordonnance = input.nextLine();

        if (ajouterClient(nom, prenom, email, password, numero, cin, adresse, ordonnance)) {
            System.out.println("Inscription réussie ☻☻ Bienvenue, " + nom + " " + prenom + "!");
        } else {
            System.out.println("Erreur lors de l'inscription. Veuillez réessayer.");
        }
    }

    private static boolean ajouterClient(String nom, String prenom, String email, String password, String numero, String cin, String adresse, String ordonnance) {
        String query = "INSERT INTO bdclients (nom, prenom, email, password, numero, cin, adresse, ordonnance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, numero);
            ps.setString(6, cin);
            ps.setString(7, adresse);
            ps.setString(8, ordonnance);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription du client : " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Principal ☻☻ ===");
            System.out.println("1. Connexion");
            System.out.println("2. Inscription");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option ☻☻ : ");

            int choix = input.nextInt();
            input.nextLine();

            switch (choix) {
                case 1:
                    choisirTypeConnexion();
                    break;

                case 2:
                    choisirTypeInscription();
                    break;

                case 3:
                    System.out.println("Au revoir ☻☻ ");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public static void choisirTypeConnexion() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Choix de connexion ===");
        System.out.println("1. Connexion Administrateur");
        System.out.println("2. Connexion Client");
        System.out.print("Choisissez une option : ");
        int choix = input.nextInt();
        input.nextLine();

        switch (choix) {
            case 1:
                System.out.println("\n=== Connexion Administrateur ☻☻ ===");
                System.out.print("Entrez votre email : ");
                String emailAdmin = input.nextLine();
                System.out.print("Entrez votre mot de passe : ");
                String passwordAdmin = input.nextLine();

                if (verifierConnexionAdmin(emailAdmin, passwordAdmin)) {
                    System.out.println("Connexion réussie ☻☻ Bienvenue, Administrateur ☻☻.");
                    ListProduit.gestionProduits();
                   // listCommande.listCommande();
                } else {
                    System.out.println("Email ou mot de passe incorrect ☻☻.");
                }
                break;

            case 2:
                System.out.println("\n=== Connexion Client ☻☻ ===");
                System.out.print("Entrez votre email : ");
                String emailClient = input.nextLine();
                System.out.print("Entrez votre mot de passe : ");
                String passwordClient = input.nextLine();

                if (verifierConnexionClient(emailClient, passwordClient)) {
                    System.out.println("Connexion réussie ☻☻ Bienvenue, Client ☻☻.");
                } else {
                    System.out.println("Email ou mot de passe incorrect ☻☻.");
                }
                break;

            default:
                System.out.println("Option invalide. Veuillez réessayer.");
                break;
        }
    }

    public static void choisirTypeInscription() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=== Choix d'inscription ===");
        System.out.println("1. Inscription Administrateur");
        System.out.println("2. Inscription Client");
        System.out.print("Choisissez une option : ");
        int choix = input.nextInt();
        input.nextLine();

        switch (choix) {
            case 1:
                inscriptionAdmin();
                break;

            case 2:
                inscriptionClient();
                break;

            default:
                System.out.println("Option invalide. Veuillez réessayer.");
                break;
        }
    }

    public static boolean verifierConnexionAdmin(String email, String password) {
        return verifierConnexion(email, password, "SELECT password FROM bdadmin WHERE email = ?");
    }

    public static boolean verifierConnexionClient(String email, String password) {
        return verifierConnexion(email, password, "SELECT password FROM clients WHERE email = ?");
    }

    private static boolean verifierConnexion(String email, String password, String query) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password").equals(password);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
        }
        return false;
    }
}
