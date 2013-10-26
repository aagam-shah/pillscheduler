package star.pillscheduler;

import java.util.Iterator;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.sax.StartElementListener;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class PillAlarmReceiver extends BroadcastReceiver {

	public static int p=0;
	
	@Override
	public void onReceive(Context arg0, Intent i) {
	
		int d = i.getIntExtra("time", 1);
		PillDB pd = new PillDB(arg0);
		List<Integer> result=pd.queryDB(d);
		Iterator<Integer> ite = result.iterator();		
		Log.e("receiver", "text notify "+d);
		
		while(ite.hasNext()){
			//Log.e("loop rx","val "+ite.next());
			int id = ite.next();//
			Log.e("pill id recvd",""+id);
			
			Log.e("times",""+d);
			
			PillAlarm pa = PillDB.getPill(id);
			
			NotificationCompat.Builder nm = new NotificationCompat.Builder(arg0)
			.setOngoing(true).setSmallIcon(R.drawable.ic).setContentTitle(pa.getTitle()).setContentText(""+pa.getPills());
			NotificationManager nm1 = (NotificationManager)arg0.getSystemService(arg0.NOTIFICATION_SERVICE);
			nm.setDefaults(Notification.DEFAULT_ALL);
			Intent i1 = new Intent(arg0,AlarmDialog.class);
			i1.putExtra("id", id+d);
			i1.putExtra("d", d);
			PendingIntent ac = PendingIntent.getActivity(arg0, id+d, i1,0);
			nm.setContentIntent(ac);
			nm1.notify(id+d, nm.build());
			//i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			//arg0.startActivity(i1);
			}
		
		}

}
