package vovkab.tagged.twitter;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vovkab.tagged.twitter.api.ApiModule;
import vovkab.tagged.twitter.fragment.AuthFragment;
import vovkab.tagged.twitter.fragment.SearchFragment;
import vovkab.tagged.twitter.preference.PreferenceModule;

@Module(
    includes = {
        ApiModule.class,
        PreferenceModule.class
    },
    injects = {
        TaggedApp.class,
        MainActivity.class,

        AuthFragment.class,
        SearchFragment.class
    }
)
public final class TaggedModule {
    private final TaggedApp mApp;

    public TaggedModule(TaggedApp app) {
        mApp = app;
    }

    @Provides @Singleton Application provideApplication() {
        return mApp;
    }
}
