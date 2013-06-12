package star.pillscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PillAlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent i) {
		// TODO Auto-generated method stub
		int val=0;
		int d = i.getIntExtra("time", val);
		
		PillDB.ring(d);
		
		
		
		
	}

}
