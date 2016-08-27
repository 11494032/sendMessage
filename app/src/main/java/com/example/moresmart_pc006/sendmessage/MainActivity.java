package com.example.moresmart_pc006.sendmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText ed_num;
    private EditText ed_contnet;

    private Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_num = (EditText)findViewById(R.id.ed_num);
        ed_contnet = (EditText)findViewById(R.id.ed_content);

        sendButton = (Button)findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num = ed_num.toString().trim();

                String content = ed_contnet.toString().trim();

                if( TextUtils.isEmpty(num) || TextUtils.isEmpty( content ))
                {
                    Toast.makeText(getApplicationContext(),"电话号码或者短信不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                SmsManager smsManager = SmsManager.getDefault();

                /*短信长度超过一定限制之后需要切割成多条分发*/
                ArrayList<String> parts =   smsManager.divideMessage(content);

                /*
                * @function:发送信息
                * @param[]:目标手机号码
                * @parm[]:短信中心号码
                * @param：短信内容
                * @param：短信如果发送成功了，那么回调改参数，通过延时和广播才能实现
                * @param:短信被对方接收
                * */
                smsManager.sendMultipartTextMessage(num,null,parts,null,null);

                Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();


            }
        });
    }
}
