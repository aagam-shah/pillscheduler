package star.pillscheduler;

import java.io.File;
import java.net.URI;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class PillListDBAdapter extends SimpleCursorAdapter{

	public Context ctx;
	public int layoutid;
	public Cursor curs;
	public String[] from;
	public int[] to;
	public LayoutInflater inflater;
	public PillListDBAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		this.ctx=context;
		this.layoutid=layout;
		this.curs=c;
		this.from=from;
		this.to=to;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return curs.getCount();
	}



	@Override
	public Cursor getCursor() {
		// TODO Auto-generated method stub
		return curs;
	}


	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		super.bindView(view, context, cursor);
	}
	@Override
	public void setViewImage(ImageView v, String value) {
		File imgFile = new  File(value);
		if(imgFile.exists()){
			BitmapFactory.Options options=new BitmapFactory.Options();
			options.inSampleSize = 8;
			//Bitmap preview_bitmap=BitmapFactory.decodeStream(is,null,options);
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
		    v.setImageBitmap(myBitmap);

		}
		else{
			Log.e("ad", "doesnt exist"+value);
		}
		Log.e("ad", "asdc"+value);
		//v.setImageURI(my);
	}


}
