package petriGraphe.AbstractClasses;
import lombok.Getter;
import lombok.Setter;
import petriGraphe.Marquages.Marquage;

@Getter
@Setter
public abstract class AbstractGraphe {
    protected  int[][] Pre;
    protected  int[][] Post;
    protected  Marquage M0;
    public AbstractGraphe(int[][] Pre, int[][] Post, Marquage M0) {
    this.Pre = Pre;
    this.Post = Post;
    this.M0 = M0;
}
}
