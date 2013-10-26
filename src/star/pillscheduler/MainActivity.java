package star.pillscheduler;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity{

	


	public int oneDay=24*60*60*1000; //86,400,000 milliseconds
	public ListView lv ;
	public static boolean firstinsall=true;
	public static Cursor c;
	public PillDB pdb;
	public ImageView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getSupportActionBar().setDisplayShowHomeEnabled(false);
		setContentView(R.layout.list);
		
		pdb= new PillDB(this);
		
		
		
			SharedPreferences instDetails = this.getSharedPreferences("install", MODE_PRIVATE);
			String check  = instDetails.getString("isInstalled", "spam");
			
			if(firstinsall){
				
				startAlarm();
			}
			
			if(check=="spam"){
				//new install
				Editor edit = instDetails.edit();
				edit.clear();
				edit.putString("isInstalled", "true");
				edit.commit();
				startAlarm();
				Toast.makeText(getApplicationContext(), "Fresh Install", Toast.LENGTH_SHORT).show();
			}
		
		
		lv = (ListView)findViewById(R.id.listView);
		
		tv= (ImageView)findViewById(android.R.id.empty);
		
		lv.setEmptyView(tv);
		
		//tv1.setVisibility(View.VISIBLE);
		listLoad();
//		NotificationCompat.Builder nm = new NotificationCompat.Builder(this)
//		.setSmallIcon(android.R.drawable.btn_star).setContentTitle("Title").setContentText("Helllooo");
//		
//		nm.setTicker("hello I am very very happpppppyyy asda pol pada sd apd aspdapd apsdpadspd");
//		NotificationManager nm1 = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//		Intent i = new Intent(this,MainActivity.class);
//		PendingIntent ac = PendingIntent.getActivity(this, 0, i, 0);
//		//nm.setContentIntent(ac);
//		nm1.notify(12222, nm.build());
		
		tv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Intent i = new Intent("star.pillscheduler.addPill");

	             startActivity(i);
				return false;
			}
		});
		
		
        }

	
	private void startAlarm() {
		// TODO Auto-generated method stub
		
		setMorning();
		setNoon();
		setEve();
		
	}


	private void setEve() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		Calendar customM =(Calendar) c.clone();
		customM.set(Calendar.HOUR_OF_DAY, 21);
		customM.set(Calendar.MINUTE, 0);
		customM.set(Calendar.SECOND, 0);
		customM.set(Calendar.MILLISECOND, 0);
		
		if(customM.before(c))
			customM.add(Calendar.DAY_OF_MONTH, 1);
		
		Intent i =new Intent(this,PillAlarmReceiver.class);
		
		i.putExtra("time",3);
		
		PendingIntent pt= PendingIntent.getBroadcast(this,3, i, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) getSystemService(this.ALARM_SERVICE);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),oneDay, pt);
	//	am.setRepeating(AlarmManager.RTC_WAKEUP, customM.getTimeInMillis(),oneDay, pt);
		Log.e("setted","eve");
	}


	private void setNoon() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		Calendar customM =(Calendar) c.clone();
		customM.set(Calendar.HOUR_OF_DAY, 13);
		customM.set(Calendar.MINUTE, 0);
		customM.set(Calendar.SECOND, 0);
		customM.set(Calendar.MILLISECOND, 0);

		if(customM.before(c))
			customM.add(Calendar.DAY_OF_MONTH, 1);
		Intent i =new Intent(this,PillAlarmReceiver.class);
		
		i.putExtra("time",2);
		
		PendingIntent pt= PendingIntent.getBroadcast(this, 2, i, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) getSystemService(this.ALARM_SERVICE);
		am.setRepeating(AlarmManager.RTC_WAKEUP, customM.getTimeInMillis(),oneDay, pt);
		Log.e("setted","noon");
	}


	private void setMorning() {
		Calendar c = Calendar.getInstance();
		Calendar customM =(Calendar) c.clone();
		customM.set(Calendar.HOUR_OF_DAY, 8);
		customM.set(Calendar.MINUTE, 0);
		customM.set(Calendar.SECOND, 0);
		customM.set(Calendar.MILLISECOND, 0);
		
		if(customM.before(c))
			customM.add(Calendar.DAY_OF_MONTH, 1);
		
		Intent i =new Intent(this,PillAlarmReceiver.class);
		
		i.putExtra("time",1);
		
		PendingIntent pt= PendingIntent.getBroadcast(this,1, i, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager) getSystemService(this.ALARM_SERVICE);
		
		//am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),oneDay, pt);
		am.setRepeating(AlarmManager.RTC_WAKEUP, customM.getTimeInMillis(),oneDay, pt);
		Log.e("setted","morn");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	public  void listLoad(){
		String[] columns = new String[] {"_id",
        	    pdb.nameDB,pdb.descrDB,"pillsleft","pilloc"
        	  };
        	 
        	  // the XML defined views which the data will be bound to
        	  int[] to = new int[] { R.id.list_row_title,
        	    R.id.list_row_title,R.id.list_row_descr,R.id.list_pills,R.id.pill_img
        	  };
        	 
        c =pdb.getPillAlarms();
        if(c.getCount()==0)
        	tv.setVisibility(View.VISIBLE);
		PillListDBAdapter pldb = new PillListDBAdapter(this, R.layout.list_row, c, columns, to);
		
		lv.setAdapter(pldb);
		
		
		
	}
	
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		listLoad();
		
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 if (item.getItemId() == R.id.addPill) {
			 Intent i = new Intent("star.pillscheduler.addPill");

             startActivity(i);
	            return false;
	        }
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}
	
	
}
