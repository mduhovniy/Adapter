package info.duhovniy.maxim.adapterexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private EditText newItem;
    private Button buttonAdd;
    private final int CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newItem = (EditText) findViewById(R.id.newItem);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.row, list);
        listView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(String.valueOf(newItem.getText()));
                adapter.notifyDataSetChanged();
                newItem.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewElement.class);
                intent.putExtra("row", list.get(position));
                startActivityForResult(intent, CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_clear) {
            list.clear();
            adapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.action_exit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE && resultCode == RESULT_OK) {
            list.add(data.getStringExtra("row to add"));
            adapter.notifyDataSetChanged();
        }
    }
}
