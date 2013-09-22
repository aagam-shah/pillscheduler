package star.pillscheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class CameraActivity extends SherlockActivity{

	public FrameLayout fr;
	public PillCamera pc;
	public Button butt;
	public Camera c;
	public static final int MEDIA_TYPE_IMAGE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		setContentView(R.layout.camera);
		c= getCameraInstance();
		pc = new PillCamera(this, c);
		fr=(FrameLayout)findViewById(R.id.cam);
		fr.addView(pc);
		
		final PictureCallback mPicture = new PictureCallback() {

	        public void onPictureTaken(byte[] data, Camera camera) {

	            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);

	            if (pictureFile == null){
	                return;
	            }

	            try {
	                FileOutputStream fos = new FileOutputStream(pictureFile);
	                fos.write(data);
	                fos.close();
	                MediaStore.Images.Media.insertImage(getContentResolver(), pictureFile.getAbsolutePath(), pictureFile.getName(), pictureFile.getName());
	            } catch (FileNotFoundException e) {

	            } catch (IOException e) {

	            }
	          }
	        };
		
	}

	private Camera getCameraInstance() {
		Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.savemenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		
		case android.R.id.home:
			
			finish();
	        return true;
	        
    	case R.id.saveButton:
    		
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
	
}
