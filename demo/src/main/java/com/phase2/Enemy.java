//Enemy Classes


//Abstract Class for enemy classes
abstract class Enemy<T> {
    private int xPos, yPox;
    
    public void getxPos() {return xPos;}
    public void getyPos() {return yPos;}
    public void touchingObject(T t);
    
}


class StationaryEnemy extends Enemy {
    StationaryEnemy(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    touchingObject(T doug) {
        //to be implemented
    }
}

class MovingEnemy extends Enemy {
    MovingEnemy(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    touchingObject(T object) {
        //to be implemented
    }
    move(int xMove,int yMove) {
        this.xPos = xPos-xMove;
        this.yPos = yPos-yMove;
    }
}

class Chocolate extends StationaryEnemy {
    Chocolate(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

}

class Onion extends StationaryEnemy {
    Onion(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

}

class Cat extends MovingEnemy {
    Cat(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

}

class Rat extends MovingEnemy {
    Rat(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

}