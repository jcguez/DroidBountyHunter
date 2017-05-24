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
    private int iMode;
    private ListView tList;

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
        iMode = aArgs.getInt(ListFragment.ARG_SECTION_NUMBER);

        tList = ((ListView) iView.findViewById(R.id.bhList));

        UpdateList();

        // Se genera el Listener para el detalle de cada elemento...
        tList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> aList, View vItem,
                                    int iPosition, long l) {
                Intent oW = new Intent();
                String[][] aDat = (String[][])aList.getTag();
                oW.setClass(getActivity(), DetalleActivity.class);
                oW.putExtra("title", ((TextView) vItem).getText());
                oW.putExtra("mode", iMode);
                oW.putExtra("id", aDat[iPosition][0]);
                startActivity(oW);
            }
        });

        return iView;
    }

    public void UpdateList(){
        String[][] aRef = Home.oDB.ObtenerFugitivos(iMode == 1);
        if (aRef != null){
            String[] aData = new String[aRef.length];
            for (int iCnt = 0 ; iCnt < aRef.length ; iCnt++){
                aData[iCnt] = aRef[iCnt][1];
            }
            ArrayAdapter<String> aList = new ArrayAdapter<String>(
                    getActivity(), R.layout.row_list, aData);
            tList.setTag(aRef);
            tList.setAdapter(aList);
        }
    }

}
