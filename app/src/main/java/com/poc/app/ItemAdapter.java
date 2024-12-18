package com.poc.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> dataList;
    private Context context;

    public ItemAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public static final int VIEW_TYPE_INCOMING = 0;
    public static final int VIEW_TYPE_OUTGOING = 1;

    @Override
    public int getItemViewType(int position) {
        return (position%2==0)?VIEW_TYPE_INCOMING:VIEW_TYPE_OUTGOING;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_incoming, parent, false);
//        return new ViewHolder(view);
        View view;
        switch (viewType) {
            case VIEW_TYPE_INCOMING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_incoming, parent, false);
                return new ViewHolderIncoming(view);
            case VIEW_TYPE_OUTGOING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_outgoing, parent, false);
                return new ViewHolderOutgoing(view);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

     @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String item = dataList.get(position);
        if(getItemViewType(position)==VIEW_TYPE_INCOMING){
            ViewHolderIncoming viewHolderIncoming = (ViewHolderIncoming) holder;
            viewHolderIncoming.textView.setText(item);
        }else{
            ViewHolderOutgoing viewHolderOutgoing = (ViewHolderOutgoing) holder;
            viewHolderOutgoing.textView.setText(item);
        }
    }

//    private LinearLayout.LayoutParams getLayoutParams(boolean isRightAligned) {
//        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.7);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//        if (isRightAligned) {
//            params.setMargins(0, 0, 16, 16); // Margin to the right
//        } else {
//            params.setMargins(16, 0, 0, 16); // Margin to the left
//        }
//        return params;
//    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolderIncoming extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolderIncoming(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    public static class ViewHolderOutgoing extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolderOutgoing(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
