package GameLogic;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import Shapes.*;

public class GameManager {

    private final int WIDTH = 400;
    private final int HEIGHT = 550;
    public static int start_X;
    public static int end_X;
    public static int start_Y;
    public static int end_Y;

    Shapes.Shape currentShape;
    private final int spawn_X;
    private final int spawn_Y;

    Shapes.Shape nextShape;
    private final int nextShape_X;
    private final int nextShape_Y;
    private static final ArrayList<Block> staticBlocks = new ArrayList<>();
    private final Random random;
    public static final int fallRate = GamePanel.REFRESH_RATE;

    private static int leftClicks;
    private static int rightClicks;
    private static int upClicks;
    private boolean gameOver = false;

    private void generateMoves(){
        int horizontal = random.nextInt(10);
        if(Math.random() > 0.5) {
            leftClicks = horizontal;
            rightClicks = 0;
        } else{
            rightClicks = horizontal;
            leftClicks = 0;
        }
        upClicks = random.nextInt(10);
    }
    public GameManager(){
        start_X = (GamePanel.WIDTH/2) - (this.WIDTH/2);
        end_X = start_X + this.WIDTH;
        start_Y = 20;
        end_Y = start_Y + this.HEIGHT;

        random = new Random();

        spawn_X = start_X + (this.WIDTH/2) - Block.BLOCK_SIZE;
        spawn_Y = start_Y + Block.BLOCK_SIZE;
        currentShape = getNewShape();
        currentShape.setXY(spawn_X, spawn_Y);

        nextShape_X = end_X + 135;
        nextShape_Y = end_Y - 100;
        nextShape = getNewShape();
        nextShape.setXY(nextShape_X, nextShape_Y);
    }

    public static ArrayList<Block> getStaticBlocks() {
        return staticBlocks;
    }

    private void checkLines(){

        int x = start_X, y = start_Y, count = 0;

        while(x < end_X && y < end_Y){
            for(Block b : staticBlocks){
                if(b.x == x && b.y == y){
                    count++;
                }
            }

            x += Block.BLOCK_SIZE;

            if(x == end_X){
                if(count == 400 / Block.BLOCK_SIZE){
                    for(int i = staticBlocks.size() - 1; i >= 0; i--){
                        if(staticBlocks.get(i).y == y){
                            staticBlocks.remove(i);
                        }
                    }

                    for(Block b : staticBlocks){
                        if(b.y < y)
                            b.y += Block.BLOCK_SIZE;

                    }
                }
                count = 0;
                x = start_X;
                y += Block.BLOCK_SIZE;
            }
        }
    }

    private Shapes.Shape getNewShape(){
        Shapes.Shape shape = null;

        switch(random.nextInt(7)){
            case 0 -> shape = new ShapeL();
            case 1 -> shape = new ShapeJ();
            case 2 -> shape = new ShapeO();
            case 3 -> shape = new ShapeI();
            case 4 -> shape = new ShapeT();
            case 5 -> shape = new ShapeZ();
            case 6 -> shape = new ShapeS();
        }
        generateMoves();
        return shape;
    }

    public void update(){

        if(currentShape.reachedBottom()){
            Block[] blocks = currentShape.getBlocks();
            staticBlocks.addAll(Arrays.asList(blocks).subList(0, 4));

            if(currentShape.getBlocks()[0].x == spawn_X && currentShape.getBlocks()[0].y == spawn_Y) gameOver = true;
            currentShape = nextShape;
            currentShape.setXY(spawn_X, spawn_Y);
            nextShape = getNewShape();
            nextShape.setXY(nextShape_X, nextShape_Y);

            checkLines();
        } else{
            currentShape.update();
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(start_X-4, start_Y-4, this.WIDTH + 8, this.HEIGHT + 8);


        int x = end_X + 50;
        int y = end_Y - 200;
        g2.drawRect(x,y, 200,200);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Next Shape", x + 35, y + 50);

        if(currentShape != null){
            currentShape.draw(g2);
        }

        nextShape.draw(g2);

        for (Block staticBlock : staticBlocks) staticBlock.draw(g2);

        if(gameOver){
            x = start_X + 20;
            y = start_Y + 300;
            g2.drawString("Game Over!", x, y);
        }
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public static int getLeftClicks(){
        return leftClicks;
    }

    public static int getRightClicks(){
        return rightClicks;
    }
    public static int getUpClicks(){
        return upClicks;
    }

    public static void decrementLeftClicks(){
        leftClicks--;
    }
    public static void decrementRightClicks(){
        rightClicks--;
    }
    public static void decrementUpClicks(){
        upClicks--;
    }

}
