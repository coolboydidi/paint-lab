import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintDemo {
    JFrame frame;
    DrawingPanel dPanel;

    // This is the constructor which sets up the JFrame
    // and all other components and containers
    public PaintDemo() {
        // Set up JFrame
        frame = new JFrame("Paint Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new DrawingPanel();
        frame.add(dPanel);

        // Set the size and set the visibility
        frame.pack();
        frame.setVisible(true);
    }

    // Main method just creates a PaintDemo object
    public static void main(String[] args) {
        PaintDemo x = new PaintDemo();
    }

    class DrawingPanel extends JPanel {
        // Constructor sets up DrawingPanel
        public DrawingPanel() {
            setBackground(Color.WHITE);
        }

        // Sets the size of the DrawingPanel, so frame.pack() considers
        // its preferred size when sizing the JFrame
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(500, 500);
        }

        // This is the method that draws everything
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.drawRect(100, 150, 300, 300);
            g.drawOval(30, 30, 50, 50);
            g.drawArc(100, 50, 300, 200, 0, 180);

            g.setColor(Color.RED);
            g.fillRect(225, 350, 50, 100);
        }
    }
}
