package com.example.oceancheung.deltahacksdemoworkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
// LOLOLOLOLOLOLOLOLOLOLOLOL
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> items = Arrays.asList("btc_cad", "eth_cad", "ltc_cad");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Is it me you are looking for
        setContentView(R.layout.activity_list_view);
        setupListView();
    }

    private void setupListView() {
        listView = findViewById(R.id.listView);

        // TODO: Absolutely nothing
        ArrayList<String>data = new ArrayList<>(items);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ListViewActivity.this, PriceActivity.class);
                intent.putExtra("crypto", items.get(position));
                startActivity(intent);
            }
        });
    }
}
