package com.example.megha.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;

public class SeperateToDoActivity extends AppCompatActivity {

    EditText title, content;
    TextView toDoDate;
    Button save, cancel;
    int color;

    int position = -9;
    Intent i;
    ToDoListContents toDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seperate_to_do);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("To Do");
        title = (EditText) findViewById(R.id.titleEditText);
        toDoDate = (TextView) findViewById(R.id.dateTextView);
        content = (EditText) findViewById(R.id.contentEditText);
        save = (Button) findViewById(R.id.saveButton);
        cancel = (Button) findViewById(R.id.cancelButton);

        i = getIntent();
        toDo = (ToDoListContents) i.getSerializableExtra(Constants.MainActivityToDo);
        color = toDo.color;
        toDoDate.setText((new Date()).toString());
        title.setText(toDo.title);
        title.setBackgroundColor(color);
        content.setText(toDo.content);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = i.getIntExtra(Constants.MainActivityPosition, -1);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_dropdown_default){
            color = 0xff4f5051;
        }
        else if(item.getItemId() == R.id.action_dropdown_red){
            color = 0xffff0000;
        }
        else if(item.getItemId() == R.id.action_dropdown_green){
            color = 0xff00ff00;
        }
        else if(item.getItemId() == R.id.action_dropdown_blue){
            color = 0xff0000ff;
        }
        title.setBackgroundColor(color);
        return true;
    }

    @Override
    public void finish(){
        if(position == -9){
            setResult(Activity.RESULT_CANCELED);
        }
        else{
            Intent intent = new Intent();
            ToDoListContents toDoObject = new ToDoListContents( title.getText().toString(), (new Date()).toString(), content.getText().toString(), color);
            toDoObject.setID(toDo.getID());
            intent.putExtra(Constants.SepereateToDoActivityToDo, (Serializable) toDoObject);
            intent.putExtra(Constants.SepereateToDoActivityPosition, position);
            setResult(Activity.RESULT_OK, intent);
        }
        super.finish();
    }

}
