package fi.samu.aikio.exercise_5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "OnCreateCalled");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.d("onStart", "OnStartCalled");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("onResume", "OnResumeCalled");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("onPause", "OnPauseCalled");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("onStop", "OnStopCalled");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("onRestart", "OnRestartCalled");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("onDestroy", "OnDestroyCalled");
        super.onDestroy();
    }
}