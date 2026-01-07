package Elio.model;

import Elio.model.hero.Hero;

public class Position {
    private int x;
    private int y;

    public Position(Position position){
        this.x = position.getX();
        this.y = position.getY();
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    //getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Position getNeighbor(Hero.Direction direction){
        return switch (direction) {
            case NONE -> null;
            case UP -> new Position(this.x, this.y - 1);
            case DOWN -> new Position(this.x, this.y + 1);
            case LEFT -> new Position(this.x - 1, this.y);
            case RIGHT -> new Position(this.x + 1, this.y);
        };
    }

    //setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


    public void setPosition(Position position){
        this.x = position.getX();
        this.y = position.getY();
    }


    //moving methods
    public void moveUp(){
        this.y--;
    }
    public void moveDown(){
        this.y++;
    }
    public void moveRight(){
        this.x++;
    }
    public void moveLeft(){
        this.x--;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        if (obj == this) return true;

        return ((Position)obj).getX() == this.x && ((Position) obj).getY() == this.y;
    }
}
