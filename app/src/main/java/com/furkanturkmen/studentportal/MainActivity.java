package com.furkanturkmen.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PortalAdapter portalAdapter;
    private RecyclerView recyclerView;
    private List<Portal> portalList;
    private PortalAdapter.PortalClickListener portalClickListener;


    public static final String EXTRA_PORTAL = "Portal";
    public static final int REQUESTCODE = 1905;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Student portals");

        portalList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        portalClickListener = new PortalAdapter.PortalClickListener() {
            @Override
            public void PortalOnClick(int i) {
                portalOnClick(i);
            }
        };


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }


                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position

                        int position = (viewHolder.getAdapterPosition());
                        portalList.remove(position);
                        portalAdapter.notifyItemRemoved(position);
                    }
                };


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        updateUI();
    }


    private void updateUI() {
        if (portalAdapter == null) {
            portalAdapter = new PortalAdapter(portalList,portalClickListener);
            recyclerView.setAdapter(portalAdapter);
        }
            portalAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void portalOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, PortalActivity.class);
        intent.putExtra(EXTRA_PORTAL, portalList.get(i));
        startActivityForResult(intent, REQUESTCODE);
    }
    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("Its returning!");
        if (requestCode == REQUESTCODE) {

            if (resultCode == RESULT_OK) {
                System.out.println("Before UpdatedPortal");
                Portal updatedPortal = data.getParcelableExtra(MainActivity.EXTRA_PORTAL);
                System.out.println("After UpdatedPortal : " + updatedPortal.toString());

                // New timestamp: timestamp of update
                portalList.add(updatedPortal);
                System.out.println("Before updated list");
                updateUI();
                System.out.println("Before updated list" + " " + portalList.toString());
            }
        }
    }
}
