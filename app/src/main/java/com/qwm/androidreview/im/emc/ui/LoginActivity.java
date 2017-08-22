/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qwm.androidreview.im.emc.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;

/**
 * Login screen
 * 
 */
public class LoginActivity extends BaseActivity {
	private static final String TAG = "LoginActivity";
	private EditText mName_edt;
	private EditText mPwd_edt;
	private boolean progressShow =  false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.em_activity_login);
		mName_edt = (EditText) findViewById(R.id.username);
		mPwd_edt = (EditText) findViewById(R.id.password);
	}

	/**
	 * 登录
	 * @param view
	 */
	public void login(View view){
		String currentUsername = mName_edt.getText().toString().trim();
		String currentPassword = mPwd_edt.getText().toString().trim();

		if (TextUtils.isEmpty(currentUsername)) {
			Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(currentPassword)) {
			Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
			return;
		}

		progressShow = true;
		final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
		pd.setCanceledOnTouchOutside(false);
		pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				Log.d(TAG, "EMClient.getInstance().onCancel");
				progressShow = false;
			}
		});
		pd.setMessage(getString(R.string.Is_landing));
		pd.show();

		EMClient.getInstance().login(currentUsername, currentPassword, new EMCallBack() {
			@Override
			public void onSuccess() {
				if (!LoginActivity.this.isFinishing() && pd.isShowing()) {
					pd.dismiss();
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
					}
				});
				finish();
			}

			@Override
			public void onError(final int code, final String message) {
				Log.d(TAG, "login: onError: " + code);
				if (!progressShow) {
					return;
				}
				runOnUiThread(new Runnable() {
					public void run() {
						pd.dismiss();
						Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
								Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onProgress(int progress, String status) {
				Log.i(TAG, "onProgress: ---"+progress+"---"+status);
			}
		});
	}

	/**
	 * 注册
	 * @param view
	 */
	public void register(View view){
		startActivity("注册",RegisterActivity.class);
	}
}
