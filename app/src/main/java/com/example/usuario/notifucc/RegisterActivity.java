package com.example.usuario.notifucc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.notifucc.servidor.BaseDeDatos;
import com.example.usuario.notifucc.servidor.Usuario;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {

    BaseDeDatos db;
    Usuario nuevoUsuario;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserRegisterTask mAuthTask = null;

    // UI references.
    private EditText mClaveView;
    private EditText mFirstnameView;
    private EditText mLastnameView;
    private EditText mPasswordView;
    private EditText mPasswordRepeatView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        mClaveView = (EditText) findViewById(R.id.edtxt_clave);
        mFirstnameView = (EditText) findViewById(R.id.edtxt_firstname);
        mLastnameView = (EditText) findViewById(R.id.edtxt_lastname);
        mPasswordRepeatView = (EditText) findViewById(R.id.repeat_password);
        mPasswordView = (EditText) findViewById(R.id.new_password);

        Bundle bundle = getIntent().getExtras();
        db = (BaseDeDatos) bundle.getSerializable("db");

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void attemptRegister() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mClaveView.setError(null);
        mFirstnameView.setError(null);
        mLastnameView.setError(null);
        mPasswordRepeatView.setError(null);
        mPasswordView.setError(null);

        String password = mPasswordView.getText().toString();
        String repeat_password = mPasswordRepeatView.getText().toString();
        String firstname = mFirstnameView.getText().toString();
        String lastname = mLastnameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(firstname)){
            mFirstnameView.setError(getString(R.string.error_field_required));
            focusView = mFirstnameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(lastname)){
            mLastnameView.setError(getString(R.string.error_field_required));
            focusView = mLastnameView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            Toast.makeText(getBaseContext(), "La contraseña debe tener al menos 4 caracteres", Toast.LENGTH_SHORT).show();
            focusView = mPasswordView;
            cancel = true;
        }

        if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(repeat_password)){
            mPasswordRepeatView.setError(getString(R.string.error_field_required));
            focusView = mPasswordRepeatView;
            cancel = true;
        }

        if (!(repeat_password.equals(password))) {
            mPasswordRepeatView.setError(getString(R.string.error_match));
            focusView = mPasswordRepeatView;
            cancel = true;
        }

        String tClave = mClaveView.getText().toString();
        int iClave = -1;
        // Check for a empty key.
        if (TextUtils.isEmpty(tClave)) {
            mClaveView.setError(getString(R.string.error_field_required));
            focusView = mClaveView;
            cancel = true;
        }
        else {
            iClave = Integer.parseInt(tClave);
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserRegisterTask(firstname, lastname, iClave, password);
            mAuthTask.execute();
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final int mClave;
        private final String mPassword;
        private final String mFirstName;
        private final String mLastName;

        UserRegisterTask(String fName, String lName, int clave, String password) {
            mFirstName = fName;
            mLastName = lName;
            mClave = clave;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            nuevoUsuario = new Usuario(mFirstName, mLastName, mClave, mPassword);
            db.addUsuario(nuevoUsuario);

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.putExtra("usuario", nuevoUsuario);
                Toast.makeText(getBaseContext(),"Registro con éxito", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
            } else {

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

