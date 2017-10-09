package com.qwm.androidreview.im.emc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.qwm.androidreview.BaseActivity;
import com.qwm.androidreview.R;
import com.qwm.androidreview.fragmentdemo.FragmentDemoActivity;
import com.qwm.androidreview.im.emc.ui.ContactListFragment;
import com.qwm.androidreview.im.emc.ui.ConversationListFragment;
import com.qwm.androidreview.im.emc.ui.LoginActivity;
import com.qwm.androidreview.im.emc.ui.SettingsFragment;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/8/22<br>
 * <b>Author:</b> qiwenming<br>
 * <b>Description:</b> <br>
 */
public class EMCActivity extends EMCBaseActivity {
    private static final String TAG = "EMCActivity";
    // textview for unread message count
    private TextView unreadLabel;
    // textview for unread event message
    private TextView unreadAddressLable;

    private Button[] mTabs;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;
    // user logged into another device
    public boolean isConflict = false;
    // user account was removed
    private boolean isCurrentAccountRemoved = false;
    private ConversationListFragment conversationListFragment;
    private ContactListFragment contactListFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_main);
        initView();
        initFragment();
    }


    /**
     * init views
     */
    private void initView() {
        unreadLabel = (TextView) findViewById(R.id.unread_msg_number);
        unreadAddressLable = (TextView) findViewById(R.id.unread_address_number);
        mTabs = new Button[3];
        mTabs[0] = (Button) findViewById(R.id.btn_conversation);
        mTabs[1] = (Button) findViewById(R.id.btn_address_list);
        mTabs[2] = (Button) findViewById(R.id.btn_setting);
        // select first tab
        mTabs[0].setSelected(true);
    }


    private void initFragment() {
        conversationListFragment = new ConversationListFragment();
        contactListFragment = new ContactListFragment();
        SettingsFragment settingFragment = new SettingsFragment();
        fragments = new Fragment[] { conversationListFragment, contactListFragment, settingFragment};

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, conversationListFragment)
                .add(R.id.fragment_container, contactListFragment).hide(contactListFragment).show(conversationListFragment)
                .commit();
    }

    /**
     * on tab clicked
     *
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_conversation:
                index = 0;
                break;
            case R.id.btn_address_list:
                index = 1;
                break;
            case R.id.btn_setting:
                index = 2;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
//        // set current tab selected
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
}
