import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.math.BigInteger;
 
public class App extends Application {
  static public Stack<GTNode> solPath = new Stack<GTNode>();
  static public HashMap<BigInteger, Integer> visited = new HashMap<BigInteger, Integer>();
  static double startTime;
  static double endTime;

  public static void main(String[] args) {
    launch(args);
  }
    
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Sliding Puzzle");

	Text shenoDimensionin = new Text("Shenoni dimenzionin: ");
	TextField dimensioni = new TextField();
	dimensioni.setMaxWidth(150);
	dimensioni.setMaxHeight(50);

	Text shenoAlgoritmin = new Text("\nShenoni algoritmin: ");
	TextField algoritmi = new TextField();
	algoritmi.setMaxWidth(150);
	algoritmi.setMaxHeight(50);
	
	Button bRandom = new Button("Random");
	bRandom.setMaxWidth(150);
	Button bShenoj = new Button("Shenoj");
	bShenoj.setMaxWidth(150);

	HBox rootNode1 = new HBox();
	TextField Value1 = new TextField("");
	rootNode1.getChildren().add(Value1);
	Value1.setMaxSize(30, 30);
	TextField Value2 = new TextField("");
	rootNode1.getChildren().add(Value2);
	Value2.setMaxSize(30, 30);
	TextField Value3 = new TextField("");
	rootNode1.getChildren().add(Value3);
	Value3.setMaxSize(30, 30);
	HBox.setMargin(Value1, new Insets(0,2,0,140));
	HBox.setMargin(Value2, new Insets(0,2,0,0));
	HBox.setMargin(Value3, new Insets(0,2,0,0));
	rootNode1.setVisible(false);

	HBox rootNode2 = new HBox();
	TextField Value4 = new TextField("");
	rootNode2.getChildren().add(Value4);
	Value4.setMaxSize(30, 30);
	TextField Value5 = new TextField("");
	rootNode2.getChildren().add(Value5);
	Value5.setMaxSize(30, 30);
	TextField Value6 = new TextField("");
	rootNode2.getChildren().add(Value6);
	Value6.setMaxSize(30, 30);
	HBox.setMargin(Value4, new Insets(0,2,0,140));
	HBox.setMargin(Value5, new Insets(0,2,0,0));
	HBox.setMargin(Value6, new Insets(0,2,0,0));
	rootNode2.setVisible(false);

	HBox rootNode3 = new HBox();
	TextField Value7 = new TextField("");
	rootNode3.getChildren().add(Value7);
	Value7.setMaxSize(30, 30);
	TextField Value8 = new TextField("");
	rootNode3.getChildren().add(Value8);
	Value8.setMaxSize(30, 30);
	TextField Value9 = new TextField("");
	rootNode3.getChildren().add(Value9);
	Value9.setMaxSize(30, 30);
	HBox.setMargin(Value7, new Insets(0,2,0,140));
	HBox.setMargin(Value8, new Insets(0,2,0,0));
	HBox.setMargin(Value9, new Insets(0,2,0,0));
	rootNode3.setVisible(false);

	Button gjejEnigmen = new Button("Gjej enigmen");
	gjejEnigmen.setMaxWidth(150);
	gjejEnigmen.setVisible(false);

	bRandom.setOnAction( e ->  {
	  if(dimensioni.getText().isEmpty() && algoritmi.getText().isEmpty()) {
	    AlertBox("Duhet te plotesohen fushat Sheno dimenzionin" + 
				 "\n\t\t   dhe Sheno algoritmin.");				
	  }
	  else if (!dimensioni.getText().isEmpty() && algoritmi.getText().isEmpty()) {
		AlertBox("Duhet te plotesohet fusha Sheno algoritmin.");								
	  }
	  else if (dimensioni.getText().isEmpty() && !algoritmi.getText().isEmpty()) {
		AlertBox("Duhet te plotesohet fusha Sheno dimenzionin.");												
	  }
	  else {
		if(
		  (dimensioni.getText().equals("3") || dimensioni.getText().equals("4")) &&
		  (algoritmi.getText().equals("Astar") || algoritmi.getText().equals("BFS") ||
		  algoritmi.getText().equals("DFS") || algoritmi.getText().equals("ID")))  {

			int Dimensioni = Integer.parseInt(dimensioni.getText());
			randomInitialState(Dimensioni, algoritmi.getText());
		}
		else {
		  AlertBoxMeDimenzione(
			  "Te fusha e dimenzionit duhet te shnoni 3 ose 4 per rendin\n" +
			  " e matrices. Kurse te fusha e alforitmit duhet te shenoni\n" +
			  " njerin nga algorimet Astar, BFS, DFS, ID.", 600, 200);												
		}
	  }  
	});

