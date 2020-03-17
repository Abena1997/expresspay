package com.expresspay.access_control;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.expresspay.access_control.models.GuestCheckedInData;
import com.expresspay.access_control.models.GuestData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class TotalCheckedIn extends Fragment {

    //define the guestData
    private List<GuestCheckedInData> guestDataList = new ArrayList<>();

    //create objects for recyclerView and the adapter
    private RecyclerView checkedInRecyclerView;
    private GuestAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total_checked_in, container, false);

        checkedInRecyclerView = view.findViewById(R.id.checkedIn_recyclerVew);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("Checked In", "on view created called. items in list "+ guestDataList.size());

        //set the fix size to true(every item of the recycler view has a fixed size)
        checkedInRecyclerView.setHasFixedSize(true);
        checkedInRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // get guest list
        adapter = new GuestAdapter(guestDataList, getContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        checkedInRecyclerView.addItemDecoration(dividerItemDecoration);

        //set adapter to this recycler view
        checkedInRecyclerView.setAdapter(adapter);
    }

    private void fetchCheckedInGuests(){

        Realm realm = Realm.getDefaultInstance();
        //fetch data from the database
        final RealmResults<GuestCheckedInData> guestCheckedInData = realm.where(GuestCheckedInData.class)
                .equalTo("checkedOut",false)
                .findAll();

        // this tells the adapter that the data  has changed so it should reload the list
        adapter.update(guestCheckedInData);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchCheckedInGuests();
        Log.e("Checked In", "on resume called. items in list "+ adapter.getItemCount());
    }
}

