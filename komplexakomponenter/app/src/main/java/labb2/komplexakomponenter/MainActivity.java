package labb2.komplexakomponenter;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ExpandableListView expList;
    String fullname;
    String groupName;
    String childName;
    ArrayList<Shades> headline;
    EditText searchText;
    int prio;
    int groupIndex;
    int childIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Adapter myAdapter = new Adapter(this);
        this.expList = (ExpandableListView) findViewById(R.id.expList);
        this.expList.setAdapter(myAdapter);
        this.searchText = (EditText) findViewById(R.id.search_text);
        this.searchText.setText("/");
        this.searchText.setSelection(this.searchText.length());
        this.headline = myAdapter.getHeadline();

        this.searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (prio == 0) {
                    groupIndex = 0;
                    childIndex = 0;
                    while (!searchShade(myAdapter, s.toString(), groupIndex, childIndex)) {
                        if (myAdapter.getChildrenCount(groupIndex) > childIndex + 1) {
                            searchText.setSelected(false);
                            childIndex++;
                        } else if (myAdapter.getGroupCount() > groupIndex + 1) {
                            childIndex = 0;
                            groupIndex++;
                        } else {
                            return;
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.expList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View viewClicked, int groupPosition, long id) {
                prio = 1;
                if (prio == 1) {
                    searchText.setText("/" + myAdapter.getGroupName(groupPosition));
                    searchText.setSelection(searchText.length());
                    prio = 0;
                }
                return false;
            }
        });

        this.expList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View viewClicked, int groupPosition, int childPosition, long id) {
                prio = 1;
                if (prio == 1) {
                    searchText.setText("/" + myAdapter.getGroupName(groupPosition) + "/" + myAdapter.getChildName(groupPosition, childPosition));
                    searchText.setSelection(searchText.length());
                    viewClicked.setSelected(true);
                    prio = 0;
                }
                return true;
            }
        });
    }

    public boolean searchShade(Adapter adapter, String textoToSearch, int groupPos, int childPos) {
        this.groupName = "/" + adapter.getGroupName(groupPos); //   /Light
        this.childName = "/" + adapter.getChildName(groupPos, childPos); //     /Yellow
        this.fullname = "/" + adapter.getGroupName(groupPos) + "/" + adapter.getChildName(groupPos, childPos); // /Light/Yellow
        if (this.fullname.contains(textoToSearch) && this.fullname.startsWith(textoToSearch)) {
            this.searchText.setSelected(false);
            if (this.groupName.equals(textoToSearch)) {
                this.expList.expandGroup(groupPos);
            }
            if (!this.fullname.equals(textoToSearch)) {
                return true;
            } else {
                    this.expList.setSelectedChild(groupPos, childPos, true);
            }
            return true;
        }
        this.searchText.setSelected(true);
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