	bShenoj.setOnAction(e ->  {
	  if(dimensioni.getText().isEmpty() && algoritmi.getText().isEmpty()) {
	    AlertBox("Duhet te plotesohen fushat Sheno dimenzionin \n\t\t " + 
				"  dhe Sheno algoritmin.");															
	  }
	  else if (!dimensioni.getText().isEmpty() && algoritmi.getText().isEmpty()) {
		AlertBox("Duhet te plotesohet fusha Sheno algoritmin.");																			
	  }
	  else if (dimensioni.getText().isEmpty() && !algoritmi.getText().isEmpty()) {
	    AlertBox("Duhet te plotesohet fusha Sheno dimenzionin.");																							
	  }
	  else {
	    if(
		  (dimensioni.getText().equals("3") || dimensioni.getText().equals("4")) &&
		  (algoritmi.getText().equals("Astar") || algoritmi.getText().equals("BFS") ||
		  algoritmi.getText().equals("DFS") || algoritmi.getText().equals("ID")))  {
			  rootNode1.setVisible(true);
			  rootNode2.setVisible(true);
			  rootNode3.setVisible(true);
			  gjejEnigmen.setVisible(true);
		}
		else {
		    AlertBoxMeDimenzione("Te fusha e dimenzionit duhet te shnoni " + 
				"3 ose 4 per rendin\n e matrices. Kurse te fusha e alforitmit" +
				"  duhet te shenoni\n njerin nga algoritmet Astar, BFS, DFS, ID."
				, 600, 200);																							
		}
	  }
	});

	gjejEnigmen.setOnAction(e -> {
	  if(
	    Value1.getText().isEmpty() || Value2.getText().isEmpty() || 
		Value3.getText().isEmpty() || Value4.getText().isEmpty() ||
		Value5.getText().isEmpty() || Value6.getText().isEmpty() ||
		Value7.getText().isEmpty() || Value8.getText().isEmpty() || 
		Value9.getText().isEmpty()) {
		  AlertBox("Duhet te plotesohen te gjitha fushat e matrices.");							
	  }
	  else if(
	   !Value1.getText().matches("[0-9]") || !Value2.getText().matches("[0-9]") || 
	   !Value3.getText().matches("[0-9]") || !Value4.getText().matches("[0-9]") ||
	   !Value5.getText().matches("[0-9]") || !Value6.getText().matches("[0-9]") ||
	   !Value7.getText().matches("[0-9]") || !Value8.getText().matches("[0-9]") || 
	   !Value9.getText().matches("[0-9]")) {
		  AlertBox("Duhet te shenohen vetem numra nga 0 deri ne 8.");											 
	  }
	  else {
		int Dimensioni = Integer.parseInt(dimensioni.getText());
		Integer value1 = Integer.valueOf(Value1.getText());
		Integer value2 = Integer.valueOf(Value2.getText());
		Integer value3 = Integer.valueOf(Value3.getText());
		Integer value4 = Integer.valueOf(Value4.getText());
		Integer value5 = Integer.valueOf(Value5.getText());
		Integer value6 = Integer.valueOf(Value6.getText());
		Integer value7 = Integer.valueOf(Value7.getText());
		Integer value8 = Integer.valueOf(Value8.getText());
		Integer value9 = Integer.valueOf(Value9.getText());

		int matrix[][] = { { value1, value2, value3 },
						   { value4, value5, value6 }, 
						   { value7, value8, value9 } };
			
		preWrittenInitialState(Dimensioni, algoritmi.getText(), matrix);
	  }
	});

	VBox root = new VBox();
	VBox.setMargin(rootNode1, new Insets(2, 2, 2, 2));
	VBox.setMargin(rootNode2, new Insets(2, 2, 2, 2));
	VBox.setMargin(rootNode3, new Insets(2, 2, 2, 2));
	VBox.setMargin(gjejEnigmen, new Insets(10, 0, 0, 0));
	VBox.setMargin(bRandom, new Insets(10, 0, 0, 0));
	VBox.setMargin(bShenoj, new Insets(10, 0, 10, 0)); 
	root.setPadding(new Insets(10, 10, 10, 10));
	root.getChildren().addAll(shenoDimensionin, dimensioni, shenoAlgoritmin, 
								algoritmi, bRandom, bShenoj, rootNode1, rootNode2, 
								rootNode3, gjejEnigmen);
	root.setAlignment(Pos.CENTER);
	primaryStage.setScene(new Scene(root, 400, 450));
	primaryStage.show();
}

