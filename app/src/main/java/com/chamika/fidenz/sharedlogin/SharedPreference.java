package com.chamika.fidenz.sharedlogin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by fidenz on 2/5/18.
 */

public class SharedPreference {

    public static final String PREFS_NAME = "AOP_NAME";
    public static final String PREFS_KEYName = "AOP_PREFS_String";

    public static final String PREFS_Password = "AOP_NAME";
    public static final String PREFS_KEYPassword = "AOP_PREFS_String";

    public static final String PREFS_Email = "AOP_NAME";
    public static final String PREFS_KEYemail = "AOP_PREFS_String";

    public static final String PREFS_logged = "AOP_NAME";
    public static final String PREFS_KEYlogged = "AOP_PREFS_String";


    public SharedPreference() {

        super();
    }

    public void save(Context context, String etName, String etPassword, String etEmail) {
        SharedPreferences name, password, email, logging;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        name = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        password = context.getSharedPreferences(PREFS_Password, Context.MODE_PRIVATE);
        email = context.getSharedPreferences(PREFS_Email, Context.MODE_PRIVATE);

        editor = name.edit();
        editor.putString(PREFS_KEYName, etName);
        editor = password.edit();
        editor.putString(PREFS_KEYemail, etPassword);
        editor = email.edit();
        editor.putString(PREFS_KEYPassword, etEmail);

        editor.commit();


    }

    public void iflogged(Context context, String key) {

        SharedPreferences keyvalue;

        SharedPreferences.Editor keyEditor;

        keyvalue = context.getSharedPreferences(PREFS_logged, Context.MODE_PRIVATE);

        keyEditor = keyvalue.edit();
        keyEditor.putString(PREFS_KEYlogged, key);
        keyEditor.commit();


    }

    public String getLogging(Context context) {
        SharedPreferences logging;
        String status;
        logging = context.getSharedPreferences(PREFS_logged, Context.MODE_PRIVATE); //1
        status = logging.getString(PREFS_KEYlogged, null); //2
        return status;
    }

    public String getValuename(Context context) {
        SharedPreferences name;
        String text;
        name = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        text = name.getString(PREFS_KEYName, null); //2
        return text;
    }

    public String getValueemail(Context context) {
        SharedPreferences email;
        String text;
        email = context.getSharedPreferences(PREFS_Email, Context.MODE_PRIVATE); //1
        text = email.getString(PREFS_KEYemail, null); //2
        return text;
    }

    public String getValuepassword(Context context) {
        SharedPreferences password;
        String text;
        password = context.getSharedPreferences(PREFS_Password, Context.MODE_PRIVATE); //1
        text = password.getString(PREFS_KEYPassword, null); //2
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_logged, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }





}
