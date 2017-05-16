package droidbountyhunter.training.edu.droidbountyhunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by darkgeat on 15/05/2017.
 */

public class ListFragment extends Fragment {
    public static final String ARG_SECTION_TEXT = "section_text";
    public static final String ARG_SECTION_NUMBER = "section_number";

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Se hace referencia al Fragment generado por XML en los Layouts y
        // se instancia en una View...
        View iView = inflater.inflate(R.layout.list_fragment, container,
                false);
        Bundle aArgs = this.getArguments();
        final int iMode = aArgs.getInt(ListFragment.ARG_SECTION_NUMBER);

        ListView tlList = ((ListView) iView.findViewById(R.id.bhList));
        String[] aData = new String[6];
        // Datos en HardCode...
        aData[0] = "Armando Olmos";
        aData[1] = "Guillermo Ortega";
        aData[2] = "Carlos Martinez";
        aData[3] = "Moises Rivas";
        aData[4] = "Adrian Rubiera";
        aData[5] = "Victor Medina";

        ArrayAdapter<String> aList = new ArrayAdapter<String>(
                getActivity(), R.layout.row_list, aData);
        tlList.setAdapter(aList);
        // Se genera el Listener para el detalle de cada elemento...
        tlList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> aList, View vItem,
                                    int iPosition, long l) {
                Intent oW = new Intent();
                oW.setClass(getActivity(), DetalleActivity.class);
                oW.putExtra("title", ((TextView) vItem).getText());
                oW.putExtra("mode", iMode);
                startActivity(oW);
            }
        });

        return iView;
    }

}
