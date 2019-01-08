package com.moovapp.riderapp.preLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.moovapp.riderapp.R;
import com.moovapp.riderapp.main.HomeActivity;
import com.moovapp.riderapp.preLogin.signUp.SignUpActivity;
import com.moovapp.riderapp.utils.Constants;
import com.moovapp.riderapp.utils.LMTBaseActivity;
import com.moovapp.riderapp.utils.retrofit.ApiClient;
import com.moovapp.riderapp.utils.retrofit.ApiInterface;
import com.moovapp.riderapp.utils.retrofit.responseModels.SocialLoginResponseModel;

import retrofit2.Call;
import retrofit2.Response;

public class Login extends LMTBaseActivity implements View.OnClickListener{

    GoogleSignInClient mGoogleSignInClient;
    private final int EMAIL_LOGIN_API = 1;
    private final int FORGOT_PASSWORD_API = 2;
    private final int FORGOT_PASSWORD_SUCCESS_DIALOG = 3;
    private final int SOCIAL_LOGIN_API = 4;

    ImageView signInButton;

    private static final String TAG = "LogActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Log.d("googleres", "onActivityResult: "+requestCode);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

//
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                GoogleSignInAccount acct = result.getSignInAccount();
//                String tokenId = acct.getIdToken();
//                String name = acct.getDisplayName();
//                String personGivenName = acct.getGivenName();
//                String personFamilyName = acct.getFamilyName();
//                String email = acct.getEmail();
//                String social_id = acct.getId();
//                String social_image = String.valueOf(acct.getPhotoUrl());
//                Log.w("googleres", name + "," + personGivenName + "," + personFamilyName + "," + email + "," + social_id + "," + social_image);
//            } else {
//                Toast.makeText(this, "failed to login with google", Toast.LENGTH_SHORT).show();
//
//            }
//        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            String image = "";
            try {
                image = account.getPhotoUrl().toString();
            } catch (Exception e) {
                e.printStackTrace();
                image = "";
            }
            callSocialLoginApi("google", account.getId(), account.getEmail(), account.getDisplayName(), image);
            try {
                mGoogleSignInClient.signOut();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }


    private void callSocialLoginApi(final String loginType, final String id, final String email, final String name, final String profilePic) {
        if (cd.isConnectingToInternet()) {
            try {
                myProgressDialog.setProgress(false);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<SocialLoginResponseModel> call = apiService.socialLogin(loginType, id, "android", appPrefes.getData(Constants.DEVICE_TOKEN), "2.0");
                call.enqueue(new retrofit2.Callback<SocialLoginResponseModel>() {
                    @Override
                    public void onResponse(Call<SocialLoginResponseModel> call, Response<SocialLoginResponseModel> response) {
                        myProgressDialog.dismissProgress();
                        Log.e("response", "onResponse: "+response.raw() );
//                        try {
//                            if (!response.body().isStatus()) {
//                                Intent intent = new Intent(Login.this, SignUpActivity.class);
//                                intent.putExtra("RegistrationType", "Social");
//                                intent.putExtra("loginType", loginType);
//                                intent.putExtra("id", id);
//                                intent.putExtra("email", email);
//                                intent.putExtra("name", name);
//                                intent.putExtra("profilePic", profilePic);
//                                startActivity(intent);
//                                Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            } else {
//                                appPrefes.SaveData(Constants.USER_ID, response.body().getData().getUser_details().getU_id() + "");
//                                appPrefes.SaveData(Constants.USER_FIRST_NAME, response.body().getData().getUser_details().getU_first_name() + "");
//                                appPrefes.SaveData(Constants.ACCESS_TOKEN, response.body().getData().getAccess_token());
//                                appPrefes.SaveData(Constants.USER_PROFILE_PIC, response.body().getData().getUser_pic_url());
//                                appPrefes.SaveDataBoolean(Constants.USER_LOGGED_IN_STATUS, true);
//                                Intent intent = new Intent(Login.this, HomeActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            showServerErrorAlert(SOCIAL_LOGIN_API);
//                        }
                    }

                    @Override
                    public void onFailure(Call<SocialLoginResponseModel> call, Throwable t) {
                        myProgressDialog.dismissProgress();
                        System.out.println("t.toString : " + t.toString());
                        showServerErrorAlert(SOCIAL_LOGIN_API);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                myProgressDialog.dismissProgress();
                showServerErrorAlert(SOCIAL_LOGIN_API);
            }
        } else {
            showNoInternetAlert(SOCIAL_LOGIN_API);
        }
    }

//    @Override
//    public void retryApiCall(int apiCode) {
//        super.retryApiCall(apiCode);
//        switch (apiCode) {
//            case EMAIL_LOGIN_API:
//                callEmailLoginApi();
//                break;
//            case FORGOT_PASSWORD_API:
//                callForgotPasswordApi();
//                break;
//        }
//    }


    private void updateUI(GoogleSignInAccount account) {
        Toast.makeText(this, ""+account.getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button){
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
    }
}
