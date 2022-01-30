package com.example.databaseapp.ui;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseapp.PetDbHelper;
import com.example.databaseapp.R;
import com.example.databaseapp.pet;

import org.jetbrains.annotations.Contract;
import com.example.databaseapp.ui.petContract.petEntry;
import com.example.databaseapp.ui.petContract;
import java.util.ArrayList;

public class petAdapter extends RecyclerView.Adapter<petAdapter.ViewHolder> {

    private final Context context;
    private Cursor mCursor;


    public petAdapter(Context context){
        this.context = context;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //pet list 2 inflate
        TextView name , breed , weight , gender , price;
        Button edit , delete;


        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.textView4);
            breed = (TextView) v.findViewById(R.id.textView5);
            weight = (TextView) v.findViewById(R.id.textView6);
            gender = (TextView) v.findViewById(R.id.textView8);
          //  price = (TextView) v.findViewById(R.id.petListPrice);

            edit = (Button) v.findViewById(R.id.petListEdit);
            delete = (Button) v.findViewById(R.id.petListDelete);

        }
    }



    @NonNull
    @Override
    public petAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pet_list3 , parent , false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull petAdapter.ViewHolder holder, int position) {

        // Find the columns of pet attributes that we're interested in
         int nameColumnIndex = mCursor.getColumnIndex(petEntry.COLUMN_PET_NAME);
         int breedColumnIndex = mCursor.getColumnIndex(petEntry.COLUMN_PET_BREED);
         int WeightColumnIndex = mCursor.getColumnIndex(petEntry.COLUMN_PET_WEIGHT);
         int genderColumnIndex = mCursor.getColumnIndex(petEntry.COLUMN_PET_GENDER);

        mCursor.moveToPosition(position);

        String petName = mCursor.getString(nameColumnIndex);
        String petBreed = mCursor.getString(breedColumnIndex);
        int petWeight = mCursor.getInt(WeightColumnIndex);
        int petGender = mCursor.getInt(genderColumnIndex);

        // If the pet breed is empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.
        if (TextUtils.isEmpty(petBreed)) {
            petBreed = context.getString(R.string.unknown_breed);
        }


        holder.name.setText(petName);
        holder.breed.setText(petBreed);
        holder.weight.setText(petWeight+"");
        switch (petGender){
            case (0) :
                holder.gender.setText("Unknown"+"");
                break;

            case (1) :
                holder.gender.setText("Male"+"");
                break;

            case (2) :
                holder.gender.setText("Female"+"");
                break;

        }



        //Add functionality to edit button
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Add functionality to edit button
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }


    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }


    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    //Create method for edit and delete buttons


}