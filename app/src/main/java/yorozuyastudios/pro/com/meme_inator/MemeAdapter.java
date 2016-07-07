package yorozuyastudios.pro.com.meme_inator;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MemeAdapter extends ArrayAdapter<Meme>{

    public MemeAdapter(Activity context, ArrayList<Meme> MemeList) {
        //Constructor to initialise the adapter with the context and List of memes
        super(context, 0, MemeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.row_layout, parent, false);
        }


        Meme currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.meme_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView


        nameTextView.setText(currentAndroidFlavor.getMemeName());


        // Find the ImageView in the row_layout.xml layout with the ID meme_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.meme_icon);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        iconView.setImageResource(currentAndroidFlavor.getImgResId());

        // Return the whole list item layout (containing 1 TextView and an ImageView)

        return listItemView;
    }


}
