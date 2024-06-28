package de.upb.se.profcalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import de.upb.se.profcalculator.calculation.*;

public class ProfCalculator

extends Application

implements EventHandler<ActionEvent>


{
	
	private final static Value defaultValue = new Value(0);

	private Value save = new Value(0);
	
	private static Expression currentExpression = new Addition(defaultValue, defaultValue);

	private StringBuilder termText = new StringBuilder();

	private Label error = new Label("");
	
	private int num = 0;

	private static TextField input = new TextField("");

	private Button addButton = new Button("+");

	private Button delButton = new Button("d");

	private Button multButton = new Button("*");

	private Button subButton = new Button("-");

	private Button divButton = new Button("/");
	
	private Button printButton = new Button("PRINT");
	
	private List<Value> resultsList;
	
	private List<String> termList;
	
	private Label sameResult = new Label("");
	
	private Label termLabel = new Label("");

	private static Label result = new Label(currentExpression.returnEquation());

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Professioral Calculator");
		error.setTextFill(Color.web("#AA0000"));
		
		resultsList = new ArrayList<>();
		termList = new ArrayList<>();


		VBox layout = new VBox(10);

		layout.getChildren().add(error);

		layout.getChildren().add(input);

		layout.getChildren().add(addButton);

		layout.getChildren().add(subButton);

		layout.getChildren().add(multButton);

		layout.getChildren().add(divButton);

		layout.getChildren().add(delButton);

		layout.getChildren().add(result);
		
		layout.getChildren().add(sameResult);
		
		layout.getChildren().add(printButton);
		
		layout.getChildren().add(termLabel);

		layout.setPadding(new Insets(20, 80, 20, 80));
		Scene scene = new Scene(layout);


		stage.setScene(scene);
		stage.show();
		addButton.setOnAction(this);
		delButton.setOnAction(this);
		multButton.setOnAction(this);
		subButton.setOnAction(this);
		divButton.setOnAction(this);
		printButton.setOnAction(this);

	}

	@Override
	public void handle(ActionEvent event) {
		try {
			if (event.getSource()== delButton) {
				currentExpression = new Addition(new Value(0), new Value(0));
				input.setText("");
				save = new Value(0);
				result.setText(currentExpression.returnEquation());
				resultsList.clear();
				sameResult.setText("");
				termText = new StringBuilder();
				termList.clear();
				termLabel.setText(termText.toString());
			}
			else if(event.getSource() == printButton) {
				num += 1;
				if (num%2 == 1) {
					termLabel.setText(termText.toString());
				}
				else {
					termLabel.setText("");
				}
			}
			else {
				Value newValue = Value.parseValue(input.getText());
				if (event.getSource()== addButton) { 
					currentExpression = new Addition(save, newValue);
				}
				else if (event.getSource()== multButton) {
					currentExpression = new Multiplication(save, newValue);
				}
				else if (event.getSource()== subButton) {
					currentExpression = new Subtraction(save, newValue);
				}
				else if (event.getSource()== divButton) {
					currentExpression = new Division(save, newValue);
				}
				
				save = new Value(currentExpression.evaluate());
				result.setText(currentExpression.returnEquation());
				if (termList.contains(currentExpression.returnEquation())!= true){
					termList.add(currentExpression.returnEquation());
					termText.append(currentExpression.returnEquation()+"\n");
				}
				if (num%2 == 1) {
					termLabel.setText(termText.toString());
				}
				resultsList.add(save);
				sameResult.setText(getResultText(save, resultsList)); 
				input.setText("");
				error.setText("");
				input.requestFocus();
			}
		}                   
		catch (NumberFormatException e) { 
			error.setText("\"" + input.getText() + "\" is not a valid integer"); 
		}
	}
	
	private String getResultText(Value result, List<Value> resultsList) {
		 int lastIndex = -1;
	        for (int i = resultsList.size() - 2; i >= 0; i--) { 
	            if (resultsList.get(i).equals(result)) {
	                lastIndex = i;
	                break;
	            }
	        }

	        if (lastIndex == -1) {
	            return "Neues Ergebnis";
	        } else {
	            int stepsAgo = resultsList.size() - 1 - lastIndex;
	            return "Ergebnis bereits vor " + stepsAgo + " Berechnungsschritten aufgetreten";
	        }
	}
	

	public static void main(String[] args){ 
		launch(args); 
	}




}


