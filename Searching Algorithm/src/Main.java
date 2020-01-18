import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main implements ActionListener, KeyListener, MouseListener , MouseMotionListener {

    /**
     * Important stuff for the window
     */
    public static Main main;
    JFrame frame = new JFrame("SearchySquares");
    RenderPanel panel = new RenderPanel();
    JPanel saveScreen = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.setFont(new Font("Verdana", Font.BOLD, 16));
            if (saving) g.drawString("Name your board: ", 50,35);
            if (loading) {
                setBackground(Color.decode("#b4f7af"));
                g.drawString("Load board: ", 50,35);
                g.setColor(Color.white);
                g.fillRoundRect(40,50,400,300,20,20);
                g.setColor(Color.black);
            }
            int multiplier = 70, xaxismover = 10;
            g.setFont(new Font("Verdana", Font.PLAIN, 14));
            for (String c : storedBoardNames) {
                g.drawString(c,xaxismover += 40,multiplier);
                xaxismover += 30;
                if (xaxismover > 300) {
                    xaxismover = 10;
                    multiplier += 40;
                }
            }
            g.setFont(new Font("Verdana", Font.BOLD, 14));

        }
    };
    JPanel controlPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.decode("#848c7f"));
            g.fillRoundRect(6,4,108,but8.getY() + 82,10,10);
        }
    };
    JTextField textField = new JTextField();
    JButton but1 = new JButton("Draw Barriers") {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            if (stage1) {
                g.fillOval(4,4,12,12);
                g.setColor(Color.green);
                g.fillOval(5,5,10,10);
            }else {
                g.fillOval(4,4,12,12);
                g.setColor(Color.red);
                g.fillOval(5,5,10,10);
            }

        }
    };
    JButton but2 = new JButton("Mark Start"){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            if (stage2) {
                g.fillOval(4,4,12,12);
                g.setColor(Color.green);
                g.fillOval(5,5,10,10);
            }else {
                g.fillOval(4,4,12,12);
                g.setColor(Color.red);
                g.fillOval(5,5,10,10);
            }

        }
    };
    JButton but3 = new JButton("Mark End"){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            if (stage3) {
                g.fillOval(4,4,12,12);
                g.setColor(Color.green);
                g.fillOval(5,5,10,10);
            }else {
                g.fillOval(4,4,12,12);
                g.setColor(Color.red);
                g.fillOval(5,5,10,10);
            }

        }
    };
    JButton but7 = new JButton("Erase Barrier"){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            if (stage7) {
                g.fillOval(4,4,12,12);
                g.setColor(Color.green);
                g.fillOval(5,5,10,10);
            }else {
                g.fillOval(4,4,12,12);
                g.setColor(Color.red);
                g.fillOval(5,5,10,10);
            }

        }
    };
    JButton but4 = new JButton("Search");
    JButton but5 = new JButton("Restart");
    JButton but6 = new JButton("Save Board");
    JButton but8 = new JButton("Load Board");

    //Test a stack
    Stack<Board> myStack = new Stack<>();
    int steps = 0;


    Board thisboard;
    int ticks = 0;
    boolean stage1 = true, stage2 = false, stage3 = false, stage7 = false, saving = false, loading = false;
    int controlpanelWidth = 120;
    ArrayList<String> storedBoardNames = new ArrayList<>();

    //Values
    boolean search = true;

    public Main() {

        thisboard = new Board();
        saveScreen.setPreferredSize(new Dimension(300,200));
        controlPanel.setPreferredSize(new Dimension(controlpanelWidth,200));
        but1.setPreferredSize(new Dimension(100,80));
        but1.addActionListener(this);
        but2.setPreferredSize(new Dimension(100,80));
        but2.addActionListener(this);
        but3.setPreferredSize(new Dimension(100,80));
        but3.addActionListener(this);
        but4.setPreferredSize(new Dimension(100,80));
        but4.addActionListener(this);
        but5.setPreferredSize(new Dimension(100,80));
        but5.addActionListener(this);
        but6.setPreferredSize(new Dimension(100,80));
        but6.addActionListener(this);
        but7.setPreferredSize(new Dimension(100,80));
        but7.addActionListener(this);
        but8.setPreferredSize(new Dimension(100,80));
        but8.addActionListener(this);
        controlPanel.add(but1);
        controlPanel.add(but7);
        controlPanel.add(but2);
        controlPanel.add(but3);
        controlPanel.add(but4);
        controlPanel.add(but5);
        controlPanel.add(but6);
        controlPanel.add(but8);

        controlPanel.setBackground(Color.black);
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setBackground(Color.WHITE);
        frame.add(panel);
        frame.add(controlPanel,BorderLayout.WEST);
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        but6.setFocusable(false);
        textField.addKeyListener(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        controlPanel.repaint();
        try {
            Scanner ss = new Scanner(new FileInputStream("boardDesigns.txt"));
            while (ss.hasNextLine()) {
                storedBoardNames.add(ss.next());
            }
        }catch (Exception asdf) {
            System.out.println("Exception loading board names: " + asdf);
        }
//        File folder = new File(Paths.get(".").toAbsolutePath().normalize().toString());
//        File[] files = folder.listFiles();
//        for (File b : files) {
//            System.out.println(b.toString());
//        }

        System.out.println(frame.getHeight());


    }

    public static void main(String[] args) {
        main = new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
       if (action.equals("Draw Barriers")) {
           stage1 = true;
           stage2 = false;
           stage3 = false;
           stage7 = false;
           controlPanel.repaint();
       }
        if (action.equals("Erase Barrier")) {
            stage1 = false;
            stage2 = false;
            stage3 = false;
            stage7 = true;
            controlPanel.repaint();
        }
        if (action.equals("Mark Start")) {
            stage1 = false;
            stage2 = true;
            stage3 = false;
            stage7 = false;
            controlPanel.repaint();
        }
        if (action.equals("Mark End")) {
            stage1 = false;
            stage2 = false;
            stage3 = true;
            stage7 = false;
            controlPanel.repaint();
        }
        if (action.equals("Search")){
            thisboard.checkClick();
            panel.repaint();
        }
        if (action.equals("Restart")){
            thisboard.clear();
            stage1 = true;
            stage2 = false;
            stage3 = false;
            panel.repaint();
        }
        if (action.equals("Save Board")){
            saving = true;
            frame.setLayout(null);
//            saveScreen.setPreferredSize(new Dimension(400,400));
            panel.setLayout(null);
            textField.setBounds(200,18,220,25);
            saveScreen.setLayout(null);
            saveScreen.add(textField);
            saveScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            saveScreen.setBackground(Color.WHITE);
            saveScreen.setBounds(panel.getWidth()/2 - 250,frame.getHeight() - 120,500,60);
            saveScreen.addKeyListener(this);
            saveScreen.setFocusable(true);
            panel.add(saveScreen);
            saveScreen.repaint();
            frame.addKeyListener(this);
            frame.revalidate();
            panel.repaint();
        }
        if (action.equals("Load Board")){
            loading = true;
            frame.setLayout(null);
//            saveScreen.setPreferredSize(new Dimension(400,400));
            panel.setLayout(null);
            textField.setBounds(200,18,220,25);
            saveScreen.setLayout(null);
            saveScreen.add(textField);
            saveScreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            saveScreen.setBackground(Color.WHITE);
            saveScreen.setBounds(panel.getWidth()/2 - 250,frame.getHeight()/2 - 200,500,400);
            saveScreen.addKeyListener(this);
            saveScreen.setFocusable(true);
            panel.add(saveScreen);
            saveScreen.repaint();
            frame.addKeyListener(this);
            frame.revalidate();
            panel.repaint();
        }

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
//        System.out.print("keypressed\n");

        if (i == KeyEvent.VK_SPACE&& !saving) {
//            thisboard.clear();
//            panel.repaint();
        }
        if (i == KeyEvent.VK_1&& !saving) {
            stage1 = true;
            stage2 = false;
            panel.repaint();
        }
        if (i == KeyEvent.VK_2&& !saving) {
            stage1 = false;
            stage2 = true;
            panel.repaint();
        }
        if (i == KeyEvent.VK_3&& !saving) {
            stage1 = false;
            stage2 = false;
            stage3 = true;
            panel.repaint();
        }
        if (i == KeyEvent.VK_4 && !saving) {
            thisboard.checkClick();
            panel.repaint();
        }
        if (i == KeyEvent.VK_ENTER && saving && !loading) {
            saving = false;
            System.out.print("inHere");
            PrintWriter outS;
            PrintWriter keeptrack;
            String saveName = textField.getText();
            storedBoardNames.add(saveName);
            try {
                outS = new PrintWriter(new FileOutputStream(saveName + ".txt"));
                keeptrack = new PrintWriter(new FileOutputStream("boardDesigns.txt",true));
                for  (Tile[] n : thisboard.tiles) {
                    for (Tile m : n) {
                        if (m.searched && !m.isBarrior) outS.print("5 ");
                        if ((!m.searched && !m.start && !m.finish)) outS.print("0 ");
                        if (m.isBarrior) outS.print("1 ");
                        if (m.start) outS.print("2 ");
                        if (m.finish) outS.print("3 ");
                    }
                    outS.print("\n");
                }
                keeptrack.println(saveName);
                keeptrack.close();
                outS.close();
            }catch (Exception q) {

            }
            textField.setText("");
            panel.remove(saveScreen);
            frame.revalidate();
            panel.repaint();
        }
        if (i == KeyEvent.VK_ENTER && loading && !saving) {
            thisboard = null;
            thisboard = new Board();
            thisboard.clear();
            loading = false;
            if (textField.getText().equals("maze5") || textField.getText().equals("maze")) {
                maze5 thismaze = new maze5();
                thismaze.loadmaze();
            }else {
                try {
                    Scanner sc = new Scanner(new FileInputStream(textField.getText() + ".txt"));
                    for  (Tile[] n : thisboard.tiles) {
                        for (Tile m : n) {
                            int num = sc.nextInt();
                            if (num == 0) {
                                m.searched = false;
                                m.isBarrior = false;
                                m.start = false;
                                m.finish = false;
                            }
                            if (num == 1) {
                                m.searched = false;
                                m.isBarrior = true;
                                m.start = false;
                                m.finish = false;
                            }
                            if (num == 2) {
                                m.searched = true;
                                m.isBarrior = false;
                                m.start = true;
                                m.finish = false;
                            }
                            if (num == 3) {
                                m.searched = false;
                                m.isBarrior = false;
                                m.start = false;
                                m.finish = true;
                            }
                        }
                        panel.repaint();
//                    sc.nextLine();
                    }
                }catch (Exception q) {
                    System.out.println(q);
                }
            }
            textField.setText("");
            panel.remove(saveScreen);
            frame.revalidate();
            panel.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        Point x = e.getPoint();
        x.y -= 20;
        x.x -= controlpanelWidth;
        if (stage1) {
            thisboard.makeBarriors(x);
        }
        if (stage2) {
            thisboard.setStart(x);
        }
        if (stage3) {
            thisboard.setEnd(x);
        }
        if (stage7) {
            thisboard.EraseBarriers(x);
        }
        panel.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point x = e.getPoint();
        if (!frame.isMaximumSizeSet()) x.y -= 20;
        x.x -= controlpanelWidth;
        if (stage1) {
            thisboard.makeBarriors(x);
            panel.repaint();
        }
        if (stage7) {
            thisboard.EraseBarriers(x);
            panel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
