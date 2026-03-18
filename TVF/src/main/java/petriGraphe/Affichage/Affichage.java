package petriGraphe.Affichage;

import java.util.Arrays;
import petriGraphe.AbstractClasses.AbstractAffichage;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Affichage extends AbstractAffichage {

   public Affichage(int[]  encoursMarquageValeur,int transition,int[] nouveauMarquageValeur)
    {
        super(encoursMarquageValeur,transition,nouveauMarquageValeur);
    }
    public String toString(){
        return(Arrays.toString(this.encoursMarquageValeur) +"--t"+this.transition+"-->"+Arrays.toString(this.nouveauMarquageValeur));
    }
}
