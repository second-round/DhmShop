package dhm.com.dhmshop.fragment.classification;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dhm.com.dhmshop.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificationFragment extends Fragment {


    public ClassificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classification, container, false);
    }

}