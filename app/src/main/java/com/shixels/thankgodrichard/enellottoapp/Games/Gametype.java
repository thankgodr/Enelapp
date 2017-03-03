package com.shixels.thankgodrichard.enellottoapp.Games;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shixels.thankgodrichard.enellottoapp.Constant;
import com.shixels.thankgodrichard.enellottoapp.MainActivity;
import com.shixels.thankgodrichard.enellottoapp.R;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class Gametype extends Fragment implements View.OnClickListener {
    Constant constant = Constant.getInstance();
    RelativeLayout threesStar, fiveStar, sixStar;
    int closeCount;

    public Gametype() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gametype, container, false);
        threesStar = (RelativeLayout) view.findViewById(R.id.threestar);
        fiveStar = (RelativeLayout) view.findViewById(R.id.fivestar);
        sixStar = (RelativeLayout) view.findViewById(R.id.sixstar);

        //Set listerner on Button
        threesStar.setOnClickListener(this);
        fiveStar.setOnClickListener(this);
        sixStar.setOnClickListener(this);

        //Set the Title
        TextView title = new TextView(getContext());
        title.setText("Games"); title.setTypeface(constant.font(5,getContext())); title.setTextColor(Color.parseColor("#ffffff"));
        title.setTextSize(21);
        ((MainActivity)getContext()).configTitleUp(null,title);

        //Display the view
        return  view;
    }


    // All on click listener
    @Override
    public void onClick(final View view) {
        int id = view.getId();
        switch (id){
            case R.id.threestar:
                ((MainActivity)getContext()).openFragment(new Threestar(),R.id.mainContainer,"threestar");
                break;
            case R.id.fivestar:
                ((MainActivity)getContext()).openFragment(new Fivestar(),R.id.mainContainer,"threestar");
                break;
            case R.id.sixstar:
                ((MainActivity)getContext()).openFragment(new Sixstar(),R.id.mainContainer,"threestar");
                break;
            default:
                //do nothing
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getContext()).resetMenu();
        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (closeCount != 2) {
                        closeCount++;
                        Toast.makeText(getActivity(), "Press back  2x to close the app", Toast.LENGTH_SHORT).show();
                        return true;
                    } else {
                        int pid = android.os.Process.myPid();
                        android.os.Process.killProcess(pid);
                        System.exit(0);
                        return true;

                    }
                }
                return false;
            }
        });
    }
}
