/**
 *
 */
package com.dida.first.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.entity.BeanAddressList;
import com.dida.first.utils.ToastUtil;
import com.dida.first.wheelview.ChangeAddressDialog;
import com.dida.first.wheelview.ChangeAddressDialog.OnAddressCListener;

/**
 * @author KingJA
 * @data 2015-9-21 下午3:49:44
 * @use
 */
public class Detail_Address_Activity extends BackTitleActivity {

    private LinearLayout ll_address_update_area;

    private TextView tv_address_update_area;
    private EditText et_address_update_address;
    private EditText et_address_update_name;
    private EditText et_address_update_phone;

    private BeanAddressList.ResEntity.DeliveryAdressListEntity address_detail;
    private CheckBox cb_address_update_default;
    private String area;
    private String address;
    private String name;
    private String phone;
    private boolean ifDefault;
    private Button btn_address_update_delete;
    private Button btn_address_update_confirm;


    @Override
    public View setView() {
        view = View.inflate(this, R.layout.activity_mine_personal_address_edit, null);
        return view;
    }

    @Override
    public void initView() {


        btn_address_update_delete = (Button) view.findViewById(R.id.btn_address_update_delete);
        btn_address_update_confirm = (Button) view.findViewById(R.id.btn_address_update_confirm);

        ll_address_update_area = (LinearLayout) view.findViewById(R.id.ll_address_update_area);


        tv_address_update_area = (TextView) view.findViewById(R.id.tv_address_update_area);
        et_address_update_address = (EditText) view.findViewById(R.id.et_address_update_address);
        et_address_update_name = (EditText) view.findViewById(R.id.et_address_update_name);
        et_address_update_phone = (EditText) view.findViewById(R.id.et_address_update_phone);

        cb_address_update_default = (CheckBox) view.findViewById(R.id.cb_address_update_default);

    }

    @Override
    public void initDoNet() {
        address_detail = (BeanAddressList.ResEntity.DeliveryAdressListEntity) getIntent().getSerializableExtra("ADDRESS_DETAIL");
        ToastUtil.showMyToast(address_detail.getArea());

    }

    @Override
    public void initEvent() {
        ll_address_update_area.setOnClickListener(this);
        btn_address_update_delete.setOnClickListener(this);
        btn_address_update_confirm.setOnClickListener(this);

    }

    @Override
    public void initData() {
        tv_address_update_area.setText(address_detail.getArea());
        et_address_update_address.setText(address_detail.getDetailAddress());
        et_address_update_name.setText(address_detail.getReceiverName());
        et_address_update_phone.setText(address_detail.getMobileNo());
        cb_address_update_default.setChecked(address_detail.getIsDefault()==1?true:false);
        setBackTitle("修改地址");

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.ll_address_update_area:
                ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
                        this);
                mChangeAddressDialog.setAddress("浙江", "温州", "鹿城区");
                mChangeAddressDialog.show();
                mChangeAddressDialog
                        .setAddresskListener(new OnAddressCListener() {

                            @Override
                            public void onClick(String province, String city, String area, String provinceId, String cityId, String areaId) {
                                tv_address_update_area.setText(province + city  + area);
                                area=provinceId + "," + cityId + "," + areaId;
                            }

                        });

                break;
            case R.id.btn_address_update_delete:
                break;
            case R.id.btn_address_update_confirm:
                address=et_address_update_address.getText().toString().trim();
                 name=et_address_update_name.getText().toString().trim();
                phone=et_address_update_phone.getText().toString().trim();
                ifDefault=cb_address_update_default.isChecked();
                updateAddress( address_detail.getDeliveryAddressId(),area ,address ,name ,phone, ifDefault);
                break;
            default:
                break;
        }

    }

    private void updateAddress(String deliveryAddressId, String area, String detailAddress, String receiverName, String mobileNo, boolean isDefault) {

    }

//    private void edit(EditText editText, TextView textView) {
//        editText.setVisibility(View.VISIBLE);
//        editText.setFocusable(true);
//        editText.setFocusableInTouchMode(true);
//        editText.requestFocus();
//        editText.setText(textView.getText());
//        editText.setSelection(textView.getText().length());
//        //打开软键盘
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//    }

    @Override
    public void setBackClick() {
        finish();

    }

}
