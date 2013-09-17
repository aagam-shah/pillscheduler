package star.pillscheduler;

import java.io.File;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PillListDBAdapter extends SimpleCursorAdapter{

	@Override
	public void setViewBinder(ViewBinder viewBinder) {
		// TODO Auto-generated method stub
		super.setViewBinder(viewBinder);
	}
	public Context ctx;
	public int layoutid;
	public Cursor curs;
	public String[] from;
	public int[] to;
	public LayoutInflater inflater;
	public int targetWH;
	
	public PillListDBAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		this.ctx=context;
		this.layoutid=layout;
		this.curs=c;
		this.from=from;
		this.to=to;
		float scale = ctx.getResources().getDisplayMetrics().density;
		targetWH = (int) (60 * scale + 0.5f);
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
	public void bindView(final View view, final Context context, final Cursor cursor) {
		TextView name = (TextView)view.findViewById(R.id.list_row_title);
		TextView descr = (TextView)view.findViewById(R.id.list_row_descr);
		ImageView img = (ImageView)view.findViewById(R.id.pill_img);
		Button edit = (Button)view.findViewById(R.id.list_edit_pill);
		Button pillNo = (Button)view.findViewById(R.id.list_pills);
		Button delete = (Button)view.findViewById(R.id.list_delete);
		final RelativeLayout lay = (RelativeLayout)view.findViewById(R.id.wrapper_list);
		RelativeLayout img_text = (RelativeLayout)view.findViewById(R.id.pill_l);
		final HorizontalScrollView hsv = (HorizontalScrollView)view.findViewById(R.id.scroll);
		
		final int id = cursor.getInt(cursor.getColumnIndex("_id"));
		setImage(img,cursor.getString(cursor.getColumnIndex("pilloc")));
		descr.setText(cursor.getString(cursor.getColumnIndex(PillDB.descrDB)));
		name.setText(cursor.getString(cursor.getColumnIndex(PillDB.nameDB)));
		pillNo.setText(curs.getString(cursor.getColumnIndex("pillsleft")));
		if(Integer.parseInt(pillNo.getText().toString())<5)
			//lay.setBackgroundColor(Color.rgb(197, 226, 109));green
			lay.setBackgroundColor(Color.rgb(255, 148, 148));
		else if(Integer.parseInt(pillNo.getText().toString())<10)
			lay.setBackgroundColor(Color.rgb(255, 175, 175));//less red
		
		
		delete.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				AlertDialog ad = new AlertDialog.Builder(context).create();
				ad.setTitle("Confirm Delete");
				ad.setIcon(android.R.drawable.ic_delete);
				ad.setButton("Yes",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						PillDB.delete(id);
						Cursor c = getCursor();
						c.requery();
						
					}
				});
				
				ad.setButton2("Cancel",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(context, "canceled :)", Toast.LENGTH_SHORT).show();
						
					}
				});
				ad.show();
			}
		});
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "edited :)", Toast.LENGTH_SHORT).show();
				Intent i = new Intent("star.pillscheduler.EditOldPill");
				i.putExtra("id", id);
				ctx.startActivity(i);
				getCursor().requery();
				
			}
		});
		
		img_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			if(hsv.getVisibility()==View.GONE)
				hsv.setVisibility(View.VISIBLE);
			else
				hsv.setVisibility(View.GONE);
			}
		});
		
	}
	
	
	
	
	private void setImage(ImageView v, String value) {
		File imgFile = new  File(value);
		if(imgFile.exists()){
			BitmapFactory.Options options=new BitmapFactory.Options();
			options.inSampleSize = 8;
			//Bitmap preview_bitmap=BitmapFactory.decodeStream(is,null,options);
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
		    Bitmap targetBitmap = Bitmap.createBitmap(targetWH, 
                    targetWH,Bitmap.Config.ARGB_8888);

		    Canvas canvas = new Canvas(targetBitmap);
	    	Path path = new Path();
	    	path.addCircle(((float) targetWH - 1) / 2,
	    			((float) targetWH - 1) / 2,
	    			(Math.min(((float) targetWH), 
	    					((float) targetWH)) / 2),
	    							Path.Direction.CCW);

	    	canvas.clipPath(path);
	    	Bitmap sourceBitmap = myBitmap;
	    	canvas.drawBitmap(sourceBitmap, 
                        new Rect(0, 0, sourceBitmap.getWidth(),
                        		sourceBitmap.getHeight()), 
                        new Rect(0, 0, targetWH,
                        		targetWH), null);
		    v.setImageBitmap(sourceBitmap);

		}
		else{
			Log.e("ad", "doesnt exist"+value);
		}
		Log.e("ad", "asdc"+value);
		//v.setImageURI(my);
	}

	
	


}
