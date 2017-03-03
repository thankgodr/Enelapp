package com.shixels.thankgodrichard.enellottoapp.Games;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shixels.thankgodrichard.enellottoapp.Constant;
import com.shixels.thankgodrichard.enellottoapp.MainActivity;
import com.shixels.thankgodrichard.enellottoapp.R;

import org.apmem.tools.layouts.FlowLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fivestar extends Fragment {
    TextView choosText;
    Constant constant = Constant.getInstance();
    FlowLayout flexboxLayout;
    int count = 0;
    LinearLayout chooseHolder;
    RelativeLayout spinner, main;

    public Fivestar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fivestar, container, false);
        choosText = (TextView) view.findViewById(R.id.choseText);
        ((MainActivity)getContext()).changeBack(new Gametype());
        //Set Fonts
        choosText.setTypeface(constant.font(6,getContext()));
        chooseHolder = (LinearLayout) view.findViewById(R.id.choseHolder);
        flexboxLayout = (FlowLayout) view.findViewById(R.id.selecteNum);
        main = (RelativeLayout) view.findViewById(R.id.main);
        spinner = (RelativeLayout) view.findViewById(R.id.spinner);
        ImageView titleImageView = new ImageView(getContext());
        titleImageView.setImageResource(R.drawable.fivestrtop);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60,31);
        titleImageView.setLayoutParams(params);
        ((MainActivity)getContext()).configTitleUp(titleImageView,null);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        main.setVisibility(View.VISIBLE);

    }

}
