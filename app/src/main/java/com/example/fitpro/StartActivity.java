package com.example.fitpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Remove Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        firebaseAuth = FirebaseAuth.getInstance();
        videoView=findViewById(R.id.videoView);

        Uri splashVideo=Uri.parse("android.resource://"+ getPackageName()+"/"+R.raw.splashvideo);

        videoView.setVideoURI(splashVideo);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(StartActivity.this,MainActivity.class));
                }
                else{
                    startActivity(new Intent(StartActivity.this,LoginActivity.class));
                }
                finish();
            }
        });
        videoView.start();
    }
}