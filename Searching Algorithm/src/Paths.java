import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;


public class Paths {

    LinkedList<String> mypath = new LinkedList<>();
    Point head = new Point();
    boolean longestpath = false;
    boolean validpath = true;


    public Paths(int i, int j) {
        head.x = i;
        head.y = j;
    }
    public Paths(int i, int j, LinkedList<String> path, String nextmove) {
        head.x = i;
        head.y = j;
        mypath = (LinkedList<String>)path.clone();
        this.mypath.add(nextmove);
    }

    public void PrintPath() {

        for (int i = 0; i < mypath.size(); i++) {
            System.out.println(mypath.get(i));
        }
    }


}

