package vovkab.tagged.twitter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class AdapterBase<T, V extends View> extends BaseAdapter {
    private List<T> mItems = new ArrayList<T>();

    @Override public int getCount() {
        return mItems.size();
    }

    @Override public T getItem(int position) {
        return mItems.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        V view;
        if (convertView == null) {
            view = createView(LayoutInflater.from(parent.getContext()), parent);
        } else {
            view = (V) convertView;
        }

        bindView(position, view, getItem(position));
        return view;
    }

    public abstract V createView(LayoutInflater inflater, ViewGroup parent);

    public abstract void bindView(int position, V view, T data);

    public void setData(List<T> items) {
        mItems.clear();
        if (items != null) {
            mItems.addAll(items);
        }
        notifyDataSetInvalidated();
    }
}
