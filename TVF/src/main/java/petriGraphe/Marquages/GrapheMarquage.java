package petriGraphe.Marquages;
import java.util.*;

import petriGraphe.Affichage.Affichage;
import petriGraphe.Graphes.Graphe;
import petriGraphe.AbstractClasses.AbstractGrapheMarquage;

public class GrapheMarquage extends AbstractGrapheMarquage {
     private Graphe graphe;
     private Set<Affichage> Resultat=new LinkedHashSet<>();
     public GrapheMarquage(Graphe graphe) {
        this.graphe=graphe;
        this.listNoEncoursTrite.push(graphe.getM0());
        this.listEtatsAccessible.add(graphe.getM0());
    }



    public Boolean estTirable(Marquage marquage, int [][] Pre , int t)
    {
        int lignes = marquage.getM().length;
        int [] M = marquage.getM();   // number of places

        for (int i = 0; i < lignes; i++) {
          if(M[i]<Pre[i][t])
             return false;

    }
    return true;
}
    public int[] calculerNouveauMarquageValeur(Marquage marquage,int[][] Pre,int[][] Post, int t){
        int[] M = marquage.getM();
        int[] nouveauMarquageValeur = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            nouveauMarquageValeur[i] = M[i] - Pre[i][t] + Post[i][t];
        }
        return nouveauMarquageValeur;
    }
    public boolean containsArray(Set<Marquage> listEtatsAccessible, Marquage marquage) {
        int[] M = marquage.getM();
            for (Marquage m : listEtatsAccessible) {
                if (Arrays.equals(m.getM(), M)) {
                    return true;
                }
            }
        return false;
    }
    public void testerBornitude()
    {   if(!this.graphe.isEstConstruit())
            construireGraphe();
        if(this.borne)
        {
            System.out.println("le graphe est borné");
        }
        else
        {
            System.out.println("le graphe n'est pas borné");
        }

    }
    public void creationListMarquageSuivants()
    {
        if(!this.graphe.isEstConstruit())
        {
            construireGraphe();
        }
        if (this.borne == true)
        {
            int[][] pre=this.graphe.getPre();
            int[][] post=this.graphe.getPost();
            for (Marquage mar: listEtatsAccessible)
            {
                this.listNoEncoursTrite.push(mar);
                while (!listNoEncoursTrite.isEmpty()) {
                    Marquage M =listNoEncoursTrite.pop();
                    int [] encoursMarquageValeur=M.getM();
                    for(int t=1;t<=pre[0].length;t++){
                        if(estTirable(M, pre, t-1)){

                            int[] nouveauMarquageValeur = new int[M.getM().length];
                            nouveauMarquageValeur=calculerNouveauMarquageValeur(M, pre, post, t-1);
                            Marquage nouveauMarquage=new Marquage(nouveauMarquageValeur);
                            if(!containsArray(mar.getMarquagesSuivants(),nouveauMarquage))
                            {
                                mar.addMarquageSuivant(nouveauMarquage);
                                this.listNoEncoursTrite.push(nouveauMarquage);
                            }
                        }

                    }

                }
            }
        }
        else
        {
            System.out.println("le graphe n'est pas borné alors le graphe n'est pas reinitialisable");
        }
    }
    public boolean testerReinitialisable()
    {
        creationListMarquageSuivants();
        if (this.borne == false)
        {
            return false;
        }
        for (Marquage m : listEtatsAccessible)
        {
            System.out.print(Arrays.toString(m.getM())+"------->");
            m.afficherListMarquagesSuivants(this.graphe.getM0());
            System.out.println("");
            if(!m.existDansMarquageSuivant(graphe.getM0()))
            {
                System.out.println("M0 n'existe pas dans cette sequence alors le graphe n'est pas reinitialisable");
                return false;
            }

        }


        System.out.println("M0 existe dans toutes les sequences alors le graphe est reinitialisable");
        return true;


    }



    public void construireGraphe()
    {
        if(!this.graphe.isEstConstruit())
        {
            int[][] pre=this.graphe.getPre();
            int[][] post=this.graphe.getPost();
            while (!listNoEncoursTrite.isEmpty()) {
                Marquage M =listNoEncoursTrite.pop();
                int [] encoursMarquageValeur=M.getM();

                for(int t=1;t<=pre[0].length;t++){
                    if(estTirable(M, pre, t-1)){

                        int[] nouveauMarquageValeur = new int[M.getM().length];
                        nouveauMarquageValeur=calculerNouveauMarquageValeur(M, pre, post, t-1);
                        Marquage nouveauMarquage=new Marquage(nouveauMarquageValeur);
                        if(!containsArray(listEtatsAccessible,nouveauMarquage))
                        {
                            nouveauMarquage.addMarquagePrecedent(M);
                            this.listEtatsAccessible.add(nouveauMarquage);
                            this.listNoEncoursTrite.push(nouveauMarquage);
                            for(Marquage m :M.getMarquagesPrecedents())
                            {
                                nouveauMarquage.addMarquagePrecedent(m);
                            }
                            if(nouveauMarquage.superieurOuEgalAuxPrecedents())
                            {
                                this.borne=false;
                                return;
                            }
                        }

                        this.Resultat.add(new Affichage(encoursMarquageValeur, t, nouveauMarquageValeur));

                    }

                }

            }
            this.borne=true;
        }
        else
        {
            System.out.println("le graphe est déja construit");
        }

    }




    public void afficherlaliste()
    {
        System.out.println("liste de marquage accessible:");
        for(Marquage m : this.listEtatsAccessible){
            System.out.println(Arrays.toString(m.getM()));
        }
    }
    public void affichergraphe()
    {
      System.out.println("le graphe de marquage");
      for(Affichage a : this.Resultat)
      {
        System.out.println(a);
      }
    }
    public void exporterGrapheHierarchique(String filename) {
        try {
            StringBuilder dot = new StringBuilder();
            dot.append("digraph GrapheMarquage {\n");
            dot.append("rankdir=TB;\n"); // Top -> Bottom hierarchy
            dot.append("node [shape=circle];\n");
    
            // create nodes
            for (Marquage m : listEtatsAccessible) {
                String nodeName = "M" + Arrays.toString(m.getM()).replaceAll("[\\[\\] ,]", "_");
                dot.append(nodeName)
                   .append(" [label=\"")
                   .append(Arrays.toString(m.getM()))
                   .append("\"];\n");
            }
    
            // create edges
            for (Affichage a : Resultat) {
                int[] source = a.getEncoursMarquageValeur();
                int[] target = a.getNouveauMarquageValeur();
                int t = a.getTransition();
    
                String srcName = "M" + Arrays.toString(source).replaceAll("[\\[\\] ,]", "_");
                String tgtName = "M" + Arrays.toString(target).replaceAll("[\\[\\] ,]", "_");
    
                dot.append(srcName)
                   .append(" -> ")
                   .append(tgtName)
                   .append(" [label=\"t")
                   .append(t)
                   .append("\"];\n");
            }
    
            dot.append("}");
    
            java.nio.file.Files.write(
                java.nio.file.Paths.get(filename),
                dot.toString().getBytes()
            );
    
            System.out.println("DOT file generated: " + filename);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
