package com.example.shardacarpooling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class LoginTabFragment extends Fragment {

    EditText shardaMail, pass;
    TextView forgetPass;
    Button login;
    FloatingActionButton google,fb,twitter;
    ImageView hidePassword,showPassword;
    SharedPreferences sharedPreferences;

    private static final String Shared_Pref_Name = "mypref";
    private static final String Key_Name = "name";
    private static final String Key_Email = "email";
    private FirebaseAuth firebaseAuth;
    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        shardaMail  = root.findViewById(R.id.systemID);
        pass = root.findViewById(R.id.pass);
        forgetPass = root.findViewById(R.id.forgetPass_btn);
        login = root.findViewById(R.id.login_btn);
        google = root.findViewById(R.id.fab_google);
        fb = root.findViewById(R.id.fab_facebook);
        twitter = root.findViewById(R.id.fab_twitter);
        hidePassword = root.findViewById(R.id.hidePassword);
        showPassword = root.findViewById(R.id.showPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = getActivity().getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);

        String name = sharedPreferences.getString(Key_Email,null);
        if (name !=null){
            Intent intent = new Intent(getActivity(),DandPselection.class);
            startActivity(intent);
        }

        hidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                hidePassword.setVisibility(View.INVISIBLE);
                showPassword.setVisibility(View.VISIBLE);

            }
        });

        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                showPassword.setVisibility(View.INVISIBLE);
                hidePassword.setVisibility(View.VISIBLE);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = shardaMail.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if (mail.isEmpty()) {
                    Toast.makeText(getActivity(), "System ID cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Key_Email,mail);
                editor.apply();
                firebaseAuth.signInWithEmailAndPassword(mail,password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getActivity(),DandPselection.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getContext(), "Invalid Details", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



        shardaMail.setTranslationX(800);
        pass.setTranslationX(800);
        hidePassword.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);
        twitter.setTranslationY(300);
        google.setTranslationY(300);
        fb.setTranslationY(300);

        shardaMail.setAlpha(v);
        pass.setAlpha(v);
        hidePassword.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);
        twitter.setAlpha(v);
        google.setAlpha(v);
        fb.setAlpha(v);

        shardaMail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        hidePassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        return root;
    }
}