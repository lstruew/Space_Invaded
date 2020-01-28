import java.awt.*;

public class Bullet {

    int x,y,diameter = 12, speed, dx, dy;
    boolean remove;

    public Bullet(Player player){
        x = player.getX()+ player.getWIDTH()/2-diameter/2;
        y = player.getY() - diameter*2;
    }

    public void move(){
        y -= 3;
    }

    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,diameter,diameter);
    }

    public int getY() {
        return y;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,diameter, diameter);
    }
    public void setRemove(){
        remove = true;
    }
    public boolean getRemove(){
        return remove;
    }
}
