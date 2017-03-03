package com.shixels.thankgodrichard.enellottoapp.Payment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shixels.thankgodrichard.enellottoapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Withdraw extends Fragment {


    public Withdraw() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_withdraw, container, false);
    }

}
