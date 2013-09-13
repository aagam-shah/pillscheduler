package star.pillscheduler;

import java.util.Iterator;
import java.util.List;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public class PillAlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent i) {
		// TODO Auto-generated method
		// arg0.startService(new Intent(arg0, PillService.class));
  		
  		
		/*int val=0;
		int d = i.getIntExtra("time", val);
		PillDB pd = new PillDB(arg0);
		List<Integer> result=pd.queryDB(d);
		Iterator<Integer> ite = result.iterator();
		
		while(ite.hasNext()){
			Log.e("ids", ""+ite.next());
			
			
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
