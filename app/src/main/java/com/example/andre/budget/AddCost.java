package com.example.andre.budget;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddCost extends AppCompatActivity {
    int year,month,day;
    Button pickerButton;
    TextView dateText;
    Calendar c;
    DatePickerDialog dpd;
    Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcost);
        pickerButton = (Button) findViewById(R.id.pickerButton);
        dateText = (TextView) findViewById(R.id.dateText);

        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c= Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);


                dpd = new DatePickerDialog(AddCost.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                selectedDate = new GregorianCalendar(year, month, day).getTime();
                                Toast.makeText(AddCost.this,
                                        selectedDate.toString(), Toast.LENGTH_LONG).show();
                                dateText.setText(day + "/" + (month+1) + "/" + year);
                            }
                        }, year, month, day);
                dpd.show();
            }
        });


    }
}
