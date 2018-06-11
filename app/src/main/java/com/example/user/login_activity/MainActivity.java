package com.example.user.login_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    SqlHandler sql;
    private static EditText editText1, editText2, editText3;
    private static Button submit_btn;
    private static String username, college, emailid;
    private static boolean result;
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sql = new SqlHandler(this);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText3);
        editText3 = (EditText) findViewById(R.id.editText2);
        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBox);
        submit_btn = (Button) findViewById(R.id.button48);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();

            }
        });

        if(result == true){
            /**
             * code to start new activity
             * and to save the preference eg:name,college,emailid;
             * ----------->
             */
        }


    }

    //Code to validate Email
    public static boolean isValidEmailAddress(String emailid) {
        String emailRegEx;
        Pattern pattern;
        // Regex for a valid email address
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx);
        Matcher matcher = pattern.matcher(emailid);
        if (!matcher.find()) {
            editText3.setError("Invalid Email");
            return false;
        }
        return true;
    }
    //Code to validate Name

    public static boolean isnamevalid(String username){
        if(username.length()<10){
            editText1.setError("Register appropriate name");
            return false;
        }
        return true;
    }

    //Code to restrict College name

    public static boolean isCollegenamevalid(String college){
        if(college.length()<15){
            editText2.setError("Register appropriate college name");
            return false;
        }
        return true;
    }


    public void addData() {
        username = editText1.getText().toString();
        college = editText2.getText().toString();
        emailid = editText3.getText().toString();
        if (!(isnamevalid(username))) {
            editText1.setError("Register appropriate name");
            editText1.setText("");
            return;
        }
        if (!(isCollegenamevalid(college))) {
            editText2.setError("Register appropriate college name");
            editText2.setText("");
            return;
        }
        if (!(isValidEmailAddress(emailid))) {
            editText3.setError("Invalid Email");
            editText3.setText("");
            return;
        }
        if (checkBoxRememberMe.isChecked()) {
            sql.onInsert(username, college, emailid);
            saveLoginDetails(username,college,emailid);
            Toast.makeText(getApplicationContext()," Successfully Registered ",Toast.LENGTH_SHORT).show();
            /**
             * code to start new activity
             * ----------->
             */
            finish();

        }
        else if (!(checkBoxRememberMe.isChecked())){

            /**
             * code to start new activity
             * ----------->
             */
        }



    }


    //Method to save login details

    private boolean saveLoginDetails(String username, String college, String emailid) {
        result =new PrefManager(this).saveLoginDetails(username, college, emailid);
        return result;
    }
}
