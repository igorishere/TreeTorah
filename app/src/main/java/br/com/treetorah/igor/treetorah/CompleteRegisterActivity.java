package br.com.treetorah.igor.treetorah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.treetorah.igor.treetorah.database.DataBase;
import br.com.treetorah.igor.treetorah.model.Action;
import br.com.treetorah.igor.treetorah.model.TableEstructure;

public class CompleteRegisterActivity extends AppCompatActivity{


    private TextView date,state,cutedTrees,volCutTrees,treesForPlant,valueToPay;
    private DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_register);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        Long id = data.getLong("id");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Reflorestamento #"+id);

        date =  findViewById(R.id.textDate);
        state =  findViewById(R.id.textState);
        cutedTrees = findViewById(R.id.textCutedTrees);
        volCutTrees = findViewById(R.id.textVol);
        treesForPlant = findViewById(R.id.textForPlantTree);
        valueToPay = findViewById(R.id.textValueToPay);

        String sql_query = "SELECT * FROM "+TableEstructure.Table.TABLE_NAME +" WHERE "+TableEstructure.Table.COLUMN_ID+"="+id;
        DataBase db =  new DataBase(CompleteRegisterActivity.this);
        Cursor cursor = db.query(sql_query);

        while(cursor.moveToNext()) {
            date.setText(  cursor.getString(  cursor.getColumnIndex(TableEstructure.Table.COLUMN_DATE) ));
            state.setText(  cursor.getString(  cursor.getColumnIndex(TableEstructure.Table.COLUMN_STATE) ));
            cutedTrees.setText(  cursor.getString(  cursor.getColumnIndex(TableEstructure.Table.COLUMN_CUTED_TREES) ));
            volCutTrees.setText(  cursor.getString(  cursor.getColumnIndex(TableEstructure.Table.COLUMN_VOLUME_CUTED_TREES) ));
            valueToPay.setText(  cursor.getString(  cursor.getColumnIndex(TableEstructure.Table.COLUMN_VALUE_TO_PAY) ));
            treesForPlant.setText(  cursor.getString(  cursor.getColumnIndex(TableEstructure.Table.COLUMN_TREES_FOR_PLANT) ));
        }
    }
}
