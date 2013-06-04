package star.pillscheduler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.*;

public class addPill extends SherlockActivity {

	public CheckBox cb1,cb2,cb3,cb4;
	public Button others;
	public int numberPills=0;
	public EditText pName,pDescr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(
                ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME
                        | ActionBar.DISPLAY_SHOW_TITLE);
        
                actionBar.setTitle("Add Pill");
		setContentView(R.layout.addedit);
		startActionMode(mActionModeCallback);
		others =(Button)findViewById(R.id.others);
		pName=(EditText)findViewById(R.id.pill_name);
		pDescr=(EditText)findViewById(R.id.pill_descr);
		setListeners();
		final EditText pill_n = (EditText)findViewById(R.id.pill_name);
	}
	private void setListeners() {
		// TODO Auto-generated method stub
		others.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(2);	
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		
		switch(id){
		case 2:
			return createPillDialog();
		default:
			return null;
		
		}
	}



	private Dialog createPillDialog() {
		 AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle("Pill Select no of pills");
	       // builder.setMessage("Please enter the number of pills you have");
	 
	         // Use an EditText view to get user input.
	         final EditText input = new EditText(this);
	       //  input.setId(TEXT_ID);
	         input.setInputType(InputType.TYPE_CLASS_NUMBER);
	         builder.setView(input);
	         builder.setCancelable(false);
	        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	 
	            @Override
	            public void onClick(DialogInterface dialog, int whichButton) {
	                String value = input.getText().toString();
	                Toast.makeText(getApplicationContext(), value+" selected", Toast.LENGTH_SHORT).show();
	                numberPills=Integer.parseInt(value);
	                Log.d("asd", "User name: " + value);
	                return;
	            }
	        });
	 
	        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	 
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	            	Toast.makeText(getApplicationContext(), "0 selected", Toast.LENGTH_SHORT).show();
	                return;
	            }
	        });
	 
	        return builder.create();
	    }
	



	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

	    // Called when the action mode is created; startActionMode() was called
	    @Override
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	        // Inflate a menu resource providing context menu items
	        MenuInflater inflater = mode.getMenuInflater();
	        inflater.inflate(R.menu.savemenu, menu);
	        return true;
	    }

	    // Called each time the action mode is shown. Always called after onCreateActionMode, but
	    // may be called multiple times if the mode is invalidated.
	    @Override
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	        return false; // Return false if nothing is done
	    }
	    
	    

	    // Called when the user selects a contextual menu item
	    @Override
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	    	
	    	switch(item.getItemId()){
	    	case R.id.saveButton:
	    		Log.e("hello", "save");
	    		savePill();
	    		showDialog(2);
	    		
	    		finish();
	    		return true;
	    	case R.id.discard:
	    		finish();
	    		return false;
	    	
	    	}
	    	
	    //	Log.e("hello tick", ""+item.getItemId());
	        return false;
	    }

	    

		// Called when the user exits the action mode
	    @Override
	    public void onDestroyActionMode(ActionMode mode) {
	       // mActionMode = null;
	    }
	};
	
	private void savePill() {
		// TODO Auto-generated method stub
		
		String name = pName.getText().toString();
		String descrt=pDescr.getText().toString();
		PillAlarm p = new PillAlarm(name,descrt);
		PillDB.addAlarm(p);
		
	}
	
	
}
