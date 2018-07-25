package com.moovapp.rider.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.moovapp.rider.R;
import com.moovapp.rider.main.moov.MoovFragment;
import com.moovapp.rider.main.paymentHistory.PaymentHistoryFragment;
import com.moovapp.rider.main.previousRides.PreviousRidesFragment;
import com.moovapp.rider.main.profile.ProfileActivity;
import com.moovapp.rider.main.settings.SettingsFragment;
import com.moovapp.rider.main.talkToUs.TalkToUsFragment;
import com.moovapp.rider.main.upcomingRides.UpcomingRidesFragment;
import com.moovapp.rider.main.wallet.WalletActivity;
import com.moovapp.rider.preLogin.LoginActivity;
import com.moovapp.rider.utils.Constants;
import com.moovapp.rider.utils.GPSTracker;
import com.moovapp.rider.utils.LMTBaseActivity;
import com.moovapp.rider.utils.myGlobalFunctions.DpToPx;
import com.moovapp.rider.utils.myGlobalFunctions.ExpandOrCollapseViews;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Lijo Mathew Theckanal on 18-Jul-18.
 */

public class HomeActivity extends LMTBaseActivity implements HomeActivityActions {

    private static final int DIALOG_LOGOUT = 1;
    private static final int ACCESS_FINE_LOCATION = 7;

    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.contentView)
    FrameLayout contentView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private LinearLayout llMoovNav;
    private RelativeLayout llRidesNav;
    private LinearLayout llExpandedViewRides;
    private LinearLayout llUpcommingRidesNav;
    private LinearLayout llPreviousRidesNav;
    private LinearLayout llPaymentHistoryNav;
    private LinearLayout llTalkToUsNav;
    private LinearLayout llSettingsNav;
    private LinearLayout llLogoutNav;
    private ImageView imgRidesArrow;
    private TextView tvUserName;
    private CircleImageView profileImage;

    private boolean isExpandedViewRidesVisible = false;
    private String currentFragment = "MoovFragment";
    public static HomeActivityActions homeActivityActions;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        homeActivityActions = this;
        setNavigationMenus();
        replaceFragment(new MoovFragment(), false, FragmentTransaction.TRANSIT_ENTER_MASK, "MoovFragment");
        if (shouldAskPermission()) {
            askPermissionLocation();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (appPrefes.getData(Constants.USER_PROFILE_PIC).length() > 3) {
                Picasso.get().load(appPrefes.getData(Constants.USER_PROFILE_PIC)).placeholder(R.mipmap.user_placeholder).error(R.mipmap.user_placeholder).into(profileImage);
            }
            tvUserName.setText(appPrefes.getData(Constants.USER_FIRST_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.cardViewWallet)
    public void cardViewWalletClick() {
        Intent intent = new Intent(HomeActivity.this, WalletActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.navMenuButton)
    public void navMenuButtonClick() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    private void setNavigationMenus() {
        setupNavigationMenu();
    }

    private void setupNavigationMenu() {
        View headerView = navigationView.getHeaderView(0);
        profileImage = (CircleImageView) headerView.findViewById(R.id.profileImage);
        tvUserName = (TextView) headerView.findViewById(R.id.tvUserName);
        llMoovNav = (LinearLayout) headerView.findViewById(R.id.llMoovNav);
        llRidesNav = (RelativeLayout) headerView.findViewById(R.id.llRidesNav);
        imgRidesArrow = (ImageView) headerView.findViewById(R.id.imgRidesArrow);
        llExpandedViewRides = (LinearLayout) headerView.findViewById(R.id.llExpandedViewRides);
        llUpcommingRidesNav = (LinearLayout) headerView.findViewById(R.id.llUpcommingRidesNav);
        llPreviousRidesNav = (LinearLayout) headerView.findViewById(R.id.llPreviousRidesNav);
        llPaymentHistoryNav = (LinearLayout) headerView.findViewById(R.id.llPaymentHistoryNav);
        llTalkToUsNav = (LinearLayout) headerView.findViewById(R.id.llTalkToUsNav);
        llSettingsNav = (LinearLayout) headerView.findViewById(R.id.llSettingsNav);
        llLogoutNav = (LinearLayout) headerView.findViewById(R.id.llLogoutNav);

        llRidesNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExpandedViewRidesVisible) {
                    imgRidesArrow.setImageResource(R.mipmap.down_arrow_black);
                    isExpandedViewRidesVisible = false;
                    ExpandOrCollapseViews.collapse(llExpandedViewRides, 300, 0);
                } else {
                    imgRidesArrow.setImageResource(R.mipmap.up_arrow_black);
                    isExpandedViewRidesVisible = true;
                    ExpandOrCollapseViews.expand(llExpandedViewRides, 300, DpToPx.dp2px(81, getApplicationContext()));
                }
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        llMoovNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                currentFragment = "MoovFragment";
                delayFlow(new MoovFragment(), "MoovFragment");
                changeMenuBackgroundColor();
            }
        });
        llUpcommingRidesNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                currentFragment = "UpcomingRidesFragment";
                delayFlow(new UpcomingRidesFragment(), "UpcomingRidesFragment");
                changeMenuBackgroundColor();
            }
        });
        llPreviousRidesNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                currentFragment = "PreviousRidesFragment";
                delayFlow(new PreviousRidesFragment(), "PreviousRidesFragment");
                changeMenuBackgroundColor();
            }
        });
        llPaymentHistoryNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                currentFragment = "PaymentHistoryFragment";
                delayFlow(new PaymentHistoryFragment(), "PaymentHistoryFragment");
                changeMenuBackgroundColor();
            }
        });
        llTalkToUsNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                currentFragment = "TalkToUsFragment";
                delayFlow(new TalkToUsFragment(), "TalkToUsFragment");
                changeMenuBackgroundColor();
            }
        });
        llSettingsNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                currentFragment = "SettingsFragment";
                delayFlow(new SettingsFragment(), "SettingsFragment");
                changeMenuBackgroundColor();
            }
        });
        llLogoutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                showAlertDialog("Logout", "Do you really want log out?", "Logout", "Cancel", DIALOG_LOGOUT);
            }
        });

    }

    private void changeMenuBackgroundColor() {
        llMoovNav.setBackgroundColor(getResources().getColor(R.color.white));
        llUpcommingRidesNav.setBackgroundColor(getResources().getColor(R.color.grayLitest));
        llPreviousRidesNav.setBackgroundColor(getResources().getColor(R.color.grayLitest));
        llPaymentHistoryNav.setBackgroundColor(getResources().getColor(R.color.white));
        llTalkToUsNav.setBackgroundColor(getResources().getColor(R.color.white));
        llSettingsNav.setBackgroundColor(getResources().getColor(R.color.white));
        switch (currentFragment) {
            case "MoovFragment":
                tvTitle.setText("Moov");
                llMoovNav.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLite));
                break;
            case "UpcomingRidesFragment":
                tvTitle.setText("Upcoming Rides");
                llUpcommingRidesNav.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLite));
                break;
            case "PreviousRidesFragment":
                tvTitle.setText("Previous Rides");
                llPreviousRidesNav.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLite));
                break;
            case "PaymentHistoryFragment":
                tvTitle.setText("Payment History");
                llPaymentHistoryNav.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLite));
                break;
            case "TalkToUsFragment":
                tvTitle.setText("Talk To Us");
                llTalkToUsNav.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLite));
                break;
            case "SettingsFragment":
                tvTitle.setText("Settings");
                llSettingsNav.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLite));
                break;
        }
    }

    //  Wait for drawer to close
    private void delayFlow(final Fragment fragment, final String name) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                replaceFragment(fragment, false, FragmentTransaction.TRANSIT_ENTER_MASK, name);
            }
        }, 330);
    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack,
                                 int transition, String name) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.setTransition(transition);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(name);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickAlertOkButton(int apiCode) {
        super.onClickAlertOkButton(apiCode);
        switch (apiCode) {
            case DIALOG_LOGOUT:
                appPrefes.clearData();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onProfileUpdate() {
        try {
            if (appPrefes.getData(Constants.USER_PROFILE_PIC).length() > 3) {
                Picasso.get().load(appPrefes.getData(Constants.USER_PROFILE_PIC)).placeholder(R.mipmap.user_placeholder).error(R.mipmap.user_placeholder).into(profileImage);
            }
            tvUserName.setText(appPrefes.getData(Constants.USER_FIRST_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    private void askPermissionLocation() {
        String[] perms = {"android.permission.ACCESS_FINE_LOCATION"};
        requestPermissions(perms, ACCESS_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case ACCESS_FINE_LOCATION:
                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (locationAccepted) {
                    GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
                    gpsTracker.getLongitude();
                } else {
                    Toast.makeText(getBaseContext(), "Permission denied. You must allow location sharing permission to use this app!", Toast.LENGTH_SHORT).show();
                    askPermissionLocation();
                }
                break;
        }
    }
}
