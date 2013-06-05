package star.pillscheduler;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.*;

public class MainActivity extends SherlockActivity {

	
	public ListView lv ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		PillDB pdb= new PillDB(this);
		lv = (ListView)findViewById(R.id.listView);
		listLoad();
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				final View newVi = view;
				TextView tv = (TextView)findViewById(R.id.list_row_title);
				TextView tv1 = (TextView)findViewById(R.id.list_row_descr);
				ImageView iv =(ImageView)view.findViewById(R.id.pill_img);
				iv.setImageResource(R.drawable.orange);
				
				Button b = (Button)findViewById(R.id.checkb);
				b.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Log.e("clicked", ":P");
					}
				});
				HorizontalScrollView hv = (HorizontalScrollView)newVi.findViewById(R.id.scroll);
				if(hv.getVisibility()==View.GONE)
	                hv.setVisibility(View.VISIBLE);
	            else{
	            	hv.setVisibility(View.GONE);
	            	Toast.makeText(getApplicationContext(), "Touched", Toast.LENGTH_SHORT).show();
				
						
					
				
			}
		}});
        }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	public void listLoad(){
		String[] columns = new String[] {"_id",
        	    PillDB.nameDB,PillDB.descrDB
        	  };
        	 
        	  // the XML defined views which the data will be bound to
        	  int[] to = new int[] { R.id.list_row_title,
        	    R.id.list_row_title,R.id.list_row_descr
        	  };
        	 
        Cursor c =PillDB.getPillAlarms();
				
		PillListDBAdapter pldb = new PillListDBAdapter(this, R.layout.list_row, c, columns, to);
		
		lv.setAdapter(pldb);
		
		
	}
	
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		listLoad();
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
