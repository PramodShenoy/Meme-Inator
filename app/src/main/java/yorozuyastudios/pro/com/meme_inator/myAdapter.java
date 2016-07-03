package yorozuyastudios.pro.com.meme_inator;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] names;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    public myAdapter(Activity context, String[] names) {
        super(context, R.layout.row_layout, names);
        this.context = context;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.row_layout, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.label);
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.icon);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        // Change the icon for Windows and iPhone
        String s = names[position];
        holder.text.setText(s);
       /* if (s.startsWith("Windows7") )
            holder.image.setImageResource(R.drawable.win);
        else if (s.startsWith("And"))
            holder.image.setImageResource(R.drawable.ic_launcher);
        else if(s.startsWith("App"))
            holder.image.setImageResource(R.drawable.apple);
        else if(s.startsWith("Black"))
            holder.image.setImageResource(R.drawable.blackberry);
        else if(s.startsWith("Lin"))
            holder.image.setImageResource(R.drawable.linux);
        else if (s.startsWith("Ubuntu"))
            holder.image.setImageResource(R.drawable.ubuntu);
        else
            holder.image.setImageResource(R.drawable.ic_launcher);*/


        return rowView;
    }
}