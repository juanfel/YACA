package com.juanfel.yaca;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by juanfel on 17-04-17.
 */

public class RecipeListAdapter extends BaseExpandableListAdapter {
    private final List<Recipe> recipes;
    public android.view.LayoutInflater inflater;
    public AppCompatActivity activity;

    public RecipeListAdapter(AppCompatActivity act, List<Recipe> recipes){
        this.recipes = recipes;
        this.activity = act;
        inflater = this.activity.getLayoutInflater();
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.detail_recipe_list, null);
        }
        String detail = (String) getChild(groupPosition, childPosition);
        TextView text = (TextView) convertView.findViewById(R.id.recipe_list_content_text);
        Log.d("YACA",detail);
        text.setText(detail);
        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.header_recipe_list, null);
        }
        Recipe recipe = (Recipe) getGroup(groupPosition);
        TextView text = (TextView) convertView.findViewById(R.id.list_recipe_title);
        text.setText(recipe.getNombre());

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //return recipes.get(groupPosition).children.get(childPosition);
        Recipe recipe = (Recipe) getGroup(groupPosition);
        return "ID: " + recipe.getId();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return recipes.get(groupPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getGroupCount() {
        return recipes.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return recipes.get(groupPosition).getId();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return recipes.get(groupPosition).children.size();
        return 1;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
