package vovkab.tagged.twitter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import me.tatarka.rxloader.RxLoader1;
import me.tatarka.rxloader.RxLoaderManager;
import me.tatarka.rxloader.RxLoaderManagerCompat;
import me.tatarka.rxloader.RxLoaderObserver;
import me.tatarka.rxloader.SaveCallback;
import rx.Observable;
import rx.functions.Func1;
import vovkab.tagged.twitter.R;
import vovkab.tagged.twitter.TaggedApp;
import vovkab.tagged.twitter.adapter.TweetsAdapter;
import vovkab.tagged.twitter.api.TwitterClient;
import vovkab.tagged.twitter.api.model.Tweet;
import vovkab.tagged.twitter.api.response.TweetsResponse;
import vovkab.tagged.twitter.utils.Utils;
import vovkab.tagged.twitter.utils.ViewUtils;

public class SearchFragment extends LoadingFragment {
    private static final String SAVED_DATA = "saved_data";
    @Inject TwitterClient mTwitter;

    private EditText mSearchView;
    private View mSearchButton;

    private ListView mListView;
    private TextView mEmptyView;
    private TweetsAdapter mAdapter;
    private RxLoader1<String, ArrayList<Tweet>> mSearchLoader;

    @Override public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaggedApp.get(getActivity()).inject(this);
        mAdapter = new TweetsAdapter();
    }

    private Func1<String, Observable<ArrayList<Tweet>>> input = new Func1<String, Observable<ArrayList<Tweet>>>() {
        @Override public Observable<ArrayList<Tweet>> call(String s) {
            return mTwitter.search(s).flatMap(new Func1<TweetsResponse, Observable<ArrayList<Tweet>>>() {
                @Override public Observable<ArrayList<Tweet>> call(TweetsResponse tweetsResponse) {
                    return Observable.just(tweetsResponse.tweets);
                }
            });
        }
    };

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
                v.clearFocus();
                Utils.hideKeyboard(v);
                mSearchLoader.restart(ViewUtils.getString(mSearchView));
            }
        });

        mEmptyView = (TextView) view.findViewById(R.id.empty_view);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setEmptyView(mEmptyView);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RxLoaderManager rxLoaderManager = RxLoaderManagerCompat.get(getActivity());
        mSearchLoader = rxLoaderManager.create(input, new RxLoaderObserver<ArrayList<Tweet>>() {
            @Override public void onStarted() {
                mSearchButton.setEnabled(false);
            }

            @Override public void onNext(ArrayList<Tweet> value) {
                mAdapter.setData(value);
                mSearchButton.setEnabled(true);
                if (value.size() == 0) {
                    mEmptyView.setText(R.string.search_no_tweets_found);
                }
            }

            @Override public void onError(Throwable e) {
                showToast(R.string.search_cant_load_tweets);
                mSearchButton.setEnabled(true);
            }
        }).save(new SaveCallback<ArrayList<Tweet>>() {
            @Override public void onSave(String key, ArrayList<Tweet> value, Bundle outState) {
                outState.putParcelableArrayList(key + SAVED_DATA, value);
            }

            @Override public ArrayList<Tweet> onRestore(String key, Bundle savedState) {
                return savedState.getParcelableArrayList(key + SAVED_DATA);
            }
        });
    }

}
