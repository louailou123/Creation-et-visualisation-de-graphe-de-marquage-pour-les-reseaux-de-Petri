package petriGraphe.Marquages;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Marquage {
    private int[] M;
    private Set<Marquage> marquagesPrecedents=new LinkedHashSet<>();
    private Set<Marquage> marquagesSuivants=new LinkedHashSet<>();


    public Marquage(int [] M)
    {
        this.M=M;
    }


    public void addMarquagePrecedent(Marquage Mar)
    {
        this.marquagesPrecedents.add(Mar);
    }

   public void addMarquageSuivant(Marquage mar){this.marquagesSuivants.add(mar);}

    public void afficherListMarquagesSuivants(Marquage mar)
    {
       for (Marquage m:this.marquagesSuivants)
       {
           if(Arrays.equals(m.getM(),mar.getM()))
           {
               System.out.println("\u001B[31m"+Arrays.toString(m.getM())+"/"+"\u001B[0m");
           }
           else {
               System.out.print(Arrays.toString(m.getM())+"/");
           }


       }
    }

    public boolean existDansMarquageSuivant(Marquage mar)
    {
        for(Marquage m :this.marquagesSuivants)
        {
            if(Arrays.equals(m.getM(),mar.getM()))
            {
                return true;
            }

        }
        return false;
    }

    public boolean superieurOuEgalAuxPrecedents() {
    for (Marquage precedent : this.marquagesPrecedents) {
        int[] M_prev = precedent.getM();
        boolean auMoinsUnInférieur = false;
        for (int i = 0; i < M.length; i++) {
            if (this.M[i] < M_prev[i]) {
                auMoinsUnInférieur = true;
                break; // une valeur est inférieure → pas valide
            }
        }
            if (!auMoinsUnInférieur) {
            // toutes les composantes sont >= à ce précédent
            return true;
        }
    }
    return false; // toutes les valeurs sont >= à tous les précédents
}
}
