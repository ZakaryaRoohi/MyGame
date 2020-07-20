package com.example.mygame.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygame.R;

public class TicTocToeFragment extends Fragment {
    private TextView mTextView;

    public TicTocToeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            Toast.makeText(getActivity(),"first run",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getActivity(),"second run",Toast.LENGTH_LONG).show();
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

    }
}