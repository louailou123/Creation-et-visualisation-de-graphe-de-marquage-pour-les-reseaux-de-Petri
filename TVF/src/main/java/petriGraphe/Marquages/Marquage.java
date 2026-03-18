package petriGraphe.Marquages;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Marquage {
    private int[] M;
    private Set<Marquage> marquagesPrecedents=new LinkedHashSet<>();
    public Marquage(int [] M)
    {
        this.M=M;
    }
    public void addMarquagePrecedent(Marquage Mar)
    {
        this.marquagesPrecedents.add(Mar);
    }
    public boolean superieurOuEgalAuxPrecedents() {
    for (Marquage precedent : marquagesPrecedents) {
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
