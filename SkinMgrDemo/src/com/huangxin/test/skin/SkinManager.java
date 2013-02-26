package com.huangxin.test.skin;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;

public class SkinManager {

	private static SkinManager instance;
	private Context context;
	public static final int DEFAULT_SKIN = 0;
	private static int CURRENT_SKIN = DEFAULT_SKIN;
	private AssetManager assetMgr;
	private Map<Integer, String> skins;
	private Properties propt = new Properties();
	
	private SkinManager(Context context, int count) {
		this.context = context;
		setUpSkin(count);
	}
	
	public static SkinManager getInstance(Context context, int count) {
		if (instance == null)
			instance = new SkinManager(context, count);
		return instance;
	}
	
	/**
	 * 
	 * @param count	Ƥ����������
	 */
	private void setUpSkin(int count) {
		assetMgr = context.getAssets();
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = assetMgr.open("skins.ini");
			prop.load(is);
			if (skins == null)
				skins = new HashMap<Integer, String>();
			for (int i=0; i<count; i++) {
				skins.put(i, prop.getProperty("skin" + i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setSkin(int skinIndex) {
		CURRENT_SKIN = skinIndex;
		if (assetMgr == null)
			assetMgr = context.getAssets();
		String skin = skins.get(skinIndex);
		String fileName = skin + ".ini";
		InputStream is = null;
		try {
			is = assetMgr.open(fileName);
			propt.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��assetĿ¼�µ������ļ��и���imgKey����valueֵ��Ҳ���ǻ��ͼƬ���ƣ�Ȼ����ݴ˷������ͼƬ��id
	 * @param imgKey
	 * @return
	 */
	public int getImgId(String imgKey) {
		String imgName = propt.getProperty(imgKey);
		return context.getResources().getIdentifier(imgName, "drawable", "com.huangxin.test.skin");
		
	}
	
	/**
	 * ��ȡ��ǰƤ����id
	 * @return
	 */
	public int getCurrentSkin() {
		return CURRENT_SKIN;
	}
	
	/**
	 * ��ȡ��ǰƤ����
	 * @return
	 */
	public String getCurrentSkinName() {
		return skins.get(CURRENT_SKIN);
	}
}
