package star.pillscheduler;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.*;

public class MainActivity extends SherlockActivity {

	private ArrayList<HashMap> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		PillDB pdb= new PillDB(this);
		ListView lv = (ListView)findViewById(R.id.listView);
        String[] columns = new String[] {"_id",
        	    PillDB.nameDB,PillDB.descrDB
        	  };
        	 
        	  // the XML defined views which the data will be bound to
        	  int[] to = new int[] { R.id.list_row_title,
        	    R.id.list_row_title,R.id.list_row_descr
        	  };
        	 
        Cursor c =PillDB.getPillAlarms();
		ListAdapter laa = new SimpleCursorAdapter(this, R.layout.list_row, c, columns, to);
		lv.setAdapter(laa);
        
        
        
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		
		return true;
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
	
	

}