public static void AlertBoxMeDimenzione(String mesazhi, int gjersia, int gjatesia) {
  Text Shpjegimi = new Text(" ");
  Shpjegimi.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  Shpjegimi.setText(mesazhi);
  VBox boxShpjegimi = new VBox();
  boxShpjegimi.setAlignment(Pos.CENTER);
  boxShpjegimi.getChildren().addAll(Shpjegimi);
  Scene skenaShpjegimi = new Scene(boxShpjegimi, gjersia, gjatesia);
  Stage pamjaShpjegimi = new Stage();
  pamjaShpjegimi.setTitle("Shpjegimi");
  pamjaShpjegimi.setScene(skenaShpjegimi);
  pamjaShpjegimi.show();
}

public static void AlertBox(String mesazhi) {
  Text Shpjegimi = new Text(" ");
  Shpjegimi.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  Shpjegimi.setText(mesazhi);
  VBox boxShpjegimi = new VBox();
  boxShpjegimi.setAlignment(Pos.CENTER);
  boxShpjegimi.getChildren().addAll(Shpjegimi);
  Scene skenaShpjegimi = new Scene(boxShpjegimi, 500, 200);
  Stage pamjaShpjegimi = new Stage();
  pamjaShpjegimi.setTitle("Shpjegimi");
  pamjaShpjegimi.setScene(skenaShpjegimi);
  pamjaShpjegimi.show();
}

public static void randomInitialState(int dimension, String choseAlgorithm) {
  GTNode initial = new GTNode(dimension);
  initial.fill();
  initial.shuffle();

  if(choseAlgorithm.equals("Astar")) {
    Astar(initial);
  }
  else if(choseAlgorithm.equals("BFS")) {
	BFS(initial);
  }
  else if(choseAlgorithm.equals("DFS")) {
	DFS(initial);
  }
  else if(choseAlgorithm.equals("ID")) {
	ID(initial);
  }
  else {
	System.out.print("Keni shenuar gabim");
  }
}

public static void preWrittenInitialState(int dimension, 
					String choseAlgorithm, int matrix[][]) {
  GTNode initial = new GTNode(dimension);
  initial.matrix = matrix;

  if(!initial.isSolvable()) {
	AlertBox("Engima qe e keni shenuar nuk eshte e zgjidhshme\n" +
			  "\t\tju lutem ndrroni inputin.");											 
  }	
  else {
	if(choseAlgorithm.equals("Astar")) {
		Astar(initial);
	}
	else if(choseAlgorithm.equals("BFS")) {
		BFS(initial);
	}
	else if(choseAlgorithm.equals("DFS")) {
		DFS(initial);
	}
	else if(choseAlgorithm.equals("ID")) {
		ID(initial);
	}
	else {
		System.out.print("Keni shenuar gabim");
	}
  }
}

public static void Astar(GTNode initial) {
  startTime = System.currentTimeMillis();
  PriorityQueue<GTNode> openList = new PriorityQueue<GTNode>();
  openList.offer(initial);
  GTNode state;
  while (!openList.isEmpty()) {
    state = openList.poll();
	visited.put(state.hash(state.matrix), state.astar);

	if (state.isComplete()) {
	  path(state);
	  endTime = System.currentTimeMillis();
	  printInfo((endTime - startTime) / 1000.00);
	  return;
	}
  state.exploreAstar(visited, openList);
  }
}

public static void BFS(GTNode initial) {
  startTime = System.currentTimeMillis();
  Queue<GTNode> openList = new LinkedList<GTNode>();
  openList.offer(initial);
  GTNode state;
  while (!openList.isEmpty()) {
	state = openList.poll();
	visited.put(state.hash(state.matrix), 0);
	if (state.isComplete()) {
	  path(state);
	  endTime = System.currentTimeMillis();
	  printInfo((endTime - startTime) / 1000.00);
	  return;
	}
  state.explore(visited, openList);
  }
}

public static void DFS(GTNode initial) {
  startTime = System.currentTimeMillis();
  Stack<GTNode> openList = new Stack<GTNode>();
  openList.push(initial);
  GTNode state;
  while (!openList.isEmpty()) {
	state = openList.pop();
	visited.put(state.hash(state.matrix), 0);
	if (state.isComplete()) {
	  path(state);
	  endTime = System.currentTimeMillis();
	  printInfo((endTime - startTime) / 1000.00);
	  return;
	}
  state.explore(visited, openList, -1);
  }
}

