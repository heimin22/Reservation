package com.example.ungabhotel.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungabhotel.databinding.FragmentHomeBinding;
import com.example.ungabhotel.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Button searchButton;
    private ListView bookingListView;
    private DBHelper dbHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        dbHelper = new DBHelper(requireContext());

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        InitializeComponents();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void InitializeComponents()
    {
        searchButton = binding.searchBtn;
        searchButton.setOnClickListener(view -> openSearchActivity());

        bookingListView = binding.bookingLists;
        DisplayBooking();
    }

    // display the initial list of available booking
    private void DisplayBooking()
    {
        List<Booking> availableBookings = dbHelper.getBookings();
        List<String> bookingStrings = new ArrayList<>();

        for(Booking booking : availableBookings)
        {
            bookingStrings.add("Room: " + booking.getRoom().getRoomNumber());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
               bookingStrings
        );
        bookingListView.setAdapter(adapter);

        bookingListView.setOnItemClickListener((parent, view, pos, id) -> {
            Booking selectedBooking = availableBookings.get(pos);

            openBookingDetails(selectedBooking);
        });
    }

    // switch to booking details act and passing the selected booking id
    private void openBookingDetails(Booking booking)
    {
        Intent intent = new Intent(getActivity(), BookingDetailsActivite.class);
        intent.putExtra("BOOKING_ID", booking.getId());
        startActivity(intent);
    }

    // navigate to search activity
    private void openSearchActivity()
    {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}