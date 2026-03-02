package petriGraphe;


public class Graphe {
private int[][] Pre;
private int[][] Post;
private int [] M0;
public Graphe(int[][] Pre, int[][] Post, int[] M0) {
    this.Pre = Pre;
    this.Post = Post;
    this.M0 = M0;
}

public int[][] getPre() {
    return Pre;
}

public void setPre(int[][] pre) {
    Pre = pre;
}

public int[][] getPost() {
    return Post;
}

public void setPost(int[][] post) {
    Post = post;
}

public int[] getM0() {
    return M0;
}

public void setM0(int[] m0) {
    M0 = m0;
}
}
