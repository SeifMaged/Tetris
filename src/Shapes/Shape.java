package Shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import GameLogic.GameManager;
import GameLogic.KeyHandler;

public abstract class Shape {
    protected Block[] blocks = new Block[4];
    protected Block[] tempBlocks = new Block[4];
    private int dropCounter = 0;
    private int rotationCount = 0;
    private boolean leftCollision, rightCollision, bottomCollision;
    private boolean reachedBottom = false;

    public Block[] getBlocks(){
        return blocks;
    }

    private void checkBlockCollision(){
        for(Block b : GameManager.getStaticBlocks()){
            int x = b.x;
            int y = b.y;

            for(Block block : blocks){
                if(block.x == x && block.y + Block.BLOCK_SIZE == y) {
                    bottomCollision = true;
                    break;
                }
            }
            for(Block block : blocks){
                if(block.x - Block.BLOCK_SIZE == x && block.y == y) {
                    leftCollision = true;
                    break;
                }
            }

            for(Block block : blocks){
                if(block.x + Block.BLOCK_SIZE == x && block.y == y) {
                    rightCollision = true;
                    break;
                }
            }


        }
    }
    public boolean reachedBottom(){
        return reachedBottom;
    }
    public int getRotationCount() {
        return rotationCount;
    }

    public void rotateZero(){
    }
    public void rotateOne(){}
    public void rotateTwo(){}
    public void rotateThree(){}
    public void create(Color c){
        for(int i = 0; i < 4; i++){
            blocks[i] = new Block(c);
        }

        for(int i = 0; i < 4; i++){
            tempBlocks[i] = new Block(c);
        }

    }

    public void checkMovementCollision(){
        leftCollision = rightCollision = bottomCollision = false;

        checkBlockCollision();
        for(int i = 0; i < 4; i++){
            if(blocks[i].x == GameManager.start_X){
                leftCollision = true;
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            if(blocks[i].x + Block.BLOCK_SIZE == GameManager.end_X){
                rightCollision = true;
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            if(blocks[i].y + Block.BLOCK_SIZE == GameManager.end_Y){
                bottomCollision = true;
                break;
            }
        }
    }

    public void checkRotationalCollision(){

        leftCollision = rightCollision = bottomCollision = false;

        checkBlockCollision();
        for(int i = 0; i < 4; i++){
            if(tempBlocks[i].x < GameManager.start_X){
                leftCollision = true;
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            if(tempBlocks[i].x + Block.BLOCK_SIZE > GameManager.end_X){
                rightCollision = true;
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            if(tempBlocks[i].y + Block.BLOCK_SIZE > GameManager.end_Y){
                bottomCollision = true;
                break;
            }
        }
    }


    public abstract void setXY(int x, int y);
    public void updateXY(int rotationCount){
        checkRotationalCollision();
        if(!leftCollision && !rightCollision && !bottomCollision){
            this.rotationCount = rotationCount % 4;
            for(int i = 0; i < 4; i++){
                blocks[i].x = tempBlocks[i].x;
                blocks[i].y = tempBlocks[i].y;
            }
        }
    }

    private void movementLogic(){
        if(GameManager.getLeftClicks() > 0){
            GameManager.decrementLeftClicks();
            KeyHandler.leftIsPressed = true;
        }

        if(GameManager.getRightClicks() > 0){
            GameManager.decrementRightClicks();
            KeyHandler.rightIsPressed = true;
        }

        if(GameManager.getUpClicks() > 0){
            GameManager.decrementUpClicks();
            KeyHandler.upIsPressed = true;
        }
    }
    public void update(){
        if(GameManager.getRightClicks() == 0 && GameManager.getLeftClicks() == 0 && GameManager.getUpClicks() == 0)
            KeyHandler.downIsPressed = true;
        if(KeyHandler.upIsPressed){
            switch (rotationCount%4){
                case 0 -> rotateOne();
                case 1 -> rotateTwo();
                case 2 -> rotateThree();
                case 3 -> rotateZero();
            }
            KeyHandler.upIsPressed = false;
        }

        checkMovementCollision();
        if(KeyHandler.downIsPressed){
            if(!bottomCollision)
                moveDown();
            KeyHandler.downIsPressed = false;
        }

        if(KeyHandler.leftIsPressed){
            if(!leftCollision)
                for(int i = 0; i < 4; i++){
                    blocks[i].x -= Block.BLOCK_SIZE;
                }
            KeyHandler.leftIsPressed = false;
        }
        if(KeyHandler.rightIsPressed){
            if(!rightCollision)
                for(int i = 0; i < 4; i++){
                    blocks[i].x += Block.BLOCK_SIZE;
                }
            KeyHandler.rightIsPressed = false;
        }
        if(bottomCollision) reachedBottom = true;
        else {
            dropCounter++;
            if (dropCounter == GameManager.fallRate) {
                movementLogic();
                moveDown();
                dropCounter = 0;
            }
        }

    }

    private void moveDown(){
        for(int i = 0; i < 4; i++){
            blocks[i].y += Block.BLOCK_SIZE;
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(blocks[0].c);
        int margin = 1;
        for(Block b : blocks){
            g2.fillRect(b.x + margin, b.y + margin, Block.BLOCK_SIZE - (2 * margin), Block.BLOCK_SIZE - (2 * margin));

        }
    }
}
