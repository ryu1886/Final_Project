package example.com.termproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;



public class MainActivity extends AppCompatActivity {


    ImageButton imageButton04;
    ImageButton imageButton01;
    ImageButton imageButton02;
    ImageButton imageButton03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton04 = (ImageButton) findViewById(R.id.imageButton04);
        imageButton01 = (ImageButton) findViewById(R.id.imageButton01);
        imageButton02 = (ImageButton) findViewById(R.id.imageButton02);
        imageButton03 = (ImageButton) findViewById(R.id.imageButton03);





        imageButton02.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent02 = new Intent(MainActivity.this, ChartActivity.class);
                startActivity(intent02);
            }
        }));

        imageButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent03 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"));
                startActivity(intent03);
            }
        });


        imageButton04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent04 = new Intent(MainActivity.this, DataBaseActivity.class);
                startActivity(intent04);

            }
        });

        imageButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent01 = new Intent(MainActivity.this, LogActivity.class);
                startActivity(intent01);
            }
        });

    }


}


