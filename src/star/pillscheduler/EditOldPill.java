package star.pillscheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class EditOldPill extends SherlockActivity{
	
	public Button timings;
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
        
		actionBar.setDisplayHomeAsUpEnabled(true);
        
        actionBar.setTitle("Edit Pill");
        
        
        id =  getIntent().getIntExtra("id", 979);
        pillAlarm =PillDB.getPill(id); 
        
        setContentView(R.layout.editpill);
		pillImg=(ImageView)findViewById(R.id.epillImg);
		setOldImage();
		pName=(EditText)findViewById(R.id.epill_name);
		pName.setText(pillAlarm.getTitle());
		
		pDescr=(EditText)findViewById(R.id.epill_descr);
		pDescr.setText(pillAlarm.getDescr());
		timings=(Button)findViewById(R.id.eaddtime);
		
		setListeners();
		
		pNo = (EditText)findViewById(R.id.epill_num);
		int p = pillAlarm.getPills();
		pNo.setText(""+p);
		
	}
	private void setOldImage() {
		// TODO Auto-generated method stub
		File imgFile = new  File(pillAlarm.getImgUri());
		if(imgFile.exists()){
			isIMG=true;
			location = pillAlarm.getImgUri();
			BitmapFactory.Options options=new BitmapFactory.Options();
			options.inSampleSize = 8;
			//Bitmap preview_bitmap=BitmapFactory.decodeStream(is,null,options);
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
		    pillImg.setImageBitmap(myBitmap);
		}
		
	}
	private void setListeners() {
		// TODO Auto-generated method stub\
		
		
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
		
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent);
	        return true;
			
    	case R.id.saveButton:
    		
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
		PillDB pdb = new PillDB(this);
		PillAlarm p;
		long id=0;
		if(isIMG){
			
			
			Log.e("imageuri", ""+location);
			p = new PillAlarm(name,descrt,dayslist,location,totalPills);
			id = pdb.addAlarm(p,location);
			finish();
		}
		else{
			p = new PillAlarm(name,descrt,dayslist,totalPills);
			id = pdb.addAlarm(p);
			finish();
		}
		
		
	}
	private int getTotalPills() {
		// TODO Auto-generated method stub
		int value=Integer.parseInt(pNo.getText().toString());
		return value;
	
	}
}
