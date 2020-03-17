package com.expresspay.access_control;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.expresspay.access_control.models.GuestCheckedInData;
import com.expresspay.access_control.models.GuestData;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.ViewHolder> {
    //define the list(guestData)
    private List<GuestCheckedInData> guestDataList;
    private Context context;

    private int[] arrayColor = {R.color.violet, R.color.lemon, R.color.brown, R.color.light_green, R.color.light_blue, R.color.blue, R.color.deep_green, R.color.orange};

    //constructor to get the list's objects and context
    public GuestAdapter(List<GuestCheckedInData> guestDataList, Context context) {
        this.guestDataList = guestDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind the data to the view (Recycler_item.xml) objects
        holder.bindView(position);
    }

    @Override
    //the size of the guestData items
    public int getItemCount() {
        return guestDataList.size();
    }

    public void update(List<GuestCheckedInData> guestDataList) {
        this.guestDataList = guestDataList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //define the view objects
        private TextView textViewFullName;
        private TextView textViewCheckedTime;
        private MaterialLetterIcon letterIcon;
        private ImageView imageViewUser_x;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //reference the objects
            textViewFullName = itemView.findViewById(R.id.fullName_tv);
            textViewCheckedTime = itemView.findViewById(R.id.checked_time_tv);
            letterIcon = itemView.findViewById(R.id.letterIcon);
            imageViewUser_x = itemView.findViewById(R.id.user_x);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item selected", Toast.LENGTH_SHORT).show();
                }
            });
        }

        void bindView(int position) {
            //reference the GuestData
            GuestCheckedInData guestCheckedInData = guestDataList.get(position);

            // gather current items from guestDataList
            textViewFullName.setText(guestCheckedInData.getVisitorName());
            if (guestCheckedInData.isCheckedOut()) {
                textViewCheckedTime.setText(guestCheckedInData.getCheckedOutTime());
                //set the drawable to user icon
                imageViewUser_x.setImageResource(R.drawable.ic_guest_check_in_icon);
            } else {
                textViewCheckedTime.setText(guestCheckedInData.getCheckedInTime());
                imageViewUser_x.setImageResource(R.drawable.ic_user_x);
            }
            // letter icon
            letterIcon.setLetter(guestCheckedInData.getVisitorName());

            letterIcon.setLettersNumber(1);
            letterIcon.setInitials(true);
            letterIcon.setInitialsNumber(2);

            //setting random colors to letterIcon shape color
            Random random = new Random();
            int r = random.nextInt(7);
//            Log.e("random", r + "" + arrayColor[r]);
            //   Color color = arrayColor[];
            letterIcon.setShapeColor(context.getResources().getColor(arrayColor[r]));
        }
    }


}
