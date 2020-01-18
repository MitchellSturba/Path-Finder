import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Stack;

public class Board implements ActionListener{

    public final int boardWidth = 60, boardHeight = 60, tileWidth = 25, tileHeight = 25;
    public int tileX = 0, tileY = tileHeight;
    public Tile[][] tiles = new Tile[boardWidth][boardHeight];
    Point mousePoint;
    int ticks = 0, i =0, j = 0, requiredLength = 0;
    Timer tim = new Timer(10, this);
    boolean over = false;
//    LinkedList<String> myPath = new LinkedList<>();
    public LinkedList<Paths> myComplicatedList = new LinkedList<>();

    public Board () {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                tiles[i][j] = new Tile(tileWidth,tileHeight, (tileX += tileWidth) - tileWidth, tileY - tileHeight);
            }
            tileX = 0;
            tileY += tileHeight;
        }
    }

//    public void buildPath() {
//            for (String x : myPath) {
//                System.out.println(x);
//            }
//    }

    public void isEndPeice() {

        //find all end peices
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j <boardWidth; j++) {
                if ( i > 0 && tiles[i][j].searched && !tiles[i][j].isBarrior && !tiles[i-1][j].searched) {
                    tiles[i][j].isEndPeice = true;
                }else if ( j < boardWidth && tiles[i][j].searched && !tiles[i][j].isBarrior && !tiles[i][j+1].searched) {
                    tiles[i][j].isEndPeice = true;
                }else if ( i < boardHeight && tiles[i][j].searched && !tiles[i][j].isBarrior && !tiles[i+1][j].searched) {
                    tiles[i][j].isEndPeice = true;
                }else if ( j > 0 && tiles[i][j].searched && !tiles[i][j].isBarrior && !tiles[i][j-1].searched) {
                    tiles[i][j].isEndPeice = true;
                }
                else {
                    tiles[i][j].isEndPeice = false;
                }
            }
        }

        boolean breakthis = false;
        int theFuckingListSize = myComplicatedList.size();
        for (int n = 0; n < theFuckingListSize; n++) {
            Paths z = myComplicatedList.get(n);
            tiles[z.head.x][z.head.y].pathchecked = true;
//            System.out.println(z.head.x + " : " + z.head.y);

//            if (tiles[z.head.x - 1][z.head.y].isEndPeice && !over) {
//                z.head.x--;
//                myComplicatedList.add(new Paths(z.head.x,z.head.y,z.mypath, "UP"));
//            }

            //all directions
            if (tiles[z.head.x+1][z.head.y].isEndPeice && tiles[z.head.x][z.head.y + 1].isEndPeice && tiles[z.head.x][z.head.y - 1].isEndPeice && tiles[z.head.x-1][z.head.y].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x,z.head.y + 1, z.mypath,"RIGHT"));
                myComplicatedList.add(new Paths(z.head.x - 1,z.head.y, z.mypath,"UP"));
                myComplicatedList.add(new Paths(z.head.x + 1,z.head.y , z.mypath,"DOWN"));
                z.head.y--;
                z.mypath.add("LEFT");
                breakthis = true;
            }
            //left up and right
            if (tiles[z.head.x][z.head.y + 1].isEndPeice && tiles[z.head.x][z.head.y - 1].isEndPeice && tiles[z.head.x-1][z.head.y].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x,z.head.y + 1, z.mypath,"RIGHT"));
                myComplicatedList.add(new Paths(z.head.x - 1,z.head.y, z.mypath,"UP"));
                z.head.y--;
                z.mypath.add("LEFT");
                breakthis = true;
            }
            //up right and down
            if (tiles[z.head.x - 1][z.head.y].isEndPeice && tiles[z.head.x][z.head.y + 1].isEndPeice && tiles[z.head.x+1][z.head.y].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x + 1,z.head.y, z.mypath,"DOWN"));
                myComplicatedList.add(new Paths(z.head.x - 1,z.head.y, z.mypath,"UP"));
                z.head.y++;
                z.mypath.add("RIGHT");
                breakthis = true;
            }
            //up left and down
            if (tiles[z.head.x - 1][z.head.y].isEndPeice && tiles[z.head.x][z.head.y - 1].isEndPeice && tiles[z.head.x+1][z.head.y].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x + 1,z.head.y, z.mypath,"DOWN"));
                myComplicatedList.add(new Paths(z.head.x - 1,z.head.y, z.mypath,"UP"));
                z.head.y--;
                z.mypath.add("LEFT");
                breakthis = true;
            }
            //Left and down and right
            if (tiles[z.head.x][z.head.y - 1].isEndPeice && tiles[z.head.x+1][z.head.y].isEndPeice && tiles[z.head.x][z.head.y+1].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x,z.head.y + 1, z.mypath,"RIGHT"));
                myComplicatedList.add(new Paths(z.head.x + 1,z.head.y, z.mypath,"DOWN"));
                z.head.y--;
                z.mypath.add("LEFT");
                breakthis = true;
            }
            //down and up
            if (tiles[z.head.x + 1][z.head.y].isEndPeice && tiles[z.head.x - 1][z.head.y].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x - 1,z.head.y, z.mypath,"UP"));
                z.head.x++;
                z.mypath.add("DOWN");
                breakthis = true;
            }
            //left and up
            if (tiles[z.head.x][z.head.y - 1].isEndPeice && tiles[z.head.x - 1][z.head.y].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x - 1,z.head.y, z.mypath,"UP"));
                z.head.y--;
                z.mypath.add("LEFT");
                breakthis = true;
            }
            //left and right
            if (tiles[z.head.x][z.head.y + 1].isEndPeice && tiles[z.head.x][z.head.y - 1].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x,z.head.y + 1, z.mypath,"RIGHT"));
                z.head.y--;
                z.mypath.add("LEFT");
                breakthis = true;
