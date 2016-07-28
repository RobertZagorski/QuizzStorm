package pl.rzagorski.quizzstorm.ui.base;

/**
 * Every presenter in the app must either implement this interface or extend BaseController
 * indicating the MvcView type that wants to be attached with.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvcView);

    void detachView();
}
