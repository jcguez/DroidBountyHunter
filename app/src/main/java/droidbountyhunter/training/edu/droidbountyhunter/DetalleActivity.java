package droidbountyhunter.training.edu.droidbountyhunter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by darkgeat on 15/05/2017.
 */

public class DetalleActivity extends AppCompatActivity {

    private String sID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Se obtiene la informacion del Intent...
        Bundle oExt = getIntent().getExtras();
        // Se pone el nombre del Fugitivo como titulo...
        setTitle(oExt.getString("title") + " - [" + oExt.getString("id") + "]");
        sID = oExt.getString("id");
        setContentView(R.layout.activity_detalle);

        TextView oMsg = (TextView)this.findViewById(R.id.lblMsg);
        // Se identifica si es Fugitivo o Capturado para el mensaje...
        if(oExt.getInt("mode") == 0)
            oMsg.setText("El fugitivo sigue suelto...");
        else {
            Button oBtn1 = (Button)findViewById(R.id.btnCap);
            oBtn1.setVisibility(View.GONE);
            oMsg.setText("Atrapado!!!");
        }
    }

    public void onDeleteClick(View view) {
        Home.oDB.DeleteFugitivo(sID);
        setResult(0);
        finish();
    }

    public void onCaptureClick(View view) {
        Home.oDB.UpdateFugitivo("1", sID);
        setResult(0);
        finish();
    }
}
