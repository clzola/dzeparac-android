package me.dzeparac.dzeparac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dzeparac.dzeparac.services.AuthService;

public class SignInActivity extends AppCompatActivity implements AuthService.OnLoginListener {

    @BindView(R.id.input_email) EditText mEmailInput;
    @BindView(R.id.input_password) EditText mPasswordInput;
    @BindView(R.id.btn_login) Button mLoginButton;
    @BindView(R.id.btn_register) Button mRegisterButton;

    private DzeparacApp mApp;
    private AuthService mAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        mApp = (DzeparacApp) getApplication();
        mAuthService = mApp.getAuthService();
    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonClick() {
        String email = mEmailInput.getText().toString();
        String password = mPasswordInput.getText().toString();
        mAuthService.login(email, password, this);
    }

    @OnClick(R.id.btn_register)
    public void onRegisterButtonClick() {

    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
