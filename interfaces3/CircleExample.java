import javafx.application.Application; 
import javafx.animation.Animation;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.KeyValue;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.Random;

         
public class CircleExample extends Application { 
   static int dx = 1;
   static int dy = 1;
   @Override 
   public void start(Stage stage) { 
      Random  aleatorio = new Random();
      Circle circle = new Circle() ; 
     
       circle.setRadius(50.0f) ; 
      circle.setCenterX(aleatorio.nextInt(450) + circle.getRadius()) ; 
      circle.setCenterY(aleatorio.nextInt(450) + circle.getRadius()) ; 
      circle.setFill(Color.RED);

      Group root = new Group(circle) ; 
         
      Scene scene = new Scene(root, 500, 500) ;   
      stage.setTitle("circulo centro") ; 

      Button botonParar = new Button("Parar");
      root.getChildren().add(botonParar);

      Timeline miTimeline = new Timeline();
      Button parar = new Button("Parar");
      parar.setOnAction(event -> {
            if (miTimeline.getStatus() == Animation.Status.RUNNING) {
                miTimeline.stop();
            }
            else {
                miTimeline.play();
            }
            
        });
        
        root.getChildren().add(parar);

      miTimeline.setCycleCount(Animation.INDEFINITE);
      KeyFrame mueveCirculo = new KeyFrame(Duration.seconds(.00100),new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        double xMin = circle.getBoundsInParent().getMinX();
                        double yMin = circle.getBoundsInParent().getMinY();
                        double xMax = circle.getBoundsInParent().getMaxX();
                        double yMax = circle.getBoundsInParent().getMaxY();

                        if (xMin < 0 || xMax > scene.getWidth()) {
                            dx = dx * -1;
                        }
                        if (yMin < 0 || yMax > scene.getHeight()) {
                            dy = dy * -1;
                        }

                        circle.setTranslateX(circle.getTranslateX() + dx);
                        circle.setTranslateY(circle.getTranslateY() + dy);

                    }
                });

      miTimeline.getKeyFrames().add(mueveCirculo);
      miTimeline.play();
         
      stage.setScene(scene);          
      stage.show() ;
   } 
   public static void main(String args[]) { 
      launch(args) ; 
   } 
}