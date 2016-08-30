package labb2.komplexakomponenter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseExpandableListAdapter {
    private Context ctx;
    private Model model;

    Adapter(Context ctx) {
        this.ctx = ctx;
        this.model = new Model();
    }

    public ArrayList<Shades> getHeadline() {
        return this.model.getHeadline();
    }

    public int getGroupCount() {
        return this.model.getGroupCount();
    }

    public int getChildrenCount(int groupPosition) {
        return this.model.getChildrenCount(groupPosition);
    }

    public Object getGroup(int groupPosition) {
        return this.model.getGroupName(groupPosition);
    }

    public String getGroupName(int groupPosition) {
        return this.model.getGroupName(groupPosition);
    }

    public String getChildName(int groupPosition, int childPosition) {
        return this.model.getChildName(groupPosition, childPosition);
    }

    public Object getChild(int groupPosition, int childPosition) {
        return this.model.getChildName(groupPosition, childPosition);
    }

    public long getGroupId(int groupPosition) {
        return 0;
    }

    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.parent_layout, null);
        }
        TextView text = (TextView) convertView.findViewById(R.id.parent_items);
        text.setText(this.model.getGroupName(groupPosition));
        text.setTypeface(Typeface.DEFAULT_BOLD);
        return text;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.child_layout, null);
        }
        ((TextView) convertView.findViewById(R.id.child_items)).setText(this.model.getChildName(groupPosition, childPosition));
        return convertView;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

