import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage myStage) {
        HBox rootNode = new HBox();
        
        TextField Value1 = new TextField();
        rootNode.getChildren().add(Value1);
        Value1.setMaxSize(30, 30);
        TextField Value2 = new TextField();
        rootNode.getChildren().add(Value2);
        Value2.setMaxSize(30, 30);
        TextField Value3 = new TextField();
        rootNode.getChildren().add(Value3);
        Value3.setMaxSize(30, 30);

        HBox rootNode2 = new HBox();
    
        TextField Value4 = new TextField();
        rootNode2.getChildren().add(Value4);
        Value4.setMaxSize(30, 30);
        TextField Value5 = new TextField();
        rootNode2.getChildren().add(Value5);
        Value5.setMaxSize(30, 30);
        TextField Value6 = new TextField();
        rootNode2.getChildren().add(Value6);
        Value6.setMaxSize(30, 30);

        HBox rootNode3 = new HBox();
    
        TextField Value7 = new TextField();
        rootNode3.getChildren().add(Value7);
        Value7.setMaxSize(30, 30);
        TextField Value8 = new TextField();
        rootNode3.getChildren().add(Value8);
        Value8.setMaxSize(30, 30);
        TextField Value9 = new TextField();
        rootNode3.getChildren().add(Value9);
        Value9.setMaxSize(30, 30);

        HBox rootNode4 = new HBox();
        Button aButton = new Button("Calculate");
        rootNode4.getChildren().add(aButton);
        TextField result = new TextField();
        aButton.setOnAction(e -> {
            Integer value1 = Integer.valueOf(Value1.getText());
            Integer value2 = Integer.valueOf(Value2.getText());
            Integer value3 = Integer.valueOf(Value3.getText());
            Integer value4 = Integer.valueOf(Value4.getText());
            Integer value5 = Integer.valueOf(Value5.getText());
            Integer value6 = Integer.valueOf(Value6.getText());
            Integer r = value1+value2+value3+value4+value5+value6;
            result.setText(r.toString());
        });
        
        VBox v = new VBox();
        v.getChildren().addAll(rootNode, rootNode2, rootNode3, rootNode4, result);

        Scene myScene = new Scene(v, 500, 400);
        myStage.setScene(myScene);
        myStage.show();
    }
    

}
