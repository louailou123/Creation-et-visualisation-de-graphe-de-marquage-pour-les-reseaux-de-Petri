package petriGraphe.Graphes;

import java.util.Vector;

import petriGraphe.AbstractClasses.AbstractGraphe;
import petriGraphe.Marquages.Marquage;

public class GrapheStochastique extends AbstractGraphe{
private Vector<Float> poids;
public GrapheStochastique(int[][] Pre, int[][] Post, Marquage M0,  Vector<Float> poids) {
    super(Pre, Post, M0);
  this.poids=poids;
}



public Vector<Float> getPoids() {
    return poids;
}

}
