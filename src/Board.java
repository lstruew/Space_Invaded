import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Player player;
    Enemy enemy;
    Enemy[][] enemies = new Enemy[5][10];
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    Timer timer;
    Long timeDelay;
    Long bulletDelay;
    Game game;

    public Board(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(1024, 768));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
        timer.start();
    }
    public void setup(){
        player = new Player(this);
        for (int row =0; row<5; row++){
            for(int col = 0; col<10; col++){
                enemies[row][col] = new Enemy(getWidth()/4+(col*50), row*50);
            }
        }


        timeDelay = System.currentTimeMillis();
        bulletDelay = System.currentTimeMillis();
    }
    public void checkCollisions(){
        for(int i = bullets.size()-1;i>=0;i--){
            for(int j = 0; j < enemies.length; j++){
                for(int k = 0; k < enemies[0].length; k++){
                    if (enemies[j][k] != null){
                        if (bullets.get(i).getBounds().intersects(enemies[j][k].getBounds())){
                            bullets.get(i).setRemove();
                            enemies[j][k] = null;
                            break;
                        }
                    }
                }
            }
            if(bullets.get(i).getRemove()){
                bullets.remove(bullets.get(i));
            }
        }
    }
    public void actionPerformed(ActionEvent e){
        long currentTime = System.currentTimeMillis();
        checkCollisions();
        //player.moveRight();
        if(game.isSpacePressed() && currentTime - bulletDelay >= 250){
            bullets.add(new Bullet(player));
            bulletDelay =  System.currentTimeMillis();
        }
        //bullet.move();
        for(int i = bullets.size()-1; i>=0;i--){
            if (bullets.get(i).getY()<0){
                bullets.remove(bullets.get(i));
            } else{
                bullets.get(i).move();
            }
        }



        if(game.isLeftPressed() && player.getX()>0){
            player.moveLeft();
        }
        if(game.isRightPressed() && player.getX()<getWidth()){
            player.moveRight();
        }



        if(currentTime - timeDelay >= 1000) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 10; col++) {
                    if (enemies[row][col] != null) {
                        enemies[row][col].move();
                    }
                }
            }
            timeDelay = System.currentTimeMillis();
        }
        repaint();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        player.paint(g);
        for (int row =0; row<5; row++){
            for(int col = 0; col<10; col++){
                if (enemies[row][col] != null) {
                    enemies[row][col].paint(g);
                }
            }
        }
        for (Bullet bullet: bullets){
            bullet.paint(g);
        }
    }


}
