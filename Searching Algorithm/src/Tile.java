import java.awt.*;

public class Tile {

    Point location = new Point();
    int height = 0;
    int width = 0;
    boolean searched = false;
    boolean downcheck = false;
    boolean isBarrior = false;
    boolean start = false;
    boolean finish = false;
    boolean isEndPeice = false;
    boolean isShortestPath = false;
    boolean pathchecked = false;

    public Tile(int w, int h, int x, int y) {
        height = h;
        width = w;
        location.x = x;
        location.y = y;
    }

}
