package com.restuibu.mycardbox.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.restuibu.mycardbox.R;
import com.restuibu.mycardbox.model.CardTemplate;
import com.restuibu.mycardbox.util.LayoutHelper;

public class CardTemplateAdapter extends BaseAdapter implements Filterable {
	private Context context;
	private List<CardTemplate> CardTemplates;
	private List<CardTemplate> OriCardTemplates;
	private AlertDialog alert;
	

	private Filter planetFilter;

	public CardTemplateAdapter(Context context, List<CardTemplate> CardTemplates,
			AlertDialog alert) {
		this.context = context;
		this.CardTemplates = CardTemplates;
		this.OriCardTemplates = CardTemplates;
		this.alert = alert;
	}

	public void resetData() {
		this.CardTemplates = this.OriCardTemplates;
	}

	public int getCount() {
		return CardTemplates.size();
	}

	public CardTemplate getItem(int position) {
		return CardTemplates.get(position);
	}

	private class ViewHolder {

		TextView tvName;
		RelativeLayout rlLayout;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.template_list_item, parent,
					false);

			viewHolder = new ViewHolder();
			viewHolder.tvName = (TextView) convertView
					.findViewById(R.id.textView1);
			viewHolder.rlLayout = (RelativeLayout) convertView
					.findViewById(R.id.relativeLayout1);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final CardTemplate row = CardTemplates.get(position);

		viewHolder.rlLayout.setBackgroundResource(row.getImg());
		viewHolder.rlLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LayoutHelper.initializeCardLayout(context, row.getLayoutType());
				LayoutHelper.enableInputCard(context);
				alert.dismiss();
			}
		});

		viewHolder.tvName.setText(row.getName());

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
		return CardTemplates.indexOf(getItem(position));
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
				results.values = OriCardTemplates;
				results.count = OriCardTemplates.size();
			} else {
				// We perform filtering operation
				List<CardTemplate> nPlanetList = new ArrayList<CardTemplate>();

				for (int i = 0; i < CardTemplates.size(); i++) {
					if (CardTemplates.get(i).getName().toUpperCase()
							.contains(constraint.toString().toUpperCase())) {
						nPlanetList.add(CardTemplates.get(i));

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

				CardTemplates = (ArrayList<CardTemplate>) results.values;
				notifyDataSetChanged();
			}

		}

	}
}
