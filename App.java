import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.math.BigInteger;
 
public class App extends Application {
    // Stack to store the steps to the solution where the head is the initial state
	// and the tail is the goal-state
	static public Stack<GTNode> solPath = new Stack<GTNode>();
	// HashMap to store the previously visited/explored states (using a hashmap for
	// O(1) access to check for duplicates when generating new states)
	static public HashMap<BigInteger, Integer> visited = new HashMap<BigInteger, Integer>();
	// varaible to record the start time of each algorithm
	static double startTime;
	// varaible to record the end time of each algorithm
	static double endTime;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sliding Puzzle");

        Text shenoDimensionin = new Text("Shenoni dimenzionin: ");
        TextField dimensioni = new TextField();
        Text shenoAlgoritmin = new Text("\nShenoni algoritmin: ");
        TextField algoritmi = new TextField();
        Text shenoNumrat = new Text("\nA deshironi ti shenoni numrat apo te gjenerohen ne menyre te rastesishme: ");
        TextField numrat = new TextField();
		
        Text hapsire = new Text("\n");
        Button b = new Button("Kliko");
	    b.setOnAction(
		e ->  {
            String Numrat = numrat.getText();
            int Dimensioni = Integer.parseInt(dimensioni.getText());

            if(Numrat.equals("random")) {
                randomInitialState(Dimensioni, algoritmi.getText());
            }
            else if(Numrat.equals("shenoj")) {
                TextField m;
                int matrix[][] = null;
                //int matrix[][] = { { 3, 2, 8 }, { 4, 0, 1 }, { 7, 6, 5 } };
                for(int i = 0; i < Dimensioni; i++) {
                    for(int j = 0; j < Dimensioni; j++) {
                         m = new TextField();
                         matrix[i][j] = Integer.parseInt(m.getText());
                    }
                }
                int matrixm[][] =  matrix;

                preWrittenInitialState(Dimensioni, algoritmi.getText(), matrixm);
            }
            else {
                System.out.println("Keni shenuar gabim 1.");
            }
        }
    );

        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(shenoDimensionin, dimensioni, shenoAlgoritmin, algoritmi, shenoNumrat, numrat, hapsire, b);
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
    }
    public static void randomInitialState(int dimension, String choseAlgorithm) {
		// The given integer in the parameter of the initilized GTNode is the dimensions
		// of the puzzle (it doesn't have to be 3x3 it can be of any of size)
		GTNode initial = new GTNode(dimension);

		// Call method fill() to fill up the puzzle with the appropriate numbers
		initial.fill();

		// Shuffle the puzzle randomly
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
//		DLS(initial, 5);
	}

	public static void preWrittenInitialState(int dimension, String choseAlgorithm, int matrix[][]) {
		// The given integer in the parameter of the initilized GTNode is the dimensions
		// of the puzzle (it doesn't have to be 3x3 it can be of any of size)
		GTNode initial = new GTNode(dimension);
		
	    

		// Prewritten initial state (if the dimensions aren't 3x3, then change the input
		// accordingly)
		//matrix[][] = { { 1, 4, 8, 0 }, { 6, 2, 3, 11 }, { 13, 7, 10, 12 }, { 5, 9, 15, 14 } };
		
		/*Scanner scan = new Scanner(System.in);
		System.out.println("Shenoj elementet e matrices");
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				matrix[i][j] = scan.nextInt();
			}
		}*/

		// Assigning the matrix to the GTNode object
		initial.matrix = matrix;
		
		// check for solvability
		if(!initial.isSolvable())
			System.out.println("This puzzle is unsolvable please change the input.");
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

	// A* Search algorithm
	public static void Astar(GTNode initial) {
		startTime = System.currentTimeMillis();
		// initiate open list as a Priority Queue, and sort by A* score, and offer it
		// the initial state
		PriorityQueue<GTNode> openList = new PriorityQueue<GTNode>();
		openList.offer(initial);
		GTNode state;
		while (!openList.isEmpty()) {
			// poll the head of the PQ (having the lowest A* score) to explore and also
			// added it to the visited list to avoid duplicates
			state = openList.poll();
			visited.put(state.hash(state.matrix), state.astar);
			// check for completeness
			if (state.isComplete()) {
				// call the path method that fills the solution Stack with the states to the
				// solution
				path(state);
				endTime = System.currentTimeMillis();
				printInfo((endTime - startTime) / 1000.00);
				return;
			}
			// generate possible states from current state
			state.exploreAstar(visited, openList);
		}
	}

	// Breadth-First Search algorithm
	public static void BFS(GTNode initial) {
		startTime = System.currentTimeMillis();
		// initiate open list as a Queue and offer it the initial state
		Queue<GTNode> openList = new LinkedList<GTNode>();
		openList.offer(initial);
		GTNode state;
		while (!openList.isEmpty()) {
			// poll the next state to explore and also added it to the visited list to avoid
			// duplicates
			state = openList.poll();
			visited.put(state.hash(state.matrix), 0);
			// check for completeness
			if (state.isComplete()) {
				// call the path method that fills the solution Stack with the states to the
				// solution
				path(state);
				endTime = System.currentTimeMillis();
				printInfo((endTime - startTime) / 1000.00);
				return;
			}
			// generate possible states from current state
			state.explore(visited, openList);
		}
	}

	// Depth-First Search algorithm
	public static void DFS(GTNode initial) {
		startTime = System.currentTimeMillis();
		// initiate open list as a Stack and push in it the initial state
		Stack<GTNode> openList = new Stack<GTNode>();
		openList.push(initial);
		GTNode state;
		while (!openList.isEmpty()) {
			// pop the next state to explore and also added it to the visited list to avoid
			// duplicates
			state = openList.pop();
			visited.put(state.hash(state.matrix), 0);
			// check for completeness
			if (state.isComplete()) {
				// call the path method that fills the solution Stack with the states to the
				// solution
				path(state);
				endTime = System.currentTimeMillis();
				printInfo((endTime - startTime) / 1000.00);
				return;
			}
			// generate possible states from current state
			state.explore(visited, openList, -1);
		}
	}

	// Iterative-Deepening algorithm
	public static void ID(GTNode initial) {
		startTime = System.currentTimeMillis();
		// initiate open list as a Stack and push in it the initial state
		Stack<GTNode> openList = new Stack<GTNode>();
		int limit = 0;
		while (true) {
			// clear the open list and visited list so it can start over
			openList.clear();
			visited.clear();
			openList.push(initial);
			GTNode state;
			while (!openList.isEmpty()) {
				// pop the next state to explore and also added it to the visited list to avoid
				// duplicates
				state = openList.pop();
				visited.put(state.hash(state.matrix), 0);
				if (state.isComplete()) {
					// call the path method that fills the solution Stack with the states to the
					// solution
					path(state);
					endTime = System.currentTimeMillis();
					printInfo((endTime - startTime) / 1000.00);
					return;
				}
				// generate possible states from current state (and mind the limit)
				state.explore(visited, openList, limit);
			}
			// increment the limit with each run
			limit++;
		}
	}

	// Depth-Limited Search algorithm
	public static void DLS(GTNode initial, int limit) {
		startTime = System.currentTimeMillis();
		// initiate open list as a Stack and push in it the initial state
		Stack<GTNode> openList = new Stack<GTNode>();
		openList.push(initial);
		GTNode state;
		while (!openList.isEmpty()) {
			// pop the next state to explore and also added it to the visited list to avoid
			// duplicates
			state = openList.pop();
			visited.put(state.hash(state.matrix), 0);
			if (state.isComplete()) {
				// call the path method that fills the solution Stack with the states to the
				// solution
				path(state);
				endTime = System.currentTimeMillis();
				printInfo((endTime - startTime) / 1000.00);
				return;
			}
			// generate possible states from current state (and mind the limit)
			state.explore(visited, openList, limit);
		}
		System.out.println("Solution not found within the specified limit.");
	}

	// Adds the solution path to a stack
	public static void path(GTNode c) {
		while (c.parent != null) {
			solPath.push(c);
			c = c.parent;
		}
		solPath.push(c);
	}

	// print the steps for the solution and outcomes of the algorithm
	public static void printInfo(double time) {
		// The size of the stack - 1 is the required moves to find the solution
		int moves = solPath.size() - 1;

		// printing the moves to find the solution
		while (!solPath.empty()) {
			GTNode p = (GTNode) solPath.pop();
			p.print();
		}

		System.out.println("Solved in " + moves + " moves.");
		System.out.println("Time taken " + time + " seconds.");
	}

}
