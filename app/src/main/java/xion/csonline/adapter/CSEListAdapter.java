package xion.csonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xion.csonline.entity.CSEInfo;
import xion.csonline.R;
import xion.csonline.widget.CirclePortraitView;

/**
 * Created by Administrator on 2016/10/20.
 */

public class CSEListAdapter extends BaseAdapter {
    private List<CSEInfo> cseList;

    public CSEListAdapter(List<CSEInfo> cseList) {
        this.cseList = cseList;
    }

    @Override
    public int getCount() {
        return cseList.size();
    }

    @Override
    public Object getItem(int position) {
        return cseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CSEInfo cseInfo = (CSEInfo) getItem(position);//获取相应位置的子项
        View cseView = null;
        ViewHolder viewHolder = null;
        if(convertView == null){
            cseView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cse_listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) cseView.findViewById(R.id.tv_sendername);
            viewHolder.tv_time = (TextView) cseView.findViewById(R.id.tv_messagetime);
            viewHolder.tv_recent = (TextView) cseView.findViewById(R.id.tv_messagerecent);
            viewHolder.cpv_portrait = (CirclePortraitView) cseView.findViewById(R.id.cpv_cse);
            cseView.setTag(viewHolder);
        }else {
            cseView = convertView;
            viewHolder = (ViewHolder) cseView.getTag();
        }
        viewHolder.tv_name.setText(cseInfo.getName());
        viewHolder.tv_time.setText(cseInfo.getTime());
        viewHolder.tv_recent.setText(cseInfo.getRecentMessage());
        viewHolder.cpv_portrait.setImageResource(cseInfo.getPortraitId());
        return cseView;
    }
    class ViewHolder{
        TextView tv_time,tv_name,tv_recent;
        CirclePortraitView cpv_portrait;
    }
}
