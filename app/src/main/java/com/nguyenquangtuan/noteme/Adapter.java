package com.nguyenquangtuan.noteme;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nguyenquangtuan.noteme.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Note> notes;

    Adapter(Context context, List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_list_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = notes.get(i).getTitle();

        long id = notes.get(i).getId();
//        Log.d("date on ", "Date on: " + date);

        viewHolder.nTitle.setText(title);
        viewHolder.nDate.setText(notes.get(i).getCreatedDate());
        viewHolder.nTime.setText(notes.get(i).getCreatedTime());
        viewHolder.nDateModified.setText(notes.get(i).getModifiedDate());
        viewHolder.nTimeModified.setText(notes.get(i).getModifiedTime());
        viewHolder.nID.setText(String.valueOf(notes.get(i).getId()));
        viewHolder.tv_hashtag.setText(notes.get(i).getSubject());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nTitle, nDate, nTime, nID,nDateModified,nTimeModified,tv_hashtag;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);
            nID = itemView.findViewById(R.id.listId);
            tv_hashtag = itemView.findViewById(R.id.tv_hashtag);
            nDateModified = itemView.findViewById(R.id.nDateModified);
            nTimeModified = itemView.findViewById(R.id.nTimeModified);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Detail.class);
                    i.putExtra("ID", notes.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(i);
                    Log.i("MYMESSAGE",notes.get(getAdapterPosition()).toString());
                }
            });
        }
    }
}
