package com.example.fetch_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<List<ListItem>> groupedItems;

    public ItemAdapter(List<List<ListItem>> groupedItems) {
        this.groupedItems = groupedItems;
    }

    public void setGroupedItems(List<List<ListItem>> groupedItems) {
        this.groupedItems = groupedItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind the group of items (each group belongs to a listId)
        List<ListItem> group = groupedItems.get(position);

        // Display the listId in one of the TextViews
        holder.listIdTextView.setText("List " + group.get(0).getListId());

        // Clear the previous dynamic TextViews
        holder.itemContainer.removeAllViews();

        // Add a TextView for each item in the group
        for (ListItem listItem : group) {
            TextView nameTextView = getTextView(holder, listItem);

            holder.itemContainer.addView(nameTextView);
        }
    }

    @NonNull
    private static TextView getTextView(@NonNull ViewHolder holder, ListItem listItem) {
        TextView nameTextView = new TextView(holder.itemView.getContext());
        nameTextView.setText("ID: " + listItem.getId() + "\nName: " + listItem.getName());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 8, 0, 0); // Adjust top margin for space between TextViews
        nameTextView.setLayoutParams(params);

        nameTextView.setBackgroundResource(R.drawable.rounded_background);
        return nameTextView;
    }

    @Override
    public int getItemCount() {
        return groupedItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView listIdTextView;
        public LinearLayout itemContainer;
        public ViewHolder(View itemView) {
            super(itemView);
            listIdTextView = itemView.findViewById(R.id.itemListId);
            itemContainer = itemView.findViewById(R.id.itemContainer);
        }
    }
}
