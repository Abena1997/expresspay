package com.expresspay.access_control;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoCheckedInGuestFragment extends Fragment {
    private FloatingActionButton floatingBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_checked_in_guest,container,false);
         floatingBtn = view.findViewById(R.id.fab);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckInActivity.class);
                getContext().startActivity(intent);
            }
        });
    }


//    private void navigate() {
//        Fragment fragmentVisitorDetails = new VisitorDetailsfragment();
//
//        FragmentTransaction transactionVisitorDetails = getFragmentManager().beginTransaction();
//
//        transactionVisitorDetails.add(R.id.fragment_container_fl, fragmentVisitorDetails);
//        transactionVisitorDetails.addToBackStack(null);
//
//        transactionVisitorDetails.commit();
//    }
}

