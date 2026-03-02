package petriGraphe;
import java.util.*;

public class GrapheMarquage {
    private Graphe g;
    private Stack<int[]> A1=new Stack<>();
    private Set<int[]> A = new LinkedHashSet<>();
    private Set<Affichage> Resultat=new LinkedHashSet<>();
    public GrapheMarquage(Graphe g)
    {
        this.g=g;
        this.A1.push(g.getM0());
        this.A.add(g.getM0());

    }
    public Boolean tirable(int[] M, int [][] Pre , int t)
    {
        int rows = M.length;          // number of places

        for (int i = 0; i < rows; i++) {
          if(M[i]<Pre[i][t])       
             return false;
        
    }
    return true;
}
    public int[] calculerM_new(int[] M,int[][] Pre,int[][] Post, int t){
        int[] M_new = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            M_new[i] = M[i] - Pre[i][t] + Post[i][t];
        }
        return M_new;
    }
    public boolean containsArray(Set<int[]> A, int[] M) {
        for (int[] m : A) {
            if (Arrays.equals(m, M)) {
                return true;
            }
        }
        return false;
    }


    public void construireGraphe()
    { 
        int[][] pre=g.getPre();
        int[][] post=g.getPost();
        while (!A1.isEmpty()) {
             int[] M =A1.pop();
             for(int t=1;t<=pre[0].length;t++){
                if(tirable(M, pre, t-1)){
                    int[] M_new = new int[M.length];
                    M_new=calculerM_new(M, pre, post, t-1);
                    this.Resultat.add(new Affichage(M, t, M_new));
                    if(containsArray(A,M_new)==false)
                    {
                        this.A.add(M_new);
                        this.A1.push(M_new);
                    }
                }

             }
            
        }
    }




    public void afficherlaliste()
    {
        System.out.println("liste de marquage accessible:");
     for(int[] m : this.A){
        System.out.println(Arrays.toString(m));
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
            for (int[] m : A) {
                String nodeName = "M" + Arrays.toString(m).replaceAll("[\\[\\] ,]", "_");
                dot.append(nodeName)
                   .append(" [label=\"")
                   .append(Arrays.toString(m))
                   .append("\"];\n");
            }
    
            // create edges
            for (Affichage a : Resultat) {
                int[] source = a.getM();
                int[] target = a.getM_new();
                int t = a.getT();
    
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
