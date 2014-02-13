package com.android.okcalculator;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.okcalculator.R;

public class CalcMain extends Activity {

	private CalcUtils calcUtils = new CalcUtils();
	private GridView keypadGrid;
	private KeyAdapter keyAdapter;
	
	//Should probably use stacks
	private LinkedList<Double> numbers = new LinkedList<Double>();
	private LinkedList<Key> operators = new LinkedList<Key>();
	private String currentNumber = "";
	
	private final String UNDEFINED = "undefined";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_main);
        
        keypadGrid = (GridView) findViewById(R.id.grdButtons);
        keyAdapter = new KeyAdapter(this);
        
        keypadGrid.setAdapter(keyAdapter);
        keyAdapter.setButtonClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				processInput((Key) b.getTag());
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void processInput(Key key) {
    	TextView tv = (TextView) findViewById(R.id.output);
    	String text = tv.getText().toString();
    	
    	handleUndefined(tv);
    	
    	switch (key.getKeyType()) {
		case BACKSPACE:
			if(empty(text)) break;
			
			//remove the last entered char
			if(!empty(currentNumber)) {
				currentNumber = removeLastChar(currentNumber);
			} else {
				operators.removeLast();
			}
	    	tv.setText(removeLastChar(text));
			break;
			
		case OPERATOR:
			pushCurrentNumber();
			operators.add(key);
			tv.append(key.getText());
			break;
			
		case ENTER:
			pushCurrentNumber();
			double result = calcUtils.evaluate(numbers, operators);
	    	String output = calcUtils.format(result);
			tv.setText(output);
	    	
	    	numbers.clear();
			operators.clear();
			currentNumber = output;
			break;
			
		case DECIMAL:
			//Don't allow more than one decimal
			if(!currentNumber.contains(".")) {
				currentNumber = currentNumber.concat(key.getText().toString());
				tv.append(key.getText());
			}
			break;
			
		case CLEAR:
				clearLists(tv);
			break;
			
		case SQUAREROOT:
			if(empty(text)) break;
			
			pushCurrentNumber();
			double res = calcUtils.evaluate(numbers, operators);
			res = Math.sqrt(res);
	    	String out = calcUtils.format(res);
			tv.setText(out);
	    	
	    	numbers.clear();
			operators.clear();
			currentNumber = out;
			break;
			
		case NUMBER:
			currentNumber = currentNumber.concat(key.getText().toString());
			tv.append(key.getText());
			break;
			
		default:
			break;
		} 
    }
    
    private boolean empty(String s) {
    	return s == null || "".equals(s);
    }
    
    private String removeLastChar(String input) {
    	return empty(input) ? "" : input.substring(0, input.length()-1);
    }
    
    //Parse the current number, push it, and clear the number accumulator
    private void pushCurrentNumber() {
    	if(!empty(currentNumber)) {
    		numbers.add(Double.parseDouble(currentNumber));
    		currentNumber = "";
    	}
    }
    
   
    
    //If undefined is displayed just toss it and start over when the user enters anything
    private void handleUndefined(TextView tv) {
		if(tv.getText().toString().contains(UNDEFINED)) {
			clearLists(tv);
		}
    }
    
    private void clearLists(TextView tv) {
    	tv.setText("");
		numbers.clear();
		operators.clear();
		currentNumber = "";
    }
    

}
