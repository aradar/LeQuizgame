package de.spitak.amazinggame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.spitak.amazinggame.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void startActivity(Class type) {
        final Intent intent = new Intent(this, type);
        startActivity(intent);
    }

    public void onClickGameStart(View view)
    {
        startActivity(GameActivity.class);
    }

    public void onClickSwipeStackTest(View view) {
        startActivity(SwipeStackTestActivity.class);
    }

    public void onClickStackLayoutTest(View view) {
        startActivity(StackLayoutTestActivity.class);
    }
}
