package com.huangxin.test.skin;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SecondActivity extends Activity {
	private LinearLayout lay;
	private Button btn1, btn2;
	private SkinManager skinMgr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		initView();
		skinMgr = SkinManager.getInstance(this, 2);	
		skinMgr.setSkin(skinMgr.getCurrentSkin());
		btn1.setBackgroundResource(skinMgr.getImgId("btn_bg"));
		btn2.setBackgroundResource(skinMgr.getImgId("btn_bg"));
		lay.setBackgroundResource(skinMgr.getImgId("bg"));
	}
	
	private void initView() {
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		lay = (LinearLayout) findViewById(R.id.second_activity);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (skinMgr.getCurrentSkin() == SkinManager.DEFAULT_SKIN)
					Toast.makeText(SecondActivity.this, "当前已是默认皮肤-female风格", Toast.LENGTH_SHORT).show();
				else {
					skinMgr.setSkin(SkinManager.DEFAULT_SKIN);
				}
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (skinMgr.getCurrentSkin() == 1)
					Toast.makeText(SecondActivity.this, "当前已是默认皮肤-business风格", Toast.LENGTH_SHORT).show();
				else {
					skinMgr.setSkin(1);
				}
			}
		});
	}
}
