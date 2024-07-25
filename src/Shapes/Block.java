package Shapes;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
public class Block extends Rectangle{
    public int x;
    public int y;
    public static final int BLOCK_SIZE = 25;
    public Color c;

    public Block(Color c){
        this.c = c;
    }

    public void draw(Graphics2D g2){
        int margin = 1;
        g2.setColor(c);
        g2.fillRect(x + margin,y + margin, BLOCK_SIZE - (2*margin), BLOCK_SIZE - (2 * margin));
    }

}
