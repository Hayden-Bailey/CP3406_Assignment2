package au.edu.jcu.cp3406.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    LoginDatabaseHelper loginDatabaseHelper;
    private Button signInButton, createAccount;
    private EditText loginEmail, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.signInButton);
        createAccount = findViewById(R.id.createAccount);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginDatabaseHelper = new LoginDatabaseHelper(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if (email.equals("") || !email.contains("@") || !email.contains(".com")) {
                    popupMessage("Please input a valid email address");
                } else if (password.equals("") || password.length() < 6) {
                    popupMessage("Please input a valid password (greater than 5 characters)");
                } else {
                    createAccount(email, password);
                    loginEmail.setText("");
                    loginPassword.setText("");
                }
            }
        });

    }

    public void createAccount(String email, String password) {
        boolean insertData = loginDatabaseHelper.addLoginDetails(email, password);

        if (insertData) {
            popupMessage("Account Created!");
        } else {
            popupMessage("Something went wrong!");
        }
    }

    public void login(View view) {
    }


    private void popupMessage(String popup) {
        Toast.makeText(this, popup, Toast.LENGTH_SHORT).show();
    }
}