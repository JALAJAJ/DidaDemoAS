/**
 *
 */
package com.dida.first.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanSimple;
import com.dida.first.entity.BeanStoreDes;
import com.dida.first.storedes.presenter.StoreDesPresenterImpl;
import com.dida.first.storedes.view.StoreDesView;
import com.dida.first.utils.ToastUtil;
import com.dida.first.utils.UIUtils;
import com.dida.first.utils.UrlUtil;

/**
 * @author KingJA
 * @data 2015-10-14 上午9:31:42
 * @use 店铺简介
 */
public class Detail_Store_Activity extends BackTitleActivity implements StoreDesView {

    private LinearLayout ll_store_favorite;
    private ImageView iv_storeDetail_icon;
    private ImageView iv_store_tel;
    private ImageView iv_store_star;
    private TextView tv_store_favorite;
    private int ifCollected;
    private TextView tv_storeDetail_phone;
    private TextView tv_storeDetail_name;
    private TextView tv_storeDetail_fance;
    private TextView tv_storeDetail_address;
    private TextView tv_storeDetail_boss;
    private TextView tv_storeDetail_date;
    private StoreDesPresenterImpl storeDesPresenter;

    @Override
    public View setView() {
        view = View.inflate(Detail_Store_Activity.this,
                R.layout.activity_store_des, null);
        return view;
    }

    @Override
    public void initView() {
        ll_store_favorite = (LinearLayout) view.findViewById(R.id.ll_store_favorite);
        iv_storeDetail_icon = (ImageView) view.findViewById(R.id.iv_storeDetail_icon);
        iv_store_tel = (ImageView) view.findViewById(R.id.iv_store_tel);
        iv_store_star = (ImageView) view.findViewById(R.id.iv_store_star);

        tv_store_favorite = (TextView) view.findViewById(R.id.tv_store_favorite);
        tv_storeDetail_phone = (TextView) view.findViewById(R.id.tv_storeDetail_phone);
        tv_storeDetail_name = (TextView) view.findViewById(R.id.tv_storeDetail_name);
        tv_storeDetail_fance = (TextView) view.findViewById(R.id.tv_storeDetail_fance);
        tv_storeDetail_address = (TextView) view.findViewById(R.id.tv_storeDetail_address);
        tv_storeDetail_boss = (TextView) view.findViewById(R.id.tv_storeDetail_boss);
        tv_storeDetail_date = (TextView) view.findViewById(R.id.tv_storeDetail_date);
        storeDesPresenter = new StoreDesPresenterImpl(this);

    }

    @Override
    public void initDoNet() {

        String shopId = getIntent().getExtras().getString("SHOP_ID");
        storeDesPresenter.loadNet(shopId, "fb9a38d82cd3405a9b60ec54cdb5ecdf");
        ToastUtil.showMyToast(shopId);

    }

    @Override
    public void initEvent() {
        ll_store_favorite.setOnClickListener(this);
        iv_store_tel.setOnClickListener(this);
        iv_storeDetail_icon.setOnClickListener(this);
    }



    @Override
    public void initData() {
        showCollect(ifCollected);
        setBackTitle("店铺简介");
        setOnTextClickListener("进店", new OnTextClickListener() {

            @Override
            public void onTextClick() {
                ToastUtil.showMyToast("进店");

            }
        });
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.ll_store_favorite:

                break;
            case R.id.iv_storeDetail_icon:

                break;
            case R.id.iv_store_tel:
                String phone = tv_storeDetail_phone.getText().toString().trim();
                Intent intentPhone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intentPhone);
                break;

            default:
                break;
        }

    }

    @Override
    public void setBackClick() {
        finish();

    }

    @Override
    public void showCollectedState(BeanSimple data) {
        ifCollected = data.getRes();
        showCollect(ifCollected);

    }

    private void showCollect(int ifCollected) {
        ll_store_favorite.setBackgroundResource(ifCollected == 0 ? R.drawable.shape_lnull_bred_r8 : R.drawable.shape_lnull_bgray_r8);
        iv_store_star.setBackgroundResource(ifCollected == 0 ? R.drawable.fav_line : R.drawable.fav_red);
        tv_store_favorite.setText(ifCollected == 0 ? "收藏" : "已收藏");
        tv_store_favorite.setTextColor(ifCollected == 0 ? UIUtils.getTextColor(R.color.white) : UIUtils.getTextColor(R.color.red));
    }

    @Override
    public void showCollectErr() {
        ToastUtil.showMyToast("收藏失败!");
    }

    @Override
    public void showProgress() {
        mLoadingAndRetryManager.showLoading();
    }

    @Override
    public void hideProgress() {
        mLoadingAndRetryManager.showContent();
    }

    @Override
    public void ShowData(BeanStoreDes data) {
        ToastUtil.showMyToast(data.getRes().getShopImformation().getAddress());
        ifCollected= data.getRes().getIsCollection();
        showCollect(ifCollected);
        BeanStoreDes.ResEntity.ShopImformationEntity shopImformation = data.getRes().getShopImformation();
        tv_storeDetail_date.setText(shopImformation.getCreateTime());
        iv_storeDetail_icon.setImageURI(UrlUtil.getUri(shopImformation.getThumb()));
        tv_storeDetail_phone.setText(shopImformation.getPhone());
        tv_storeDetail_name.setText(shopImformation.getName());
        tv_storeDetail_fance.setText(shopImformation.getFansCount() + " 位粉丝");
        tv_storeDetail_address.setText(shopImformation.getAddress());
        tv_storeDetail_boss.setText(shopImformation.getSellerName());

    }

    @Override
    public void showError() {

    }
}
