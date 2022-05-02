/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.restuibu.mycardbox.adapter;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

import com.restuibu.mycardbox.util.LayoutHelper;

public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {
	private Context context;
	private ArrayList<Bitmap> images;
	private int h;

	public FancyCoverFlowSampleAdapter(Context c, ArrayList<Bitmap> images,
			int h, int w) {
		// TODO Auto-generated constructor stub
		this.context = c;
		this.images = images;
		this.h = h;
	}

	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public Bitmap getItem(int i) {
		return images.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
		ImageView imageView = null;

		if (reuseableView != null) {
			imageView = (ImageView) reuseableView;
		} else {
			//set coverflow image size
			imageView = new ImageView(viewGroup.getContext());
			imageView.setScaleType(ScaleType.FIT_CENTER);
			imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(
					LayoutParams.MATCH_PARENT, (h * 2) / 5));

		}
		final Bitmap imgSrc = this.getItem(i);

		imageView.setImageBitmap(imgSrc);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutHelper.zoomCard(context, imgSrc);
			}
		});

		return imageView;
	}
}
