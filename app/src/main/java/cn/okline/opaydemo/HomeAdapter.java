package cn.okline.opaydemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * OKLine(HangZhou) co.,Ltd.
 * Author  : Zheng Jun
 * Email   : zhengjun@okline.cn
 * Date    : 2017/9/10
 * Summary : 在这里描述Class的主要功能
 */

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private List<String> mList;
    private  OnClickListener onClickListener;

    public HomeAdapter(Context context, List<String> mList, OnClickListener onClickListener) {
        this.context = context;
        this.mList = mList;
        this.onClickListener = onClickListener;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(View.inflate(context,R.layout.tv_item,null));
    }

    public void removeData(int position){
        mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.text_view.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         TextView text_view;

        public ViewHolder(View itemView) {
            super(itemView);
            text_view = (TextView) itemView.findViewById(R.id.text);
        }
    }

    interface  OnClickListener{
        void onClick(View view,int position);
        void onLongClick(View view,int position);
    }
}
