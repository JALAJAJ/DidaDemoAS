package com.dida.first.view;

import com.dida.first.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class DialogProgress extends AlertDialog {

	/**
	 * @param context
	 */
	public DialogProgress(Context context) {
		super(context,R.style.dialog_progress);
		this.setCancelable(false);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_progress);
	}
	
}
