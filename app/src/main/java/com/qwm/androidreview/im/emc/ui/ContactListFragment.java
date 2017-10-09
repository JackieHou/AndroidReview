package com.qwm.androidreview.im.emc.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.qwm.androidreview.R;
import com.qwm.androidreview.im.emc.widget.ContactItemView;

/**
 * <b>Project:</b> AndroidReview01<br>
 * <b>Create Date:</b> 2017/8/23<br>
 * <b>Author:</b> qwm<br>
 * <b>Description:</b> <br>
 */
public class ContactListFragment extends EaseContactListFragment {
    private static final String TAG = ContactListFragment.class.getSimpleName();
    private ContactSyncListener contactSyncListener;
    private BlackListSyncListener blackListSyncListener;
    private ContactInfoSyncListener contactInfoSyncListener;
    private View loadingView;
    private ContactItemView applicationItem;

    @SuppressLint("InflateParams")
    @Override
    protected void initView() {
        super.initView();
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.em_contacts_header, null);
        HeaderItemClickListener clickListener = new HeaderItemClickListener();
        applicationItem = (ContactItemView) headerView.findViewById(R.id.application_item);
        applicationItem.setOnClickListener(clickListener);
        headerView.findViewById(R.id.group_item).setOnClickListener(clickListener);
        headerView.findViewById(R.id.chat_room_item).setOnClickListener(clickListener);
        headerView.findViewById(R.id.robot_item).setOnClickListener(clickListener);
        listView.addHeaderView(headerView);
        //add loading view
        loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.em_layout_loading_data, null);
        contentContainer.addView(loadingView);

        registerForContextMenu(listView);
    }


    class ContactSyncListener implements DataSyncListener{
        @Override
        public void onSyncComplete(final boolean success) {
            EMLog.d(TAG, "on contact list sync success:" + success);
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    getActivity().runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                            if(success){
                                loadingView.setVisibility(View.GONE);
                                refresh();
                            }else{
                                String s1 = getResources().getString(R.string.get_failed_please_check);
                                Toast.makeText(getActivity(), s1, Toast.LENGTH_LONG).show();
                                loadingView.setVisibility(View.GONE);
                            }
                        }

                    });
                }
            });
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        //

    }

    public void contactList(){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        try {
            EMClient.getInstance().contactManager().getAllContactsFromServer();
        } catch (HyphenateException e) {
            Log.i(TAG, "contactList: "+e.getDescription());
        }
    }

    protected class HeaderItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.application_item:
                    // 进入申请与通知页面
//                    startActivity(new Intent(getActivity(), NewFriendsMsgActivity.class));
                    break;
                case R.id.group_item:
                    // 进入群聊列表页面
//                    startActivity(new Intent(getActivity(), GroupsActivity.class));
                    break;
                case R.id.chat_room_item:
                    //进入聊天室列表页面
//                    startActivity(new Intent(getActivity(), PublicChatRoomsActivity.class));
                    break;
                case R.id.robot_item:
                    //进入Robot列表页面
//                    startActivity(new Intent(getActivity(), RobotsActivity.class));
                    break;

                default:
                    break;
            }
        }

    }

    class BlackListSyncListener implements DataSyncListener{

        @Override
        public void onSyncComplete(boolean success) {

        }

    }

    class ContactInfoSyncListener implements DataSyncListener{

        @Override
        public void onSyncComplete(final boolean success) {

        }

    }

    public interface DataSyncListener {
        /**
         * sync complete
         * @param success true：data sync successful，false: failed to sync data
         */
        void onSyncComplete(boolean success);
    }

}
