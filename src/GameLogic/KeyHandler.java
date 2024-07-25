package GameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public static boolean upIsPressed, downIsPressed, leftIsPressed, rightIsPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_UP -> upIsPressed = true;
            case KeyEvent.VK_DOWN -> downIsPressed = true;
            case KeyEvent.VK_LEFT -> leftIsPressed = true;
            case KeyEvent.VK_RIGHT -> rightIsPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
