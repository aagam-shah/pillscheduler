package star.pillscheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class PillDB {
	
	private static final String dbVersion="1"; 
	private static final String DATABASE_NAME = "pills";
	public static int alarmID=1;
	
	public static String nameDB = "name";
	public static String idDB = "_id";
	public static String descrDB = "descr";
	private static final String CREATE_TABLE_PILLS ="create table if not exists lists " +
			"( _id integer primary key autoincrement, "
            + "name varchar(50), descr varchar(50), justNotify boolean default true," +
            "pillImageLocation text default '',ringtone integer default 1);";
	
	
	private static final String CREATE_TABLE_DAYS="create table if not exists days "+
            "(idPill integer,days varchar(30),pillsL integer,currday integer);";
	
	private static final String TOTAL_ALARMS="SELECT COUNT(*) FROM table_name;";
	
	private static Context context;
	private static SQLiteDatabase db;
	public int databseID=1;
	
	public PillDB(Context ctx){
		context=ctx;
		try{
		db = ctx.openOrCreateDatabase(DATABASE_NAME,0, null);
		db.execSQL(CREATE_TABLE_PILLS);
		
		db.execSQL(CREATE_TABLE_DAYS);
		}
		catch(Exception e){
			db=null;
			Toast.makeText(ctx, "Error in creating DB,Please re-install", Toast.LENGTH_LONG).show();
			return;
		}
		
		
		
		
	}
	
	
	public static long addAlarm(PillAlarm newA){
		
		String title= newA.getTitle();
		String descr = newA.getDescr();
		String timings=newA.getTimings();
		int pillsL=newA.getPills();
		
		ContentValues values = new ContentValues();
        values.put("name", title);
        values.put("descr", descr);
       // values.put("pillsLeft", 10);
        
        
        		long t=db.insert("lists", null, values);
        		ContentValues values1 = new ContentValues();
        		values1.put("idPill", (int)t);
              //  values1.put("days", title);
                values1.put("pillsL", descr);
                values1.put("days", timings);
        		Log.e("string passed db", ""+timings);
        		db.insert("days", null, values1);
        		//Log.e("msgs   ", ""+t);
        		alarmID=(int) t;
        		Log.e("number", ""+getNumberOfAlarms());
        		List<Integer> tppp= queryDB(1);
        		//watchDB();
        		return t;
		
	}
	


	public static long getNumberOfAlarms(){
		
		long size = DatabaseUtils.queryNumEntries(db, "lists");
		//db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
		//db.execSQL(TOTAL_ALARMS);
		return size;
	}
	
	public static Cursor getPillAlarms(){
		
		try{Cursor mCursor = db.query("lists", new String[] {"_id","name","descr"}, 
			    null, null, null, null, null);
			 
			  if (mCursor != null) {
			   mCursor.moveToFirst();
			  }
			  
		return mCursor;
		}
		catch(Exception e){
			Log.e("error","here");
			Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
			return null;
		}
		
	}
	

	public static void ring(int i){
		//queryDB();
		if(i==1){
			//its morning
			int today = Calendar.DAY_OF_WEEK;
			// Intent itt = new Intent("star.pillscheduler.addPill");

             //context.startActivity(itt);
			//obtain string of all the alarms and then check if there is any alarm for today morning
			Log.e("morning", "recvd");
		}
		else if(i==2){
			//its noon
			Log.e("noon", "recvd");
		}
		else if(i==3){
			//its night
			Log.e("night", "recvd");
		}
		else{
			//some error
			Log.e("error "+i, "recvd");
			return;
		}
	}
	
	//public static Cursor curs;
	
	public static List<Integer> queryDB(int i){
		List<Integer> idsA= new ArrayList<Integer>();
		
		Cursor curs = db.rawQuery("select idPill, days from days ", null);
		String curr="";
		while(curs.moveToNext()){
			int id=curs.getInt(0);
			String s = curs.getString(1);
			Calendar c = Calendar.getInstance();
			int day =c.get(Calendar.DAY_OF_WEEK);
			
			day=(day-2)*3;
			
			String ss=s.substring(day, day+3);
			curr=getStatus(ss);
			if(curr.charAt(i-1)!='0')
			{
			idsA.add(id);
			}
			Log.e("id"+id, " when "+curr);
		}
		
		
		
			return idsA;
	}


	private static String getStatus(String ss) {
		// TODO Auto-generated method stub
		String tp="";
		StringBuilder sb = new StringBuilder(tp);
		if(ss.charAt(0)=='1')
			sb.append("M");
		else
			sb.append("0");
		if(ss.charAt(1)=='1')
			sb.append("N");
		else
			sb.append("0");
		if(ss.charAt(2)=='1')
			sb.append("E");
		else
			sb.append("0");
		
		
		
		return sb.toString();
	}
	
}
