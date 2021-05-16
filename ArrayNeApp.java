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

		TextField antari00 = new TextField();
		TextField antari01 = new TextField();
		TextField antari02 = new TextField();

		TextField antari10 = new TextField();
		TextField antari11 = new TextField();
		TextField antari12 = new TextField();

		TextField antari20 = new TextField();
		TextField antari21 = new TextField();
		TextField antari22 = new TextField();

		HBox rreshti1 = new HBox();
		rreshti1.getChildren().addAll(antari00,antari01,antari02);

		HBox rreshti2 = new HBox();
		rreshti2.getChildren().addAll(antari10,antari11,antari12);

		HBox rreshti3 = new HBox();
		rreshti3.getChildren().addAll(antari20,antari21,antari22);

		VBox matrica = new VBox();
		matrica.getChildren().addAll(rreshti1,rreshti2,rreshti3);


        aButton.setOnAction(e -> {
            Integer value1 = Integer.valueOf(Value1.getText());
            Integer value2 = Integer.valueOf(Value2.getText());
            Integer value3 = Integer.valueOf(Value3.getText());
            Integer value4 = Integer.valueOf(Value4.getText());
            Integer value5 = Integer.valueOf(Value5.getText());
            Integer value6 = Integer.valueOf(Value6.getText());
			Integer value7 = Integer.valueOf(Value7.getText());
            Integer value8 = Integer.valueOf(Value8.getText());
            Integer value9 = Integer.valueOf(Value9.getText());

		    int matrix[][] = { { value1, value2, value3 }, { value4, value5, value6 }, { value7, value8, value9 } };

			System.out.print("\n__________________________________");
			for (int i = 0; i < matrix.length; i++) {
				System.out.println("\n");
				for (int j = 0; j < matrix.length; j++) {
					System.out.print("|" + matrix[i][j] + "|" + "\t");
				}
			}
			System.out.println("\n__________________________________");

			antari00.setText(Integer.toString(matrix[0][0]));
			antari01.setText(Integer.toString(matrix[0][1]));
			antari02.setText(Integer.toString(matrix[0][2]));

			antari10.setText(Integer.toString(matrix[1][0]));
			antari11.setText(Integer.toString(matrix[1][1]));
			antari12.setText(Integer.toString(matrix[1][2]));

			antari20.setText(Integer.toString(matrix[2][0]));
			antari21.setText(Integer.toString(matrix[2][1]));
			antari22.setText(Integer.toString(matrix[2][2]));
        });
        
        VBox v = new VBox();
        v.getChildren().addAll(rootNode, rootNode2, rootNode3, rootNode4, matrica);

		

        Scene myScene = new Scene(v, 500, 400);
        myStage.setScene(myScene);
        myStage.show();
    }
    

}
