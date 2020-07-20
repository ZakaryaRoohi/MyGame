package com.example.mygame.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mygame.R;
import com.example.mygame.fragment.FourInRowFragment;
import com.example.mygame.fragment.TicTocToeFragment;

public class GameActivity extends AppCompatActivity {

    private Button mButtonTicTocToe;
    private Button mButton4InRow;
    private Button mButtonSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViews();
        setClickListeners();


    }

    private void setClickListeners() {
        mButtonTicTocToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(R.id.fragment_container, new TicTocToeFragment())
                        .commit();


            }
        });
        mButton4InRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager= getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(R.id.fragment_container,new FourInRowFragment())
                        .commit();
            }
        });
    }

    private void findViews() {
        mButtonSetting = findViewById(R.id.button_setting);
        mButtonTicTocToe = findViewById(R.id.button_tic_tac_toe);
        mButton4InRow = findViewById(R.id.button_4_in_a_row);

    }

}