package Shapes;

import java.awt.*;

public class ShapeO extends Shape{

    public ShapeO(){
        create(Color.YELLOW);
    }
    @Override
    public void setXY(int x, int y) {
        blocks[0].x = x;
        blocks[0].y = y;
        blocks[1].x = blocks[0].x;
        blocks[1].y = blocks[0].y + Block.BLOCK_SIZE;
        blocks[2].x = blocks[0].x + Block.BLOCK_SIZE;
        blocks[2].y = blocks[0].y;
        blocks[3].x = blocks[0].x + Block.BLOCK_SIZE;
        blocks[3].y = blocks[0].y + Block.BLOCK_SIZE;
    }

    public void rotateZero(){}
    public void rotateOne(){}
    public void rotateTwo(){}
    public void rotateThree(){}
}
