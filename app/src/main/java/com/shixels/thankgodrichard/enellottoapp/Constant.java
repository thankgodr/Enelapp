package com.shixels.thankgodrichard.enellottoapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.QBSettings;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import com.shixels.thankgodrichard.enellottoapp.Games.GenericFunction;

import org.apmem.tools.layouts.FlowLayout;

/**
 * Created by thankgodrichard on 1/9/17.
 */
public class Constant {
    static final String APP_ID = "50071";
    static final String AUTH_KEY = "urqaWJqEAREPAfB";
    static final String AUTH_SECRET = "duk8622kPqMXcfY";
    static final String ACCOUNT_KEY = "44L1JQ5GinG4ivqFHip5";
    public static String Pref = "com.shixels.thankgodrichard.enelLottoappcustomer";

    //private constructor
    private static Constant ourInstance = new Constant();

    //init this functions by calling Constant.getInstance
    public static Constant getInstance() {
        return ourInstance;
    }

    private Constant() {
    }

    //Font configurations
    public Typeface font(int i,Context c) {
         Typeface latolight = Typeface.createFromAsset(c.getAssets(), "fonts/latolight.ttf");
         Typeface latomid = Typeface.createFromAsset(c.getAssets(), "fonts/latomid.ttf");
         Typeface latoregular = Typeface.createFromAsset(c.getAssets(), "fonts/latoregular.ttf");
        Typeface semibold = Typeface.createFromAsset(c.getAssets(), "fonts/semibold.ttf");
        Typeface latobold = Typeface.createFromAsset(c.getAssets(), "fonts/latobold.ttf");
        Typeface myrabold = Typeface.createFromAsset(c.getAssets(), "fonts/myrabold.OTF");
        Typeface yuntiReg = Typeface.createFromAsset(c.getAssets(), "fonts/yuanti_regular.ttf");
        Typeface yuntiLight = Typeface.createFromAsset(c.getAssets(), "fonts/yuanti_light.ttf");
        Typeface yuntibold = Typeface.createFromAsset(c.getAssets(), "fonts/yuanti_bold.ttf");




        switch (i){
            case 1:
                return latolight;
            case 2:
                return latomid;
            case 3:
                return latoregular;
            case 4:
                return semibold;
            case 5:
                return  latobold;
            case 6:
                return myrabold;
            case 7:
                return  yuntibold;
            case 0:
                return yuntiLight;
            default:
                return yuntiReg;

        }
    }
    //Register users to users to quickblox
    public void registeruser(final String[] login, final Context c, final CallBackFunc callBackFunc){
        final  QBUser user = new QBUser(login[0],login[1]);
        QBSettings.getInstance().init(c, APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
        user.setFullName(login[2]);
        user.setPhone(login[3]);
        user.setCustomData(login[4]);
        QBAuth.createSession(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession session, Bundle params) {
                // success
                Log.i("sesion", "Created");
                QBUsers.signUp( user,new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser userz, Bundle args) {
                        // success
                        String[]  savelog = new String[2];
                        savelog[0] = login[0];
                        savelog[1] = login[1];
                        savePref(c,savelog,200);
                        callBackFunc.success(userz);
                    }

                    @Override
                    public void onError(QBResponseException error) {
                        // error
                        savePref(c,login,0);
                        callBackFunc.error(error.getMessage());
                    }
                });
            }

            @Override
            public void onError(QBResponseException error) {
                // errors
                Log.i("session", "No created");
                callBackFunc.error(error.getMessage());

            }
        });


    }

    //Signin users to quickblox
    public void SignIn(final String[] login, final Context c, final CallBackFunc callBackFunc){
        final  QBUser user = new QBUser(login[0],login[1]);
        QBSettings.getInstance().init(c, APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
        QBAuth.createSession(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession session, Bundle params) {
                // success
                Log.i("sesion", "Created");
                QBUsers.signIn( user,new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser user, Bundle args) {
                        // success
                        savePref(c,login,200);
                        callBackFunc.success(user);
                    }

                    @Override
                    public void onError(QBResponseException error) {
                        // error
                        savePref(c,login,0);
                        callBackFunc.error(error.getMessage());
                    }
                });
            }

            @Override
            public void onError(QBResponseException error) {
                // errors
                Log.i("session", "No created");
                callBackFunc.error(error.getMessage());
                //
            }
        });

    }

    //save detials to shared preference
    private void savePref(Context context , String[] login, int respondCode){
        SharedPreferences pref = context.getSharedPreferences(Pref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        if(respondCode == 200){
            String loginInfo = gson.toJson(login,String[].class);
            editor.putString("login",loginInfo);
            editor.commit();
        }
        else{
            editor.putString("login","");
            editor.commit();
        }
    }

     // ckeck the required forms  is filed
     public boolean checkFieldsRequired(EditText edittext){
         if (edittext.getText().toString().trim().equals("")) {
             edittext.setError("Required!");
             return false;
         }
         else {
             return true;
         }
    }

    //Customize dialog
    public void openDialog(Context c, int layout, DialogOver dialogover) {
        Dialog dialog = new Dialog(c);
        if(layout  != 0){
            dialog.setContentView(layout);
        }
        dialogover.customiseDialogView(dialog);
    }

    //Simple Dialog
    public void openAlert(final Context context, String message, String title){
       AlertDialog.Builder alertdialog = new AlertDialog.Builder(context) .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    public void generateNumberPan(final int max, final FlowLayout flexboxLayout, int drawableID, final Activity activity, int btnHeight, int btnWidth, final GenericFunction funtion){
        Drawable background = activity.getResources().getDrawable(drawableID);
        for(int i = 1; i <= max; i++ ){
            final Button btnAddARoom = new Button(activity, null, android.R.attr.buttonStyleSmall);
            btnAddARoom.setText(i + "");
            btnAddARoom.setHeight(btnHeight);
            btnAddARoom.setWidth(btnWidth);
            btnAddARoom.setBackgroundDrawable(background);
            btnAddARoom.setTypeface(font(4,flexboxLayout.getContext()));
            btnAddARoom.setTextColor(activity.getResources().getColor(R.color.chooseText));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60,60);
            params.rightMargin =16; params.bottomMargin = 16;
            btnAddARoom.setPadding(5,5,5,5);
            btnAddARoom.setGravity(Gravity.CENTER);
            btnAddARoom.setLayoutParams(params);
            btnAddARoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    funtion.generalFunction(btnAddARoom);

                }
            });
            flexboxLayout.addView(btnAddARoom);

        }

    }
    public void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }




}
