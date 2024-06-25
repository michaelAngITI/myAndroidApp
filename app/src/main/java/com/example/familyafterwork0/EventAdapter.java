package com.example.familyafterwork0;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Event> eventList;
    private Context context;

    public EventAdapter(Context context) {
        this.context = context;
        loadEventsFromDatabase();
    }
    private void loadEventsFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        eventList = dbHelper.getAllEvents();
    }


    //Class that holds the items to be displayed (Views in card_layout)
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemDate;
        TextView itemTime;
        TextView itemName;
        TextView itemKid;
        TextView itemTitle;
        TextView itemDetail;

        public ViewHolder(View itemView, Context context, List<Event> eventList) {
            super(itemView);
            itemDate = itemView.findViewById(R.id.item_date);
            itemTime = itemView.findViewById(R.id.item_time);
            itemName = itemView.findViewById(R.id.item_name);
            itemKid = itemView.findViewById(R.id.item_kid);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Event event = eventList.get(position);
                    Intent intent = new Intent(context, ShowEventActivity.class);
                    intent.putExtra(ShowEventActivity.EXTRA_EVENT_ID, event.getID());
                    context.startActivity(intent);
                }
            });
        }
    }

    //Methods that must be implemented for a RecyclerView.Adapter
    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card_layout, parent, false);
        return new ViewHolder(v,context, eventList);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
//        holder.itemTitle.setText(titles[position]);
//        holder.itemDetail.setText(details[position]);
//        holder.itemImage.setImageResource(images[position]);
        Event event = eventList.get(position);
        holder.itemDate.setText(event.getDate());
        holder.itemTime.setText(event.getTime());
        holder.itemName.setText(event.getMember());
        holder.itemKid.setText(event.getKid());
        holder.itemTitle.setText(event.getTitle());
        holder.itemDetail.setText(event.getDescr());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
