package com.alien.xenocorez.plarnpoung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XenoCoreZ on 29/4/2558.
 */
public class foodItemAdapter_xtended extends ArrayAdapter<foodItem> {
    private ArrayList<foodItem> objects;
    private ArrayList<foodItem> objects2;
    public foodItemAdapter_xtended(Context context, int resource, ArrayList<foodItem> objects1) {
        super(context, resource, objects1);
        this.objects = objects1;
        this.objects2 = objects1;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                objects = (ArrayList<foodItem>) results.values;
                foodItemAdapter_xtended.this.notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();

                //Copy! World
                if(objects.size() != objects2.size()){
                    objects = objects2;
                }
                // We implement here the filter logic
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = objects;
                    results.count = objects.size();
                }
                else {
                    // We perform filtering operation
                    List<foodItem> nCurrencyList = new ArrayList<foodItem>();

                    for (foodItem c : objects) {
                        if (c.getFoodName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                            nCurrencyList.add(c);
                    }

                    results.values = nCurrencyList;
                    results.count = nCurrencyList.size();

                }
                return results;
            }
        };
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public foodItem getItem(int position)
    {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemViewType(int arg0)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public boolean hasStableIds()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty()
    {
        // TODO Auto-generated method stub
        return objects.size() == 0;
    }


    public View getView(final int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item_xtended, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        foodItem i = objects.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView tt = (TextView) v.findViewById(R.id.product_name);

            tt.setText(i.getFoodName());

            TextView ttd = (TextView) v.findViewById(R.id.product_cal);

            ttd.setText(Integer.toString(i.getFoodCal()));
        }

        View.OnClickListener addC = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(), objects.get(position).getFoodName(), Toast.LENGTH_SHORT).show();
            }
        };

        v.setOnClickListener(addC);

        // the view must be returned to our activity
        return v;

    }

}
