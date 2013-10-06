package star.pillscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmDialog extends Activity {

	public TextView pName, pNumber;
	public Button pTaken, pNot;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	/*	
		final int pillIdfetched = getIntent().getIntExtra("id", 0);
		final int d = getIntent().getIntExtra("d", 0);
		final int pillId = pillIdfetched-d;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ringdialog);
		
		if(android.os.Build.VERSION.SDK_INT>=11){
		this.setFinishOnTouchOutside(false);
		}
		
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
				Log.e("Alarm Dialog pillId", ""+pillId);
				PillDB.update(pillId,value+5);
				NotificationManager m = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				m.cancel(pillIdfetched);
				finish();
				
			}
		});
		
		pNot.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.e("not taken pill", "clked");
				NotificationManager m = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				m.cancel(pillId);
				finish();
			}
		});
		*/
		
		super.onCreate(savedInstanceState);
		final int pillIdfetched = getIntent().getIntExtra("id", 0);
		final int d = getIntent().getIntExtra("d", 0);
		final int pillId = pillIdfetched-d;
		final PillAlarm pa = PillDB.getPill(pillId);
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder
	        .setTitle("asd ")
	        .setMessage("Pills Left : ")
	        .setCancelable(false)
	        .setPositiveButton("Taken", new DialogInterface.OnClickListener() 
	        {
	            public void onClick(DialogInterface dialog, int id) 
	            {
	            	NotificationManager m = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
					m.cancel(pillId);
	                dialog.cancel();
	                finish();
	            }
	        })
	        .setNegativeButton("Not Taken", new DialogInterface.OnClickListener() 
	        {
	            public void onClick(DialogInterface dialog, int id) 
	            {
	            	NotificationManager m = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
					m.cancel(pillId);
	                dialog.cancel();
	                finish();
	            }
	        });
	    AlertDialog alert = builder.create();
	    alert.show();
		
	}
	
}
