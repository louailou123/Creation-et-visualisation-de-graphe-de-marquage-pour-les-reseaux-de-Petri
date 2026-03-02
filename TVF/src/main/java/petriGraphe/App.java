package petriGraphe;


public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[][] Pre = {
            {1, 3},
            {0, 0},
            {1, 1}
        };

        
        int[][] Post = {
            {0, 0},
            {2, 1},
            {1, 1}
        };
        int []M0={4, 2, 1};
        Graphe g0=new Graphe(Pre, Post, M0);
        GrapheMarquage gm0=new GrapheMarquage(g0);
        gm0.construireGraphe();
        gm0.afficherlaliste();
        gm0.affichergraphe();
        gm0.exporterGrapheHierarchique("graphe.dot");
    }
}
