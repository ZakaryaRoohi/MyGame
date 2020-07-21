package com.example.mygame.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygame.R;

public class TicTocToeFragment extends Fragment implements View.OnClickListener {
    public static final String EXTRA_BUNDLE_ROUND_COUNT = "roundCount";
    public static final String EXTRA_BUNDLE_PLAYER_1_POINTS = "extraBundlePlayer1Points";
    public static final String EXTRA_BUNDLE_PLAYER_2_POINTS = "extraBundlePlayer2Points";
    public static final String EXTRA_BUNDLE_PLAYER_1_TURN = "extraBundlePlayer1Turn";
    private Button[][] mButtons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;
    private TextView mTextViewPlayer1;
    private TextView mTextViewPlayer2;
    private Button mButtonReset;

    public TicTocToeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            Toast.makeText(getActivity(), "first run", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getActivity(), "second run", Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_toc_toe, container, false);
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        mTextViewPlayer1 = view.findViewById(R.id.text_view_player1);
        mTextViewPlayer2 = view.findViewById(R.id.text_view_player2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                mButtons[i][j] = view.findViewById(resID);
                mButtons[i][j].setOnClickListener(TicTocToeFragment.this);
            }

        }
        mButtonReset = view.findViewById(R.id.button_reset);
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("o");
        }
        roundCount++;
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = mButtons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;

    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(getActivity(), "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(getActivity(), "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(getActivity(), "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();

    }

    private void updatePointsText() {
        mTextViewPlayer1.setText("Player 1: " + player1Points);
        mTextViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                mButtons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;
    }
    private void resetGame(){
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_BUNDLE_ROUND_COUNT,roundCount);
        outState.putInt(EXTRA_BUNDLE_PLAYER_1_POINTS,player1Points);
        outState.putInt(EXTRA_BUNDLE_PLAYER_2_POINTS,player2Points);
        outState.putBoolean(EXTRA_BUNDLE_PLAYER_1_TURN,player1Turn);
    }
//
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        roundCount= savedInstanceState.getInt(EXTRA_BUNDLE_ROUND_COUNT);
//        player1Points=savedInstanceState.getInt(EXTRA_BUNDLE_PLAYER_1_POINTS);
//        player2Points=savedInstanceState.getInt(EXTRA_BUNDLE_PLAYER_2_POINTS);
//        player1Turn=savedInstanceState.getBoolean(EXTRA_BUNDLE_PLAYER_1_TURN);
//    }
}