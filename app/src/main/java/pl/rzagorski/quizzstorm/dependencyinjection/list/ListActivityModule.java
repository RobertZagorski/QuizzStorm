package pl.rzagorski.quizzstorm.dependencyinjection.list;


import dagger.Module;
import dagger.Provides;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.ui.list.ListActivity;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@Module
public class ListActivityModule {
    private ListActivity listActivity;

    public ListActivityModule(ListActivity listActivity) {
        this.listActivity = listActivity;
    }

    @Provides
    @ActivityScope
    ListActivity provideListActivity() {
        return listActivity;
    }
}