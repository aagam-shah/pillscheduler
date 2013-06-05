package star.pillscheduler;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
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

	
	
	

}
