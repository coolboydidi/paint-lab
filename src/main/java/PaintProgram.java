import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintProgram implements ActionListener {
    JFrame frame;
    DrawingPanel dPanel;
    JPanel buttonPanel;
    JButton pencilButton, eraserButton;


    // This is the PaintProgram constructor which sets up the JFrame
    // and all other components and containers
    // ** Code to be edited in Part C **
    public PaintProgram() {
        // Set up JFrame using BorderLayout
        frame = new JFrame("Paint Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new DrawingPanel();
        frame.add(dPanel);

        // Create buttonPanel and buttons
        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        pencilButton = new JButton("Pencil");
        pencilButton.addActionListener(this);
        buttonPanel.add(pencilButton);

        eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(this);
        buttonPanel.add(eraserButton);

        JButton sprayButton = new JButton("Spray");
        sprayButton.addActionListener(this);
        buttonPanel.add(sprayButton);



        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clearButton.getActionCommand().equals("Clear")) {
                    dPanel.clearButtonfunction();
                }
            }
        });
        buttonPanel.add(clearButton);

        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.PAGE_AXIS));
        frame.add(colorPanel, BorderLayout.EAST);

        JButton blackButton = new JButton("Black");
        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (blackButton.getActionCommand().equals("Black")){
                    dPanel.setColor(Color.BLACK);
                }
            }
        });
        colorPanel.add(blackButton);

        JButton blueButton = new JButton("Blue");
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (blueButton.getActionCommand().equals("Blue")){
                    dPanel.setColor(Color.BLUE);
                }
            }
        });
        colorPanel.add(blueButton);

        JButton redButton = new JButton("Red");
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (redButton.getActionCommand().equals("Red")) {
                    dPanel.setColor(Color.RED);
                }
            }
        });
        colorPanel.add(redButton);

        JButton greenButton = new JButton("Green");
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (greenButton.getActionCommand().equals("Green")) {
                    dPanel.setColor(Color.GREEN);
                }
            }
        });

        colorPanel.add(greenButton);

        JButton libraryButton = new JButton("Library");
        libraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
                if (selectedColor != null) {
                    dPanel.setColor(selectedColor);
                }
            }
        });
        colorPanel.add(libraryButton);

        JButton pickButton = new JButton("Pick");
        pickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dPanel.setMode(dPanel.mode+"Pick");

            }
        });
        colorPanel.add(pickButton);

        JPanel markerPanel = new JPanel();
        markerPanel.setLayout(new BoxLayout(markerPanel, BoxLayout.PAGE_AXIS));
        frame.add(markerPanel, BorderLayout.WEST);

        JButton markerButton = new JButton("Marker");
        markerButton.addActionListener(this);
        markerPanel.add(markerButton);

        JButton markerShape1 = new JButton("Shape 1");
        markerShape1.addActionListener(this);
        markerPanel.add(markerShape1);

        JButton markerShape2 = new JButton("Shape 2");
        markerShape2.addActionListener(this);
        markerPanel.add(markerShape2);

        JButton markerShape3 = new JButton("Shape 3");
        markerShape3.addActionListener(this);
        markerPanel.add(markerShape3);

        JButton markerShape4 = new JButton("Shape 4");
        markerShape4.addActionListener(this);
        markerPanel.add(markerShape4);

        // Set the size and set the visibility
        frame.pack();
        frame.setVisible(true);
    }

    // This the code that is called when any button is pressed
    // We should have a separate case for each button
    // ** Code to be edited in Part B **
    public void actionPerformed(ActionEvent ae) {
        // If pencilButton is pressed, set drawingPanel mode to "Pencil"
        if (ae.getActionCommand().equals("Pencil")) {
            dPanel.setMode("Pencil");
        }
        if (ae.getActionCommand().equals("Eraser")) {
            dPanel.setMode("Eraser");
        }
        if (ae.getActionCommand().equals("Spray")) {
            dPanel.setMode("Spray");
        }
        if (ae.getActionCommand().equals("Marker")) {
            dPanel.setMode("Marker");
        }
        if (dPanel.mode.equals("Marker") && ae.getActionCommand().equals("Shape 1") ||
                dPanel.mode.indexOf("Shape") > -1 && ae.getActionCommand().equals("Shape 1")) {
            dPanel.setMode("Shape 1");
        }
        if (dPanel.mode.equals("Marker") && ae.getActionCommand().equals("Shape 2") ||
                dPanel.mode.indexOf("Shape") > -1 && ae.getActionCommand().equals("Shape 2")) {
            dPanel.setMode("Shape 2");
        }
        if (dPanel.mode.equals("Marker") && ae.getActionCommand().equals("Shape 3") ||
                dPanel.mode.indexOf("Shape") > -1 && ae.getActionCommand().equals("Shape 3")) {
            dPanel.setMode("Shape 3");
        }
        if (dPanel.mode.equals("Marker") && ae.getActionCommand().equals("Shape 4") ||
                dPanel.mode.indexOf("Shape") > -1 && ae.getActionCommand().equals("Shape 4")) {
            dPanel.setMode("Shape 4");
        }
    }

    // Main method just creates a PaintProgram object
    public static void main(String[] args) {
        PaintProgram x = new PaintProgram();
    }

    class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
        // DrawingPanel has the following instance variables:

        // A 2D array which stores whether or not
        // each pixel should be painted
        // ** To be used in Part B **
        private boolean[][] isPainted;

        // A 2D array which stores the Java Colors
        // of each pixel
        // ** To be used in Part C **
        private Color[][] colors;

        // The mode is a String that we can use to keep track of
        // what should happen if the user presses the mouse
        // ** To be used in Part B **
        private String mode;

        // This keeps track of the current selected color
        // ** To be used in Part C **
        private Color color;

        // These are constant values
        private static final int WIDTH = 500;
        private static final int HEIGHT = 500;

        // Constructor sets up DrawingPanel
        // ** You should never need to edit this code **
        public DrawingPanel() {
            // Set background color
            setBackground(Color.WHITE);

            // Add mouse listeners
            addMouseListener(this);
            addMouseMotionListener(this);


            // Initialize instance variables
            isPainted = new boolean[WIDTH][HEIGHT];
            colors = new Color[WIDTH][HEIGHT];
            mode = "Pencil";
            color = Color.BLACK;
        }

        public void clearButtonfunction() {
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    isPainted[x][y] = false;
                    colors[x][y] = Color.WHITE;
                }
            }
            repaint();
        }
        // Can be called to change the current mode
        // of the drawing panel
        // ** You should never need to edit this code **
        public void setMode(String mode) {
            this.mode = mode;
        }

        // Can be called to change the current color
        // of the drawing panel
        // ** You should never need to edit this code **
        public void setColor(Color color) {
            this.color = color;
        }

        // Sets the size of the DrawingPanel, so frame.pack() considers
        // its preferred size when sizing the JFrame
        // ** You should never need to edit this code **
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }

        // This is the method that draws everything
        // ** Code to be edited in Part C **
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Loop through the 2D array and draw a 1x1 rectangle
            // on each pixel that is currently painted
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (isPainted[x][y]) {
                        g.setColor(colors[x][y]);
                        g.drawRect(x, y, 1, 1);

                    }
                }
            }
        }

        // MouseListener methods
        // This is the method that is called when the mouse
        // is pressed. This is where most of your code will go
        // ** Code to be edited in Part B **
        public void pencilMethod(MouseEvent e) {
            if (e.getX() >= 0 && e.getX() < WIDTH &&
                    e.getY() >= 0 && e.getY() < HEIGHT) {
                // Set current pixel as painted
                colors[e.getX()][e.getY()] = this.color;
                isPainted[e.getX()][e.getY()] = true;

            }
        }


        public void mousePressed(MouseEvent e) {
            // Check the current mode
            // * If "pencil" mode, we should mark the current
            //   pixel as painted
            if (mode.equals("Pencil")) {
                // Check that mouse is in bounds of panel
                pencilMethod(e);
            }
            if (mode.equals("Eraser")) {
                if (e.getX()-2 > 0 && e.getX()+2 < WIDTH &&
                        e.getY()-2 > 0 && e.getY()+2 < HEIGHT) {
                    for(int i = e.getX() - 2; i <= e.getX()+ 2; i++){
                        for(int j = e.getY() - 2; j <= e.getY() + 2; j++) {
                            isPainted[i][j] = false;

                        }
                    }

                }
}
            if(mode.equals("Spray")) {
                if (e.getX()-10 >= 0 && e.getX()+10 < WIDTH &&
                        e.getY()-10 >= 0 && e.getY()+10 < HEIGHT) {
                    for (int i = e.getX() - 10; i <= e.getX() + 10; i++) {
                        for (int j = e.getY() - 10; j <= e.getY() + 10; j++) {
                            int randomizer = (int)(Math.random()*100);
                            if (randomizer > 90) {
                                isPainted[i][j] = true;
                                colors[i][j] = this.color;
                            }

                        }
                    }
                }
            }

            if(mode.equals("Shape 1")) {
                if (e.getX()-1 >= 0 && e.getX()+1 < WIDTH &&
                        e.getY()-10 >= 0 && e.getY()+10 < HEIGHT) {
                    for (int i = e.getX() - 1; i <= e.getX() + 1; i++) {
                        for (int j = e.getY() - 10; j <= e.getY() + 10; j++) {
                                isPainted[i][j] = true;
                                colors[i][j] = this.color;
                                //colors[i-1][j] = this.color;
                                //colors[i][j] = Color.ORANGE;
                            }

                        }
                    }
                }
            if(mode.equals("Shape 2")){
                int x = e.getX();
                if (e.getX()-1 >= 0 && e.getX()+22 < WIDTH &&
                    e.getY()-10 >= 0 && e.getY()+10 < HEIGHT) {
                    for (int j = e.getY() - 10; j <= e.getY() + 10; j++) {
                        x++;
                        isPainted[x][j] = true;
                        colors[x][j] = this.color;
                    }

                }
            }
            if(mode.equals("Shape 3")) {
                if (e.getX()-10 >= 0 && e.getX()+10 < WIDTH &&
                        e.getY()-1 >= 0 && e.getY()+1 < HEIGHT) {
                    for (int i = e.getX() - 10; i <= e.getX() + 10; i++) {
                        for (int j = e.getY() - 1; j <= e.getY() + 1; j++) {
                            isPainted[i][j] = true;
                            colors[i][j] = this.color;
                        }

                    }
                }
            }
            if (mode.equals("Shape 4")) {
                if (e.getX()-4 >= 0 && e.getX()+4 < WIDTH &&
                        e.getY()-4 >= 0 && e.getY()+4 < HEIGHT) {
                    for (int i = e.getX() - 4; i <= e.getX() + 4; i++) {
                        for (int j = e.getY() - 4; j <= e.getY() + 4; j++) {
                            isPainted[i][j] = true;
                            colors[i][j] = this.color;
                        }

                    }
                }
            }
            if (mode.indexOf("Pick") > -1) {
                color = colors[e.getX()][e.getY()];
                setMode(mode.substring(0,mode.indexOf("Pick") - 1));
            }


            // We need to manually tell the panel to repaint
            // and call the paintComponent method
            repaint();
        }

        // This is a MouseMotionListener method
        // We have this method so that we don't need to click each
        // pixel that we want to draw
        // ** You should never need to edit this code **
        public void mouseDragged(MouseEvent e) {
            mousePressed(e);
        }

        // The remaining MouseListener and MouseMotionLister
        // methods are left blank
        // ** You should never need to edit this code **
        public void mouseReleased(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseEntered(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseExited(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseClicked(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseMoved(MouseEvent e) {
            // This method is intentionally blank
        }
    }
}
