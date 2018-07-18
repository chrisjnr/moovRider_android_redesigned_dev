package com.moovapp.rider.main.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moovapp.rider.R;
import com.moovapp.rider.utils.LMTBaseActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class ProfileActivity extends LMTBaseActivity {

    @BindView(R.id.cardViewEdit)
    CardView cardViewEdit;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.edFirstName)
    EditText edFirstName;
    @BindView(R.id.edLastName)
    EditText edLastName;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edUniversity)
    EditText edUniversity;
    @BindView(R.id.edPhoneNumber)
    EditText edPhoneNumber;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private boolean isInEditMode = false;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.profile_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.navBackButton)
    public void navBackButtonClick() {
        if (isInEditMode) {
            disableEditing();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (isInEditMode) {
            disableEditing();
        } else {
            super.onBackPressed();
        }
    }

    private void disableEditing() {
        tvTitle.setText("Profile");
        isInEditMode = false;
        cardViewEdit.setVisibility(View.VISIBLE);
        tvSubmit.setVisibility(View.GONE);
        edFirstName.setClickable(false);
        edFirstName.setFocusable(false);
        edLastName.setClickable(false);
        edLastName.setFocusable(false);
        edEmail.setClickable(false);
        edEmail.setFocusable(false);
        edUniversity.setClickable(false);
        edUniversity.setFocusable(false);
        edPhoneNumber.setClickable(false);
        edPhoneNumber.setFocusable(false);
    }

    @OnClick(R.id.cardViewEdit)
    public void cardViewEditClick() {
        tvTitle.setText("Edit Profile");
        isInEditMode = true;
        cardViewEdit.setVisibility(View.GONE);
        tvSubmit.setVisibility(View.VISIBLE);
        edFirstName.setClickable(true);
        edFirstName.setFocusable(true);
        edFirstName.setFocusableInTouchMode(true);
        edLastName.setClickable(true);
        edLastName.setFocusable(true);
        edLastName.setFocusableInTouchMode(true);
        edEmail.setClickable(true);
        edEmail.setFocusable(true);
        edEmail.setFocusableInTouchMode(true);
        edUniversity.setClickable(true);
        edUniversity.setFocusable(true);
        edUniversity.setFocusableInTouchMode(true);
        edPhoneNumber.setClickable(true);
        edPhoneNumber.setFocusable(true);
        edPhoneNumber.setFocusableInTouchMode(true);
    }
}
