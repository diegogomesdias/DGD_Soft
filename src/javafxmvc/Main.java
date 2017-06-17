package javafxmvc;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    
    @Override
    public void start(Stage stage) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("D.G.D. Soft");
        //stage.setMaximized(true);//tela inicia maximizada
        stage.show();
        
       
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
