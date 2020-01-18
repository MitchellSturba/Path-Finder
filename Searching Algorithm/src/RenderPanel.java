import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Main a = Main.main;

//        for (Tile x[] : a.thisboard.tiles) {
//            for (Tile z: x) {
//                g.setColor(Color.black);
//                g.drawRect(z.location.x,z.location.y,z.width,z.height);
//                if (z.searched) {
//                    g.setColor(Color.decode("#cfcdc4"));
//                    g.fillRect(z.location.x+1,z.location.y+1,z.width-2,z.height-2);
//                }
//                if (z.start) {
//                    g.setColor(Color.decode("#56d131"));
//                    g.fillRect(z.location.x+1,z.location.y+1,z.width-2,z.height-2);
//                }
//                if (z.finish) {
//                    g.setColor(Color.decode("#ff403d"));
//                    g.fillRect(z.location.x+1,z.location.y+1,z.width-2,z.height-2);
//                }
//                if (z.isBarrior) {
//                    g.setColor(Color.BLACK);
//                    g.fillRect(z.location.x+1,z.location.y+1,z.width-2,z.height-2);
//                }
//            }
//
//        }

//        for (EndPeiceNode q : a.thisboard.myComplicatedList) {
//            g.fillRoundRect(q.pos.x);
//        }

//        if (a.thisboard.over) {
//            for (a.thisboard.myComplicatedList.)
//        }

        //design 2
        for (Tile x[] : a.thisboard.tiles) {
            for (Tile z: x) {
                g.setColor(Color.black);
                g.drawRect(z.location.x,z.location.y,z.width,z.height);
                if (z.searched && !z.isBarrior) {
                    g.setColor(Color.decode("#cfcdc4"));
                    g.fillRect(z.location.x,z.location.y,z.width,z.height);
                }
                if (z.start) {
                    g.setColor(Color.decode("#56d131"));
                    g.fillRect(z.location.x,z.location.y,z.width,z.height);
                }
                if (z.isEndPeice && !a.thisboard.over) {
                    g.setColor(Color.decode("#428ce6"));
                    g.fillRect(z.location.x,z.location.y,z.width,z.height);
                }
                if (z.finish) {
                    g.setColor(Color.decode("#ff403d"));
                    g.fillRect(z.location.x,z.location.y,z.width,z.height);
                }
                if (z.isBarrior) {
                    g.setColor(Color.BLACK);
                    g.fillRoundRect(z.location.x,z.location.y,z.width,z.height,2,2);
                }
                if (z.isShortestPath) {
                    g.setColor(Color.decode("#428ce6"));
                    g.fillRect(z.location.x,z.location.y,z.width,z.height);
                }
            }

        }

        for (Paths x : a.thisboard.myComplicatedList) {
            //enable this to view all the paths
            if (x.longestpath) {
                g.setColor(Color.decode("#428ce6"));
                g.fillRoundRect(a.thisboard.tiles[x.head.x][x.head.y].location.x,a.thisboard.tiles[x.head.x][x.head.y].location.y,25,25,0,0);
            }
//            if (!x.longestpath && x.) {
//                g.setColor(Color.decode("#428ce6"));
//                g.fillRoundRect(a.thisboard.tiles[x.head.x][x.head.y].location.x,a.thisboard.tiles[x.head.x][x.head.y].location.y,25,25,0,0);
//            }
        }
//
//        for (Paths x : a.thisboard.myComplicatedList) {
//            //enable this to view all the paths
//            if (!x.longestpath) {
//                g.setColor(Color.red);
//                g.fillRoundRect(a.thisboard.tiles[x.head.x][x.head.y].location.x,a.thisboard.tiles[x.head.x][x.head.y].location.y,25,25,24,24);
//            }
//        }

    }
}
