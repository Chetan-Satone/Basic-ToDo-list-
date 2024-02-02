package com.example.basictodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<String> mToDoListItems;

    public ListAdapter(Context context, ArrayList<String> ToDoList){   //this constructor used to instantiate a new listadapter
        mContext = context;                                            //object, it takes a context and an arraylist of stringas paramters
        mToDoListItems = ToDoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        return new ListAdapter.ViewHolder(view);
        // this method inflates the layout for each item fo the recycelr view. it creates a new viewholder that holds the view
        // for each list item
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String currentItem = mToDoListItems.get(position);

        holder.listItem.setText(currentItem);

        holder.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mToDoListItems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mToDoListItems.size());

                FileHelper.writeData(mToDoListItems, mContext);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mToDoListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView listItem;
        public ImageButton doneButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listItem = itemView.findViewById(R.id.txtList_item_String);
            doneButton = itemView.findViewById(R.id.doneButton);
        }
    }
}
