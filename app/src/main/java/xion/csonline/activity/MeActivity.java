package xion.csonline.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import xion.csonline.R;

/**
 * Created by Administrator on 2016/12/12.
 */

public class MeActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_menu_modify;//修改个人资料
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_user);
        initWidget();
        setWidget();
    }

    private void initWidget() {
        ll_menu_modify = (LinearLayout) findViewById(R.id.ll_modify_profile);
    }
    private void setWidget(){
        ll_menu_modify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_modify_profile:

                break;
            default:
                break;
        }
    }
}
