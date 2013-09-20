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

	@Override
	public void onReceive(Context arg0, Intent i) {
	
		int d = i.getIntExtra("time", 5);
		PillDB pd = new PillDB(arg0);
		List<Integer> result=pd.queryDB(d);
		Iterator<Integer> ite = result.iterator();
		
		
		Log.e("receiver", "text notify "+d);
		
		while(ite.hasNext()){
			//Log.e("loop rx","val "+ite.next());
			int id = ite.next();
			PillAlarm pa = PillDB.getPill(id);
			
			NotificationCompat.Builder nm = new NotificationCompat.Builder(arg0)
			.setSmallIcon(R.drawable.orange).setContentTitle(pa.getTitle()).setContentText(""+pa.getPills());
			NotificationManager nm1 = (NotificationManager)arg0.getSystemService(arg0.NOTIFICATION_SERVICE);
			Intent i1 = new Intent(arg0,AlarmDialog.class);
			i1.putExtra("id", id);
			PendingIntent ac = PendingIntent.getActivity(arg0, id*300, i1,0);
			nm.setContentIntent(ac);
			nm1.notify(id+23, nm.build());
			Log.e("count of night", ""+result.size());
		}
		/*LayoutInflater layoutInflater 
	     = (LayoutInflater)arg0
	      .getSystemService(Service.LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.ringdialog, null);  
		PopupWindow pop = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,  
               LayoutParams.WRAP_CONTENT);
		  pop.showAtLocation(popupView, Gravity.CENTER, 50, 50);
		  pop.setFocusable(true);
		  
		  pop.setOutsideTouchable(false);
		 
        //popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
		Intent showAlarm = new Intent(arg0, AlarmDialog.class);
		showAlarm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		arg0.startActivity(showAlarm);*/
		
		}

}
