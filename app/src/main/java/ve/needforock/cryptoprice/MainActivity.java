package ve.needforock.cryptoprice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ve.needforock.cryptoprice.adapters.SectionsPagerAdapter;
import ve.needforock.cryptoprice.tabs.CryptoCalculatorFragment;
import ve.needforock.cryptoprice.tabs.CryptoListFragment;

public class MainActivity extends AppCompatActivity {

    private CryptoListFragment cryptoListFragment;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int count;
    private TabLayout tabLayout;
    private int menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count=0;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton addFab = (FloatingActionButton) findViewById(R.id.addFab);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //cryptoListFragment = (CryptoListFragment) getSupportFragmentManager().findFragmentById(R.id.listfragment);

        FloatingActionButton executeFab = (FloatingActionButton) findViewById(R.id.executeFab);
        executeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = tabLayout.getSelectedTabPosition();

                Fragment fragment = SectionsPagerAdapter.getFragment(tabLayout.getSelectedTabPosition());
                if (fragment != null) {
                    switch (position) {
                        case 0:
                            if (menuId == R.id.usd_settings) {
                                ((CryptoListFragment) fragment).refreshList("USD");
                            }else if(menuId == R.id.eur_settings) {
                                ((CryptoListFragment) fragment).refreshList("EUR");
                            }else if(menuId == R.id.clp_settings) {
                                ((CryptoListFragment) fragment).refreshList("CLP");
                            }
                            break;
                        case 1:
                            ((CryptoCalculatorFragment) fragment).calculate();
                            break;
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Nulo", Toast.LENGTH_SHORT).show();
                }

            }
        });


        addFab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                int position = tabLayout.getSelectedTabPosition();

                Fragment fragment = SectionsPagerAdapter.getFragment(tabLayout.getSelectedTabPosition());
                if (fragment != null) {
                    switch (position) {
                        case 0:
                            //TODO
                            break;
                        case 1:
                            count++;
                            ((CryptoCalculatorFragment) fragment).addField(count);
                            break;
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Nulo", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        menuId = item.getItemId();
        Log.d("opcion", String.valueOf(menuId));

        //noinspection SimplifiableIfStatement
        if (menuId == R.id.usd_settings) {
            int position = tabLayout.getSelectedTabPosition();
            Fragment fragment = SectionsPagerAdapter.getFragment(tabLayout.getSelectedTabPosition());
            if (fragment != null) {
                switch (position) {
                    case 0:
                        ((CryptoListFragment) fragment).refreshList("USD");
                        break;
                    case 1:

                        break;
                }

            }
        }

        if (menuId == R.id.eur_settings){
            int position = tabLayout.getSelectedTabPosition();
            Fragment fragment = SectionsPagerAdapter.getFragment(tabLayout.getSelectedTabPosition());
            if (fragment != null) {
                switch (position) {
                    case 0:
                        ((CryptoListFragment) fragment).refreshList("EUR");
                        break;
                    case 1:

                        break;
                }

            }

        }
        if (menuId == R.id.clp_settings){
            int position = tabLayout.getSelectedTabPosition();
            Fragment fragment = SectionsPagerAdapter.getFragment(tabLayout.getSelectedTabPosition());

            if (fragment != null) {
                switch (position) {
                    case 0:
                        ((CryptoListFragment) fragment).refreshList("CLP");
                        break;
                    case 1:

                        break;
                }

            }

        }

        return super.onOptionsItemSelected(item);
    }


}
