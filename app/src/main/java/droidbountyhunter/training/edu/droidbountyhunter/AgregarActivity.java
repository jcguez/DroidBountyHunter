package droidbountyhunter.training.edu.droidbountyhunter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by darkgeat on 15/05/2017.
 */

public class AgregarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
    }

    public void onSaveClick(View view) {
        TextView oTxtN = (TextView) findViewById(R.id.txtNew);
        if (!oTxtN.getText().toString().isEmpty()){
            Home.oDB.InsertFugitivo(oTxtN.getText().toString());
            setResult(0);
            finish();
        }else {
            new AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setMessage("Favor de Capturar el nombre del fugitivo.")
                    .show();
        }
    }
}
