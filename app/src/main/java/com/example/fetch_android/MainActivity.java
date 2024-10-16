package com.example.fetch_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Set up RecyclerView
    RecyclerView recyclerView;

    // Initialize adapter
    ItemAdapter itemAdapter;
    TextView errorView;
    Button refreshButton;
    View imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        errorView = findViewById(R.id.errorView);
        recyclerView = findViewById(R.id.recyclerView);
        refreshButton = findViewById(R.id.refreshButton);
        imageView = findViewById(R.id.imageView);
        refreshButton.setOnClickListener(v -> refreshEvents());
        itemAdapter = new ItemAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        fetchData();
    }

    private void refreshEvents() {
        fetchData();
        imageView.setVisibility(View.INVISIBLE);
        refreshButton.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
    }
    private void fetchData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

        // Request a string response from the provided URL.
        @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"}) StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        // Parse the JSON array
                        JSONArray jsonArray = new JSONArray(response);
                        // Create a HashMap to group items by listId
                        HashMap<Integer, List<ListItem>> groupedItems = new HashMap<>();


                        // Loop through each JSON object and extract data
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            int listId = jsonObject.getInt("listId");
                            String name = jsonObject.getString("name");

                            // Filter out empty and null names
                            if (name.isEmpty() || name.equals("null")) {
                                continue;
                            }

                            ListItem item = new ListItem(id, listId, name);
                            // Build list for each listId
                            if (!groupedItems.containsKey(listId)) {
                                groupedItems.put(listId, new ArrayList<>());
                            }
                            // Create an Item object and add it to the list
                            groupedItems.get(listId).add(item);
                        }

                        // Convert the grouped map to a list of items for the adapter
                        List<List<ListItem>> groupedItemList = new ArrayList<>(groupedItems.values());
                        for (List<ListItem> group : groupedItemList) {
                            group.sort(Comparator.comparing(ListItem::getId));
                        }

                        // Pass the data to the RecyclerView adapter
                        itemAdapter.setGroupedItems(groupedItemList);
                        itemAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        errorView.setText("Something went wrong! Refer to: " + e.getMessage());
                    }
                }, error -> {
                    // Handle network errors
                    imageView.setVisibility(View.VISIBLE);
                    refreshButton.setVisibility(View.VISIBLE);
                    errorView.setVisibility(View.VISIBLE);
                    errorView.setText("Failed to load. You might not be connected to the Internet.");
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}