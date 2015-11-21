package com.assist.dg.androidassist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.assist.dg.androidassist.model.Major;

/**
 * Created by Spencer Do on 2015. 11. 20..
 */
public class MajorListAdapter extends ListAdapter<Major> {
  public MajorListAdapter(OnItemClickListener<Major> l) {
    super(l);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
    TextView title = (TextView) holder.itemView;
    title.setText(getItems().get(position).getName());
    title.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (getListener() != null) {
          getListener().onClickItem(getItems().get(position));
        }
      }
    });
  }
}
