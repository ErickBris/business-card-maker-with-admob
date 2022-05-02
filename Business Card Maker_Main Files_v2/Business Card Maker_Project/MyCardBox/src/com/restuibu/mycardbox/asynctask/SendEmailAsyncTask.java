package com.restuibu.mycardbox.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.restuibu.mycardbox.util.Mail;

public class SendEmailAsyncTask extends AsyncTask<String, String, String> {

	private ProgressDialog pDialog;
	private Context c;
	private final String EMAIL = "emirkipang@gmail.com";
	private final String PASSWORD = "prpncrthnemierz";
	private final String FACEBOOK = "https://www.facebook.com/emir.kipang";
	private final String TWITTER = "https://twitter.com/emirkipang";
	private final String APK = "https://yourapklink.apk";

	public SendEmailAsyncTask(Context c) {
		this.c = c;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		pDialog = new ProgressDialog(c);
		pDialog.setMessage("Sending email...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
	}

	@Override
	protected String doInBackground(String... args) {
		String result = "";
		String[] to = args[0].split(";");
		String fullName = args[2];
		String cardName = args[1];		
		String subject = fullName + " - " + "My Business Card";		
		
		// set manually your email account
		Mail m = new Mail(EMAIL, PASSWORD);

		// your target email
		m.setTo(to);
		m.setFrom(EMAIL);
		m.setSubject(subject);

		try {
			String APPLICATION_PACKAGE_NAME = c.getPackageName();
			String path = Environment.getExternalStorageDirectory() + "/"
					+ APPLICATION_PACKAGE_NAME + "/" + cardName + ".png";

			String text = loadAssetTextAsString(c, "email.html");

			//attach file based on path and filename
			m.addAttachment(path, fullName);
			m.setBody(String.format(text, fullName, fullName, APK, TWITTER,
					FACEBOOK));

			if (m.send())
				result = "Email sent";
			else
				result = "Email was not send";

		} catch (Exception e) {
			result = e.toString();
		}

		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		// Display the result on webview
		Toast.makeText(c, result, Toast.LENGTH_SHORT).show();
		pDialog.dismiss();
	}

	private String loadAssetTextAsString(Context context, String name) {
		BufferedReader in = null;
		try {
			StringBuilder buf = new StringBuilder();
			InputStream is = context.getAssets().open(name);
			in = new BufferedReader(new InputStreamReader(is));

			String str;
			boolean isFirst = true;
			while ((str = in.readLine()) != null) {
				if (isFirst)
					isFirst = false;
				else
					buf.append('\n');
				buf.append(str);
			}
			return buf.toString();
		} catch (IOException e) {
			Log.e("APP", "Error opening asset " + name);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					Log.e("APP", "Error closing asset " + name);
				}
			}
		}

		return null;
	}

}
