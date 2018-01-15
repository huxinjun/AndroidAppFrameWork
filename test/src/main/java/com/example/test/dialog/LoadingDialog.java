package com.example.test.dialog;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.SmartDialog;
import com.app.annotation.BindFieldName;
import com.app.annotation.LayoutDataType;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.presenter.impl.layout.LayoutCreater;
import com.app.test.TestModel;
import com.example.test.R;

@BindLayoutCreater(creater=LoadingDialog.LoadingDialogCreater.class)
public class LoadingDialog extends SmartDialog {

	
	
	public LoadingDialog(Context context) {
		super(context);
	}

	@LayoutDataType(TestModel.class)
	@BindView(R.layout.dialog_loading)
	public static class LoadingDialogCreater extends LayoutCreater {
		

		@BindFieldName("abc")
		@BindView(R.id.tv_content)
		public TextView tv_content;
		
		

		@Override
		public void onDataPrepared() {

		}
	}

	
}
