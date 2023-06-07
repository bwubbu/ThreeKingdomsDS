import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the button
        Button startButton = new Button("Start");

        // Create the panel (pane) and add the button to it
        StackPane pane = new StackPane();
        pane.getChildren().add(startButton);

        // Create the scene with the panel
        Scene scene = new Scene(pane, 300, 200);

        // Set the scene on the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Three Kingdoms");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}