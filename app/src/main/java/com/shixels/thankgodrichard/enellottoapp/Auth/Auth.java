package com.shixels.thankgodrichard.enellottoapp.Auth;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.quickblox.users.model.QBUser;
import com.shixels.thankgodrichard.enellottoapp.CallBackFunc;
import com.shixels.thankgodrichard.enellottoapp.Constant;
import com.shixels.thankgodrichard.enellottoapp.DialogOver;
import com.shixels.thankgodrichard.enellottoapp.MainActivity;
import com.shixels.thankgodrichard.enellottoapp.R;

public class Auth extends AppCompatActivity implements View.OnClickListener {

    EditText username,password;
    TextView forgotPasswor, donthave, register,ortext;
    Button signinText;
    ImageView facebookLogin;
    LinearLayout loginConatainer, forms;


    Constant constant = Constant.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_auth);
        //Assign all views to thier Ids
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        forgotPasswor = (TextView) findViewById(R.id.forgotpass);
        signinText = (Button) findViewById(R.id.signinText);
        facebookLogin = (ImageView) findViewById(R.id.facebooksignin);
        donthave = (TextView) findViewById(R.id.donthave);
        register = (TextView) findViewById(R.id.register);
        ortext = (TextView) findViewById(R.id.orText);
        forms = (LinearLayout) findViewById(R.id.forms);
        loginConatainer = (LinearLayout) findViewById(R.id.loginContainer);

        //Set fonts accordingly
        forgotPasswor.setTypeface(constant.font(7,this));
        username.setTypeface(constant.font(0,this));
        password.setTypeface(constant.font(0,this));
        signinText.setTypeface(constant.font(35,this));
        ortext.setTypeface(constant.font(35,this));
        donthave.setTypeface(constant.font(0,this));
        register.setTypeface(constant.font(7,this));

        //Set OnclickListerner
        signinText.setOnClickListener(this);
        register.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.signinText:
                if(constant.checkFieldsRequired(username) && constant.checkFieldsRequired(password)) {
                    constant.openDialog(this, R.layout.lodiag, new DialogOver() {
                        @Override
                        public void customiseDialogView(final Dialog dialog) {
                            dialog.setCancelable(false);
                            dialog.show();
                            String usname = username.getText().toString();
                            String passwd = password.getText().toString();
                            String[] login = new String[2];
                            login[0] = usname;
                            login[1] = passwd;
                            constant.SignIn(login,Auth.this, new CallBackFunc() {
                                @Override
                                public void success(QBUser user) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Auth.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }

                                @Override
                                public void error(String error) {
                                    //Todo fix error message
                                    dialog.dismiss();
                                    Toast.makeText(Auth.this,error,Toast.LENGTH_LONG).show();


                                }
                            });

                        }
                    });

                }
                break;
            case R.id.register:
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.mainContainer);
                relativeLayout.removeAllViews();
                Fragment fragment = new Register();
                openFragment(fragment,R.id.mainContainer,"Register");
                break;


        }
    }
    public void openFragment(Fragment frag, int containerId, String stack){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(stack);
        ft.replace(containerId,frag);
        ft.commit();
    }
}
