package star.pillscheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class setTimings extends SherlockActivity{

	public ToggleButton[] tg = new ToggleButton[27];
	
	public int[]ids={R.id.tb19,R.id.tb20,R.id.tb21,R.id.tb1,R.id.tb2,R.id.tb3, 
			R.id.tb4,R.id.tb5,R.id.tb6,R.id.tb7,R.id.tb8,R.id.tb9,
			R.id.tb10,R.id.tb11,R.id.tb12,R.id.tb13,R.id.tb14,R.id.tb15,R.id.tb16,R.id.tb17, 
			R.id.tb18,R.id.tb22,R.id.tb23,R.id.tb24,R.id.tb25,R.id.tb26,R.id.tb27};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.days);
		ActionBar actionBar = getSupportActionBar();
		//startActionMode(mActionModeCallback);
		actionBar.setDisplayHomeAsUpEnabled(true);
        
        actionBar.setTitle("Select Time");
		initToggleButtons();
		
		
	}
	
	
	private void initToggleButtons() {
		// TODO Auto-generated method stub
		for(int i=0;i<27;i++){
			tg[i]=(ToggleButton)findViewById(ids[i]);
			
			
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.savemenu, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.saveButton:
    		Log.e("hello", "save");
    		savedays();
    		Intent i =new Intent();
//    		i.putextra
    		i.putExtra("lists", data);
    		setResult(Activity.RESULT_OK, i);
    		finish();
    		return true;
    	case R.id.discard:
    		savedays();
    		Intent i1 =new Intent();
    		i1.putExtra("lists", data);
    		setResult(Activity.RESULT_CANCELED, i1);
    		finish();
    		return false;
    	
    	}
		
		return false;
	}

	public byte b;
	public String data="";
	
	public void savedays(){
		StringBuffer bf = new StringBuffer();
		for(int i=0;i<27;i++){
			if(tg[i].isChecked())
			bf.append(1);
			else
				bf.append(0);
			
		}
		data=bf.toString();
		Toast.makeText(getApplicationContext(), ""+data, Toast.LENGTH_LONG).show();
		Log.e("string saved with length",""+data.length());
		
		
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
	    		savedays();
	    		Intent i =new Intent();
//	    		i.putextra
	    		i.putExtra("lists", data);
	    		setResult(Activity.RESULT_OK, i);
	    		finish();
	    		return true;
	    	case R.id.discard:
	    		savedays();
	    		Intent i1 =new Intent();
	    		i1.putExtra("lists", data);
	    		setResult(Activity.RESULT_CANCELED, i1);
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
	

	
	
}
