package info.duhovniy.maxim.adapterexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewElement extends AppCompatActivity {

    private TextView item;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_element);

        item = (TextView) findViewById(R.id.textView);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        final Intent intent = getIntent();

        item.setText(intent.getStringExtra("row"));

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("row to add", intent.getStringExtra("row") + " was pressed");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
