package vovkab.tagged.twitter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vovkab.tagged.twitter.R;
import vovkab.tagged.twitter.TaggedApp;
import vovkab.tagged.twitter.adapter.TweetsAdapter;
import vovkab.tagged.twitter.api.TwitterClient;
import vovkab.tagged.twitter.api.model.Tweet;
import vovkab.tagged.twitter.api.response.TweetsResponse;
import vovkab.tagged.twitter.utils.Utils;

public class SearchFragment extends LoadingFragment {
    private static final String SAVED_DATA = "saved_data";
    @Inject TwitterClient mTwitter;

    private EditText mSearchView;
    private View mSearchButton;

    private ListView mListView;
    private TweetsAdapter mAdapter;
    private ArrayList<Tweet> mData = new ArrayList<Tweet>();

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaggedApp.get(getActivity()).inject(this);
        mAdapter = new TweetsAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSearchView = (EditText) view.findViewById(R.id.search_view);
        mSearchButton = view.findViewById(R.id.search);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Utils.hideKeyboard(v);
                mTwitter.search(mSearchView.getText().toString(), new Callback<TweetsResponse>() {
                    @Override
                    public void success(TweetsResponse tweetsResponse, Response response) {
                        mData.clear();
                        mData.addAll(tweetsResponse.tweets);
                        mAdapter.setData(mData);
                    }

                    @Override public void failure(RetrofitError error) {
                        showToast("Sorry, we can't load tweets.");
                    }
                });
            }
        });

        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            mData = savedInstanceState.getParcelableArrayList(SAVED_DATA);
            mAdapter.setData(mData);
        }
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(SAVED_DATA, mData);
    }
}
