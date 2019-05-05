package com.print.demo.printview;

import com.print.demorego.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * @author Adil Soomro
 * 
 */
@SuppressWarnings("deprecation")
public class GraphicTabsActivity extends TabActivity {
	TabHost tabHost;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picmain);
		tabHost = getTabHost();
		setTabs();
	}

	private void setTabs() {
		addTab(R.string.tab_lan, R.drawable.tab_piclan, GraphicFirstActivity.class);
		addTab(R.string.tab_pic, R.drawable.tab_picdraw, GraphicSecondActivity.class);
		addTab(R.string.tab_picapply, R.drawable.tab_texthome,
				GraphicThirdActivity.class);
	}

	private void addTab(int labelId, int drawableId, Class<?> c) {
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

		View tabIndicator = LayoutInflater.from(this).inflate(
				R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}