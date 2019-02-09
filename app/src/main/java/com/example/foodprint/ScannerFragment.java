package com.example.foodprint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScannerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScannerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScannerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PAGE = "ARG_PAGE";

    public static String codeContent;
    public String codeFormat;

    private OnFragmentInteractionListener mListener;


    public ScannerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page
     * @return A new instance of fragment ScannerFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static ScannerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ScannerFragment fragment = new ScannerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void onActivityResult(int requestCode,int resultCode,Intent intent) {
        IntentResult scanResult =
                IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        codeContent = scanResult.getContents();
        codeFormat = scanResult.getFormatName();
    }

    private void onBarCodeScanned(String foodName) {
        int defaultExp = 0;
        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();
        c.add(Calendar.DATE, defaultExp);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = dateFormat.format(currentDate);
        MainActivity.setFoodItem(foodName, strDate);
        Log.d("printies strDate date", strDate);
        Log.d("printies strDate food", foodName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scanner, container, false);
        onBarCodeScanned("banana");
        Log.d("printies", "banana bitches");
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            IntentIntegrator integrator =
                    new IntentIntegrator(this.getActivity()).forSupportFragment(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
            integrator.setCameraId(0);
            integrator.initiateScan();
        }
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
