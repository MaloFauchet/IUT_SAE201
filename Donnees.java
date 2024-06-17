package lepatio.sae.IHM_Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Donnees {
    static private ObservableList<Client> lesClients = FXCollections.observableArrayList();
    static public boolean clientCreerParReservation = false;

    static public void chargementDonnees() {
        lesClients.add(new Abonne("Dupont", "Jean", "Paris", "0600112233", "jean.dupont@example.com", new Reservation("Dupont", "Jean", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Client("Martin", "Marie", "Lyon", "0622334455", "marie.martin@example.com", new Reservation("Martin", "Marie", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Client("Bernard", "Luc", "Marseille", "0633445566", "luc.bernard@example.com", new Reservation("Bernard", "Luc", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Client("Dupont", "Alice", "Lyon", "0644556677", "alice.petit@example.com", new Reservation("Dupont", "Alice", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Abonne("Durand", "Pierre", "Bordeaux", "0655667788", "pierre.durand@example.com", new Reservation("Durand", "Pierre", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Client("Leroy", "Sophie", "Toulouse", "0666778899", "sophie.leroy@example.com", new Reservation("Leroy", "Sophie", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Client("Moreau", "Paul", "Nantes", "0677889900", "paul.moreau@example.com", new Reservation("Moreau", "Paul", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Client("Dupont", "Julie", "Nice", "0688990011", "julie.simon@example.com", new Reservation("Simon", "Julie", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Abonne("Michel", "Antoine", "Strasbourg", "0699001122", "antoine.michel@example.com", new Reservation("Michel", "Antoine", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Abonne("Lefevre", "Emma", "Rennes", "0611122233", "emma.lefevre@example.com", new Reservation("Lefevre", "Emma", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
        lesClients.add(new Client("Garcia", "Hugo", "Grenoble", "0622233344", "hugo.garcia@example.com", new Reservation("Garcia", "Hugo", "Slimane", LocalDate.of(2024, 6, 28), "Orchestre", "D", "53", "1", "4", "2", "3", 10)));
    }

    static public ObservableList<Client> getLesClients() {
        return lesClients;
    }

    // méthodes de mise à jour
    static public void ajouterClient(Client client) {
        lesClients.add(client);
    }

    static public void supprimerClient(Client client) {
        boolean trouve = false;
        int i=0;
        while (!trouve && i<lesClients.size()) {
            if (lesClients.get(i).getNumero().equals(client.getNumero())){
                lesClients.remove(i);
                trouve = true;
            }
            i++;
        }
    }

    static public void modifierClient(Client e) {
        boolean trouve = false;
        int i=0;
        while (!trouve && i<lesClients.size()) {
            if (lesClients.get(i).getNumero().equals(e.getNumero())){
                lesClients.set(i, e);
                trouve = true;
            }
            i++;
        }
    }

    public static ObservableList<Client> getClientTrieNumero() {
        lesClients.sort(Client::compareNumero);
        return lesClients;
    }

    public static ObservableList<Client> getClientTrieNomVille() {
        lesClients.sort((o1, o2) -> {
            if (o1.getNom().equals(o2.getNom())) {
                return o1.getAdresse().compareTo(o2.getAdresse());
            }
            return o1.getNom().compareTo(o2.getNom());
        });
        return lesClients;
    }
    
    
    // Chercher si un client existe avec le nom et la ville en paramètre
    public static ObservableList<Client> existeClient(String nomClient, String villeClient) {
        ObservableList<Client> result = FXCollections.observableArrayList();
        for (Client client : lesClients) {
            // si le nom et la ville donnee est la meme que le client etudie
            if (client.getAdresse().compareToIgnoreCase(villeClient) == 0 && client.getNom().compareToIgnoreCase(nomClient) == 0) {
                result.add(client);
            }
        }
    	return result;
    }
}

