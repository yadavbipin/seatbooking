package com.at.seatbooking;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BookingFragment extends Fragment implements OnSeatSelected{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

     Context globalContext = null;
    private String mParam1;
    private String mParam2;
    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;
    public BookingFragment() {
        // Required empty public constructor
    }

    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        globalContext = this.getActivity();
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        txtSeatSelected = (TextView)view.findViewById(R.id.txt_seat_selected);

        List<AbstractItem> items = new ArrayList<>();
        for (int i=0; i<30; i++) {

            if (i%COLUMNS==0 || i%COLUMNS==4) {
                items.add(new EdgeItem(String.valueOf(i)));
            } else if (i%COLUMNS==1 || i%COLUMNS==3) {
                items.add(new CenterItem(String.valueOf(i)));
            } else {
                items.add(new EmptyItem(String.valueOf(i)));
            }
        }

        GridLayoutManager manager = new GridLayoutManager(getContext(), COLUMNS);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        AirplaneAdapter adapter = new AirplaneAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onSeatSelected(int count) {
        txtSeatSelected.setText("Book "+count+" seats");
    }
}