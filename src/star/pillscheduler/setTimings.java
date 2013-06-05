package star.pillscheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class setTimings extends SherlockActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.days);
		ActionBar actionBar = getSupportActionBar();
		startActionMode(mActionModeCallback);
		
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
	    		Intent i =new Intent();
	    		i.putExtra("lists", "aagam");
	    		setResult(Activity.RESULT_OK, i);
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
	

	
	
}
