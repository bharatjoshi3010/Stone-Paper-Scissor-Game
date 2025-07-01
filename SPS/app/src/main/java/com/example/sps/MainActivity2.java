package com.example.sps;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView textQuote;
    TextView YourSc;
    TextView CompSc;
    TextView finalResult;
    ImageView resImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        textQuote = findViewById(R.id.textView15);
        resImg = findViewById(R.id.imageView6);
        CompSc = findViewById(R.id.textView24);
        YourSc = findViewById(R.id.textView20);
        finalResult = findViewById(R.id.textView25);
        Intent intent2 = new Intent(this, MainActivity.class);
        Intent intent = getIntent();
        int Result = intent.getIntExtra(MainActivity3.Extra_Result, 0);
        int win = intent.getIntExtra(MainActivity3.Extra_Win, 0);
        int lose = intent.getIntExtra(MainActivity3.Extra_Lose, 0);
        YourSc.setText(String.valueOf(win));
        CompSc.setText(String.valueOf(lose));
        if(Result == 0){
            finalResult.setText("You Lose :(");
            resImg.setImageResource(R.drawable.sadboy);
            textQuote.setText("कर्म किया लाख बार, किस्मत बोले — नहीं यार!");
        } else if (Result == 1) {
            finalResult.setText("You Win :)");
            resImg.setImageResource(R.drawable.hapboy);
            textQuote.setText("ख़ुदा मेहरबान तो गधा पहलवान");
        } else{
            finalResult.setText("Its a draw");
            resImg.setImageResource(R.drawable.eqal);
            textQuote.setText("Better luck Next time");
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(intent2);
            }
        });
    }
}