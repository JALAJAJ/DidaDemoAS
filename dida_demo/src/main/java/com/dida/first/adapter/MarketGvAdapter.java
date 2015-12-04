package com.dida.first.adapter;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.bean.MarketBean;
import com.dida.first.utils.UImageLoaderUitl;

import java.util.List;

public class MarketGvAdapter extends BaseAdapter {
    private List<MarketBean.ResEntity.ProductsEntity.StEntity> list;
    private Context context;
    private LinearLayout.LayoutParams param;

    public MarketGvAdapter(List<MarketBean.ResEntity.ProductsEntity.StEntity> list, Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int itemWidth = (screenWidth - 3 * 8) / 2;
        param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, itemWidth);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_marketlist, null);
            viewHolder=new ViewHolder(convertView);
            viewHolder.ivmarketitemimg=(ImageView) convertView
                    .findViewById(R.id.iv_market_item_img);
            viewHolder.tvmarketitemtitle=(TextView) convertView
                    .findViewById(R.id.tv_market_item_title);
            viewHolder.tvmarketitemprice=(TextView) convertView
                    .findViewById(R.id.tv_market_item_price);
            viewHolder.tvmarketitemcount=(TextView) convertView
                    .findViewById(R.id.tv_market_item_count);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.ivmarketitemimg.setLayoutParams(param);
        viewHolder.tvmarketitemtitle.setText(list.get(position).getName());
        viewHolder.tvmarketitemprice.setText(list.get(position).getPrice() + "");
        viewHolder.tvmarketitemcount.setText(list.get(position).getSalesCount()+"");
        UImageLoaderUitl.displayLvMidImage(list.get(position).getThumb(),viewHolder.ivmarketitemimg);
        return convertView;
    }


    public class ViewHolder {
        public ImageView ivmarketitemimg;
        public TextView tvmarketitemtitle;
        public TextView tvmarketitemprice;
        public TextView tvmarketitemcount;
        public View root;

        public ViewHolder(View root) {
            ivmarketitemimg = (ImageView) root.findViewById(R.id.iv_market_item_img);
            tvmarketitemtitle = (TextView) root.findViewById(R.id.tv_market_item_title);
            tvmarketitemprice = (TextView) root.findViewById(R.id.tv_market_item_price);
            tvmarketitemcount = (TextView) root.findViewById(R.id.tv_market_item_count);
            this.root = root;
        }
    }
}
