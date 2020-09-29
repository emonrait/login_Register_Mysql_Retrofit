package com.example.user_login_register_mysql.activites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;


import com.example.user_login_register_mysql.R;
import com.example.user_login_register_mysql.models.ApiResponse;
import com.example.user_login_register_mysql.models.InputValidation;
import com.example.user_login_register_mysql.retrifitui.ApiClient;
import com.example.user_login_register_mysql.retrifitui.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Emon Raihan on 21/09/2020.
 */
public class RegisterActivity extends AppCompatActivity {

    private final AppCompatActivity activity = RegisterActivity.this;

    private ScrollView scrollView;
    final int REQUEST_GALLERY_CODE = 999;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutMobile;
    private TextInputLayout textInputLayoutAddress;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextMobile;
    private TextInputEditText textInputEditTextAddress;
    private AppCompatImageView appCompatImageView;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister);

        initViews();

        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignup();
            }
        });

        appCompatTextViewLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        appCompatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY_CODE);
            }
        });
    }

    public void performSignup() {
        String name = textInputEditTextName.getText().toString().trim();
        String email = textInputEditTextEmail.getText().toString().trim();
        String mobile = textInputEditTextMobile.getText().toString().trim();
        String address = textInputEditTextAddress.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();

        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class).signinInformation(name, email, mobile, address, password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus().equals("ok")) {
                        if (response.body().getResult_code() == 1) {
                            //Toast toast = Toast.makeText(getApplicationContext(), "Registration Success....", Toast.LENGTH_SHORT);
                            Snackbar.make(scrollView, "Registration Success....", Snackbar.LENGTH_LONG).show();
                            emptyInputEditText();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            onBackPressed();
                            finish();

                        } else {
                            Snackbar.make(scrollView, "User Already Exist....", Snackbar.LENGTH_LONG).show();

                        }

                    } else {
                        Snackbar.make(scrollView, "Something went wrong....", Snackbar.LENGTH_LONG).show();

                    }

                } else {
                    Snackbar.make(scrollView, "Something went wrong....", Snackbar.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                //Snackbar.make(scrollView, "Something went wrong....", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void initViews() {
        scrollView = (ScrollView) findViewById(R.id.scrollview);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutMobile = (TextInputLayout) findViewById(R.id.textInputLayoutMobile);
        textInputLayoutAddress = (TextInputLayout) findViewById(R.id.textInputLayoutAddress);
        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextMobile = (TextInputEditText) findViewById(R.id.textInputEditTextMobile);
        textInputEditTextAddress = (TextInputEditText) findViewById(R.id.textInputEditTextAddress);
        //appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
        appCompatImageView = (AppCompatImageView) findViewById(R.id.addPhoto);
        inputValidation = new InputValidation(activity);

    }


    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditTextMobile.setText(null);
        textInputEditTextAddress.setText(null);
        appCompatImageView.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
