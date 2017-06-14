package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Deque;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import snake.Position;
import snake.Snake;

public class Board {

    private final JFrame frame;
    private final BoardPanel panel;

    private final int width;
    private final int height;

    public Board(int width, int height, Snake snake) {

        this.width = width;
        this.height = height;

        panel = new BoardPanel( width, height, snake );

        frame = new JFrame( "Snake" );
        frame.add( panel );
        frame.setResizable( false );
        frame.pack();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
    }

    public void addFood(Position food) {

        panel.addFood( food );
    }

    public void nextView() {

        panel.repaint();
    }

    public void gameOverView() {

    }

    public Set<Position> getFoods() {
        return panel.getFoods();
    }

    public void removeFood(Position food) {
        panel.removeFood( food );
    }

    public Position getRandomFreeSpot() {

        Random rand = new Random();

        Position pos;

        do {
            pos = new Position( rand.nextInt( width ), rand.nextInt( height ) );
        } while ( panel.getFoods().contains( pos ) || panel.getSnake().contains( pos ) );

        return pos;
    }

    public Set<Position> getObstacles() {
        return panel.getObstacles();
    }

    public void addObstacle(Position obstacle) {
        panel.addObstacle( obstacle );
    }

    private static class BoardPanel extends JPanel {

        private final Snake snake;

        private static final int SCALE_MULTIPLIER = 15;
        private Set<Position> foods;
        private Set<Position> obstacles;

        private BoardPanel(int width, int height, Snake snake) {

            this.setBackground( Color.black );
            this.setFocusable( true );
            this.setPreferredSize( new Dimension( SCALE_MULTIPLIER * width, SCALE_MULTIPLIER * height ) );

            this.addKeyListener( new DirectionController( snake ) );

            this.snake = snake;
            this.foods = new HashSet<>();
            this.obstacles = new HashSet<>();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent( g );

            g.setColor( Color.YELLOW );
            for ( Position food : foods ) {
                g.fillOval( food.x * SCALE_MULTIPLIER, food.y * SCALE_MULTIPLIER,
                        SCALE_MULTIPLIER * 3 / 4, SCALE_MULTIPLIER * 3 / 4 );
            }

            g.setColor( Color.RED );
            for ( Position obstacle : obstacles ) {
                g.fillOval( obstacle.x * SCALE_MULTIPLIER, obstacle.y * SCALE_MULTIPLIER,
                        SCALE_MULTIPLIER, SCALE_MULTIPLIER );
            }

            g.setColor( Color.WHITE );
            for ( Position position : snake ) {
                g.fillOval( position.x * SCALE_MULTIPLIER, position.y * SCALE_MULTIPLIER,
                        SCALE_MULTIPLIER, SCALE_MULTIPLIER );
            }

            g.setColor( Color.GREEN );
            g.fillOval( snake.getHead().x * SCALE_MULTIPLIER, snake.getHead().y * SCALE_MULTIPLIER,
                    SCALE_MULTIPLIER, SCALE_MULTIPLIER );
        }

        private Set<Position> getFoods() {
            return foods;
        }

        private void addFood(Position food) {
            this.foods.add( food );
        }

        private void removeFood(Position food) {
            this.foods.remove( food );
        }

        private Set<Position> getObstacles() {
            return this.obstacles;
        }

        private void addObstacle(Position obstacle) {
            this.obstacles.add( obstacle );
        }

        private void removeObstacle(Position obstacle) {
            this.obstacles.remove( obstacle );
        }

        private Deque<Position> getSnake() {
            return this.snake.getBody();
        }

    }
}
