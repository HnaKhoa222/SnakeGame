import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.*;


public class Snake extends JPanel {
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

        

        private Image snakeImage;

        Snake(){
            setPreferredSize(new Dimension(boardWidht, boardHeight));
            setBackground(Color.black);

            wallImage = new ImageIcon(getClass().getResource("./sprites/wall.png")).getImage();
            
            snakeUpImage = new ImageIcon(getClass().getResource("./sprites/snakeUp.png")).getImage();
            snakeDownImage = new ImageIcon(getClass().getResource("./sprites/snakeDown.png")).getImage();
            snakeLeftImage = new ImageIcon(getClass().getResource("./sprites/snakeLeft.png")).getImage();
            snakeRightImage = new ImageIcon(getClass().getResource("./sprites/snakeRight.png")).getImage();
            
        }
}
