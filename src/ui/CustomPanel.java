package ui;
import javax.swing.JPanel;
import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

public class CustomPanel extends JPanel {
    private Image backgroundImage;

    public CustomPanel(String imagePath) {
    try{
        URL imageUrl = getClass().getResource(imagePath);

        if(imageUrl !=null ){
            backgroundImage = new ImageIcon(imageUrl).getImage();
        }
        else{
            System.out.println("Image not found");
        }
    }catch (Exception e){
        e.printStackTrace();
    }
}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}