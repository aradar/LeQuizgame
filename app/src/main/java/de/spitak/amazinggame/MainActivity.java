package de.spitak.amazinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSwipeTest(View view) {
        startActivity(SwipeTestActivity.class);
    }

    private void startActivity(Class type) {
        final Intent intent = new Intent(this, type);
        startActivity(intent);
    }

    public void onClickShapeTest(View view) {
        startActivity(ShapeTestActivity.class);
    }

    public void onClickCountdownTest(View view) {
        startActivity(CountdownTestActivity.class);
    }

    public void onClickSwipeStackTest(View view) {
        startActivity(SwipeStackTestActivity.class);
    }
}
