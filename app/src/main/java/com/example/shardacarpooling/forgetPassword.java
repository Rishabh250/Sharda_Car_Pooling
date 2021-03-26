package com.example.shardacarpooling;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class forgetPassword extends Fragment {

    EditText shardaMail;
    TextView Error_mail;
    Button reset;
    FirebaseAuth firebaseAuth;
    float v =0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.forgetpassword, container, false);

        shardaMail = root.findViewById(R.id.shardaMail);
        Error_mail = root.findViewById(R.id.error_mail);
        reset = root.findViewById(R.id.resetPass);
        firebaseAuth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail = shardaMail.getText().toString().trim();
                if(mail.isEmpty())
                {
                    Error_mail.setVisibility(View.VISIBLE);
                    Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
                    Error_mail.startAnimation(animShake);
                    return;
                    
                }
                firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getContext(), "Reset Link has been sent", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Error! Reset link not sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        return root;
    }
}
