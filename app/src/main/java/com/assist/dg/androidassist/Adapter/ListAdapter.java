package com.assist.dg.androidassist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.assist.dg.androidassist.R;
import com.assist.dg.androidassist.model.AssistData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spencer Do on 2015. 10. 28..
 */
public abstract class ListAdapter<T extends AssistData> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public ListAdapter(OnItemClickListener<T> l) {
    setClickListener(l);
  }

  public interface OnItemClickListener<T> {
    void onClickItem(T c);
  }

  private List<T> items = new ArrayList<>();
  private OnItemClickListener listener;

  public void setData(List<T> data) {
    this.items = data;
    notifyDataSetChanged();
  }

  public List<T> getItems() {
    return items;
  }

  public void setClickListener(OnItemClickListener l) {
    listener = l;
  }

  protected OnItemClickListener getListener() {
    return listener;
  }

  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    TextView view = (TextView) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_college, parent, false);
    return new ListItemTextHolder(view);
  }

  @Override public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, final int position);

  @Override public int getItemCount() {
    return items.size();
  }

  public static class ListItemTextHolder extends RecyclerView.ViewHolder {

    private View base;

    public ListItemTextHolder(View itemView) {
      super(itemView);
      base = itemView;
    }

    public TextView getTitle() {
      return (TextView) base;
    }
  }
}
