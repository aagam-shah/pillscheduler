package star.pillscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class AlarmDialog extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		/*super.onCreate(savedInstanceState);
		// Make us non-modal, so that others can receive touch events.
	  //  getWindow().setFlags(LayoutParams.FLAG_NOT_TOUCH_MODAL, LayoutParams.FLAG_NOT_TOUCH_MODAL);

	    // ...but notify us that it happened.
	    getWindow().setFlags(LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON+
	            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD+
	            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED+
	            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		setContentView(R.layout.ringdialog);*/
		
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		Context ctx = this;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setMessage("asda asda asdad asdad adads");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button
		        	   finish();
		           }
		       });
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User cancelled the dialog
		           }
		       });
		builder.create();
		builder.show();
	}
	
	/*@Override
	  public boolean onTouchEvent(MotionEvent event) {
	    // If we've received a touch notification that the user has touched
	    // outside the app, finish the activity.
	    if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
	      //finish();s
	      return true;
	    }

	    // Delegate everything else to Activity.
	    return false;
	  }*/

	
}
