package ve.needforock.cryptoprice.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import ve.needforock.cryptoprice.tabs.CryptoCalculatorFragment;
import ve.needforock.cryptoprice.tabs.CryptoListFragment;

/**
 * Created by Soporte on 04-Oct-17.
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private static SparseArray<WeakReference<Fragment>> instantiatedFragments = new SparseArray<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CryptoListFragment.newInstance();
            case 1:
                return CryptoCalculatorFragment.newInstance();

        }

        return CryptoListFragment.newInstance();
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Market List";
            case 1:
                return "Calculator";

        }
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        instantiatedFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Nullable
    public static Fragment getFragment( int position) {
         WeakReference<Fragment> wr = instantiatedFragments.get(position);
        if (wr != null) {
            return wr.get();
        } else {
            return null;
        }
    }


}

