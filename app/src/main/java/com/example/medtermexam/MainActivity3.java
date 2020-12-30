package com.example.medtermexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        StudentArchive[] studentArchive = MainActivity2.getStudentArchive();
        int counter = getIntent().getIntExtra("counter", 0);

        TableLayout tableLayoutDisplay = findViewById(R.id.tableLayoutDisplay);
        tableLayoutDisplay.setStretchAllColumns(true);
        List<String> submitdocs;

        for (int i = 0; i<counter; i++){
            if(studentArchive[i] == null)
                break;
            else{
                submitdocs = new ArrayList<String>();
                String[] getdocs = studentArchive[i].getDocsSubmit();
                for(int x = 0; x < getdocs.length; x++)
                    submitdocs.add(getdocs[x]);
                String crsyr = ""+studentArchive[i].getCourse() +"-"+ studentArchive[i].getYear();
                String name ="" + studentArchive[i].getLastname() +", "+ studentArchive[i].getFirstname() +", "+ studentArchive[i].getMi();
                TableRow row = new TableRow(MainActivity3.this);

                TableLayout tableLayoutdocs = new TableLayout(MainActivity3.this);
                tableLayoutdocs.setBackgroundResource(R.drawable.border);

                TextView textViewid = new TextView(MainActivity3.this);
                textViewid.setBackgroundResource(R.drawable.border);
                textViewid.setGravity(Gravity.CENTER);
                textViewid.setLayoutParams(new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 99));

                TextView textViewName = new TextView(MainActivity3.this);
                textViewName.setBackgroundResource(R.drawable.border);
                textViewName.setGravity(Gravity.CENTER);
                textViewName.setLayoutParams(new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 99));

                TextView textViewCrsYr = new TextView(MainActivity3.this);
                textViewCrsYr.setBackgroundResource(R.drawable.border);
                textViewCrsYr.setGravity(Gravity.CENTER);
                textViewCrsYr.setLayoutParams(new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 99));

                TextView textViewGender = new TextView(MainActivity3.this);
                textViewGender.setBackgroundResource(R.drawable.border);
                textViewGender.setGravity(Gravity.CENTER);
                textViewGender.setLayoutParams(new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 99));

                textViewid.setText(studentArchive[i].getId());
                textViewName.setText(name);
                textViewCrsYr.setText(crsyr);
                textViewGender.setText(studentArchive[i].getGender());

                row.addView(textViewid);
                row.addView(textViewName);
                row.addView(textViewCrsYr);
                row.addView(textViewGender);
                for(int j = 0; j<submitdocs.size(); j++){
                    TableRow rowdocs = new TableRow(MainActivity3.this);
                    TextView textViewdocs = new TextView(MainActivity3.this);
                    textViewdocs.setGravity(Gravity.CENTER);
                    textViewdocs.setText(submitdocs.get(j));
                    rowdocs.addView(textViewdocs);
                    tableLayoutdocs.addView(rowdocs);
                }
                row.addView(tableLayoutdocs);
                tableLayoutDisplay.addView(row);
            }
        }


    }
}