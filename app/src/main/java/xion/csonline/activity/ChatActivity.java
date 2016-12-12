package xion.csonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xion.csonline.R;
import xion.csonline.adapter.MsgAdapter;
import xion.csonline.entity.Msg;
import xion.csonline.widget.CirclePortraitView;
import xion.csonline.widget.ClearEditText;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ChatActivity extends AppCompatActivity {
    private List<Msg> msgList;
    private ListView lv_chat_msg;
    private MsgAdapter adapter;

    private TextView tv_tittle;
    private CirclePortraitView cpv_left,cpv_right;//聊天左边头像，右边头像
    private ClearEditText cet_chat;
    private Button btn_send;
    private DateFormat dateFormat;
    private String currentTime;//用字符存储当前时间（小时，分钟)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        dateFormat = new SimpleDateFormat("HH:mm");//确定时间格式为小时：分钟
        initWidget();
        setwidget();
        initData();
    }

    private void initWidget() {
        tv_tittle = (TextView) findViewById(R.id.tv_chat_title);
        lv_chat_msg = (ListView) findViewById(R.id.lv_chat_msg);
        cpv_left = (CirclePortraitView) findViewById(R.id.cpv_chat_left);
        cpv_right = (CirclePortraitView) findViewById(R.id.cpv_chat_right);
        cet_chat = (ClearEditText) findViewById(R.id.cet_chat);
        btn_send = (Button) findViewById(R.id.btn_send);
    }

    private void setwidget() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editText = cet_chat.getText().toString();
                if(!"".equals(editText)){
                    currentTime = dateFormat.format(new Date());
                    Msg msg = new Msg();
                    msg.setMsgPortrait(R.drawable.sherlock);
                    msg.setTime(currentTime);
                    msg.setContent(editText);
                    msg.setType(Msg.TYPED_SEND);
                    msgList.add(msg);//将信息对象 添加到 信息list存储
                    adapter.notifyDataSetChanged();
                    lv_chat_msg.setSelection(msgList.size());
                    cet_chat.setText(" ");//最后清空,这里如果设为“”则会崩溃
                }
            }
        });
    }
    private void initData(){

        //获取从CSE列表传递过来的CSE_nickname;
        Intent intent = getIntent();
        String str = intent.getStringExtra("nickname");
        tv_tittle.setText(str);
        msgList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Msg msg = new Msg();
            msg.setTime("14:4"+i);
            msg.setContent("产生的消息"+i);
            msg.setType(i%2==0? Msg.TYPED_RECEIVED:Msg.TYPED_SEND);
            msg.setMsgPortrait(R.drawable.sherlock);
            msgList.add(msg);
        }
        adapter = new MsgAdapter(msgList);
        lv_chat_msg.setAdapter(adapter);
    }
}
