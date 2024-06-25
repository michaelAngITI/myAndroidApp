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
import java.util.Objects;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder> {

//    private final String[] titles = {"Member One", "Member Two", "Member Three", "Member Four"};
//    private final String[] details = {"Member one details", "Member two details", "Member three details",
//            "Member four details"};
//    private final int[] images = { R.drawable.girl,R.drawable.man,R.drawable.boy, R.drawable.woman};

    private List<Family> familyList;
    private Context context;

    public FamilyAdapter(Context context){
        this.context = context;
        loadFamilyFromDatabase();
    }
    private void loadFamilyFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        familyList = dbHelper.getAllMembers();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemRole;

        public ViewHolder(View itemView, Context context, List<Family> familyList) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemRole = itemView.findViewById(R.id.item_role);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Family family = familyList.get(position);
                    Intent intent = new Intent(context, ShowMemberActivity.class);
                    intent.putExtra(ShowMemberActivity.EXTRA_MEMBER_ID, family.getID());
                    context.startActivity(intent);
                }
            });
        }
    }
    //Methods that must be implemented for a RecyclerView.Adapter
    @NonNull
    @Override
    public FamilyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_card_layout, parent, false);
        return new FamilyAdapter.ViewHolder(v,context, familyList);
    }

    @Override
    public void onBindViewHolder(FamilyAdapter.ViewHolder holder, int position) {

        Family family = familyList.get(position);
        int drawableId;
        if (Objects.equals(family.getImg(), "boy")){
            drawableId = R.drawable.boy;
        }else if (Objects.equals(family.getImg(), "girl")){
            drawableId = R.drawable.girl;
        }else if (Objects.equals(family.getImg(), "man")){
            drawableId = R.drawable.man;
        }else if (Objects.equals(family.getImg(), "woman")){
            drawableId = R.drawable.woman;
        }else{
            drawableId = R.drawable.ic_add_a_photo;
        }


        holder.itemImage.setImageResource(drawableId);
        holder.itemName.setText(family.getName());
        holder.itemRole.setText(family.getRole());
    }

    @Override
    public int getItemCount() {
        return familyList.size();
    }
}
