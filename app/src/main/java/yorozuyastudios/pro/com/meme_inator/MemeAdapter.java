package yorozuyastudios.pro.com.meme_inator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.MyViewHolder> {

    private List<Meme> list_item ;
    public Context mcontext;



    public MemeAdapter(List<Meme> list, Context context) {

        list_item = list;
        mcontext = context;
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @Override
    public MemeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a layout
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_layout, null);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position ) {

        final Meme obj=list_item.get(position);
        viewHolder.meme_name.setText(obj.getMemeName());
        viewHolder.meme_icon.setImageResource(obj.getImgResId());

    }

    // initializes textview in this class
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView meme_name;
        public ImageView meme_icon;

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            meme_name = (TextView) itemLayoutView.findViewById(R.id.meme_name);
            meme_icon = (ImageView) itemLayoutView.findViewById(R.id.meme_icon);

        }
    }

    //Returns the total number of items in the data set hold by the adapter.
    @Override
    public int getItemCount() {
        return list_item.size();
    }

}