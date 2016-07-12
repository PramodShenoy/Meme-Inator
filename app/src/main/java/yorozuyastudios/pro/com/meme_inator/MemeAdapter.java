package yorozuyastudios.pro.com.meme_inator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.MyViewHolder> {

    private List<Meme> memeList2=null;
    private ArrayList<Meme> arraylist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView meme_name;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            meme_name = (TextView) view.findViewById(R.id.meme_name);
            image=(ImageView) view.findViewById(R.id.meme_icon);


        }
    }


    public MemeAdapter(List<Meme> memeList) {
        this.memeList2 = memeList;
        this.arraylist = new ArrayList<Meme>();
        this.arraylist.addAll(memeList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Meme meme = memeList2.get(position);
        holder.meme_name.setText(meme.getMemeName());
        holder.image.setImageResource(meme.getImgResId());
    }

    @Override
    public int getItemCount() {
        return memeList2.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        memeList2.clear();
        if (charText.length() == 0) {
            memeList2.addAll(arraylist);
        } else {
            for (Meme wp : arraylist) {
                if (wp.getMemeName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    memeList2.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}