package com.pace.cs639spring.hw1;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView bird;
    private ImageView cat;
    private ImageView dog;

    private TextView birdInfo;
    private TextView catInfo;
    private TextView dogInfo;

    private Button firstButton;
    private Button secondButton;
    private Button thirdButton;
    private Button fourthButton;
    private Button fifthButton;

    int currentColor=0;
    int currentAnimal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_display);

        final Context context = getApplicationContext();
        CharSequence errorText = "Please select an animal first!";
        final int duration = Toast.LENGTH_LONG;
        final Toast toast = Toast.makeText(context, errorText, duration);

        bird = findViewById(R.id.bird);
        cat = findViewById(R.id.cat);
        dog = findViewById(R.id.dog);

        birdInfo = findViewById(R.id.birdInfo);
        catInfo = findViewById(R.id.catInfo);
        dogInfo = findViewById(R.id.dogInfo);

        firstButton=findViewById(R.id.firstbutton);
        secondButton=findViewById(R.id.secondbutton);
        thirdButton=findViewById(R.id.thirdbutton);
        fourthButton=findViewById(R.id.fourthbutton);
        fifthButton=findViewById(R.id.fifthbutton);

        //Event listener for the three animal icons which switch the visibility for their corresponding fun facts and keep a track of the currently selected animal
        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click","Clicked");
                if (birdInfo.getVisibility()==View.INVISIBLE){
                    birdInfo.setVisibility(View.VISIBLE);
                    catInfo.setVisibility(View.INVISIBLE);
                    dogInfo.setVisibility(View.INVISIBLE);
                    currentAnimal=1;
                }
                else
                {
                    birdInfo.setVisibility(View.INVISIBLE);
                    currentAnimal=0;
                }
            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click","Clicked");
                if (catInfo.getVisibility()==View.INVISIBLE){
                    catInfo.setVisibility(View.VISIBLE);
                    birdInfo.setVisibility(View.INVISIBLE);
                    dogInfo.setVisibility(View.INVISIBLE);
                    currentAnimal=2;
                }
                else
                {
                    catInfo.setVisibility(View.INVISIBLE);
                    currentAnimal=0;
                }
            }
        });


        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click","Clicked");
                if (dogInfo.getVisibility()==View.INVISIBLE){
                    dogInfo.setVisibility(View.VISIBLE);
                    catInfo.setVisibility(View.INVISIBLE);
                    birdInfo.setVisibility(View.INVISIBLE);
                    currentAnimal=3;
                }
                else
                {
                    dogInfo.setVisibility(View.INVISIBLE);
                    currentAnimal=0;
                }
            }
        });

    //Event listener for the color pickers which check whether an animal is selected and color that icon accordingly or display a toast message
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_dark);
                if(currentAnimal==0){
                    toast.show();
                }
                else
                {
                    if (currentAnimal==1)
                    {
                        bird.setColorFilter(currentColor);
                    }
                    else if(currentAnimal==2)
                    {
                        cat.setColorFilter(currentColor);
                    }
                    else
                    {
                        dog.setColorFilter(currentColor);
                    }
                }

            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_dark);
                if(currentAnimal==0){
                    toast.show();
                }
                else
                {
                    if (currentAnimal==1)
                    {
                        bird.setColorFilter(currentColor);
                    }
                    else if(currentAnimal==2)
                    {
                        cat.setColorFilter(currentColor);
                    }
                    else
                    {
                        dog.setColorFilter(currentColor);
                    }
                }
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.holo_green_dark);
                if(currentAnimal==0){
                    toast.show();
                }
                else
                {
                    if (currentAnimal==1)
                    {
                        bird.setColorFilter(currentColor);
                    }
                    else if(currentAnimal==2)
                    {
                        cat.setColorFilter(currentColor);
                    }
                    else
                    {
                        dog.setColorFilter(currentColor);
                    }
                }
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray);
                if(currentAnimal==0){
                    toast.show();
                }
                else
                {
                    if (currentAnimal==1)
                    {
                        bird.setColorFilter(currentColor);
                    }
                    else if(currentAnimal==2)
                    {
                        cat.setColorFilter(currentColor);
                    }
                    else
                    {
                        dog.setColorFilter(currentColor);
                    }
                }
            }
        });

        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.black);
                if(currentAnimal==0){
                    toast.show();
                }
                else
                {
                    if (currentAnimal==1)
                    {
                        bird.setColorFilter(currentColor);
                    }
                    else if(currentAnimal==2)
                    {
                        cat.setColorFilter(currentColor);
                    }
                    else
                    {
                        dog.setColorFilter(currentColor);
                    }
                }
            }
        });


    }
}

