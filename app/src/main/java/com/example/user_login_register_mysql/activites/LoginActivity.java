package com.example.user_login_register_mysql.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.example.user_login_register_mysql.R;
import com.example.user_login_register_mysql.models.ApiResponse;
import com.example.user_login_register_mysql.models.InputValidation;
import com.example.user_login_register_mysql.retrifitui.ApiClient;
import com.example.user_login_register_mysql.retrifitui.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;

    private ScrollView scrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputEditTextEmail.getText().toString().trim();
                String password = textInputEditTextPassword.getText().toString().trim();

                Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class).logininInformation(email, password);

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.code() == 200) {
                            if (response.body().getStatus() == "ok") {
                                if (response.body().getResult_code() == 1) {
                                    String name = response.body().getName();
                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                    intent.putExtra("name", name);
                                    startActivity(intent);
                                    Snackbar.make(scrollView, "login Ok", Snackbar.LENGTH_LONG).show();
                                    emptyInputEditText();
                                    finish();
                                }

                            } else {
                                Snackbar.make(scrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
                            }

                        } else {
                            Snackbar.make(scrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {

                    }
                });
            }
        });

        textViewLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initViews() {

        scrollView = (ScrollView) findViewById(R.id.scrollview);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        inputValidation = new InputValidation(activity);

    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, "Enter Valid Email or Mobile No")) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, "Enter Password")) {
            return;
        }

        if (inputValidation == null) {


            Intent accountsIntent = new Intent(activity, DashboardActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            Snackbar.make(scrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}