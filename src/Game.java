import display.Display;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;
    private boolean running = false;
    private Thread thread;

    public String title;
    public int width, height;

    private BufferStrategy bs;
    private Graphics g;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init(){
        display = new Display(title, width, height);
        Assets.init();
    }

    int x = 0;

    private void update(){
        x += 1;
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();

        // Check if starting game (0 buffers) and set buffer amount;
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        // Clear screen
        g.clearRect(0, 0, width, height);

        // Draw here
        g.drawImage(Assets.dirt, x, 0, null);

        // End drawing
        bs.show(); // move data from buffer to actual screen
        g.dispose(); // clean Graphics object

    }

    public void run(){
        init();

        int fps = 60;
        double timePerUpdate = 1000000000 / fps; // 1 * 10^9 because nano seconds
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // returns current time in nano seconds
        long timer = 0;
        int updates = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            timer += (now - lastTime);
            lastTime = now;

            if( delta >= 1){
                update();
                render();
                updates++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and frames: " + updates);
                updates = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start(){
        // Check if game is not already running
        if(running){
            return;
        }else{
            running = true;
            thread = new Thread(this); // this -> Game class
            thread.start(); // calls run method
        }
    }

    public synchronized void stop(){
        // Check if game is already stoped
        if(!running){
            return;
        }else{
            running = false;
            try{
                thread.join(); // must be inside try / catch
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }

}
