package Shapes;

import java.awt.*;

public class ShapeT extends Shape{

    public ShapeT(){
        create(Color.MAGENTA);
    }
    @Override
    public void setXY(int x, int y) {
        blocks[0].x = x;
        blocks[0].y = y;
        blocks[1].x = blocks[0].x;
        blocks[1].y = blocks[0].y - Block.BLOCK_SIZE;
        blocks[2].x = x - Block.BLOCK_SIZE;
        blocks[2].y = blocks[0].y;
        blocks[3].x = x + Block.BLOCK_SIZE;
        blocks[3].y = blocks[0].y;
    }

    public void rotateZero(){
        tempBlocks[0].x = blocks[0].x;
        tempBlocks[0].y = blocks[0].y;
        tempBlocks[1].x = blocks[0].x;
        tempBlocks[1].y = blocks[0].y - Block.BLOCK_SIZE;
        tempBlocks[2].x = blocks[0].x - Block.BLOCK_SIZE;
        tempBlocks[2].y = blocks[0].y;
        tempBlocks[3].x = blocks[0].x + Block.BLOCK_SIZE;
        tempBlocks[3].y = blocks[0].y;
        updateXY(0);
    }
    public void rotateOne(){
        tempBlocks[0].x = blocks[0].x;
        tempBlocks[0].y = blocks[0].y;
        tempBlocks[1].x = blocks[0].x + Block.BLOCK_SIZE;
        tempBlocks[1].y = blocks[0].y;
        tempBlocks[2].x = blocks[0].x;
        tempBlocks[2].y = blocks[0].y - Block.BLOCK_SIZE;
        tempBlocks[3].x = blocks[0].x;
        tempBlocks[3].y = blocks[0].y + Block.BLOCK_SIZE;
        updateXY(1);

    }
    public void rotateTwo(){
        tempBlocks[0].x = blocks[0].x;
        tempBlocks[0].y = blocks[0].y;
        tempBlocks[1].x = blocks[0].x;
        tempBlocks[1].y = blocks[0].y + Block.BLOCK_SIZE;
        tempBlocks[2].x = blocks[0].x + Block.BLOCK_SIZE;
        tempBlocks[2].y = blocks[0].y;
        tempBlocks[3].x = blocks[0].x - Block.BLOCK_SIZE;
        tempBlocks[3].y = blocks[0].y;
        updateXY(2);
    }
    public void rotateThree(){
        tempBlocks[0].x = blocks[0].x;
        tempBlocks[0].y = blocks[0].y;
        tempBlocks[1].x = blocks[0].x - Block.BLOCK_SIZE;
        tempBlocks[1].y = blocks[0].y;
        tempBlocks[2].x = blocks[0].x;
        tempBlocks[2].y = blocks[0].y + Block.BLOCK_SIZE;
        tempBlocks[3].x = blocks[0].x;
        tempBlocks[3].y = blocks[0].y - Block.BLOCK_SIZE;
        updateXY(3);
    }
}
