package star.pillscheduler;

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
            "pillImageLocation text default '',ringtone int default 1);";
	
	
	private static final String CREATE_TABLE_DAYS="create table if not exists days "+
            "(id integer,isalterDay boolean,alterDay integer default 1,allDays boolean,mon integer default 000," +
            "tue integer default 000,wed integer default 000,thu integer default 000,fri integer default 000," +
            "sat integer default 000,sun integer default 000);";
	
	private static final String TOTAL_ALARMS="SELECT COUNT(*) FROM table_name;";
	
	private static Context context;
	private static SQLiteDatabase db;
	
	public PillDB(Context ctx){
		context=ctx;
		try{
		db = ctx.openOrCreateDatabase(DATABASE_NAME,0, null);
		db.execSQL(CREATE_TABLE_PILLS);
		
		db.execSQL(CREATE_TABLE_DAYS);
		}
		catch(Exception e){
			db=null;
			Toast.makeText(ctx, "Error in creating DB", Toast.LENGTH_LONG).show();
			return;
		}
		
		
		
		
	}
	
	public static long addAlarm(PillAlarm newA){
		String title= newA.getTitle();
		String descr = newA.getDescr();
		String[] dayTime=newA.getDays();
		ContentValues values = new ContentValues();
        values.put("name", title);
        values.put("descr", descr);
       // values.put("pillsLeft", 10);
        
        
        		long t=db.insert("lists", null, values);
        		
        		Log.e("msgs   ", ""+t);
        		alarmID=(int) t;
        		Log.e("number", ""+getNumberOfAlarms());
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

}
