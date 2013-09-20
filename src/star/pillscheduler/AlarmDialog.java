package star.pillscheduler;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AlarmDialog extends Activity {

	public TextView pName, pNumber;
	public Button pTaken, pNot;
	
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
	            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);*/
		
		final int pillId = getIntent().getIntExtra("id", 0);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ringdialog);
		final PillAlarm pa = PillDB.getPill(pillId);
		
		pName  =(TextView)findViewById(R.id.alarm_pillnname);
		pName.setText(pa.getTitle());
		pNumber  =(TextView)findViewById(R.id.alarm_pillno);
		pNumber.setText(""+pa.getPills());
		pTaken = (Button)findViewById(R.id.alarm_taken);
		pNot = (Button)findViewById(R.id.alarm_nottaken);

		pTaken.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int value = pa.getPills();
				Log.e("taken", ""+pa.getTitle());
				PillDB.update(pillId,value+5);
				NotificationManager m = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				m.cancel(23+pillId);
				finish();
				
			}
		});
		
		pNot.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.e("not taken pill", "clked");
				NotificationManager m = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				m.cancel(23+pillId);
				finish();
			}
		});
		
	}
	
}
