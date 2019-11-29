package com.zenith.hotelbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private Spinner spin, spin1;
    private TextView ckin, ckout, country, cost, tovat, sc, tocost, netto, roomcost, roomno, totalday;
    private EditText name, room, adult, children;
    private Button btnc;

    int y1,y2,m1,m2,d1,d2, diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        spin=findViewById(R.id.spin);
        spin1=findViewById(R.id.spin1);
        //input data binding
        ckin=findViewById(R.id.ckin);
        ckout=findViewById(R.id.ckout);
        country=findViewById(R.id.country);
        name=findViewById(R.id.name);
        adult=findViewById(R.id.adult);
        children=findViewById(R.id.children);
        room=findViewById(R.id.room);
        btnc=findViewById(R.id.btnc);
        //output binding
        cost=findViewById(R.id.cost);
        tovat=findViewById(R.id.vat);
        tocost=findViewById(R.id.tcost);
        netto=findViewById(R.id.nett);
        roomcost=findViewById(R.id.roomcost);


        //passing array to country spinner
        String countries[] = {"Nepal", "USA", "UK", "China", "Japan", "Netherland", "Canada"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,countries);
        spin.setAdapter(arrayAdapter);

        //passing array to Room Type spinner
        String roomType[]={"Deluxe","Platinum","Presidental"};
        final ArrayAdapter arrayAdapter1=new ArrayAdapter(this,
                android.R.layout.select_dialog_item,roomType);
        spin1.setAdapter(arrayAdapter1);

        //making onclick to check in date
        ckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });
        ckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDate();
            }
        });

        //button fot total
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText())) {
                    name.setError("Please Enter Name");
                    return;
                } else if (TextUtils.isEmpty(room.getText())) {
                    room.setText("Enter no of Rooms to Book");
                    return;
                } else if (TextUtils.isEmpty(adult.getText())) {
                    adult.setText("Enter no. of adults that will be staying");
                    return;
                } else if (TextUtils.isEmpty(children.getText())) {
                    children.setText("Enter no. pf children");
                    return;
                } else if (TextUtils.isEmpty(ckin.getText())) {
                    ckin.setText("Enter Check in date");
                    return;
                } else if (TextUtils.isEmpty(ckout.getText())) {
                    ckout.setText("Enter Check out date");
                    return;
                }
                //Calculate no. if days stayed
                Calendar cale1 = Calendar.getInstance();
                Calendar cale2 = Calendar.getInstance();
                cale1.set(y1, m1, d1);
                cale2.set(y2, m2, d2);
                long milis1 = cale1.getTimeInMillis();
                long milis2 = cale2.getTimeInMillis();
                long diff = milis1 - milis2;
                long diffDays = (diff / (8640000));
                int numRoom = Integer.parseInt(room.getText().toString());

                //to calculate net total
                double roomprice, totalprice;
                double vat, servicecharge, nettotal;

                String roomType = spin1.getSelectedItem().toString();

                if (roomType == "Deluxe") {
                    roomprice = 2000;
                    totalprice = roomprice * numRoom * diffDays;
                    vat = (0.13 * totalprice) + totalprice;
                    nettotal = servicecharge = (0.10 * vat) + vat;
                    cost.setText("Total cost is:" + totalprice);
                    tovat.setText("Total price with VAT:" + vat);
                    netto.setText("Final Price:" + nettotal);
                    roomcost.setText("Cost of room is:" +roomprice);
                } else if (roomType == "Platinum") {
                    roomprice = 4000;
                    totalprice = roomprice * numRoom * diffDays;
                    vat = (0.13 * totalprice) + totalprice;
                    nettotal = servicecharge = (0.10 * vat) + vat;
                    cost.setText("Total cost is:" + totalprice);
                    tovat.setText("Total price with VAT:" + vat);
                    netto.setText("Final Price:" + nettotal);
                    roomcost.setText("Cost of room is:" +roomprice);
                } else if (roomType == "Presidental") {
                    roomprice = 7000;
                    totalprice = roomprice * numRoom * diffDays;
                    vat = (0.13 * totalprice) + totalprice;
                    servicecharge = (0.10*vat);
                    nettotal = servicecharge+ vat;
                    cost.setText("Total cost is:" + totalprice);
                    tovat.setText("Total price with VAT:" + vat);
                    netto.setText("Final Price:" + nettotal);
                    roomcost.setText("Cost of room is:" +roomprice);
                }
            }
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "CheckIn Date:" + (month+1) + "/" + dayOfMonth + "/" + year;
        y1= year;
        m1= month;
        d1= dayOfMonth;

        ckin.setText(date);
    }
    private void loadDatePicker()
    {
        final Calendar c = Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }


    private void loadDate(){
        final Calendar c1= Calendar.getInstance();
        int year=c1.get(Calendar.YEAR);
        int month=c1.get(Calendar.MONTH);
        final int day = c1.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "CheckIn Date:" + (month+1) + "/" + dayOfMonth + "/" + year;
                y2= year;
                m2= month;
                d2= dayOfMonth;

                ckout.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }


}


