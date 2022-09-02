package com.renansn.appcalculadora;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText edit_valora, edit_valorb;
    Button btnAdd, viewData, btnSub, btnDiv, btnMultiplica;
    int valora, valorb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        myDB = new DatabaseHelper( this );

        edit_valora = findViewById( R.id.valora );
        edit_valorb = findViewById( R.id.valorb );


        btnAdd = findViewById( R.id.button );
        viewData = findViewById( R.id.button2 );
        btnSub = findViewById( R.id.button3 );
        btnDiv = findViewById( R.id.button4 );
        btnMultiplica = findViewById( R.id.button5);

        AddData();
        viewData();


    }

    public void AddData(){

        btnAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_valora.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira o primeiro numero", Toast.LENGTH_SHORT).show();
                    return;
                }else if
                (edit_valorb.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira o segundo numero", Toast.LENGTH_SHORT).show();
                    return;
                }


                valora = Integer.parseInt(edit_valora.getText().toString());
                valorb = Integer.parseInt(edit_valorb.getText().toString());

                int resultado = valora + valorb;
                String operacao = "+";

                edit_valora.setText("");
                edit_valorb.setText("");


                Date date1 = new Date();
                String date = DateFormat.getDateTimeInstance(). format(date1);

                boolean isInserted = myDB.instertData( valora, valorb, resultado, date, operacao);

                if(isInserted == true){
                    Toast.makeText( MainActivity.this, "Cálculo armazenado com sucesso", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( MainActivity.this, "Cálculo não armazenado", Toast.LENGTH_SHORT ).show();
            }
        });
        btnSub.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_valora.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira o primeiro numero", Toast.LENGTH_SHORT).show();
                    return;
                }else if
                (edit_valorb.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira o segundo numero", Toast.LENGTH_SHORT).show();
                    return;
                }


                valora = Integer.parseInt(edit_valora.getText().toString());
                valorb = Integer.parseInt(edit_valorb.getText().toString());

                int resultado = valora - valorb;
                String operacao = "-";

                edit_valora.setText("");
                edit_valorb.setText("");



                Date date1 = new Date();
                String date = DateFormat.getDateTimeInstance(). format(date1);

                boolean isInserted = myDB.instertData( valora, valorb, resultado, date, operacao);

                if(isInserted == true){
                    Toast.makeText( MainActivity.this, "Cálculo armazenado com sucesso", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( MainActivity.this, "Cálculo não armazenado", Toast.LENGTH_SHORT ).show();
            }

    });
        btnDiv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_valora.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira o primeiro numero", Toast.LENGTH_SHORT).show();
                    return;
                }else if
                (edit_valorb.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira o segundo numero", Toast.LENGTH_SHORT).show();
                    return;
                }


                valora = Integer.parseInt(edit_valora.getText().toString());
                valorb = Integer.parseInt(edit_valorb.getText().toString());

                int resultado = valora / valorb;
                String operacao = "/";

                edit_valora.setText("");
                edit_valorb.setText("");



                Date date1 = new Date();
                String date = DateFormat.getDateTimeInstance(). format(date1);

                boolean isInserted = myDB.instertData( valora, valorb, resultado, date, operacao);

                if(isInserted == true){
                    Toast.makeText( MainActivity.this, "Cálculo armazenado com sucesso", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( MainActivity.this, "Cálculo não armazenado", Toast.LENGTH_SHORT ).show();
            }

        });

        btnMultiplica.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_valora.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira o primeiro numero", Toast.LENGTH_SHORT).show();
                    return;
                }else if
                (edit_valorb.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira o segundo numero", Toast.LENGTH_SHORT).show();
                    return;
                }



                valora = Integer.parseInt(edit_valora.getText().toString());
                valorb = Integer.parseInt(edit_valorb.getText().toString());

                int resultado = valora * valorb;
                String operacao = "+";
                edit_valora.setText("");
                edit_valorb.setText("");



                Date date1 = new Date();
                String date = DateFormat.getDateTimeInstance(). format(date1);

                boolean isInserted = myDB.instertData( valora, valorb, resultado, date, operacao);

                if(isInserted == true){
                    Toast.makeText( MainActivity.this, "Cálculo armazenado com sucesso", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( MainActivity.this, "Cálculo não armazenadod", Toast.LENGTH_SHORT ).show();
            }

        });



    }


    public void viewData(){

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getData();

                if (res.getCount() == 0) {
                    showMessage("Error", "Data not found!");
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID: " + res.getString(0) + "\n");
                        buffer.append("Valor A: " + res.getString(1) + "\n");
                        buffer.append("Valor B: " + res.getString(2) + "\n");
                        buffer.append("Operacao: " + res.getString(5) + "\n");
                        buffer.append("Resultado: " + res.getString(3) + "\n");
                        buffer.append("Data e hora:\n" + res.getString(4) + "\n\n");
                    }

                    showMessage("Data", buffer.toString());

                }
            }
        });
    }




    private void showMessage (String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

