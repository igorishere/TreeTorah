package br.com.treetorah.igor.treetorah;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Ref;
import java.sql.SQLDataException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.treetorah.igor.treetorah.database.DataBase;
import br.com.treetorah.igor.treetorah.model.Action;
import br.com.treetorah.igor.treetorah.model.Reforestation;
import br.com.treetorah.igor.treetorah.model.TableEstructure;

public class FormPolimorf extends AppCompatActivity {

    private Spinner spinner;
    private EditText editQtd,editVol;
    private TextView txtArvores,txtValor;
    private Button btnSave;
    private NumberFormat n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_new_register);
        //data by intent
        Intent intent = getIntent();
        final Bundle data =  intent.getExtras();
        final String action =  data.getString("action");

        //screen itens
        spinner =  findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.teste,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        editQtd = findViewById(R.id.editQtd);
        editVol = findViewById(R.id.editVol);

        txtArvores =  findViewById(R.id.textArvores);
        txtValor =  findViewById(R.id.textValor);

        btnSave = findViewById(R.id.btnSaveData);

        //database
        final DataBase db = new DataBase(FormPolimorf.this);
        //reforestation
        final Reforestation ref =  new Reforestation();


        if( action.equals(Action.UPDATE) )
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Editar");

            Long id = data.getLong("id");

            String sql_query = "SELECT * FROM "+TableEstructure.Table.TABLE_NAME +" WHERE "+TableEstructure.Table.COLUMN_ID+"="+id;
            Cursor cursor = db.query(sql_query);

            while(cursor.moveToNext()){
                spinner.setSelection( adapter.getPosition( cursor.getString( cursor.getColumnIndex(TableEstructure.Table.COLUMN_STATE)) ));
                editQtd.setText( String.valueOf( cursor.getInt( cursor.getColumnIndex(TableEstructure.Table.COLUMN_CUTED_TREES) ) ));
                editVol.setText( String.valueOf( cursor.getDouble( cursor.getColumnIndex(TableEstructure.Table.COLUMN_VOLUME_CUTED_TREES) ) ));
                txtArvores.setText(  String.valueOf( cursor.getInt( cursor.getColumnIndex(TableEstructure.Table.COLUMN_TREES_FOR_PLANT) ) ) );
                txtValor.setText( String.valueOf( cursor.getDouble( cursor.getColumnIndex(TableEstructure.Table.COLUMN_VALUE_TO_PAY) ) ));
            }
        }
        else
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Novo registro");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ref.setId( data.getLong("id")  );
                    ref.setState( spinner.getSelectedItem().toString() );
                    ref.setCutedTrees( Integer.parseInt( editQtd.getText().toString() ) );
                    ref.setVolumeCutedTrees( Double.parseDouble( editVol.getText().toString() ) );
                    ref.setTreesForPlant(Integer.parseInt( formatData( txtArvores.getText().toString() ) ));
                    ref.setValueToPay(Double.parseDouble( formatData( txtValor.getText().toString() ) ) );

                if( action.equals(Action.UPDATE) ){
                    db.updateData( ref );
                    db.close();
                    Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_LONG).show();
                }else{
                    SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd/MM/yyyy");
                    Date todayDate = new Date();
                    String atualDate = simpleDateFormat.format(todayDate);

                    ref.setDate( atualDate );

                    db.saveData(ref);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_LONG).show();
                }
                Intent i =  new Intent(FormPolimorf.this,MainActivity.class);
                startActivity(i);
            }
        });
        editVol.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if( editVol.getText().length()==0 ){
                    txtValor.setText("R$ 0.00");
                    txtArvores.setText("0 Mudas");
                }else{

                    int qtdArvores = (int) Double.parseDouble( editVol.getText().toString() ) * 6;
                    txtArvores.setText( String.valueOf(qtdArvores)+" Mudas" );

                    double valor =  Double.parseDouble( editVol.getText().toString() ) * 0.75;
                    txtValor.setText("R$ "+String.format("%.2f",valor));
                }
            }

        });
    }

    public  boolean EmptyInput(){
        if( editVol.getText().length()==0 || editQtd.getText().length()==0 ) {
            return true;
        }else{
            return false;
        }
    }

    public String formatData( String data ){
        data = data.replaceAll( "[^\\.0-9]","");
        return data;
    }
}

