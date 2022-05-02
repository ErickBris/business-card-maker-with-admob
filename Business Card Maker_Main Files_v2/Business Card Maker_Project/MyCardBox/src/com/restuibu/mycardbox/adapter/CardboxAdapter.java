package com.restuibu.mycardbox.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.restuibu.mycardbox.EditActivity;
import com.restuibu.mycardbox.R;
import com.restuibu.mycardbox.model.Card;
import com.restuibu.mycardbox.util.LayoutHelper;

public class CardboxAdapter extends BaseAdapter implements Filterable {
	private Context context;
	private List<Card> Cards;
	private List<Card> OriCards;

	private Filter planetFilter;

	public CardboxAdapter(Context context, List<Card> Cards) {
		this.context = context;
		this.Cards = Cards;
		this.OriCards = Cards;
	}

	public void resetData() {
		this.Cards = this.OriCards;
	}

	public int getCount() {
		return Cards.size();
	}

	public Card getItem(int position) {
		return Cards.get(position);
	}

	private class ViewHolder {
		ImageButton ibEdit, ibDel, ibEmail, ibShare;
		RelativeLayout rlLayout;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.cardbox_list_item, parent,
					false);

			viewHolder = new ViewHolder();

			viewHolder.rlLayout = (RelativeLayout) convertView
					.findViewById(R.id.relativeLayout1);
			viewHolder.ibEdit = (ImageButton) convertView
					.findViewById(R.id.imageButton1);
			viewHolder.ibDel = (ImageButton) convertView
					.findViewById(R.id.imageButton2);
			viewHolder.ibEmail = (ImageButton) convertView
					.findViewById(R.id.imageButton3);
			viewHolder.ibShare = (ImageButton) convertView
					.findViewById(R.id.imageButton4);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final Card row = Cards.get(position);

		viewHolder.rlLayout.setBackgroundDrawable(new BitmapDrawable(context
				.getResources(), row.getCardBitmap()));

		viewHolder.ibEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context, EditActivity.class);
				i.putExtra("layoutType", Integer.toString(row.getLayoutType()));
				i.putExtra("cardName", row.getCardName());
				i.putExtra("fullName", row.getEtFullname());
				i.putExtra("jobTitle", row.getEtJobTitle());
				i.putExtra("phone", row.getEtPhone());
				i.putExtra("email", row.getEtEmail());
				i.putExtra("address", row.getEtAddress());
				i.putExtra("company", row.getEtCompany());
				context.startActivity(i);
			}
		});
		viewHolder.ibDel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutHelper.alertDeleteConfirmation(context, Cards,
						row.getCardName(), position);
			}
		});
		viewHolder.ibEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutHelper.sendEmail(context, row.getCardName(),
						row.getEtFullname());
			}
		});
		viewHolder.ibShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutHelper.shareToSocMed(context, row.getCardName());
			}
		});

		// switch (position){
		// case 0:
		// viewHolder.ivImg.setImageResource(R.drawable.addcard);
		// viewHolder.tvName.setText("Create New Card");
		// }

		// setting the image resource and title
		// imgIcon.setImageResource(row_pos.getIcon());
		return convertView;
	}

	@Override
	public long getItemId(int position) {
		return Cards.indexOf(getItem(position));
	}

	@Override
	public Filter getFilter() {
		if (planetFilter == null)
			planetFilter = new PlanetFilter();

		return planetFilter;
	}

	private class PlanetFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = OriCards;
				results.count = OriCards.size();
			} else {
				// We perform filtering operation
				List<Card> nPlanetList = new ArrayList<Card>();

				for (int i = 0; i < Cards.size(); i++) {
					if (Cards.get(i).getEtFullname().toUpperCase()
							.contains(constraint.toString().toUpperCase())) {
						nPlanetList.add(Cards.get(i));

					}
				}

				results.values = nPlanetList;
				results.count = nPlanetList.size();

			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			if (results.count == 0)
				notifyDataSetInvalidated();
			else {

				Cards = (ArrayList<Card>) results.values;
				notifyDataSetChanged();
			}

		}

	}
}
