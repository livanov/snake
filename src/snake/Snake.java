package snake;

import control.Board;
import control.Direction;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class Snake implements Iterable<Position> {

    private final Deque<Position> body;
    private Position head;
    private Direction direction;

    public Snake() {
        body = new ArrayDeque<>();
        body.add( new Position( 5, 5 ) );
        body.add( new Position( 6, 5 ) );
        body.add( new Position( 7, 5 ) );

        head = body.getLast();

        direction = Direction.EAST;
    }

    @Override
    public Iterator<Position> iterator() {
        return body.iterator();
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Position getHead() {
        return head;
    }

    public Deque<Position> getBody() {
        return body;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    void moveAndEat() {
        body.add( head.getNext( direction ) );
        head = body.getLast();
    }

    void move() {
        moveAndEat();
        body.poll();
    }

}
