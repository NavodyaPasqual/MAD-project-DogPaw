package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Photography_second extends AppCompatActivity {

    EditText txtNo, txtBreed, txtTime, txtDate, txtInfo;
    RadioButton weight1, weight2, weight3, traditional, candid, action, artistic, digital, cd, physical;
    DatabaseReference dbRef;
    Photography photography;
    Button save;
    public static final String EXTRA_NUMBER = "com.example.dogpaw.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_second);
        Intent intent = getIntent();

        txtNo = findViewById(R.id.editText1);
        txtBreed = findViewById(R.id.editText2);
        txtTime = findViewById(R.id.editText4);
        txtDate = findViewById(R.id.editTextDate);
        txtInfo = findViewById(R.id.editText5);
        weight1 = findViewById(R.id.radioButton);
        weight2 = findViewById(R.id.radioButton2);
        weight3 = findViewById(R.id.radioButton3);
        traditional = findViewById(R.id.radioButton4);
        candid = findViewById(R.id.radioButton5);
        action = findViewById(R.id.radioButton6);
        artistic = findViewById(R.id.radioButton7);
        digital = findViewById(R.id.radioButton9);
        cd = findViewById(R.id.radioButton10);
        physical = findViewById(R.id.radioButton11);
        save = findViewById(R.id.button);

        photography = new Photography();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Photography");

                Intent intent = new Intent(Photography_second.this, Payment.class);

                //Extra no
                EditText editText = (EditText) findViewById(R.id.editText1);
                int number = Integer.parseInt(editText.getText().toString());
                intent.putExtra(EXTRA_NUMBER, number);
                startActivity(intent);

                try {
                    if (TextUtils.isEmpty(txtNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter number of dogs", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtTime.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter preffered time", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a date to start", Toast.LENGTH_SHORT).show();
                    else {
                        photography.setNo(Integer.parseInt(txtNo.getText().toString().trim()));
                        photography.setBreed(txtBreed.getText().toString().trim());
                        if(weight1.isChecked()){
                            photography.setWeight(weight1.getText().toString().trim());
                        }
                        else if(weight2.isChecked()){
                            photography.setWeight(weight2.getText().toString().trim());
                        }
                        else{
                            photography.setWeight(weight3.getText().toString().trim());
                        }
                        if(traditional.isChecked()){
                            photography.setType(traditional.getText().toString().trim());
                        }
                        else if(candid.isChecked()){
                            photography.setType(candid.getText().toString().trim());
                        }
                        else if(action.isChecked()){
                            photography.setType(action.getText().toString().trim());
                        }
                        else{
                            photography.setType(artistic.getText().toString().trim());
                        }
                        photography.setTime(txtTime.getText().toString().trim());
                        if(digital.isChecked()){
                            photography.setFormat(digital.getText().toString().trim());
                        }
                        else if(cd.isChecked()){
                            photography.setFormat(cd.getText().toString().trim());
                        }
                        else{
                            photography.setFormat(physical.getText().toString().trim());
                        }
                        photography.setDate(txtDate.getText().toString().trim());
                        photography.setInfo(txtInfo.getText().toString().trim());

                        //dbRef.push().setValue(photography);
                        dbRef.child("photography1").setValue(photography);
                        Toast.makeText(getApplicationContext(),"Your photography is sucessfully booked.",Toast.LENGTH_SHORT).show();
                        clearControls();
                    }


                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid input for number of dogs.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void clearControls(){
        txtNo.setText("");
        txtBreed.setText("");
        txtTime.setText("");
        txtDate.setText("");
        txtInfo.setText("");
        weight1.setChecked(false);
        weight2.setChecked(false);
        weight3.setChecked(false);
        traditional.setChecked(false);
        candid.setChecked(false);
        action.setChecked(false);
        artistic.setChecked(false);
        digital.setChecked(false);
        cd.setChecked(false);
        physical.setChecked(false);

    }
}