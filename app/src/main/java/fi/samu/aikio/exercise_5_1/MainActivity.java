package fi.samu.aikio.exercise_5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int hitValue, creationValue, visibleValue;

    private Counter hitCounter, creationCounter, visiblesCounter;
    private Button hitMeButton, resetButton;
    private TextView creationView, visiblesView, hitsView;
    private String currentHitText, currentCreationsText, currentVisiblesText;
    private int lastSavedCreations, lastSavedVisibles, lastSavedHits;
    private int startValueHit, startValueCreations, startValueVisibles, defaultValue = 0;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("onCreate", "OnCreateCalled");
        sharedPreferences = getSharedPreferences("Saved data", Context.MODE_PRIVATE);

        //Creation GetSet
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("CreateCount", creationCounter.getValue());
        editor.commit();
        Toast.makeText(MainActivity.this, "Hit Count Saved", Toast.LENGTH_LONG).show();



        hitValue = sharedPreferences.getInt("HitCount", defaultValue);
        creationValue = sharedPreferences.getInt("CreateCount", defaultValue);
        visibleValue = sharedPreferences.getInt("VisibleCount", defaultValue);


        //App create a new counter if don't have one
        hitCounter = new Counter();
        creationCounter = new Counter();
        visiblesCounter = new Counter();

        //Button references
        hitMeButton = findViewById(R.id.hitMeButton);
        resetButton = findViewById(R.id.resetButton);

        //TextView references
        creationView = findViewById(R.id.creationsViewText);
        visiblesView = findViewById(R.id.visiblesViewText);
        hitsView = findViewById(R.id.hitMeViewTxt);

        //Set start values
        startValueHit = hitCounter.getValue();
        startValueCreations = creationCounter.getValue();
        startValueVisibles = visiblesCounter.getValue();


        //Set start headers
        currentHitText = hitsView.getText().toString() + " \n";
        hitsView.setText(currentHitText + startValueHit);

        currentCreationsText = creationView.getText().toString() + " \n";
        creationView.setText(currentCreationsText + startValueCreations);

        currentVisiblesText = visiblesView.getText().toString() + " \n";
        visiblesView.setText(currentVisiblesText + startValueVisibles);

        creationView.setText(currentCreationsText + Integer.toString(creationCounter.incrementValueByStep()));

        //If having something
        hitsView.setText(currentHitText + Integer.toString(hitValue));


        //Hit me-button pressed method
        hitMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitsView.setText(currentHitText + Integer.toString(hitCounter.incrementValueByStep()));


            }
        });

        //Reset all values
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hitsView.setText(currentHitText + Integer.toString(hitCounter.resetValue()));
                creationView.setText(currentCreationsText + Integer.toString(creationCounter.resetValue()));
                visiblesView.setText(currentVisiblesText + Integer.toString(visiblesCounter.resetValue()));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart", "OnStartCalled");
        visiblesView.setText(currentVisiblesText + Integer.toString(visiblesCounter.incrementValueByStep()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "OnResumeCalled");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause", "OnPauseCalled");
        saveHitCount();
        saveCreationCount();


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "OnStopCalled");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart", "OnRestartCalled");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "OnDestroyCalled");

    }

    private void saveHitCount(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("HitCount", hitCounter.getValue());
        editor.commit();
        Toast.makeText(MainActivity.this, "Hit Count Saved", Toast.LENGTH_LONG).show();
    }

    private void saveCreationCount(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("CreationCount", creationCounter.getValue());
        editor.commit();
        Toast.makeText(MainActivity.this, "Creation Count Saved", Toast.LENGTH_LONG).show();
    }
}