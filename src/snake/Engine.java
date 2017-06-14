package snake;

import control.Board;

public class Engine {

    public static void main(String[] args) throws InterruptedException {

        int width = 40;
        int height = 40;

        Snake snake = new Snake();

        Board board = new Board( width, height, snake );
        board.addFood( board.getRandomFreeSpot() );
        board.addFood( board.getRandomFreeSpot() );

        board.addObstacle( board.getRandomFreeSpot() );
        board.addObstacle( board.getRandomFreeSpot() );

        while ( true ) {
            Thread.sleep( 100 );

            Position nextPosition = snake.getHead().getNext( snake.getDirection() );

            // hit a wall
            if ( board.getObstacles().contains( nextPosition ) ) {
                board.gameOverView();
                break;
            }

            if ( snake.getBody().contains( nextPosition ) ) {

                // self-bite
                board.gameOverView();
                break;
            }

            if ( board.getFoods().contains( nextPosition ) ) {
                board.removeFood( nextPosition );
                board.addFood( board.getRandomFreeSpot() );
                snake.moveAndEat();
            } else {
                snake.move();
            }

            board.nextView();
        }
    }
}
