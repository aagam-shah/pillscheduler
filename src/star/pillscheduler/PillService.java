package star.pillscheduler;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PillService extends Service{

	@Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("starting serice", "class");
        Intent intent1 = new Intent(this, AlarmDialog.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);


    }
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
