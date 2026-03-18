package petriGraphe.AbstractClasses;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import petriGraphe.Marquages.Marquage;

public abstract class AbstractGrapheMarquage {

    protected  Stack<Marquage> listNoEncoursTrite=new Stack<>();
    protected  Set<Marquage> listEtatsAccessible = new LinkedHashSet<>();

}
