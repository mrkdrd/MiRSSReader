package com.example.mirko.mirssreader.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mirko.mirssreader.MainActivity;
import com.example.mirko.mirssreader.R;


class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{
    public TextView txtTitle;
    private ItemClickListener itemClickListener;

    public ListViewHolder(View itemView) {
        super(itemView);

        txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
public class ListAdapter extends RecyclerView.Adapter<ListViewHolder>{
    private RecyclerView recyclerView;
    private Context mContext;
    private LayoutInflater inflater;

    public ListAdapter(RecyclerView recyclerView, Context mContext) {
        this.recyclerView = recyclerView;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_recycler_item_view,parent,false);
        return new ListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        holder.txtTitle.setText(MainActivity.RSS_Name_List.get(position));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick)
                {
                    //TODO
                    Log.d("test", "onClick: sucks");
                    MainActivity.load(recyclerView, MainActivity.RSS_Link_List.get(position),mContext);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return MainActivity.RSS_Name_List.size();
    }
}
