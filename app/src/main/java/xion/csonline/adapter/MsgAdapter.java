package xion.csonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import xion.csonline.R;
import xion.csonline.entity.Msg;
import xion.csonline.widget.CirclePortraitView;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MsgAdapter extends BaseAdapter{
private List<Msg> msgList;

    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int position) {
        return msgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View msgView;
        Msg msg = (Msg) getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            msgView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_msg_listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.leftlayout = (LinearLayout) msgView.findViewById(R.id.ll_left);
            viewHolder.rightlayout = (LinearLayout) msgView.findViewById(R.id.ll_right);
            viewHolder.leftmsg = (TextView) msgView.findViewById(R.id.tv_left_msg);
            viewHolder.cpv_left = (CirclePortraitView) msgView.findViewById(R.id.cpv_chat_left);
            viewHolder.rightmsg = (TextView) msgView.findViewById(R.id.tv_right_msg);
            viewHolder.tv_received_time = (TextView) msgView.findViewById(R.id.tv_received_time);
            viewHolder.tv_send_time = (TextView) msgView.findViewById(R.id.tv_send_time);
            viewHolder.cpv_right = (CirclePortraitView) msgView.findViewById(R.id.cpv_chat_right);
            msgView.setTag(viewHolder);
        }
        else {
            msgView = convertView;
            viewHolder = (ViewHolder) msgView.getTag();
        }

        if(msg.getType() ==  Msg.TYPED_RECEIVED){
            viewHolder.leftlayout.setVisibility(View.VISIBLE);
            viewHolder.rightlayout.setVisibility(View.GONE);
            viewHolder.leftmsg.setText(msg.getContent());
            viewHolder.tv_received_time.setText(msg.getTime());
            viewHolder.cpv_left.setImageResource(msg.getMsgPortrait());

        }
        else if(msg.getType() == Msg.TYPED_SEND){
            viewHolder.rightlayout.setVisibility(View.VISIBLE);
            viewHolder.leftlayout.setVisibility(View.GONE);
            viewHolder.rightmsg.setText(msg.getContent());
            viewHolder.tv_send_time.setText(msg.getTime());
            viewHolder.cpv_right.setImageResource(msg.getMsgPortrait());
        }
        return msgView;
    }

    class ViewHolder {
        LinearLayout leftlayout;
        LinearLayout rightlayout;
        CirclePortraitView cpv_left;
        CirclePortraitView cpv_right;
        TextView leftmsg;
        TextView rightmsg;
        TextView tv_received_time;
        TextView tv_send_time;
    }
}

