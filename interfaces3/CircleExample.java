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
import javafx.scene.shape.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
         
public class CircleExample extends Application { 
   static int dx = 1;
   static int dy = 1;
   static int movimientoBarraValor = 2;
   static int movimientoBarraValorDerecha = 2;
   static final int ALEATORIO_TAMAÑO_ESCENAX = 400;
   static final int ALEATORIO_TAMAÑO_ESCENAY = 400;
   @Override 
   public void start(Stage stage) { 
      Random  aleatorio = new Random();
      Circle circle = new Circle() ; 
     
      circle.setRadius(30) ; 
      circle.setCenterX(aleatorio.nextInt(ALEATORIO_TAMAÑO_ESCENAX) + circle.getRadius()) ; 
      circle.setCenterY(aleatorio.nextInt(ALEATORIO_TAMAÑO_ESCENAY) + circle.getRadius()) ; 
      circle.setFill(Color.RED);

      Group root = new Group(circle) ; 
         
      Scene scene = new Scene(root, 500, 500) ;   
      stage.setTitle("circulo centro") ; 

      Button botonParar = new Button("Parar");
      root.getChildren().add(botonParar);
      
      Rectangle r = new Rectangle();
      r.setX(30);
      r.setY(480);
      r.setWidth(100);
      r.setHeight(10);
      r.setArcWidth(5);
      r.setArcHeight(5);
      r.setFill(Color.BLUE);
      root.getChildren().add(r);
      
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
      KeyFrame mueveCirculo = new KeyFrame(Duration.seconds(.00600),new EventHandler<ActionEvent>() {
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
                        if (circle.getBoundsInParent().intersects(r.getBoundsInParent())) {
                            dy = dy * -1;
                        }
                        System.out.println(r.getBoundsInParent());

                        circle.setTranslateX(circle.getTranslateX() + dx);
                        circle.setTranslateY(circle.getTranslateY() + dy);
                        r.setTranslateX(r.getTranslateX() + movimientoBarraValor);

                    }
                });
   
        
      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                 public void handle(KeyEvent ke) {
                if (ke.getCode()== KeyCode.RIGHT ) {
                    movimientoBarraValor = movimientoBarraValorDerecha;
                }
                 else if (ke.getCode()== KeyCode.LEFT) {
                    movimientoBarraValor= -movimientoBarraValor;
                }
            }});
                
      miTimeline.getKeyFrames().add(mueveCirculo);
      miTimeline.play();
         
      stage.setScene(scene);          
      stage.show() ;
   } 
   public static void main(String args[]) { 
      launch(args) ; 
   } 
}