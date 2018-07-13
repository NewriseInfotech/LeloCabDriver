package com.lelocabdriver.scheduledride;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lelocabdriver.R;
import com.lelocabdriver.baseclasses.BaseActivity;
import com.lelocabdriver.newride.NewRideFragment;
import com.lelocabdriver.raitings.RatingsAdapter;
import com.lelocabdriver.raitings.RatingsFragment;

/**
 * Created by ashish on 09-05-2017.
 */

public class ScheduledRideAdapter extends RecyclerView.Adapter<ScheduledRideAdapter.ViewHolder> {
    private BaseActivity mActivity;

    public ScheduledRideAdapter(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scheduled_ride_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getBinding().executePendingBindings();
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContent, new ScheduledRideDetailFragment())
                        .addToBackStack(RatingsFragment.class.getName())
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        private LinearLayout llMain;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            llMain = (LinearLayout) itemView.findViewById(R.id.ll_main);
        }


        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
