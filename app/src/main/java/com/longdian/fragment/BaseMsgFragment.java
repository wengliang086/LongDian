package com.longdian.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longdian.R;
import com.longdian.activity.ContentActivity;
import com.longdian.fragment.base.BaseMsgAboutSystemFragment;
import com.longdian.fragment.base.BaseMsgPrivateMsgFragment;
import com.longdian.fragment.base.BaseMsgQrCodeFragment;
import com.longdian.fragment.base.BaseMsgUpdatePasswordFragment;
import com.longdian.fragment.base.TableStationFragment;
import com.longdian.fragment.runningstate.TableFragment;

public class BaseMsgFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base_msg, container, false);

        view.findViewById(R.id.id_private_msg).setOnClickListener(this);
        view.findViewById(R.id.id_update_pwd).setOnClickListener(this);
        view.findViewById(R.id.id_qr_code).setOnClickListener(this);
        view.findViewById(R.id.id_about_system).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_private_msg:
                ContentActivity.start(getActivity(), BaseMsgPrivateMsgFragment.class);
                break;
            case R.id.id_update_pwd:
                ContentActivity.start(getActivity(), BaseMsgUpdatePasswordFragment.class);
                break;
            case R.id.id_qr_code:
//                ContentActivity.start(getActivity(), BaseMsgQrCodeFragment.class);
                ContentActivity.start(getActivity(), TableStationFragment.class);
                break;
            case R.id.id_about_system:
                ContentActivity.start(getActivity(), BaseMsgAboutSystemFragment.class);
                break;
        }
    }
}
