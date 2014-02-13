package com.android.okcalculator;

import com.example.okcalculator.R;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class KeyAdapter extends BaseAdapter {
	 private Context context;
	 
	 private OnClickListener buttonClickListener;
		
	  public KeyAdapter(Context c) {
	    context = c;
	  }

	  public int getCount() {
	    return buttons.length;
	  }

	  public Object getItem(int position) {
	    return buttons[position];
	  }

	  public long getItemId(int position) {
	    return 0;
	  }

	// create a new ButtonView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
	  Button btn;
	  if (convertView == null) { // if it's not recycled, initialize some attributes
		btn = new Button(context);
	    Key keypadButton = buttons[position];
		if(keypadButton != Key.BLANK) {			
		    // Set the tag so we know what to do on button press
			btn.setTag(keypadButton);
			
		    btn.setOnClickListener(buttonClickListener);
		    //Set text styles.  Would be nice to set up a style in style.xml
		    btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		    btn.setGravity(Gravity.CENTER);
		    btn.setBackgroundResource(R.drawable.calc_button);
		}
	  } 
	  else {
	    btn = (Button) convertView;
	  }

	  btn.setText(buttons[position].getText());
	  return btn;
	}

	//blanks are empty spots in the grid layout
	private Key[] buttons = 
			{Key.ONE, Key.TWO,Key.THREE, Key.PLUS,
			Key.FOUR, Key.FIVE, Key.SIX, Key.MINUS,  
			Key.SEVEN, Key.EIGHT, Key.NINE, Key.MULTIPLY, 
			Key.ZERO, Key.DECIMAL, Key.BLANK, Key.DIVIDE, 
			Key.CLEAR, Key.BCKSP, Key.SQUAREROOT, Key.ENTER};

	public void setButtonClickListener(OnClickListener buttonClickListener) {
		this.buttonClickListener = buttonClickListener;
	}
	
	
}
