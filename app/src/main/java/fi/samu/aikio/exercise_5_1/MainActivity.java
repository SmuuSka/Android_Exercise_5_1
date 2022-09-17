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

    private int hitValue, creationValue, visibleValue;
    private Counter hitCounter, creationCounter, visiblesCounter;
    private TextView creationView, visiblesView, hitsView;
    private String currentHitText, currentCreationsText, currentVisiblesText;
    private int defaultValue = 0;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("onCreate", "OnCreateCalled");
        sharedPreferences = getSharedPreferences("Saved data", Context.MODE_PRIVATE);

        //Get saved values or set default value = 0
        hitValue = sharedPreferences.getInt("Hits", defaultValue);
        creationValue = sharedPreferences.getInt("Creations", defaultValue);
        visibleValue = sharedPreferences.getInt("Visibles", defaultValue);

        //App create a new counter
        hitCounter = new Counter(-100,100,hitValue,1);
        creationCounter = new Counter(-100,100,creationValue,1);
        visiblesCounter = new Counter(-100,100,visibleValue,1);

        //Button references
        Button hitMeButton = findViewById(R.id.hitMeButton);

        Button resetButton = findViewById(R.id.resetButton);

        //TextView references
        creationView = findViewById(R.id.creationsViewText);
        visiblesView = findViewById(R.id.visiblesViewText);
        hitsView = findViewById(R.id.hitMeViewTxt);

        //Set textView headers
        currentHitText = hitsView.getText().toString() + " \n";
        hitsView.setText(currentHitText + Integer.toString(hitCounter.getValue()));

        currentCreationsText = creationView.getText().toString() + " \n";
        creationView.setText(currentCreationsText + Integer.toString(creationCounter.getValue()));

        currentVisiblesText = visiblesView.getText().toString() + " \n";
        visiblesView.setText(currentVisiblesText + Integer.toString(visiblesCounter.getValue()));

        //Increase creations by one
        creationView.setText(currentCreationsText + Integer.toString(creationCounter.incrementValueByStep()));

        //Save creation value to sharedPreferences
        saveCount("Creations", creationCounter.getValue());

        //Hit me-button pressed method
        hitMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitsView.setText(currentHitText + Integer.toString(hitCounter.incrementValueByStep()));
            }
        });

        //Reset-button resets all values
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

        //If pause was called, save values to sharedPreferences
        saveCount("Hits", hitCounter.getValue());
        saveCount("Creations", creationCounter.getValue());
        saveCount("Visibles", visiblesCounter.getValue());
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
    // Method for saving values to sharedPreferences
    private void saveCount(String key, int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
        Toast.makeText(MainActivity.this, key + " Count Saved", Toast.LENGTH_LONG).show();
    }
}