package com.fabricio.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.fabricio.festafimdeano.R;
import com.fabricio.festafimdeano.constants.FimDeAnoConstants;
import com.fabricio.festafimdeano.util.securityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private securityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mSecurityPreferences = new securityPreferences(this);

        this.mViewHolder.check_participate = (CheckBox) findViewById(R.id.check_participate);
        this.mViewHolder.check_participate.setOnClickListener(this);

        this.loadDataFromActivity();

    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstants.PRESENCE);

            if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
                this.mViewHolder.check_participate.setChecked(true);
            } else {
                this.mViewHolder.check_participate.setChecked(false);
            }

        }//end if (extras != null)
    }//end private void loadDataFromActivity()

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.check_participate) {
            if (this.mViewHolder.check_participate.isChecked()) {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WILL_GO);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WONT_GO);
            }//end if (this.mViewHolder.check_participate.isChecked())
        }//end if (id == R.id.check_participate)
    }//end public void onClick(View view)

    private static class ViewHolder {
        CheckBox check_participate;
    }//end private static class ViewHolder

}//end public class DetailsActivity extends AppCompatActivity implements View.OnClickListener