public static void ID(GTNode initial) {
  startTime = System.currentTimeMillis();
  Stack<GTNode> openList = new Stack<GTNode>();
  int limit = 0;
  
  while (true) {
	openList.clear();
	visited.clear();
	openList.push(initial);
	GTNode state;
	while (!openList.isEmpty()) {
	  state = openList.pop();
	  visited.put(state.hash(state.matrix), 0);
	  if (state.isComplete()) {
		path(state);
		endTime = System.currentTimeMillis();
		printInfo((endTime - startTime) / 1000.00);
		return;
	  }
	  state.explore(visited, openList, limit);
	}
	limit++;
  }
}

public static void path(GTNode c) {
  while (c.parent != null) {
    solPath.push(c);
	c = c.parent;
  }
  solPath.push(c);
}

public static void printInfo(double time) {
  int moves = solPath.size() - 1;

  Text antari00 = new Text(" "), antari01 = new Text(" "), 
  	   antari02 = new Text(" "), antari10 = new Text(" "),
	   antari11 = new Text(" "), antari12 = new Text(" "),
	   antari20 = new Text(" "), antari21 = new Text(" "),
	   antari22 = new Text(" ");

  antari00.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  antari01.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  antari02.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  antari10.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  antari11.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  antari12.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  antari20.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  antari21.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  antari22.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	
  Button kontrollo = new Button("Kontrollo");
  Text hapsire = new Text("\n");

  kontrollo.setOnAction(e -> {
    if (!solPath.empty()) {
	  GTNode p = (GTNode) solPath.pop();
	  int matrix[][] = { 
		  { p.print().get(0), p.print().get(1), p.print().get(2) }, 
		  { p.print().get(3), p.print().get(4), p.print().get(5) }, 
		  { p.print().get(6), p.print().get(7), p.print().get(8) } };
							
	  antari00.setText(String.valueOf(matrix[0][0]));
	  antari01.setText(String.valueOf(matrix[0][1]));
	  antari02.setText(String.valueOf(matrix[0][2]));
	  antari10.setText(String.valueOf(matrix[1][0]));
	  antari11.setText(String.valueOf(matrix[1][1]));
	  antari12.setText(String.valueOf(matrix[1][2]));
	  antari20.setText(String.valueOf(matrix[2][0]));
	  antari21.setText(String.valueOf(matrix[2][1]));
	  antari22.setText(String.valueOf(matrix[2][2]));			
	}
  });
	
  HBox rreshti1 = new HBox();
  rreshti1.getChildren().addAll(antari00, antari01, antari02);
  rreshti1.setAlignment(Pos.CENTER);
  rreshti1.setSpacing(15);

  HBox rreshti2 = new HBox();
  rreshti2.getChildren().addAll(antari10, antari11, antari12);
  rreshti2.setAlignment(Pos.CENTER);
  rreshti2.setSpacing(15);

  HBox rreshti3 = new HBox();
  rreshti3.getChildren().addAll(antari20, antari21, antari22);
  rreshti3.setAlignment(Pos.CENTER);
  rreshti3.setSpacing(15);

  VBox box = new VBox();
  box.getChildren().addAll(kontrollo, hapsire, rreshti1, rreshti2, rreshti3);
  box.setAlignment(Pos.CENTER);
  Scene skena = new Scene(box, 400, 200);
  Stage pamja = new Stage();
  pamja.setTitle("Shfaqja e levizjeve");
  pamja.setScene(skena);
  pamja.show();

  PrintimiIKohesDheLevizjeve(moves, time);
}

public static void PrintimiIKohesDheLevizjeve(int levizjet, double koha) {
  Text shumaELevizjeve = new Text(" ");
  Text kohaTotale = new Text(" ");

  shumaELevizjeve.setFont(Font.font("Arial", FontWeight.BOLD, 20));
  kohaTotale.setFont(Font.font("Arial", FontWeight.BOLD, 20));

  shumaELevizjeve.setText("Enigma eshte zgjedhur ne " + levizjet + " levizje.");
  kohaTotale.setText("Koha per zgjidhje eshte " + koha + " sekonda.");

  VBox box = new VBox();
  box.setAlignment(Pos.CENTER);
  box.getChildren().addAll(shumaELevizjeve, kohaTotale);
  Scene skena = new Scene(box, 400, 200);
  Stage pamja = new Stage();
  pamja.setTitle("Koha dhe levizjet");
  pamja.setScene(skena);
  pamja.show();
  }
}
