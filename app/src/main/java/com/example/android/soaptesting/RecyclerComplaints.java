package com.example.android.soaptesting;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;



import java.util.ArrayList;



public class RecyclerComplaints extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> id = new ArrayList<>();

    ArrayList<String> title = new ArrayList<>();

    ArrayList<String> description = new ArrayList<>();


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_layout);



        id.clear();

        title.clear();

        description.clear();



        id.add("1");

        title.add("Case 1");

        description.add("Some Small Description");



        id.add("2");

        title.add("TechRead");

        description.add("Read, Enjoy And Discover technology news & information");



        id.add("3");

        title.add("Techlines");

        description.add("Techlines brings you Tech News in one line. Its grabs news from various news websites around the world.");



        id.add("4");

        title.add("Hackers Dictionary");

        description.add("Dictionary for Hackers. Anyone can contribute. It’s for Hackers, by Hackers!");



        id.add("5");

        title.add("CyberChoco");

        description.add("Cyberchoco is an upcoming release, Which is an Online Virtual Lab for Security Professionals.");



        id.add("6");

        title.add("Tutorials Now");

        description.add("An Aroliant Training Initiative, android development, web design video tutorials in Tamil & English");



        recyclerView = findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter(getApplicationContext(), id, title, description);

        recyclerView.setAdapter(mAdapter);

    }

}