//               requiredLength -= 2;
            }
            //up and right
            if (tiles[z.head.x - 1][z.head.y].isEndPeice && tiles[z.head.x][z.head.y + 1].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x - 1,z.head.y, z.mypath,"UP"));
                z.head.y++;
                z.mypath.add("RIGHT");
                breakthis = true;
            }
            //right and down
            if (tiles[z.head.x][z.head.y + 1].isEndPeice && tiles[z.head.x+1][z.head.y].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x + 1,z.head.y, z.mypath,"DOWN"));
                z.head.y++;
                z.mypath.add("RIGHT");
                breakthis = true;
            }
            //Left and down
            if (tiles[z.head.x][z.head.y - 1].isEndPeice && tiles[z.head.x+1][z.head.y].isEndPeice) {
                myComplicatedList.add(new Paths(z.head.x + 1,z.head.y, z.mypath,"DOWN"));
                z.head.y--;
                z.mypath.add("LEFT");
                breakthis = true;
            }
            //right
                if (tiles[z.head.x][z.head.y + 1].isEndPeice) {
                    z.mypath.add("RIGHT");
                    z.head.y++;
                    breakthis = true;
                }
                //down
                if (tiles[z.head.x + 1][z.head.y].isEndPeice) {
                    z.mypath.add("DOWN");
                    z.head.x++;
                    breakthis = true;
                }
                //left
                if (tiles[z.head.x][z.head.y - 1].isEndPeice) {
                    z.mypath.add("LEFT");
                    z.head.y--;
                    breakthis = true;
                }
                //up
                if (tiles[z.head.x - 1][z.head.y].isEndPeice) {
                    z.mypath.add("UP");
                    z.head.x--;
                    breakthis = true;
                }
                if (breakthis) requiredLength++;

        }
