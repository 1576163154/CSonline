package xion.csonline.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import xion.csonline.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_toAI;
    private ImageView iv_toCSE;
    private TextView tv_title;

    private Fragment[] fragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        initWidget();
        initFragment();
        setWidget();
    }

    private void initWidget() {
        iv_toAI = (ImageView) findViewById(R.id.iv_toAI);
        iv_toCSE = (ImageView) findViewById(R.id.iv_toCSE);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }
    private void initFragment(){
        fragments = new Fragment[2];
        fragmentManager = getSupportFragmentManager();
        fragments[0] = fragmentManager.findFragmentById(R.id.frag_ai);
        fragments[1] = fragmentManager.findFragmentById(R.id.frag_cse);
        fragmentTransaction = fragmentManager.beginTransaction().hide(fragments[0]).hide(fragments[1]);
        fragmentTransaction.show(fragments[0]).commit();//默认显示智能回复页面
        iv_toCSE.setVisibility(View.VISIBLE);
        iv_toAI.setVisibility(View.GONE);
        tv_title.setText(getString(R.string.ic));
    }
    private void setWidget(){
        iv_toAI.setOnClickListener(this);
        iv_toCSE.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        fragmentTransaction = fragmentManager.beginTransaction().hide(fragments[0]).hide(fragments[1]);
        switch (v.getId()){
            case R.id.iv_toAI:
                fragmentTransaction.show(fragments[0]).commit();
                tv_title.setText(getString(R.string.ic));
                iv_toAI.setVisibility(View.GONE);
                iv_toCSE.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_toCSE:
                fragmentTransaction.show(fragments[1]).commit();
                tv_title.setText(getString(R.string.cse));
                iv_toCSE.setVisibility(View.GONE);
                iv_toAI.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
