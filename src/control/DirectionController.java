package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import snake.Snake;

public class DirectionController extends KeyAdapter {

    private final Snake snake;

    public DirectionController(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        Direction direction = snake.getDirection();

        switch ( key ) {
            case KeyEvent.VK_LEFT:
                if ( direction != Direction.EAST ) {
                    snake.setDirection( Direction.WEST );
                }
                break;
            case KeyEvent.VK_RIGHT:
                if ( direction != Direction.WEST ) {
                    snake.setDirection( Direction.EAST );
                }
                break;
            case KeyEvent.VK_UP:
                if ( direction != Direction.SOUTH ) {
                    snake.setDirection( Direction.NORTH );
                }
                break;
            case KeyEvent.VK_DOWN:
                if ( direction != Direction.NORTH ) {
                    snake.setDirection( Direction.SOUTH );
                }
                break;
        }
    }
}
