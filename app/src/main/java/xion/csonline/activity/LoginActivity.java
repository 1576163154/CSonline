package xion.csonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import xion.csonline.R;

/**
 * Created by Administrator on 2016/12/5.
 */

public class LoginActivity extends AppCompatActivity {
    private TextView tv_login_newuser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initWidget();
        setWidget();
    }

    private void initWidget() {
        tv_login_newuser = (TextView) findViewById(R.id.tv_login_newuser);
    }

    private void setWidget() {
        tv_login_newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegActivity.class));
            }
        });
    }

}
