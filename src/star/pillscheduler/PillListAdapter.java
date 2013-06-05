package star.pillscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class PillListAdapter extends BaseAdapter {


    public LayoutInflater inflater;
    public ArrayList<HashMap> list;
    public Activity ac;
    public Context ctx;

    public PillListAdapter(Activity a,Context ct,ArrayList<HashMap> l) {
        ac=a;
        ctx=ct;
        inflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list=l;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi=view;
        if(view==null)
            vi = inflater.inflate(R.layout.list_row, null);

        ImageView tv = (ImageView)vi.findViewById(R.id.pill_img);
        RelativeLayout pill_details = (RelativeLayout)vi.findViewById(R.id.pill_l);
        final View finalVi = vi;
        pill_details.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				 HorizontalScrollView hv = (HorizontalScrollView) finalVi.findViewById(R.id.scroll);
	                hv.setHorizontalScrollBarEnabled(false);
	               if(hv.getVisibility()==View.GONE)
	                hv.setVisibility(View.VISIBLE);
	               else
	            	   hv.setVisibility(View.GONE);
	               Log.e("clic", ":)");
	               return false;
			}
		});

       /* TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

        HashMap&lt;String, String&gt; song = new HashMap&lt;String, String&gt;();
        song = data.get(position);

        // Setting all values in listview
        title.setText(song.get(CustomizedListView.KEY_TITLE));
        artist.setText(song.get(CustomizedListView.KEY_ARTIST));
        duration.setText(song.get(CustomizedListView.KEY_DURATION));
        imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);*/
        return vi;
    }
}
