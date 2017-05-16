package droidbountyhunter.training.edu.droidbountyhunter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

/**
 * Created by darkgeat on 15/05/2017.
 */

public class AboutFragment extends Fragment {
    public AboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Se hace referencia al Fragment generado por XML en los Layouts y
        // se instanc’a en una View...
        View iView = inflater.inflate(R.layout.about_fragment, container,
                false);
        // Se accede a los elementos ajustables del Fragment...
        RatingBar rbApp = ((RatingBar) iView.findViewById(R.id.ratingApp));

        String sRating = "0.0"; // Variable para lectura del Rating guardado
        // en el property.
        try {
            if (System.getProperty("rating") != null) {
                sRating = System.getProperty("rating");
            }
        } catch (Exception ex) {
        }
        if (sRating.isEmpty())
            sRating = "0.0";

        rbApp.setRating(Float.parseFloat(sRating));
        // Listener al Raiting para la actualizacion de la property...
        rbApp.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                System.setProperty("rating", String.valueOf(rating));
                ratingBar.setRating(rating);
            }
        });

        return iView;
    }

}
