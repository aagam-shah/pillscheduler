package star.pillscheduler;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PillCamera extends SurfaceView implements SurfaceHolder.Callback {

	public SurfaceHolder hold;
	public Camera cam;

	public PillCamera(Context context, Camera c) {
		super(context);
		cam=c;
		hold = getHolder();
	    hold.addCallback(this);
	    hold.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
//		 Camera.Parameters parameters = cam.getParameters();
//		   parameters.set("orientation","portrait");
//		    cam.setParameters(parameters);
//		    cam.startPreview();
		

        if (holder.getSurface() == null){
          // preview surface does not exist
          return;
        }

        // stop preview before making changes
        try {
            cam.stopPreview();
        } catch (Exception e){
          // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            cam.setPreviewDisplay(holder);
            cam.startPreview();

        } catch (Exception e){
            Log.d("cam", "Error starting camera preview: " + e.getMessage());
        }
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, now tell the camera where to draw the preview.
        try {
            cam.setPreviewDisplay(holder);
            cam.startPreview();
        } catch (Exception e) {
            Log.d("cam create", "Error setting camera preview: " + e.getMessage());
        }
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		cam.stopPreview();
	    cam = null;
	}

	

}
