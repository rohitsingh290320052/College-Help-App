package com.example.itsadeal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    Button agent1,agent2,agent3,agent4,agent5,agent6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        String p=intent.getStringExtra(MainActivity.product_name);
        String t=intent.getStringExtra(MainActivity.time_range);
        String ur=intent.getStringExtra(MainActivity.university_roll_no);
        agent1=findViewById(R.id.agent1);
        agent2=findViewById(R.id.agent2);
        agent3=findViewById(R.id.agent3);
        agent4=findViewById(R.id.agent4);
        databasehelper datahelp=databasehelper.getdb(this);
        agent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imsg= new Intent(Intent.ACTION_SENDTO);
                imsg.setData(Uri.parse("smsto:"+Uri.encode("11111")));
                imsg.putExtra("sms_body","product: "+p+" time range: "+t);
                Toast.makeText(MainActivity2.this, "your order is confirmed, Kindly send us a message for confirmation", Toast.LENGTH_SHORT).show();
                startActivity(imsg);

            }
        });
        agent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imsg= new Intent(Intent.ACTION_SENDTO);
                imsg.setData(Uri.parse("smsto:"+Uri.encode("11111")));
                imsg.putExtra("sms_body","product: "+p+" time range: "+t);
                Toast.makeText(MainActivity2.this, "your order is confirmed, Kindly send us a message for confirmation", Toast.LENGTH_SHORT).show();
                startActivity(imsg);
                datahelp.expense_dao().addTx(
                        new Expense(p,t,ur)

                );
                ArrayList<Expense> arrExpense= (ArrayList<Expense>) datahelp.expense_dao().getallexpense();

                for(int i=0;i<arrExpense.size();i++){
                    Log.d("Data","p"+arrExpense.get(i).getProduct()+"t" + arrExpense.get(i).getTime()+"ur"+ arrExpense.get(i).getRoll());
                }

            }
        });
        agent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imsg= new Intent(Intent.ACTION_SENDTO);
                imsg.setData(Uri.parse("smsto:"+Uri.encode("11111")));
                imsg.putExtra("sms_body","product: "+p+" time range: "+t);
                Toast.makeText(MainActivity2.this, "your order is confirmed, Kindly send us a message for confirmation", Toast.LENGTH_SHORT).show();
                startActivity(imsg);
                datahelp.expense_dao().addTx(
                        new Expense(p,t,ur)

                );
                ArrayList<Expense> arrExpense= (ArrayList<Expense>) datahelp.expense_dao().getallexpense();

                for(int i=0;i<arrExpense.size();i++){
                    Log.d("Data","p"+arrExpense.get(i).getProduct()+"t" + arrExpense.get(i).getTime()+"ur"+ arrExpense.get(i).getRoll());
                }

            }
        });
        agent4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imsg= new Intent(Intent.ACTION_SENDTO);
                imsg.setData(Uri.parse("smsto:"+Uri.encode("11111")));
                imsg.putExtra("sms_body","product: " + p + "time range:" + t);
                startActivity(imsg);
                datahelp.expense_dao().addTx(
                        new Expense(p,t,ur)

                );
                ArrayList<Expense> arrExpense= (ArrayList<Expense>) datahelp.expense_dao().getallexpense();

                for(int i=0;i<arrExpense.size();i++){
                    Log.d("Data","p"+arrExpense.get(i).getProduct()+"t" + arrExpense.get(i).getTime()+"ur"+ arrExpense.get(i).getRoll());
                }

            }
        });

        /*    confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datahelp.expense_dao().addTx(
                        new Expense(p,t,ur)

                );
                ArrayList<Expense> arrExpense= (ArrayList<Expense>) datahelp.expense_dao().getallexpense();

                for(int i=0;i<arrExpense.size();i++){
                    Log.d("Data","p"+arrExpense.get(i).getProduct()+"t" + arrExpense.get(i).getTime()+"ur"+ arrExpense.get(i).getRoll());
                }
                Intent intent=new Intent(MainActivity2.this, final_page.class);
                startActivity(intent);

            }
        });

*/
    }
}