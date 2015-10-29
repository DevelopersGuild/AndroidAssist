package com.assist.dg.androidassist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.model.College;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public class CollegeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public interface OnCollegeItemClickListener {
    public void onClickCollegeItem(College c);
  }

  private List<College> colleges = new ArrayList<>();
  private OnCollegeItemClickListener listener;

  public void setColleges(List<College> colleges) {
    this.colleges = colleges;
    notifyDataSetChanged();
  }

  public void setClickListener(OnCollegeItemClickListener l) {
    listener = l;
  }

  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    TextView view = (TextView) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_college, parent, false);
    return new CollegeTextHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
    TextView title = (TextView) holder.itemView;
    title.setText(colleges.get(position).getName());
    title.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (listener != null) {
          listener.onClickCollegeItem(colleges.get(position));
        }
      }
    });
  }

  @Override public int getItemCount() {
    return colleges.size();
  }

  public static class CollegeTextHolder extends RecyclerView.ViewHolder {

    private View base;

    public CollegeTextHolder(View itemView) {
      super(itemView);
      base = itemView;
    }

    public TextView getTitle() {
      return (TextView) base;
    }
  }
}
