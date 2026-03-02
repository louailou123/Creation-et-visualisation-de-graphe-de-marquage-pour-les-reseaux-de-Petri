package petriGraphe;


import java.util.*;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Affichage {
    private int[] M;
    private int t;
    private int [] M_new;
    Affichage(int[] M,int t,int[] M_new)
    {
        this.M=M;
        this.t=t;
        this.M_new=M_new;
    }
    public String toString(){
        return(Arrays.toString(this.M) +"--t"+this.t+"-->"+Arrays.toString(this.M_new));
    }
}
