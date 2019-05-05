package com.print.demo.printview;

import java.io.InputStream;

import rego.printlib.export.regoPrinter;
import com.print.demorego.R;
import utils.ApplicationContext;
import utils.preDefiniation.BarcodeType;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class GraphicSecondActivity extends Activity {
	private ApplicationContext context;
	private Button pridraw;
	// 标签设置
	public CheckBox label;
	public EditText wight;
	public EditText hight;
	
	/**
	 * 打印机控制类
	 */
	private regoPrinter mobileprinter;
	private int iObjectCode;
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picdraw);
		pridraw = (Button) findViewById(R.id.button_pridraw);
		// 标签设置
		label = (CheckBox) findViewById(R.id.checkBox_lanlab);
		wight = (EditText) findViewById(R.id.editText_picwide);
		hight = (EditText) findViewById(R.id.editText_pichight);
		final InputStream bitmap = this.getResources().openRawResource(
				R.drawable.star);
		final InputStream bitmaptwo = this.getResources().openRawResource(
				R.drawable.printico);
		context = (ApplicationContext) getApplicationContext();
		
		mobileprinter = context.getObject();
		iObjectCode = context.getState();
		
		pridraw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.getObject().CON_PageStart(context.getState(), true,
						Integer.parseInt(wight.getText().toString()),
						Integer.parseInt(hight.getText().toString()));
				context.getObject().ASCII_CtrlReset(context.getState());
				context.getObject().DRAW_SetFillMode(false);
				context.getObject().DRAW_SetLineWidth(4);
				context.getObject().DRAW_PrintText(context.getState(), 10, 10,
						"REGO", 60);
				context.getObject().DRAW_PrintLine(context.getState(), 162, 7,
						162, 70);
				context.getObject().DRAW_PrintText(context.getState(), 164, 10,
						"瑞工", 60);
				context.getObject().DRAW_PrintText(context.getState(), 90, 80,
						"专业微型打印产品设计、生产、销售", 18);
				context.getObject().DRAW_PrintRectangle(context.getState(), 10,
						100, 320, 160);
				context.getObject().DRAW_PrintLine(context.getState(), 25, 110,
						25, 140);
				context.getObject().DRAW_PrintLine(context.getState(), 25, 140,
						57, 140);
				context.getObject().DRAW_PrintLine(context.getState(), 25, 110,
						57, 140);
				context.getObject().DRAW_PrintCircle(context.getState(), 90,
						125, 15);
				context.getObject().DRAW_PrintOval(context.getState(), 120,
						110, 160, 140);
				context.getObject().DRAW_PrintPicture(context.getState(),
						bitmap, 165, 102, 220, 147);
				context.getObject().DRAW_PrintRectangle(context.getState(),
						225, 110, 250, 140);
				context.getObject().DRAW_Print1D2DBarcode(context.getState(),
						BarcodeType.BT_QRcode.getValue(), 257, 100, 58, 58,
						"12345678");
				context.getObject().DRAW_SetRotate(context.getState(), 90);
				context.getObject().DRAW_PrintPicture(context.getState(),
						bitmaptwo, 0, 170, 180, 180);

				context.getObject().CON_PageEnd(context.getState(),
						context.getPrintway());
			}
		});
	}
}
