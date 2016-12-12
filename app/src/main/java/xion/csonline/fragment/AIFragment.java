package xion.csonline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xion.csonline.R;
import xion.csonline.activity.ChatActivity;

/**
 * Created by Administrator on 2016/12/6.
 */

public class AIFragment extends Fragment {
    private RelativeLayout rl_ai;
    private TextView tv_ai;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View AI_View = inflater.inflate(R.layout.fragment_ai,null);
        //智能机器人信息内置
        rl_ai = (RelativeLayout) AI_View.findViewById(R.id.rl_ai);
        tv_ai = (TextView) AI_View.findViewById(R.id.tv_sendername_ai);
        setWidget();
        return AI_View;
    }

    private void setWidget() {
        rl_ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("nickname",tv_ai.getText().toString());
                startActivity(intent);
            }
        });
    }


}
