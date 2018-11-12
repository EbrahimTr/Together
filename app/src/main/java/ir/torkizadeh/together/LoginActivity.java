package ir.torkizadeh.together;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView head_image;
    String image_url = "https://www.clipartmax.com/png/full/51-515709_people-together-icon-png.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.register).setOnClickListener(LoginActivity.this);
        findViewById(R.id.sing_up).setOnClickListener(LoginActivity.this);
        head_image = findViewById(R.id.image_header);
        Glide.with(this).load(image_url).into(head_image);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                Toast.makeText(this, "register", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.sing_up:
                break;
        }
    }
}