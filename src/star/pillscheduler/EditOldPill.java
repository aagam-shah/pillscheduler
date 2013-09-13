package star.pillscheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class EditOldPill extends SherlockActivity{
	public CheckBox[] cb1=new CheckBox[4];
	public int[] cbids={R.id.cb1,R.id.cb2,R.id.cb3,R.id.cb4};
	public Button others,timings;
	public ImageView pillImg;
	public int numberPills;
	public EditText pName,pDescr,pNo;
	public String dayslist="000000000000000000000000000";
	public boolean isIMG=false;
	public int dialogvalue=0;
	public static String location="";
	public static int fileid=125;
	public PillAlarm pillAlarm;
	public int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getSupportActionBar();
        
        actionBar.setDisplayShowHomeEnabled(true);
        
        actionBar.setTitle("Edit Pill");
        
        id =  getIntent().getIntExtra("id", 979);
        pillAlarm =PillDB.getPill(id); 
        
        numberPills = pillAlarm.getPills();
        
        
		setContentView(R.layout.addedit);
		pillImg=(ImageView)findViewById(R.id.pillImg);
		setOldImage();
		
		others =(Button)findViewById(R.id.others);
		pName=(EditText)findViewById(R.id.pill_name);
		pName.setText(pillAlarm.getTitle());
		
		pDescr=(EditText)findViewById(R.id.pill_descr);
		pDescr.setText(pillAlarm.getDescr());
		timings=(Button)findViewById(R.id.addtime);
		for(int i=0;i<4;i++){
			cb1[i]=(CheckBox)findViewById(cbids[i]);
			
		}
		setListeners();
		//asda
		pNo = (EditText)findViewById(R.id.pill_name);
	}
	private void setOldImage() {
		// TODO Auto-generated method stub
		File imgFile = new  File(pillAlarm.getImgUri());
		if(imgFile.exists()){
			BitmapFactory.Options options=new BitmapFactory.Options();
			options.inSampleSize = 8;
			//Bitmap preview_bitmap=BitmapFactory.decodeStream(is,null,options);
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
		    pillImg.setImageBitmap(myBitmap);
		}
		
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
				location=Environment.getExternalStorageDirectory()+"/Pills/star-"+System.currentTimeMillis()+".jpg";
				new File(Environment.getExternalStorageDirectory()+"/Pills").mkdirs();
				Uri uriSavedImage=Uri.fromFile(new File(location));
				
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
			    startActivityForResult(intent, 100);	
			}
		});
		
		
		
		timings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Log.e("location", ""+location);
				Intent i = new Intent("star.pillscheduler.setTimings");
		
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
			//picUri = data.getData();
			Log.e("picnewuir", "pl"+location);
			//String s =picUri.getEncodedPath();
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
		PillDB.delete(id);
		String name = pName.getText().toString();
		String descrt=pDescr.getText().toString();
		int totalPills=getTotalPills();
		
		PillAlarm p;
		long id=0;
		if(isIMG){
			
			
			Log.e("imageuri", ""+location);
			p = new PillAlarm(name,descrt,dayslist,location,totalPills);
			id = PillDB.addAlarm(p,location);
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
