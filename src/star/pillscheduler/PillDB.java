package star.pillscheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
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
            "pilloc varchar(60) default 'aagamshah',isimg boolean,pillsleft integer default 0,ringtone integer default 1);";
	
	
	private static final String CREATE_TABLE_DAYS="create table if not exists days "+
            "(idPill integer,days varchar(27));";
	
	private static final String TOTAL_ALARMS="SELECT COUNT(*) FROM table_name;";
	
	private static Context context;
	private static SQLiteDatabase db;
	public int databseID=1;
	
	
	//create DB
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
	
	//Add an alarm without changing image of pill
	public long addAlarm(PillAlarm newA){
		
		String title= newA.getTitle();
		String descr = newA.getDescr();
		String timings=newA.getTimings();
		int pillsL=newA.getPills();
		
		ContentValues values = new ContentValues();
        values.put("name", title);
        values.put("descr", descr);
        values.put("pillsleft", pillsL);
        values.put("isimg", false);
       // values.put("pillsLeft", 10);
        
        
        		long t=db.insert("lists", null, values);
        		ContentValues values1 = new ContentValues();
        		values1.put("idPill", (int)t);
                values1.put("days", timings);
        		Log.e("string passed db", ""+timings);
        		db.insert("days", null, values1);
        		//Log.e("msgs   ", ""+t);
        		alarmID=(int) t;
        		//watchDB();
        		return t;
		
	}
	//Add an alarm which also has a specific pic, i.e - uri
public long addAlarm(PillAlarm newA,String uri){
		
		String title= newA.getTitle();
		String descr = newA.getDescr();
		String timings=newA.getTimings();
		int pillsL=newA.getPills();
		
		ContentValues values = new ContentValues();
        values.put("name", title);
        values.put("descr", descr);
        values.put("pillsleft", pillsL);
        values.put("isimg", true);
        values.put("pilloc", uri);
       // values.put("pillsLeft", 10);
        
        
        		long t=db.insert("lists", null, values);
        		ContentValues values1 = new ContentValues();
        		values1.put("idPill", (int)t);
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
	

//TO get the total number of alarms..
	public static long getNumberOfAlarms(){
		
		long size = DatabaseUtils.queryNumEntries(db, "lists");
		//db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
		//db.execSQL(TOTAL_ALARMS);
		return size;
	}
	
	
//zto obtain a cursor of all the alarms	
	public static Cursor getPillAlarms(){
		
		try{Cursor mCursor = db.query("lists", new String[] {"_id","name","descr","pillsleft","pilloc"}, 
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
	
	//Here i is the current time i.e-Morning,Noon,Evening...Accordingly check if 
	//alarm is set for each item in the list at that time and than 
	//return the list of alarms to be ring at that time

	
	public  List<Integer> queryDB(int i){
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

//A 3 char string is passed and accordingly M,N,E is printed
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

	public static void delete(int i) {
		db.delete("lists", "_id=?",new String[] {""+i});
		db.delete("days", "idPill=?",new String[] {""+i});
	}
	
	public static PillAlarm getPill(int id){
		Cursor cursor = db.query("lists", new String[] { "name",
	            "descr", "pilloc","pillsLeft" }, "_id" + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
		 if (cursor != null)
		        cursor.moveToFirst();
		 
		 int z = Integer.parseInt(cursor.getString(3));
		 Log.e("getPill", "pills :"+z );
		 PillAlarm pA = new  PillAlarm(cursor.getString(0), cursor.getString(1),
				 "0", cursor.getString(2), z);
		 
		 return pA;
		
	}
}
