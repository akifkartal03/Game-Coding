package com.example.winterhw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
/**
 * This Class is for first screen of the game such that we give the game information to the program.
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //play button action listener
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RadioGroup radioGroup=findViewById(R.id.radio);
                final EditText row= findViewById(R.id.row);
                final EditText column= findViewById(R.id.column);
                //check the information given from gamer
                //if there is a problem with information then give a message to the gamer.
                if (row.getText().toString().equals("") || column.getText().toString().equals("")){
                    if (row.getText().toString().equals(""))
                        giveMessage(2);
                    if (column.getText().toString().equals(""))
                        giveMessage(3);
                }
                else{
                    if ((Integer.parseInt(row.getText().toString())<3 || Integer.parseInt(row.getText().toString())>9) || (Integer.parseInt(column.getText().toString())<3 || Integer.parseInt(column.getText().toString())>9)){
                        if ((Integer.parseInt(row.getText().toString())<3 || Integer.parseInt(row.getText().toString())>9))
                            giveMessage(4);
                        if ((Integer.parseInt(column.getText().toString())<3 || Integer.parseInt(column.getText().toString())>9))
                            giveMessage(5);
                    }
                    else{ // if there is no problem with information start the game.
                        Intent i = new Intent(MainActivity.this,Play.class);
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        // find the radiobutton by returned id
                        RadioButton radioButton =findViewById(selectedId);
                        i.putExtra("row",row.getText().toString());
                        i.putExtra("column",column.getText().toString());
                        i.putExtra("mode",radioButton.getText().toString());
                        startActivity(i);
                    }

                }

            }
        });
    }
    /**
     * This method is to give a message to the gamer according case.
     * @param flag is the case information
     * */
    public void giveMessage(int flag){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Warning");
        if (flag ==2)
            builder.setMessage("Please Enter Row Number!");
        else if (flag ==3)
            builder.setMessage("Please Enter Column Number!");
        else if (flag ==4)
            builder.setMessage("Please Enter Row Number between 3 and 9!");
        else if (flag ==5)
            builder.setMessage("Please Enter Column Number between 3 and 9!");
        builder.setCancelable(true);
        builder.setIcon(R.drawable.warning); //set the icon
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
