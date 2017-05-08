package com.xmnode.demo.activity;

import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xmnode.demo.R;
import com.xmnode.demo.base.BaseActivity;
import com.xmnode.demo.fragment.MainFragment;

public class DrawerLayoutActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener{
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initialWidgets();
    }

    private void initialWidgets() {
        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setVisibility(View.GONE);
        final ImageButton imgBtnList= (ImageButton) findViewById(R.id.img_btn_list);
        imgBtnList.setVisibility(View.VISIBLE);
          mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        imgBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawer(GravityCompat.START);

                }else{
                    mDrawerLayout.openDrawer(GravityCompat.START);

                }


            }
        });
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        if(navigationView!=null){
            setupDrawerContent(navigationView);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance("HomeFragment","")).commit();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private MenuItem lastCheckedItem;

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance("HomeFragment","")).commit();
                        break;
                    case R.id.nav_discussion:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance("DiscussionFragment","")).commit();
                        break;
                    case R.id.nav_friends:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance("FriendsFragment","")).commit();
                        break;
                    case R.id.nav_messages:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance("MessageFragment","")).commit();
                        break;
                }
                if(lastCheckedItem!=null){
                    lastCheckedItem.setChecked(false);
                }
                item.setChecked(true);
                lastCheckedItem=item;
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
