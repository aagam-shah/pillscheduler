package star.pillscheduler;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.*;

public class addPill extends SherlockActivity {

	


	public CheckBox[] cb1=new CheckBox[4];
	public int[] cbids={R.id.cb1,R.id.cb2,R.id.cb3,R.id.cb4};
	public Button others,timings;
	public ImageView pillImg;
	public int numberPills=0;
	public EditText pName,pDescr,pNo;
	public String dayslist="000000000000000000000000000";
	public boolean isIMG=false;
	public int dialogvalue=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getSupportActionBar();
        
        actionBar.setDisplayShowHomeEnabled(true);
        
        actionBar.setTitle("Add Pill");
		setContentView(R.layout.addedit);
		pillImg=(ImageView)findViewById(R.id.pillImg);
		others =(Button)findViewById(R.id.others);
		pName=(EditText)findViewById(R.id.pill_name);
		pDescr=(EditText)findViewById(R.id.pill_descr);
		timings=(Button)findViewById(R.id.addtime);
		for(int i=0;i<4;i++){
			cb1[i]=(CheckBox)findViewById(cbids[i]);
			
		}
		setListeners();
		//asda
		pNo = (EditText)findViewById(R.id.pill_name);
	}
	private void setListeners() {
		// TODO Auto-generated method stub\
		
		others.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(2);	
			}
		});
		
		pillImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isIMG=true;
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			    startActivityForResult(intent, 100);	
			}
		});
		
		
		
		timings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("star.pillscheduler.setTimings");
				i.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				startActivityForResult(i, 39);
			}
		});
		
		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.savemenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
    	case R.id.saveButton:
    		boolean isEmpty = checkNotEmpty();
    		Log.e("hello", "save");
    		savePill();
    		finish();
    		return true;
    	case R.id.discard:
    		finish();
    		return false;
    	default:
    		Log.e("hello", "save");
    		Toast.makeText(getApplicationContext(), "eror",Toast.LENGTH_SHORT).show();
    		return true;
    	}
	}
	
	private boolean checkNotEmpty() {
		// TODO Auto-generated method stub
		
		
		return false;
	}
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		
		switch(id){
		case 2:
			return createPillDialog();
		default:
			return null;
		
		}
	}



	private Dialog createPillDialog() {
		 AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle("Pill Select no of pills");
	       // builder.setMessage("Please enter the number of pills you have");
	 
	         // Use an EditText view to get user input.
	         final EditText input = new EditText(this);
	       //  input.setId(TEXT_ID);
	         input.setInputType(InputType.TYPE_CLASS_NUMBER);
	         builder.setView(input);
	         builder.setCancelable(false);
	        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	 
	            @Override
	            public void onClick(DialogInterface dialog, int whichButton) {
	                String value = input.getText().toString();
	                Toast.makeText(getApplicationContext(), value+" selected", Toast.LENGTH_SHORT).show();
	                numberPills=Integer.parseInt(value);
	                dialogvalue=numberPills;
	                Log.d("asd", "User name: " + value);
	                return;
	            }
	        });
	 
	        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	 
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	            	dialogvalue=0;
	            	Toast.makeText(getApplicationContext(), "0 selected", Toast.LENGTH_SHORT).show();
	                return;
	            }
	        });
	 
	        return builder.create();
	    }
		
	public Uri picUri;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//Log.e("camera", "");
		
		if(requestCode==39 && resultCode==Activity.RESULT_OK){
			Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
			dayslist = data.getStringExtra("lists");
			
			
		}
		
		else if(requestCode==100){
			///camera here :)
			picUri = data.getData();
			Log.e("picnewuir", ""+picUri.toString());
			String s =picUri.getEncodedPath();
			InputStream stream = null;
			try {
				stream = getContentResolver().openInputStream(data.getData());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Bitmap bitmap = BitmapFactory.decodeStream(stream);

	       
			
			pillImg.setImageBitmap(bitmap);
			isIMG=true;
			Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
			//Log.e("camera", ""+s);
			
		}
		else{
			//gg
			Toast.makeText(this, "Added w/o", Toast.LENGTH_SHORT).show();
			Log.e("camera", "gone here");
			
		}
		
		
		
	}
	private void savePill() {
		// TODO Auto-generated method stub
		
		String name = pName.getText().toString();
		String descrt=pDescr.getText().toString();
		int totalPills=getTotalPills();
		
		PillAlarm p;
		long id=0;
		if(isIMG){
			String imguri =picUri.toString();
			Log.e("imageuri", ""+imguri);
			p = new PillAlarm(name,descrt,dayslist,imguri,totalPills);
			id = PillDB.addAlarm(p,imguri);
		}
		else{
			p = new PillAlarm(name,descrt,dayslist,totalPills);
			id = PillDB.addAlarm(p);
		}
		
		
	}
	private int getTotalPills() {
		// TODO Auto-generated method stub
		int value=0;
		for(int i=0;i<4;i++){
			if(cb1[i].isChecked()){
				value =value + Integer.parseInt(cb1[i].getText().toString());
			}
			
		}
		value=value+dialogvalue;
		return value;
	
	}
}
