package GUI;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class TestGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        // InputStream stream = new FileInputStream("demo\\src\\CardPNGs\\1_of_clubs.png");
        // Image image = new Image(stream);
        // above method works
        InputStream stream = Files.newInputStream(Paths.get("demo\\src\\CardPNGs\\1_of_clubs.png"));
       
        BufferedImage bufferedImage = ImageIO.read(stream);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setX(10);
        imageView.setY(10);
        // imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);
        
        Group root = new Group(imageView);
        Scene scene = new Scene(root, 595, 370);
        scene.setFill(Color.GREEN);
        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        stage.show();
    }
/*
 * Plan: use buttons with images for cards
 * Issues: How do I position the cards?
 */
    

    public static void main(String[] args) {
      launch(args);
        
    }

}
