package display;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // User will not be able to resize
        frame.setLocationRelativeTo(null); // Centers window
        frame.setVisible(true); // Makes JFrame visible

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height)); // sets size of canvas
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));

        frame.add(canvas); // add canvas to jframe
        frame.pack(); // resized frame to fit whole canvas
    }

    public Canvas getCanvas(){
        return canvas;
    }
}
