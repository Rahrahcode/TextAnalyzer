package application;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class tut extends Application {

	public static final String BLANK = "";

	GridPane grid;
	Label EnterWord, Count;
	TextField WordField, CountField;
	Button countButton, clearButton;

	Map<String, Integer> map = new HashMap<String, Integer>();

	public void start(Stage stage) throws Exception {

		loadWordsMap();

		stage.setTitle("Word Count");

		grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(50, 50, 25, 25));

		EnterWord = new Label("Enter Word");
		grid.add(EnterWord, 0, 0);

		WordField = new TextField();
		grid.add(WordField, 1, 0);
		
		Count = new Label("Word Count");
		grid.add(Count, 0, 1);		

		countButton = new Button("Get the Count");
		grid.add(countButton, 1, 2);

		CountField = new TextField();
		grid.add(CountField, 1, 1);
		CountField.setEditable(false);

		clearButton = new Button("Clear");
		grid.add(clearButton, 2, 2);
		
		countButton.setOnAction(actionEvent -> {
			String word = WordField.getText();
			if (BLANK.equals(word)) {
				this.alert("No Input", "Enter a word", AlertType.ERROR);
				return;
			}
			map.get(word);
			int count = (map.get(word) == null) ? 0 : map.get(word);
			CountField.setText(String.valueOf(count));
		});

		  clearButton.setOnAction(event -> {
			   WordField.clear();
			   CountField.clear();
			  });
		
		Scene scene = new Scene(grid, 500, 275);
		stage.setScene(scene);

		stage.show();
	}
	
/////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		launch(args);
	}
	
	private void loadWordsMap() throws Exception {

		String Word;

		
	       File textFile = new File("/Users/rahiemhylton/Desktop/textFile.txt");
	        Scanner scan = new Scanner(textFile); 
	        
	        while (scan.hasNextLine())
	        {
	            String val = scan.nextLine(); 
	            if(map.containsKey(val) == false) 
	                map.put(val, 1);
	            else 
	            {
	                int count = (int)(map.get(val)); 
	                map.remove(val);  
	                map.put(val,count+1); 
	            }
		
	
		}
		// sorting 
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return (b.getValue()).compareTo(a.getValue());
			}
		});

		for (Map.Entry<String, Integer> i : sortedList) {
			System.out.println(i.getKey() + " = " + i.getValue());
		}

	}

	public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}