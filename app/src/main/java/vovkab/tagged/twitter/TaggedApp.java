package vovkab.tagged.twitter;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

public class TaggedApp extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject();
    }

    public void buildObjectGraphAndInject() {
        mObjectGraph = ObjectGraph.create(new TaggedModule(this));
        mObjectGraph.inject(this);
    }

    public void inject(Object o) {
        mObjectGraph.inject(o);
    }

    public static TaggedApp get(Context context) {
        return (TaggedApp) context.getApplicationContext();
    }

}
