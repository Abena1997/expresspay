package com.expresspay.access_control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class CheckInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        Fragment fragmentVisitorDetails = new VisitorDetailsfragment();
       FragmentTransaction transactionVisitorDetails = getSupportFragmentManager().beginTransaction();

       transactionVisitorDetails.add(R.id.fragment_container_fl, fragmentVisitorDetails);
      transactionVisitorDetails.addToBackStack(null);

        transactionVisitorDetails.commit();

    }


    @Override
    public void onBackPressed() {

        // get the current fragment
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_fl);
        if (currentFragment instanceof VisitorDetailsfragment) {
            finish();
        } else {
            super.onBackPressed();
        }


    }

  //  public void onRadioButtonClicked(View view) {

           // boolean checked =((RadioButton) view ).isChecked();

            //check which radio button was clicked
         //   switch (view.getId()){
          //      case R.id.personal_rbtn:
           //         if(checked){
            //            Toast.makeText(view.getContext(), "hello", Toast.LENGTH_SHORT).show();
             //       }
              //          break;

             //   case R.id.officail_rbtn:
               //     if(checked){
                //        Toast.makeText(view.getContext(),"hey",Toast.LENGTH_SHORT).show();
                 //   }

                  //      break;
          //  }


}
