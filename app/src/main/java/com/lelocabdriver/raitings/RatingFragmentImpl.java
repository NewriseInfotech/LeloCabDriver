package com.lelocabdriver.raitings;

import com.lelocabdriver.baseclasses.BaseActivity;

/**
 * Created by ashish on 08-05-2017.
 */

class RatingFragmentImpl implements IRatingFragmentPresenter {
    private BaseActivity mActivity;
    private IRatingFragmentView view;

    public RatingFragmentImpl(BaseActivity mActivity, IRatingFragmentView view) {
        this.mActivity = mActivity;
        this.view = view;
    }

    @Override
    public void getRating() {


    }
}
