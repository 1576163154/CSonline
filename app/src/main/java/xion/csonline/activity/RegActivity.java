package xion.csonline.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xion.csonline.R;
import xion.csonline.widget.ClearEditText;

/**
 * Created by Administrator on 2016/12/5.
 */

public class RegActivity extends AppCompatActivity implements TextWatcher {
    //注册相关信息
    private ClearEditText cet_reg_account;
    private ClearEditText cet_reg_password;
    private ClearEditText cet_reg_confirm_password;
    private ClearEditText cet_reg_phone;
    private ClearEditText cet_reg_code;
    //格式检查消息
    private TextView tv_account;
    private TextView tv_password;
    private TextView tv_password_confirm;
    private TextView tv_phone;
    private TextView tv_code;
    private Button btn_reg;
    private boolean isAccount = false, isPassword = false, isConfirm_password = false, isPhone = false, isCode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        initWidget();
        setWidget();
    }

    private void initWidget() {
        cet_reg_account = (ClearEditText) findViewById(R.id.cet_reg_account);
        cet_reg_password = (ClearEditText) findViewById(R.id.cet_reg_password);
        cet_reg_confirm_password = (ClearEditText) findViewById(R.id.cet_reg_confirm_password);
        cet_reg_phone = (ClearEditText) findViewById(R.id.cet_reg_phone);
        cet_reg_code = (ClearEditText) findViewById(R.id.cet_reg_code);
        btn_reg = (Button) findViewById(R.id.btn_reg);

        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_password_confirm = (TextView) findViewById(R.id.tv_password_confirm);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_code = (TextView) findViewById(R.id.tv_code);
    }

    private void setWidget() {
        //分别给这几个文本框添加文本监听
        cet_reg_account.addTextChangedListener(this);
        cet_reg_password.addTextChangedListener(this);
        cet_reg_confirm_password.addTextChangedListener(this);
        cet_reg_phone.addTextChangedListener(this);
        cet_reg_code.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int account = cet_reg_account.getText().length();
        int password = cet_reg_password.getText().length();
        int confirm_password = cet_reg_confirm_password.getText().length();
        //检验账号
        if (account >= 8) {
            isAccount = true;
            tv_account.setVisibility(View.GONE);
        } else if (account > 0 && account < 8) {
            isAccount = false;
            tv_account.setText(getString(R.string.account_check));
            tv_account.setVisibility(View.VISIBLE);
        } else {
            isAccount = false;
            tv_account.setVisibility(View.GONE);
        }
        //检验密码
        isPassword = checkPassword(String.valueOf(cet_reg_password.getText()));
        if (cet_reg_confirm_password.getText().toString().equals(cet_reg_password.getText().toString())) {
            isConfirm_password = true;
            tv_password_confirm.setVisibility(View.GONE);
        } else if (confirm_password == 0 || (confirm_password != 0 &&password == 0 )) {
            isConfirm_password = false;
            tv_password_confirm.setVisibility(View.GONE);
        } else {
            tv_password_confirm.setText(getString(R.string.password_confirm_check));
            tv_password_confirm.setVisibility(View.VISIBLE);
            isConfirm_password = false;
        }
        //判断手机号
        isPhone = isMobile(cet_reg_phone.getText().toString());
        //验证码待定
        //当条件都满足时按钮才可用
        if (isAccount && isPassword && isConfirm_password & isPhone) {
            btn_reg.setEnabled(true);
            btn_reg.setTextColor(getResources().getColor(R.color.white));
            btn_reg.setBackgroundResource(R.drawable.btn);
        } else {
            btn_reg.setEnabled(false);
            btn_reg.setBackgroundResource(R.drawable.btn_grayshape);
        }
    }

    //check密码方法
    public boolean checkPassword(String s) {
        boolean isDigit = false, isLetter = false;
        int l = s.length();
        boolean isInvalid = false;
        if (l >= 8 && l <= 18) {
            for (int i = 0; i < l; i++) {
                if (Character.isDigit(s.charAt(i))) {
                    isDigit = true;
                }
                if (Character.isLetter(s.charAt(i))) {
                    isLetter = true;
                }
            }
            if (isDigit && isLetter) {
                tv_password.setVisibility(View.GONE);
                isInvalid = true;
            } else if ((isDigit && !isLetter) || (!isDigit && isLetter) || (!isDigit && !isLetter)) {
                tv_password.setText(getString(R.string.password_check1));
                tv_password.setVisibility(View.VISIBLE);
                isInvalid = false;
            }
        } else if (l > 0 && l < 8) {
            tv_password.setText(getString(R.string.password_check2));
            tv_password.setVisibility(View.VISIBLE);
            isInvalid = false;
        } else if (l == 0) {
            tv_password.setVisibility(View.GONE);
            isInvalid = false;
        }
        return isInvalid;
    }

    //验证手机号是否合法
    public boolean isMobile(String s) {
        Pattern p = null;
        Matcher m = null;
        boolean isElevenNum = false;
        boolean isPhoneNum = false;
        int mobileNum = s.length();
        if (mobileNum == 11) {
            p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
            m = p.matcher(s);
            isPhoneNum = m.matches();
            if (!isPhoneNum){
                tv_phone.setText(getString(R.string.phonenum_check2));
                tv_phone.setVisibility(View.VISIBLE);
            }else {
                tv_phone.setVisibility(View.GONE);
            }
        } else if(mobileNum ==0){

            tv_phone.setVisibility(View.GONE);
        }else {
            tv_phone.setText(getString(R.string.phonenum_check1));
            tv_phone.setVisibility(View.VISIBLE);

        }
        return isPhoneNum;
    }
}
