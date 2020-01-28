import java.awt.*;

public class Enemy {
    int x,y,diameter = 25;
    int speed, dx, dy = 4;
    int numEnemies;

    public Enemy(){
        x=50;
        y=50;
    }
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void move(){
        y += dy;
    }


    public void paint(Graphics g){
        g.setColor(Color.green);
        g.fillOval(x,y,diameter, diameter);
    }

    public int getNumEnemies(){
        return numEnemies;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,diameter,diameter);
    }

}
