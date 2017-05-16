package droidbountyhunter.training.edu.droidbountyhunter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by darkgeat on 15/05/2017.
 */

public class DetalleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // Se obtiene la informacion del Intent...
        Bundle oExt = this.getIntent().getExtras();
        // Se pone el nombre del Fugitivo como titulo...
        this.setTitle(oExt.getString("title"));
        setContentView(R.layout.activity_detalle);
        TextView oMsg = (TextView)this.findViewById(R.id.lblMsg);
        // Se identifica si es Fugitivo o Capturado para el mensaje...
        if(oExt.getInt("mode") == 0)
            oMsg.setText("El fugitivo sigue suelto...");
        else
            oMsg.setText("Atrapado!!!");
    }

}
