package star.pillscheduler;

import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PillAlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent i) {
		// TODO Auto-generated method stubasdasd
		int val=0;
		int d = i.getIntExtra("time", val);
		PillDB pd = new PillDB(arg0);
		List<Integer> result=pd.queryDB(d);
		Iterator<Integer> ite = result.iterator();
		while(ite.hasNext()){
			Log.e("ids", ""+ite.next());
			
		}
		PillDB.ring(d);
		//PillDB.queryDB();
		
		
		
	}

}
