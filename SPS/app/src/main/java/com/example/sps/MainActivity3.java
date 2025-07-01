package com.example.sps;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    public static final String Extra_Result = "com.example.sps.Result";
    public static final String Extra_Win = "com.example.sps.Win";
    public static final String Extra_Lose = "com.example.sps.Lose";
    TextView textWin;
    TextView textLose;
    TextView textTie;
    TextView roundText;
    int rand =0 , round =0;
    int win=0, lose=0, tie=0, hs=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        textLose = findViewById(R.id.textView9);
        textWin = findViewById(R.id.textView10);
        textTie = findViewById(R.id.textView11);
        roundText = findViewById(R.id.textView14);
        roundText.setText("It's round "+(round+1)+"/10\nSelect your move");
        Intent gIntent = getIntent();
        hs = Integer.parseInt(gIntent.getStringExtra("hs"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (round < 10) {
                    new AlertDialog.Builder(MainActivity3.this)
                            .setTitle("Warning")
                            .setMessage("You haven’t completed all 10 rounds. If you go back now, you’ll lose your progress.")
                            .setPositiveButton("Stay", null)
                            .setNegativeButton("Leave Anyway", (dialog, which) -> {
                                finish(); // Exit activity
                            })
                            .show();
                } else {
                    finish(); // Allow exit
                }
            }
        });
    }
    public void result(int num){
        SharedPreferences sp = getSharedPreferences("highScore", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        rand = (int)(Math.random()*10) % 3;
        round++;
        if(num == rand){
            tie++;
            textTie.setText(String.valueOf(tie));
        }else if((num == 0 && rand == 1)||(num==1 && rand == 2)||(num == 2 && rand == 0)){
            lose++;
            textLose.setText(String.valueOf(lose));
        }else{
            win++;
            textWin.setText(String.valueOf(win));
        }

        if(round>=10){
            Intent intent2 = new Intent(this, MainActivity2.class);
            if(win>hs && (win != tie)){
                Toast.makeText(this, "Congratulations ! You break Records.", Toast.LENGTH_SHORT).show();
                ed.putString("highest", String.valueOf(win));
                ed.apply();
            }
            intent2.putExtra(Extra_Win, win);
            intent2.putExtra(Extra_Lose, lose);
            if(win > lose){
                intent2.putExtra(Extra_Result, 1);
            } else if (win<lose) {
                intent2.putExtra(Extra_Result, 0);
            } else{
                intent2.putExtra(Extra_Result, 2);
            }

            startActivity(intent2);
        }
        roundText.setText("It's round "+(round+1)+"/10\nSelect your move");
    }
    public void stoneSelected(View v){
        result(0);
    }
    public void paperSelected(View v){
        result(1);
    }
    public void scissorSelected(View v){
        result(2);
    }

}
