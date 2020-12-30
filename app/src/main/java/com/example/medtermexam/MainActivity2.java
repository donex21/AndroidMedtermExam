package com.example.medtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity2 extends AppCompatActivity {
    static StudentArchive[] studentArchives;
    int counter = 0;
    Button clearbtn, viewbtn, saveUpdatebtn, searcbtn;
    EditText editTextSearch, editTextIdnumber, editTextLastname, editTextFirstname, editTextMI, editTextCourse;
    RadioGroup radioGroupYear, radioGroupGender;
    RadioButton radioButtonYear, radioButtonGender;
    LinearLayout layoutdocsSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        studentArchives = new StudentArchive[5];

        layoutdocsSubmit = findViewById(R.id.layoutdocsSubmit);

        saveUpdatebtn = findViewById(R.id.btnSaveUpdate);
        clearbtn = findViewById(R.id.btnClear);
        viewbtn = findViewById(R.id.btnView);
        searcbtn = findViewById(R.id.btnsearch);

        editTextSearch = findViewById(R.id.editTextSearch);
        editTextIdnumber = findViewById(R.id.editTextIdnumber);
        editTextLastname = findViewById(R.id.editTextLastname);
        editTextFirstname = findViewById(R.id.editTextFirstname);
        editTextMI = findViewById(R.id.editTextMI);
        editTextCourse = findViewById(R.id.editTextCourse);

        radioGroupYear = findViewById(R.id.radioGroupYear);
        radioGroupGender = findViewById(R.id.radioGroupGender);



        //Save Or Update Button
        saveUpdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check Checkbox that check
                String[] docsSubmit = new String[3];
                docsSubmit[0] = ""; docsSubmit[1] = ""; docsSubmit[2] = "";
                int docscounter = 0;
                int countdocsSubmit = layoutdocsSubmit.getChildCount();
                for (int i = 0; i < countdocsSubmit; i++) {
                    v = layoutdocsSubmit.getChildAt(i);
                    if (v instanceof CheckBox) {
                        if (((CheckBox) v).isChecked()) {
                            docsSubmit[docscounter] = ((CheckBox) v).getText().toString();
                            docscounter++;
                        }
                    }
                }
                //Check which RadioButton is selected
                radioButtonYear = findViewById(radioGroupYear.getCheckedRadioButtonId());
                radioButtonGender = findViewById(radioGroupGender.getCheckedRadioButtonId());

                if (!completefillup())
                    Toast.makeText(MainActivity2.this, "Pls Complete the Fill Up Form", Toast.LENGTH_SHORT).show();
                else if(editTextMI.getText().toString().trim().length()>1)
                    editTextMI.setError("Invalid Middle Initial");
                else if (!(docsSubmit[0].equals("TOR") && docsSubmit[1].equals("Good Moral"))
                        && radioButtonYear.getText().toString().equals("1st"))
                    Toast.makeText(MainActivity2.this, "If 1st Year should has TOR and Good Moral", Toast.LENGTH_SHORT).show();
                else if (saveUpdatebtn.getText().toString().equals("Edit/Update")) {
                    //for update Button
                    for (int i = 0; i < counter; i++) {
                        if (editTextIdnumber.getText().toString().equals(studentArchives[i].getId())) {
                            studentArchives[i].setLastname(editTextLastname.getText().toString());
                            studentArchives[i].setFirstname(editTextFirstname.getText().toString());
                            studentArchives[i].setMi(editTextMI.getText().toString());
                            studentArchives[i].setCourse(editTextCourse.getText().toString());
                            studentArchives[i].setYear(radioButtonYear.getText().toString());
                            studentArchives[i].setGender(radioButtonGender.getText().toString());
                            studentArchives[i].setDocsSubmit(docsSubmit);
                        }
                    }
                    Toast.makeText(MainActivity2.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    editTextIdnumber.setEnabled(true);
                    clearallfields(v);
                }else {
                    //for Save new enrollment
                    if (uniqueID()) {
                        studentArchives[counter] = new StudentArchive(editTextIdnumber.getText().toString(), editTextLastname.getText().toString(),
                                editTextFirstname.getText().toString(), editTextMI.getText().toString(), editTextCourse.getText().toString(),
                                radioButtonYear.getText().toString(), radioButtonGender.getText().toString(), docsSubmit);
                        counter++;
                        Toast.makeText(MainActivity2.this, "Sucessfully Saved", Toast.LENGTH_SHORT).show();
                        clearallfields(v);
                    }else {
                        Toast.makeText(MainActivity2.this, "The Id number is Exist", Toast.LENGTH_SHORT).show();
                        editTextIdnumber.setText("");
                    }
                }
            }
        });

        //Search Button
        searcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextSearch.getText().toString().equals("")){
                    Toast.makeText(MainActivity2.this, "Pls fill the search field", Toast.LENGTH_SHORT).show();
                }else{
                    boolean idexist = false;
                    String getYear = "", getGender = "";
                    String[] getSubmitDocs = new String[3];
                    for(int i=0; i<counter; i++){
                        if(studentArchives[i].getId().equals(editTextSearch.getText().toString())){
                            editTextIdnumber.setText(studentArchives[i].getId());
                            editTextLastname.setText(studentArchives[i].getLastname());
                            editTextFirstname.setText(studentArchives[i].getFirstname());
                            editTextMI.setText(studentArchives[i].getMi());
                            editTextCourse.setText(studentArchives[i].getCourse());
                            getYear = studentArchives[i].getYear();
                            getGender = studentArchives[i].getGender();
                            getSubmitDocs = studentArchives[i].getDocsSubmit();
                            idexist = true;
                            break;
                        }
                    }
                    if(idexist){
                        saveUpdatebtn.setText("Edit/Update");
                        //Display Selected RadioButton in Year
                        int countradioGroupYear = radioGroupYear.getChildCount();
                        for (int i = 0; i < countradioGroupYear; i++) {
                            v = radioGroupYear.getChildAt(i);
                            if (v instanceof RadioButton) {
                               if(((RadioButton) v).getText().toString().equals(getYear)) {
                                   ((RadioButton) v).setChecked(true);
                                   break;
                               }
                            }
                        }
                        //Display Selected Radio Button in gender
                        int countradioButtonGender = radioGroupGender.getChildCount();
                        for (int i = 0; i < countradioButtonGender; i++) {
                            v = radioGroupGender.getChildAt(i);
                            if (v instanceof RadioButton) {
                                if(((RadioButton) v).getText().toString().equals(getGender)) {
                                    ((RadioButton) v).setChecked(true);
                                    break;
                                }
                            }
                        }
                        //Display Selected Checkbox from the Searched ID
                        int countdocsSubmit =layoutdocsSubmit.getChildCount();
                        for(int x = 0; x < getSubmitDocs.length; x++){
                            for (int i = 0; i < countdocsSubmit; i++) {
                                v = layoutdocsSubmit.getChildAt(i);
                                if (v instanceof CheckBox) {
                                    if(((CheckBox) v).getText().toString().equals(getSubmitDocs[x])){
                                        ((CheckBox) v).setChecked(true);
                                        break;
                                    }
                                }
                            }

                        }
                        editTextIdnumber.setEnabled(false);

                    }else{
                        Toast.makeText(MainActivity2.this, "The ID NUmber does not exist", Toast.LENGTH_SHORT).show();}
                    editTextSearch.setText("");
                }
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearallfields(v);
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("counter", counter);
                startActivity(intent);
            }
        });
    }

    private boolean completefillup()
    {
        int year = radioGroupYear.getCheckedRadioButtonId();
        int gender = radioGroupGender.getCheckedRadioButtonId();

        if(year ==-1 || gender == -1 || editTextIdnumber.getText().toString().equals("") || editTextLastname.getText().toString().equals("") ||
                editTextFirstname.getText().toString().equals("") || editTextCourse.getText().toString().equals(""))
        {
            return false;
        }
        return true;
    }

    private boolean uniqueID(){
        for (int i = 0; i < counter; i++)
        {
            if(studentArchives[i].getId().equals(editTextIdnumber.getText().toString())){
                return false;
            }
        }
        return true;
    }

    private void clearallfields(View v){
        editTextIdnumber.setEnabled(true);
        editTextIdnumber.setText("");
        editTextFirstname.setText("");
        editTextLastname.setText("");
        editTextMI.setText("");
        editTextCourse.setText("");
        radioGroupGender.clearCheck();
        radioGroupYear.clearCheck();
        saveUpdatebtn.setText("Save");
        int countdocsSubmit =layoutdocsSubmit.getChildCount();
        for (int i = 0; i < countdocsSubmit; i++) {
            v = layoutdocsSubmit.getChildAt(i);
            if (v instanceof CheckBox) {
                ((CheckBox) v).setChecked(false);
            }
        }

    }

    public static StudentArchive[] getStudentArchive(){
        return studentArchives;
    }

}