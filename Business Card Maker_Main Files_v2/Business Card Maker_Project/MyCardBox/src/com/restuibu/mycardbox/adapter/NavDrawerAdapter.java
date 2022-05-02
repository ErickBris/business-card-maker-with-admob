package com.restuibu.mycardbox.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.restuibu.mycardbox.R;
import com.restuibu.mycardbox.model.NavDrawerItem;

public class NavDrawerAdapter extends BaseAdapter implements Filterable {
	private Context context;
	private List<NavDrawerItem> NavDrawerItems;
	private List<NavDrawerItem> OriNavDrawerItems;

	private Filter planetFilter;

	public NavDrawerAdapter(Context context, List<NavDrawerItem> NavDrawerItems) {
		this.context = context;
		this.NavDrawerItems = NavDrawerItems;
		this.OriNavDrawerItems = NavDrawerItems;
	}

	public void resetData() {
		this.NavDrawerItems = this.OriNavDrawerItems;
	}

	public int getCount() {
		return NavDrawerItems.size();
	}

	public NavDrawerItem getItem(int position) {
		return NavDrawerItems.get(position);
	}

	private class ViewHolder {

		TextView tvName;
		ImageView ivImg;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.sliding_list_item, parent,
					false);

			viewHolder = new ViewHolder();

			viewHolder.tvName = (TextView) convertView
					.findViewById(R.id.textView1);
			viewHolder.ivImg = (ImageView) convertView
					.findViewById(R.id.imageView1);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final NavDrawerItem row = NavDrawerItems.get(position);
		viewHolder.tvName.setText(row.getName());
		viewHolder.ivImg.setImageResource(row.getImg());

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
		return NavDrawerItems.indexOf(getItem(position));
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
				results.values = OriNavDrawerItems;
				results.count = OriNavDrawerItems.size();
			} else {
				// We perform filtering operation
				List<NavDrawerItem> nPlanetList = new ArrayList<NavDrawerItem>();

				for (int i = 0; i < NavDrawerItems.size(); i++) {
					if (NavDrawerItems.get(i).getName().toUpperCase()
							.contains(constraint.toString().toUpperCase())) {
						nPlanetList.add(NavDrawerItems.get(i));

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

				NavDrawerItems = (ArrayList<NavDrawerItem>) results.values;
				notifyDataSetChanged();
			}

		}

	}
}
