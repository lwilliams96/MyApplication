package com.example.treyn.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        helper.reCreate();
    }


    public void calculate(View v){
        EditText intake = findViewById(R.id.editGain);
        EditText loss = findViewById(R.id.editLoss);
        int finalIn;
        int finalLoss;

        String calIn = intake.getText().toString();
        if(calIn.matches("")) {
            finalIn = 0;
        }else{
            finalIn = Integer.parseInt(calIn);
        }
        String calLos = loss.getText().toString();
        if(calLos.matches("")) {
            finalLoss = 0;
        }else{
            finalLoss = Integer.parseInt(calLos);
        }


        Calories calories = new Calories();
        calories.setGain(finalIn);
        calories.setLoss(finalLoss);
        calories.setTotal(finalIn, finalLoss);

        helper.insertCalories(calories);

        TextView gain = findViewById(R.id.totalIntake);
        TextView lost = findViewById(R.id.totalBurned);
        TextView total = findViewById(R.id.totalNum);

        int gainInt;
        gainInt = helper.getTotalIntake();
        gain.setText(Integer.toString(gainInt));

        int lossInt;
        lossInt = helper.getTotalLoss();
        lost.setText(Integer.toString(lossInt));

        int totalInt;
        totalInt = helper.getTotal();
        total.setText(Integer.toString(totalInt));

        intake.setText("");
        loss.setText("");

    }
}
