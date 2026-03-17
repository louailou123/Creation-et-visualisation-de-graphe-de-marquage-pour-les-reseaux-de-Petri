package petriGraphe.AbstractClasses;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public abstract class AbstractAffichage {
    protected int[] encoursMarquageValeur;
    protected  int transition ;
    protected  int [] nouveauMarquageValeur;

   public AbstractAffichage(int[] encoursMarquageValeur,int transition,int[] nouveauMarquageValeur)
    {
        this.encoursMarquageValeur=encoursMarquageValeur;
        this.transition=transition;
        this.nouveauMarquageValeur=nouveauMarquageValeur;
    }
}