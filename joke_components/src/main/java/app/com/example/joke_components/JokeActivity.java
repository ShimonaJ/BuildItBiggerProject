package app.com.example.joke_components;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String joke ="No Jokes";
        if(intent!=null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            joke = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        setContentView(R.layout.activity_joke);
        TextView tv =(TextView)findViewById(R.id.joke_text);
        tv.setText(joke);
    }
}
