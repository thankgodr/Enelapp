package com.shixels.thankgodrichard.enellottoapp.Auth;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.Circle;
import com.quickblox.users.model.QBUser;
import com.shixels.thankgodrichard.enellottoapp.CallBackFunc;
import com.shixels.thankgodrichard.enellottoapp.Constant;
import com.shixels.thankgodrichard.enellottoapp.DialogOver;
import com.shixels.thankgodrichard.enellottoapp.MainActivity;
import com.shixels.thankgodrichard.enellottoapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {


    public Register() {
        // Required empty public constructor
    }
    EditText fullname, username,password,phone,pin;
    Button signup;
    TextView already, login;

    Constant constant = Constant.getInstance();


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        fullname = (EditText) view.findViewById(R.id.fullname);
        username = (EditText) view.findViewById(R.id.userName);
        password = (EditText) view.findViewById(R.id.password);
        phone = (EditText) view.findViewById(R.id.phone);
        pin = (EditText) view.findViewById(R.id.pin);
        already = (TextView) view.findViewById(R.id.alreadyHave);
        login = (TextView) view.findViewById(R.id.login);
        signup = (Button) view.findViewById(R.id.signup);

        //Set Fonts
        fullname.setTypeface(constant.font(0,getContext()));
        username.setTypeface(constant.font(0,getContext()));
        password.setTypeface(constant.font(0,getContext()));
        phone.setTypeface(constant.font(0,getContext()));
        pin.setTypeface(constant.font(0,getContext()));
        login.setTypeface(constant.font(4,getContext()));
        already.setTypeface(constant.font(0,getContext()));
        signup.setTypeface(constant.font(7,getContext()));

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(constant.checkFieldsRequired(fullname) && constant.checkFieldsRequired(username) && constant.checkFieldsRequired(password)
                    && constant.checkFieldsRequired(phone) && constant.checkFieldsRequired(pin)){
                constant.openDialog(getContext(), 0, new DialogOver() {
                    @Override
                    public void customiseDialogView(final Dialog dialog1) {
                        final ProgressBar progressBar = new ProgressBar(getContext());
                        Circle circle = new Circle();
                        progressBar.setIndeterminateDrawable(circle);
                        final TextView errorMessage = new TextView(getContext());
                        dialog1.setContentView(progressBar);
                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog1.show();
                        dialog1.setCancelable(false);
                        String[] details = new String[]{username.getText().toString(),password.getText().toString(),fullname.getText().toString(),phone.getText().toString(),pin.getText().toString()};
                        constant.registeruser(details, getContext(), new CallBackFunc() {
                            @Override
                            public void success(QBUser user) {
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }

                            @Override
                            public void error(final String error) {
                                //Todo fix error message
                                errorMessage.setText(error);
                                dialog1.setContentView(errorMessage);
                                progressBar.setVisibility(View.GONE);

                            }
                        });
                    }
                });

            }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    Intent intent = new Intent(getContext(),Auth.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                return false;
            }
        });
    }


}
