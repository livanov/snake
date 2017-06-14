package snake;

import control.Direction;

public class Position {

    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getNext(Direction direction) {

        switch ( direction ) {
            case EAST:
                return new Position( x + 1, y );
            case WEST:
                return new Position( x - 1, y );
            case NORTH:
                return new Position( x, y - 1 );
            case SOUTH:
                return new Position( x, y + 1 );
            default:
                throw new IllegalArgumentException( "Unknown direction: " + direction );
        }
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Position other = (Position) obj;
        if ( this.x != other.x ) {
            return false;
        }
        return this.y == other.y;
    }
}
