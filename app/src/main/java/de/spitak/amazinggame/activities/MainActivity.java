package de.spitak.amazinggame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.spitak.amazinggame.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void startActivity(Class type) {
        final Intent intent = new Intent(this, type);
        startActivity(intent);
    }

    public void onClickSwipeTest(View view) {
        startActivity(SwipeTestActivity.class);
    }

    public void onClickSwipeStackTest(View view) {
        startActivity(SwipeStackTestActivity.class);
    }

    public void onClickStackLayoutTest(View view) {
        startActivity(StackLayoutTestActivity.class);
    }
}
