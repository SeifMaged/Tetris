package GameLogic;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    public static final int HEIGHT = 600;
    public static final int WIDTH = 1000;
    GameManager manager;
    Thread gameThread;
    public static final int REFRESH_RATE = 60;

    public GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.BLACK);
        manager = new GameManager();
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        this.startNewGame();

    }

    public void startNewGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double refreshInterval = 1000000000.0 / REFRESH_RATE;
        double difference = 0;
        long previousTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            difference += (currentTime - previousTime) / refreshInterval;
            previousTime = currentTime;

            if(difference >= 1){
                update();
                repaint();
                difference -= 1;
            }
        }
    }

    private void update(){

        if(!manager.isGameOver())
            manager.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        manager.draw((Graphics2D)g);
    }
}
