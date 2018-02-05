package com.fabricio.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fabricio.festafimdeano.R;
import com.fabricio.festafimdeano.constants.FimDeAnoConstants;
import com.fabricio.festafimdeano.util.securityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private securityPreferences  mSecurityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mViewHolder.textToday = (TextView) findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = (TextView) findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = (Button) findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mSecurityPreferences = new securityPreferences(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeftToEndOfYear()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);

    }//end protected void onCreate(Bundle savedInstanceState)


    private void verifyPresence() {
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);

        if (presence.equals("")){
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        }
        else if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        } else {
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
        }

    }//end private void verifyPresence()

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.button_confirm){
            Intent intentDetails = new Intent(this, DetailsActivity.class);

            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);

            intentDetails.putExtra(FimDeAnoConstants.PRESENCE, presence);

            startActivity(intentDetails);
        }//end if (id == R.id.button_confirm)
    }//end public void onClick(View view)

    private int getDaysLeftToEndOfYear(){
        Calendar calendarToday = Calendar.getInstance();
        Calendar calendarLastDay = Calendar.getInstance();

        int today = calendarToday.get(Calendar.DAY_OF_YEAR);
        int dayDecember31 = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayDecember31 - today;

    }//end private int getDaysLeftToEndOfYear()

/*
    @Override
    protected void onStart(){
        super.onStart();

    }
*/

    @Override
    protected void onResume(){
        super.onResume();
        this.verifyPresence();

    }

/*    @Override
    protected void onPause(){
        super.onPause();

    }

    @Override
    protected void onStop(){
        super.onStop();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

    }
*/

    private static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
        Button   buttonConfirm;
    }//end private static class mviewHolder

}//end public class MainActivity extends AppCompatActivity implements View.OnClickListener
