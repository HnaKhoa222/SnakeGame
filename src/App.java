import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        int rowCount = 19;
        int columnCount = 19;
        int tileSize = 32;
        int boardWidht = columnCount * tileSize;
        int boardHeight = rowCount * tileSize;        

        JFrame frame = new JFrame("Snake");
        frame.setSize(boardWidht, boardHeight);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Snake snakeGame = new Snake();
        frame.add(snakeGame);
        frame.pack();
        frame.setVisible(true);   
        snakeGame.requestFocus();
    } 
}