//
//        for (Paths z : myComplicatedList) {
//            if (z.mypath.size() != requiredLength) {
//                z.validpath = false;
//            }
//        }

    }

    public void PrintPaths () {
        //this for loop will print all the paths
//        for (Paths q : myComplicatedList) {
//            q.PrintPath();
//            System.out.println("\nBREAK\n");
//        }
        //this one prints the longest
        for (Paths q : myComplicatedList) {
            //checks if the head is touching the finish line and validates the path
            if (q.head.x - 1 > 0 && q.head.y - 1 > 0 && q.head.x < boardHeight && q.head.y < boardWidth) {
                if (tiles[q.head.x - 2][q.head.y].finish || tiles[q.head.x + 2][q.head.y].finish || tiles[q.head.x][q.head.y + 2].finish || tiles[q.head.x][q.head.y - 2].finish) {
                    q.longestpath = true;
                }
            }
        }
        for (Paths q : myComplicatedList) {
            if (q.longestpath) {
                drawShortestPath(q);
            }
        }

//        myComplicatedList.get(index).PrintPath();
    }

    public void drawShortestPath(Paths q) {

        Point DrawNode = new Point();
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {

                if (tiles[i][j].start) {
                    DrawNode.x = j;
                    DrawNode.y = i;
                }

            }
        }
        for (int i = 0; i < q.mypath.size(); i++) {
            String nextmove = q.mypath.get(i);
            switch (nextmove) {
                case "LEFT" :
                    tiles[DrawNode.y][--DrawNode.x].isShortestPath = true;
                    break;
                case "RIGHT":
                    tiles[DrawNode.y][++DrawNode.x].isShortestPath = true;
                    break;
                case "DOWN" :
                    tiles[++DrawNode.y][DrawNode.x].isShortestPath = true;
                    break;
                case "UP":
                    tiles[--DrawNode.y][DrawNode.x].isShortestPath = true;
                    break;
            }
        }

    }

    public void setStart(Point x) {
        for (i = 0; i < boardHeight; i++) {
            for (j = 0; j <  boardWidth; j++) {
                if (tiles[i][j].location.x <= x.x && tiles[i][j].location.x + tileWidth > x.x && tiles[i][j].location.y <= x.y && tiles[i][j].location.y + tileHeight >= x.y) {
                    tiles[i][j].start = true;

                    //Add start to the list.
                    myComplicatedList.add(new Paths(i,j));
                    break;
                }
            }

        }
        System.out.println("Path head: " + myComplicatedList.peek().head.x + " " + myComplicatedList.peek().head.y);
    }
    public void setEnd(Point x) {
        for (i = 0; i < boardHeight; i++) {
            for (j = 0; j <  boardWidth; j++) {
                if (tiles[i][j].location.x <= x.x && tiles[i][j].location.x + tileWidth > x.x && tiles[i][j].location.y <= x.y && tiles[i][j].location.y + tileHeight >= x.y) {
                    tiles[i][j].finish = true;
                    break;
                }
            }

        }
    }

    public void makeBarriors(Point x) {
        for (i = 0; i < boardHeight; i++) {
            for (j = 0; j <  boardWidth; j++) {
                if (tiles[i][j].location.x <= x.x && tiles[i][j].location.x + tileWidth > x.x && tiles[i][j].location.y <= x.y && tiles[i][j].location.y + tileHeight >= x.y) {
                    tiles[i][j].isBarrior = true;
                    tiles[i][j].searched = true;
                    break;
                }
            }

        }
    }
    public void EraseBarriers(Point x) {
        for (i = 0; i < boardHeight; i++) {
            for (j = 0; j <  boardWidth; j++) {
                if (tiles[i][j].location.x <= x.x && tiles[i][j].location.x + tileWidth > x.x && tiles[i][j].location.y <= x.y && tiles[i][j].location.y + tileHeight >= x.y) {
                    tiles[i][j].isBarrior = false;
                    tiles[i][j].searched = false;
                    tiles[i][j].start = false;
                    tiles[i][j].finish = false;

                    break;
                }
            }

        }
    }

    public void checkClick() {
        boolean broken = false;
        for (i = 0; i < boardHeight && !broken; i++) {
            for (j = 0; j <  boardWidth; j++) {
                if (tiles[i][j].start) {
                    tiles[i][j].searched = true;
                }
            }
        }

        tim.start();
    }
    //check every node
    public void search() {
        boolean broken = false;
        for (i = 0; i < boardHeight ;i++) {
            for (j = 0; j <  boardWidth - 1; j++) {
                if (tiles[i][j].location.x < Main.main.panel.getWidth() && tiles[i][j].location.y < Main.main.panel.getHeight()) {
                    if (tiles[i][j].searched && !tiles[i][j].downcheck && !tiles[i][j].isBarrior && tiles[i][j].location.y < Main.main.frame.getHeight() && tiles[i][j].location.x < Main.main.frame.getWidth()) {
                        if (i != 0 && !tiles[i-1][j].searched) {
                            tiles[i-1][j].searched = true;
                            Main.main.panel.repaint();
                        }
                        if (j != 0 && !tiles[i][j-1].searched) {
                            tiles[i][j-1].searched = true;
                            Main.main.panel.repaint();
                        }
                        if (i < boardHeight - 1 && !tiles[i+1][j].searched && !tiles[i+1][j].downcheck) {
                            tiles[i+1][j].searched = true;
                            tiles[i+1][j].downcheck = true;
                            Main.main.panel.repaint();
                        }
                        if (j != boardWidth - 1 && !tiles[i][j+1].searched) {
                            tiles[i][j+1].searched = true;
                            Main.main.panel.repaint();
                            j+= 1;
                        }

                    }
                }
            }
        }
        for (i = 0; i < boardHeight; i++) {
            for (j = 0; j <  boardWidth; j++) {
                   tiles[i][j].downcheck = false;
                   if (tiles[i][j].finish && ((!tiles[i-1][j].isBarrior && tiles[i-1][j].searched) || (tiles[i][j+1].searched && !tiles[i][j+1].isBarrior) || (tiles[i+1][j].searched && !tiles[i+1][j].isBarrior) || (tiles[i][j-1].searched && !tiles[i][j-1].isBarrior))) {
                       tim.stop();
                       System.out.println("\n\n List size:" + myComplicatedList.size() + " : Required Path Length:" + requiredLength + "\n" + "Path 1 size: " + myComplicatedList.get(0).mypath.size());
                       over = true;
                       PrintPaths();

//                       validateFinalPath();
                   }
            }
        }
//        for (i = 0; i < boardHeight && !broken ;i++) {
//            for (j = 0; j <  boardWidth - 1; j++) {
//                if (tiles[i][j].searched) {
//                    if (!tiles[i+1][j].searched) {
//                        tiles[i+1][j].searched = true;
//                        Main.main.panel.repaint();
//                        broken = true;
//                        break;
//                    }
//                }
//            }
//
//        }
    }

    public void undraw() {
        tim.start();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        ticks++;
        if (ticks % 3 == 0) {
            search();
            isEndPeice();
            Main.main.panel.repaint();

        }

    }

    public void clear() {
        for (Tile z[] : tiles) {
            for (Tile y : z) {
                y.searched = false;
                y.isBarrior = false;
                y.downcheck = false;
                y.start = false;
                y.finish = false;
                y.isEndPeice = false;
                y.pathchecked = false;
                y.isEndPeice = false;
                y.isShortestPath = false;
            }
        }
    }

}
