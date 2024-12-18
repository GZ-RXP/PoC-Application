package com.poc.app;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class TargetActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ItemAdapter adapter;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        // Initialize data list
        dataList = new ArrayList<>();
        populateData();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(this, dataList);
        recyclerView.setAdapter(adapter);

        // Set up SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("MESSAGE_KEY")) {
            String message = extras.getString("MESSAGE_KEY");
            Toast.makeText(this, "Pop from TargetActivity:"+message, Toast.LENGTH_LONG).show();
        }

        scrollToBottom();
    }

    private void populateData() {
        for (int i = 0; i < 200; i++) {
            dataList.add("Item " + (i + 1)+"\nMultiple lines\nMultiple lines and this is a very long line");
        }
    }

    private void refreshData() {
        // Simulate a network request or data update
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Clear existing data
                dataList.clear();

                // Add new data
                for (int i = 0; i < 20; i++) {
                    dataList.add("New Item " + (i + 1));
                }

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();

                // Stop the refreshing indicator
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000); // Simulate a delay of 2 seconds
    }

    private void scrollToBottom() {
        // Ensure the layout manager is set before scrolling
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .scrollToPositionWithOffset(dataList.size() - 1, 0);
        }
    }
}