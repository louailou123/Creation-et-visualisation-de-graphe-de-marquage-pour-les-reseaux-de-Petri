package petriGraphe;

import java.util.Scanner;
import java.util.Vector;

import petriGraphe.Graphes.Graphe;
import petriGraphe.Graphes.GrapheStochastique;
import petriGraphe.Marquages.GrapheMarquage;
import petriGraphe.Marquages.GrapheMarquageStochastique;
import petriGraphe.Marquages.Marquage;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre de places (lignes) du réseau : ");
        int lines;
        while (true) {
            try {
                lines = Integer.parseInt(scanner.nextLine().trim());
                if (lines > 0) break;
                System.out.print("Veuillez saisir un entier strictement positif : ");
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide: saisissez un entier positif : ");
            }
        }

        System.out.print("Nombre de transitions (colonnes) du réseau : ");
        int cols;
        while (true) {
            try {
                cols = Integer.parseInt(scanner.nextLine().trim());
                if (cols > 0) break;
                System.out.print("Veuillez saisir un entier strictement positif : ");
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide. Saisissez un entier positif : ");
            }
        }

        int[][] Pre = new int[lines][cols];
        int[][] Post = new int[lines][cols];

        System.out.println("Saisir la matrice Pre (" + lines + " lignes, " + cols + " colonnes) :");
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Pre[" + i + "][" + j + "] = ");
                while (true) {
                    try {
                        int value = Integer.parseInt(scanner.nextLine().trim());
                        if (value >= 0) {
                            Pre[i][j] = value;
                            break;
                        }
                        System.out.print("Veuillez saisir un entier >= 0 : ");
                    } catch (NumberFormatException e) {
                        System.out.print("Entrée invalide. Saisissez un entier valide : ");
                    }
                }
            }
        }

        System.out.println("Saisir la matrice Post (" + lines + " lignes, " + cols + " colonnes) :");
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Post[" + i + "][" + j + "] = ");
                while (true) {
                    try {
                        int value = Integer.parseInt(scanner.nextLine().trim());
                        if (value >= 0) {
                            Post[i][j] = value;
                            break;
                        }
                        System.out.print("Veuillez saisir un entier >= 0 : ");
                    } catch (NumberFormatException e) {
                        System.out.print("Entrée invalide. Saisissez un entier valide : ");
                    }
                }
            }
        }

        System.out.println("Entrez le marquage initial M0 (" + lines + " valeurs) :");
        int[] M0 = new int[lines];
        for (int i = 0; i < lines; i++) {
            System.out.print("M0[" + i + "] = ");
            while (true) {
                try {
                    int value = Integer.parseInt(scanner.nextLine().trim());
                    if (value >= 0) {
                        M0[i] = value;
                        break;
                    }
                    System.out.print("Veuillez saisir un entier >= 0 : ");
                } catch (NumberFormatException e) {
                    System.out.print("Entrée invalide. Saisissez un entier valide : ");
                }
            }
        }

        Marquage M0Obj = new Marquage(M0);

        System.out.print("Le graphe est-il stochastique ? (oui/non) : ");
        boolean isStochastic = false;
        while (true) {
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("oui") || response.equals("o")) {
                isStochastic = true;
                break;
            } else if (response.equals("non") || response.equals("n")) {
                break;
            } else {
                System.out.print("Réponse invalide. Veuillez saisir 'oui' ou 'non' : ");
            }
        }

        if (isStochastic) {
            Vector<Float> poids = new Vector<>();
            System.out.print("Nombre des poids du réseau : ");
            int poidsCount;
            while (true) {
                try {
                    poidsCount = Integer.parseInt(scanner.nextLine().trim());
                    if (poidsCount > 0) break;
                    System.out.print("Veuillez saisir un entier strictement positif : ");
                } catch (NumberFormatException e) {
                    System.out.print("Entrée invalide: ");
                }
            }

            System.out.println("Entrez le vecteur des poids (" + poidsCount + " valeurs) :");
            for (int i = 0; i < poidsCount; i++) {
                while (true) {
                    try {
                        System.out.print("poids[" + i + "] = ");
                        float value = Float.parseFloat(scanner.nextLine().trim());
                        if (value >= 0) {
                            poids.add(value);
                            break;
                        } else {
                            System.out.print("Veuillez saisir un nombre >= 0 : ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Entrée invalide. Saisissez un nombre réel valide : ");
                    }
                }
            }
            GrapheStochastique gs = new GrapheStochastique(Pre, Post, M0Obj,poids);
            GrapheMarquageStochastique gms = new GrapheMarquageStochastique(gs);
            gms.construireGraphe();
            gms.afficherlaliste();
            gms.affichergraphe();
            gms.testerBornitude();
            gms.exporterGrapheHierarchique("graphe");

        } else {
            Graphe g = new Graphe(Pre,Post,M0Obj);
            GrapheMarquage gm = new GrapheMarquage(g);
            gm.testerReinitialisable();
        }






    }
}