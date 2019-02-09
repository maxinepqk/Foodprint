package com.example.foodprint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;


    TextView thedate;
    TextView btngocalendar;

    private String foodItem;
    private String date;

//    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page Parameter.
     * @return A new instance of fragment Calendar.
     */
    // TODO: Rename and change types and number of parameters

    public static CalendarFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // date and calendar view
        thedate = (TextView) view.findViewById(R.id.date);
        btngocalendar = (Button) view.findViewById(R.id.btngocalendar);

        Intent incoming = getActivity().getIntent();
        date = incoming.getStringExtra("date");
        if (date != null) {
            thedate.setText(date);
            Log.d("printies", "i set the fucking date");
            foodItem = MainActivity.getFoodItem(date);
            Log.d("printies CF food", ""+foodItem);
        }

        // Date operations

        btngocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CalendarActivity.class);
                startActivity(intent);
            }
        });

        // Showing expiring food item
        TextView foodItemView = view.findViewById(R.id.foodItemView);
        foodItem = MainActivity.getFoodItem(date);
        if (foodItem != null) foodItemView.setText(foodItem);

        return view;
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            if (date != null) {
//                foodItem = MainActivity.getFoodItem(date);
//                Log.d("printies CF date", ""+date);
//
//            }
//        }
//    }

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
