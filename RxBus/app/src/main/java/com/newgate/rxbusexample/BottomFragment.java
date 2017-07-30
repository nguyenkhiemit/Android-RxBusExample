package com.newgate.rxbusexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by khiemnd on 7/30/17.
 */

public class BottomFragment extends Fragment {

    @BindView(R.id.txt_click)
    TextView clickTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        ButterKnife.bind(this, view);
        clickTextView = (TextView) view.findViewById(R.id.txt_click);

        bindView(view);
        return view;
    }

    void bindView(View view) {
        MyRxBus.instanceOf().getEvent()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.immediate())
                .subscribe(o -> {
                    if(o instanceof TapEvent) {
                        clickTextView.setVisibility(((TapEvent) o).isTap() == true ?  View.VISIBLE : View.GONE);
                    }
                });
        }
}
