package xion.csonline.entity;

/**
 * Created by Administrator on 2016/9/6.
 */
public class Msg {
    public static final int TYPED_RECEIVED  = 0;
    public static final int TYPED_SEND = 1;
    private  String content;
    private int type;

    public int getMsgPortrait() {
        return msgPortrait;
    }

    public void setMsgPortrait(int msgPortrait) {
        this.msgPortrait = msgPortrait;
    }

    private int msgPortrait;

    public String getTime() {
        return time;
    }

    private String time;


    public void setContent(String content) {
        this.content = content;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}//创建 消息 类，属性内容和类型。并实现构造方法及两个属性的getter方法
