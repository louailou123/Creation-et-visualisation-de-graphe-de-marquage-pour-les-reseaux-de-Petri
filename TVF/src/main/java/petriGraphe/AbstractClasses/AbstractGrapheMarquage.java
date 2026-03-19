package petriGraphe.AbstractClasses;

import java.util.*;

import petriGraphe.Marquages.Marquage;

public abstract class AbstractGrapheMarquage {

    protected  Stack<Marquage> listNoEncoursTrite=new Stack<>();
    protected  Set<Marquage> listEtatsAccessible = new LinkedHashSet<>();
    protected Boolean borne ;

}

