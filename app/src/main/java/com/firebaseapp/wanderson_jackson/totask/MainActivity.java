package com.firebaseapp.wanderson_jackson.totask;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> tasks = new ArrayList<String>();
    private ListView listView;
    private ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView= (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter(this,
                R.layout.item_task,
                R.id.textView, tasks);

        listView.setAdapter(arrayAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_add:
                final EditText editText= new EditText(this);

                AlertDialog dialog = new AlertDialog.Builder(this)                                  //dialogo que aparece ap
                        .setTitle("Adiconar novo task")
                        .setMessage("O que deseja adicionar? ")
                        .setView(editText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String task = String.valueOf(editText.getText());

                                tasks.add(task);
                                Log.d("PRINCIPAL:", "task adicionado" + tasks);
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create();
                dialog.show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //metodo que remove task
    public void removeTask(View view){
        View parent  = (View) view.getParent();

        TextView task = (TextView) parent.findViewById(R.id.textView);
        String taskText = String.valueOf(task.getText());

        arrayAdapter.remove(taskText);
        arrayAdapter.notifyDataSetChanged();
    }
}
