import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.*;



public class Snake extends JPanel implements ActionListener, KeyListener {
    class Block {
        int x;
        int y;
        int width;
        int height;
        Image image;

        int startX;
        int startY;
        char direction = 'U';
        int velocityX = 0;
        int velocityY = 0;
        
        Block(Image image, int x, int y, int width, int height){
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.startX = x;
            this.startY = y;
        }

        void updateDirection(char direction){
            this.direction = direction;
            updateVelocity();
        }

        void updateVelocity(){
            switch (this.direction) {
                case 'D' -> {
                    this.velocityX = 0;
                    this.velocityY = tileSize/4;
                }
                case 'U' -> {
                    this.velocityX = 0;
                    this.velocityY = -tileSize/4;
                }
                case 'L' -> {
                    this.velocityX = -tileSize/4;
                    this.velocityY = 0;
                }
                case 'R' -> {
                    this.velocityX = tileSize/4;
                    this.velocityY = 0;
                }
                default -> {
                }
            }
        }
    }
        private int rowCount = 19;
        private int columnCount = 19;
        private int tileSize = 32;
        private int boardWidht = columnCount * tileSize;
        private int boardHeight = rowCount * tileSize;

        private Image wallImage;
        private Image foodImage;

        private Image snakeUpImage;
        private Image snakeDownImage;
        private Image snakeLeftImage;
        private Image snakeRightImage;

        private Image snakeBodyImage;

        private String[] tileMap ={
            "XXXXXXXXXXXXXXXXXXX",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X        S        X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX",
        };

        HashSet<Block> walls;
        Block snakeHead;
        LinkedList<Block> snakeBody;

        Timer gameLoop;

        Snake(){
            setPreferredSize(new Dimension(boardWidht, boardHeight));
            setBackground(Color.black);
            addKeyListener(this);
            setFocusable(true);

            snakeBody = new LinkedList<>();
            wallImage = new ImageIcon(getClass().getResource("./sprites/wall.png")).getImage();
            snakeUpImage = new ImageIcon(getClass().getResource("./sprites/snakeUp.png")).getImage();
            snakeDownImage = new ImageIcon(getClass().getResource("./sprites/snakeDown.png")).getImage();
            snakeLeftImage = new ImageIcon(getClass().getResource("./sprites/snakeLeft.png")).getImage();
            snakeRightImage = new ImageIcon(getClass().getResource("./sprites/snakeRight.png")).getImage();
            snakeBodyImage = new ImageIcon(getClass().getResource("./sprites/snakeBody.png")).getImage();
            
            loadMap();
            gameLoop = new Timer(50, this);
            gameLoop.start();
            // System.out.println(walls.size());
            // System.out.println(snakeBody.size());
        } 

        public void loadMap(){
            walls = new HashSet<Block>();
            for (int r = 0; r < rowCount; r ++){
                for (int c = 0; c < columnCount; c++){
                    String row = tileMap[r];
                    char tileMapChar = row.charAt(c);
                    int x = c*tileSize;
                    int y = r*tileSize;

                    if (tileMapChar == 'X') { //wall
                        Block wall = new Block(wallImage, x, y, tileSize, tileSize);
                        walls.add(wall);
                    } else if (tileMapChar == 'S'){ //snake head
                        snakeHead = new Block(snakeUpImage, x, y, tileSize, tileSize);
                        snakeBody.add(new Block(snakeBodyImage, x , y + tileSize, tileSize, tileSize ));
                        snakeBody.add(new Block(snakeBodyImage, x , y + 2*tileSize, tileSize, tileSize ));
                        snakeBody.add(new Block(snakeBodyImage, x , y + 3*tileSize, tileSize, tileSize ));
                    }
                } 
            }
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g);
        }
        
    public void draw(Graphics g){
        g.drawImage(snakeHead.image, snakeHead.x, snakeHead.y, snakeHead.width, snakeHead.height, null);
        for (Block wall : walls){
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }
        for (Block bodypart : snakeBody){
            g.drawImage(bodypart.image, bodypart.x, bodypart.y, bodypart.width, bodypart.height, null);
            
        }
    }

    public void move(){
        snakeHead.x += snakeHead.velocityX;
        snakeHead.y += snakeHead.velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e){


    }
    @Override
    public void keyPressed(KeyEvent e){
        
    }
    @Override
    public void keyReleased(KeyEvent e){
        // System.out.println("KeuEvent: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP && snakeHead.direction != 'D'){
            snakeHead.updateDirection('U');
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && snakeHead.direction != 'U'){
            snakeHead.updateDirection('D');
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && snakeHead.direction != 'R'){
            snakeHead.updateDirection('L');
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snakeHead.direction != 'L'){
            snakeHead.updateDirection('R');
        }
    }
}
