package pl.rzagorski.quizzstorm.model.api;

import pl.rzagorski.quizzstorm.model.database.Rate;
import pl.rzagorski.quizzstorm.utils.interfaces.LayerTransformer;

/**
 * Created by Robert Zagórski on 27.07.2016.
 */
public class ApiRate implements LayerTransformer<Rate> {
    private Long from;
    private Long to;
    private String content;

    @Override
    public Rate transform() {
        Rate rate = new Rate();
        rate.setScoreFrom(from);
        rate.setScoreTo(to);
        rate.setContent(content);
        return rate;
    }
}
