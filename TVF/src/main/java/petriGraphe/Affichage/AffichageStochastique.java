package petriGraphe.Affichage;

import lombok.Getter;
import lombok.Setter;
import petriGraphe.AbstractClasses.AbstractAffichage;

import java.util.Arrays;

@Getter
@Setter
public class AffichageStochastique extends AbstractAffichage {
    private Float poids;
    public AffichageStochastique(int[] encoursMarquageValeur,int transition,int[] nouveauMarquageValeur,Float poids)
    {
        super(encoursMarquageValeur,transition,nouveauMarquageValeur);
        this.poids=poids;
    }
    public String toString(){
        return(Arrays.toString(this.encoursMarquageValeur) +"--t"+this.transition+"--poids--"+this.poids+"-->"+Arrays.toString(this.nouveauMarquageValeur));
    }
}
