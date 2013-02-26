package com.huangxin.test.skin;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SkinMgrDemoActivity extends Activity {
	private Button btn1, btn2, btn3;
	private LinearLayout main;
	private SkinManager skinMgr;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        skinMgr = SkinManager.getInstance(this, 2);
        
    }
    
    private void initView() {
    	btn1 = (Button) findViewById(R.id.button1);
    	btn2 = (Button) findViewById(R.id.button2);
    	btn3 = (Button) findViewById(R.id.button3);
    	main = (LinearLayout) findViewById(R.id.main);
    	
    	btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (skinMgr.getCurrentSkin() == SkinManager.DEFAULT_SKIN) {
					Toast.makeText(SkinMgrDemoActivity.this, "当前已是默认皮肤-female风格", Toast.LENGTH_SHORT).show();
					
				} else {
					skinMgr.setSkin(SkinManager.DEFAULT_SKIN);
				}
			}
		});
    	
    	btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (skinMgr.getCurrentSkin() == 1)
					Toast.makeText(SkinMgrDemoActivity.this, "当前已是默认皮肤-business风格", Toast.LENGTH_SHORT).show();
				else {
					skinMgr.setSkin(1);
				}
			}
		});
    	
    	btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(SkinMgrDemoActivity.this, SecondActivity.class);
				startActivity(it);
			}
		});
    }

	@Override
	protected void onStart() {
		super.onStart();
		skinMgr.setSkin(skinMgr.getCurrentSkin());
        main.setBackgroundResource(skinMgr.getImgId("bg"));
        btn1.setBackgroundResource(skinMgr.getImgId("btn_bg"));
		btn2.setBackgroundResource(skinMgr.getImgId("btn_bg"));
        btn3.setBackgroundResource(skinMgr.getImgId("btn_bg"));
	}
}