package com.pace.cs639spring.hw2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String[] animalTitles;

    private ImageView selectedAnimal;
    private Button firstButton;
    private Button secondButton;
    private Button thirdButton;
    private Button fourthButton;
    private Button fifthButton;
    private LinearLayout buttonLayout;
    private TextView animalFacts;
    int currentColor = 0;
    private Button nextButton;
    private Button deleteButton;
    int currentAnimal = 0;

    int[] images = {R.drawable.bird, R.drawable.cat, R.drawable.dog, R.drawable.ic_icons8_ant_96, R.drawable.ic_icons8_windows_8_astrology_year_of_pig, R.drawable.ic_icons8_fish_96, R.drawable.ic_icons8_lamb_96, R.drawable.ic_icons8_seahorse_96, R.drawable.ic_icons8_snail_96, R.drawable.ic_icons8_whale_96};
    boolean[] status = {false, false, false, false, false, false, false, false, false, false};
    int[] colors={android.R.color.black,android.R.color.black,android.R.color.black,android.R.color.black,android.R.color.black,android.R.color.black,android.R.color.black,android.R.color.black,android.R.color.black,android.R.color.black};
    ArrayList<ArrayList<String>> factsArrayList = new ArrayList<ArrayList<String>>();
    private Button addButton;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );
        Resources res = getResources();
        list = findViewById( R.id.listView );
        list.setItemsCanFocus(false);
        animalTitles = res.getStringArray( R.array.titles );
        selectedAnimal = findViewById( R.id.animalsImages );
        firstButton = findViewById( R.id.firstbutton );
        secondButton = findViewById( R.id.secondbutton );
        thirdButton = findViewById( R.id.thirdbutton );
        fourthButton = findViewById( R.id.fourthbutton );
        fifthButton = findViewById( R.id.fifthbutton );
        addButton = findViewById( R.id.addFact );
        editButton = findViewById( R.id.editButton );
        initializeAllData();

        final AnimalAdapter adapter = new AnimalAdapter( this, animalTitles, images, status,colors,factsArrayList);
        list.setAdapter( adapter );
        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentAnimal = i;
                Log.e( "AnimalPosition: "," "+currentAnimal );
                adapter.updateStatusAtPOsition( i );
            }
        } );

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_dark);
                adapter.updateColors( currentAnimal, currentColor );
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_dark);
                adapter.updateColors( currentAnimal, currentColor );
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.holo_green_dark);
                adapter.updateColors( currentAnimal, currentColor );
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray);
                adapter.updateColors( currentAnimal, currentColor );
            }
        });

        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentColor = ContextCompat.getColor(MainActivity.this, android.R.color.black);
                adapter.updateColors( currentAnimal, currentColor );
            }
        });

        addButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Add Fact");

                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int screenWidth = (int) (metrics.widthPixels * 0.80);

                final EditText factEditText = dialog.findViewById( R.id.factEditText );
                Button addFactButton = dialog.findViewById( R.id.addFactButton );
                addFactButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fact = factEditText.getText().toString().trim();
                        if(!TextUtils.isEmpty( fact )){
                            adapter.addFactToList(fact,currentAnimal);
                            dialog.dismiss();
                        }
                    }
                } );

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout( screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        } );
        
        editButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.custom_dialog);

                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int screenWidth = (int) (metrics.widthPixels * 0.80);

                final EditText factEditText = dialog.findViewById( R.id.factEditText );
                Button addFactButton = dialog.findViewById( R.id.addFactButton );

                String fact = adapter.getFactAt(currentAnimal);
                factEditText.setText( fact );

                addFactButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fact = factEditText.getText().toString().trim();
                        if(!TextUtils.isEmpty( fact )){
                            adapter.updateFactToList(fact,currentAnimal);
                            dialog.dismiss();
                        }
                    }
                } );

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout( screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        } );
    }

    private void initializeAllData() {
        for(int i = 0;i<10;i++){
            ArrayList<String> facts = new ArrayList<String>();

            for(int j = 0;j<3;j++){
                facts.add("FACT #"+j);
            }
            factsArrayList.add(facts);
        }
    }


    class AnimalAdapter extends ArrayAdapter<String> {
        Context context;
        int[] images;
        String[] titleArray;
        boolean[] status;
        int[] colors;
        ArrayList<ArrayList<String>> factsArrayList = new ArrayList<ArrayList<String>>();
        int[] factIndex = {0,0,0,0,0,0,0,0,0,0};

        AnimalAdapter(Context c, String[] titles, int imgs[], boolean[] status, int[] colors,ArrayList<ArrayList<String>> factsArrayList) {
            super( c, R.layout.single_row, R.id.animalsNames, titles );
            this.images = imgs;
            this.context = c;
            this.titleArray = titles;
            this.status = status;
            this.colors = colors;
            this.factsArrayList = factsArrayList;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View row = inflater.inflate( R.layout.single_row, parent, false );
            final ImageView myImage = row.findViewById( R.id.animalsImages );
            TextView myTitle = row.findViewById( R.id.animalsNames );
            buttonLayout = row.findViewById(R.id.ButtonLayout);
            animalFacts = row.findViewById( R.id.animalFacts );
            nextButton=row.findViewById( R.id.nextButton );
            deleteButton=row.findViewById( R.id.deleteButton );
            myImage.setImageResource( images[position] );
            myTitle.setText( titleArray[position] );

            if(status[position]){
                myTitle.setVisibility( View.VISIBLE );
                buttonLayout.setVisibility( View.VISIBLE );
                animalFacts.setVisibility( View.VISIBLE );
            }else{
                myTitle.setVisibility( View.INVISIBLE );
                buttonLayout.setVisibility( View.INVISIBLE);
                animalFacts.setVisibility( View.INVISIBLE );
            }

            animalFacts.setText( factsArrayList.get(0).get(factIndex[position]));


            if(colors[position]!=android.R.color.black) {
                myImage.setColorFilter( colors[position], PorterDuff.Mode.SRC_IN );
            }

            nextButton.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ArrayList<String> facts = factsArrayList.get(position);
                    int index = factIndex[position];
                    index = index + 1;
                    Log.e( "Tag" ,facts.size()+"--"+index);
                    if(index>=facts.size()){
                        factIndex[position] = 0;
                        notifyDataSetChanged();
                    }else{
                        factIndex[position] = index;
                        notifyDataSetChanged();
                    }
                }
            } );

            deleteButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<String> facts = factsArrayList.get(position);
                    int index = factIndex[position];
                    if(facts.size()==1){
                        Log.e( "Last item","!");
                        return;
                    }
                    else {
                        facts.remove(index);
                        int newindex;
                        if(index == 0){
                             newindex = factIndex[position]+1;
                        }else{
                             newindex = factIndex[position]-1;
                        }
                        factIndex[position] = newindex;
                        notifyDataSetChanged();
                    }
                }
            } );


            return row;
        }



        public void updateStatusAtPOsition(int pos) {
            for (int i = 0; i < status.length; i++) {
                if (i == pos) {
                    status[i] = !status[i];
                } else {
                    status[i] = false;
                }
            }

            notifyDataSetChanged();
        }

        public void updateColors(int currentAnimal, int color) {
            colors[currentAnimal] = color;
            notifyDataSetChanged();
        }

        public void addFactToList(String fact, int currentAnimal) {
            ArrayList<String> factArrayList = factsArrayList.get( currentAnimal );
            factArrayList.add( fact );
            factsArrayList.set( currentAnimal,factArrayList );
            notifyDataSetChanged();
        }

        public String getFactAt(int currentAnimal) {
            return factsArrayList.get( currentAnimal ).get( factIndex[currentAnimal] );
        }

        public void updateFactToList(String fact, int currentAnimal) {
            ArrayList<String> factArrayList = factsArrayList.get( currentAnimal );
            Log.e( "Update",factIndex[currentAnimal]+"="+currentAnimal );
            factArrayList.set( factIndex[currentAnimal],fact );
            factsArrayList.set( currentAnimal,factArrayList );
            notifyDataSetChanged();
        }
    }
}
