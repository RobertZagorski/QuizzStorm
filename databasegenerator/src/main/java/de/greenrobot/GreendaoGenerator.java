package de.greenrobot;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * A database generator for the quiz application.
 *
 * @author Robert Zagórski
 */
public class GreendaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "pl.rzagorski.quizzstorm.model.database");

        Entity category = schema.addEntity("Category");
        category.addIdProperty();
        category.addLongProperty("uid");
        category.addStringProperty("name");
        category.addStringProperty("nameEng");
        category.addStringProperty("type");

        Entity photoEntity = schema.addEntity("Photo");
        photoEntity.addStringProperty("id").primaryKey();
        photoEntity.addStringProperty("author");
        photoEntity.addLongProperty("width");
        photoEntity.addLongProperty("height");
        photoEntity.addLongProperty("mediaId");
        photoEntity.addStringProperty("source");
        photoEntity.addStringProperty("title");
        photoEntity.addStringProperty("url");
        photoEntity.addStringProperty("localUrl");

        Entity quizRatesEntity = schema.addEntity("QuizRates");
        quizRatesEntity.addIdProperty();
        Property quizRatesQuizProperty = quizRatesEntity.addLongProperty("quiz").getProperty();
        Property quizRatesRateProperty = quizRatesEntity.addLongProperty("rate").getProperty();

        Entity ratesEntity = schema.addEntity("Rate");
        ratesEntity.addIdProperty();
        ratesEntity.addLongProperty("scoreFrom");
        ratesEntity.addLongProperty("scoreTo");
        ratesEntity.addStringProperty("content");

        ratesEntity.addToMany(quizRatesEntity, quizRatesRateProperty).setName("RateRef");

        Entity answerEntity = schema.addEntity("Answer");
        answerEntity.addIdProperty();
        answerEntity.addLongProperty("order");
        answerEntity.addStringProperty("text");
        answerEntity.addBooleanProperty("isCorrect");

        Entity questionEntity = schema.addEntity("Question");
        questionEntity.addIdProperty();
        Property questionPhotoProperty = questionEntity.addStringProperty("photo").getProperty();
        Property questionQuizId = questionEntity.addLongProperty("quiz").getProperty();
        questionEntity.addStringProperty("text");
        questionEntity.addStringProperty("answer");
        questionEntity.addStringProperty("type");
        questionEntity.addLongProperty("order");

        questionEntity.addToOne(photoEntity, questionPhotoProperty).setName("photoRef");

        Entity quizLatestResults = schema.addEntity("QuizResult");
        quizLatestResults.addIdProperty();
        Property quizLatestResultQuizId = quizLatestResults.addLongProperty("quiz").getProperty();
        quizLatestResults.addLongProperty("city");
        quizLatestResults.addDateProperty("endDate");
        quizLatestResults.addFloatProperty("result");
        quizLatestResults.addLongProperty("resolvedTime");


        Entity quizEntity = schema.addEntity("Quiz");
        quizEntity.addIdProperty();
        quizEntity.addStringProperty("buttonStart");
        quizEntity.addStringProperty("shareTitle");
        quizEntity.addLongProperty("questions");
        quizEntity.addDateProperty("createdAt");
        quizEntity.addBooleanProperty("sponsored");
        Property quizCategoryProperty = quizEntity.addLongProperty("Category").getProperty();
        quizEntity.addStringProperty("type");
        quizEntity.addStringProperty("title");
        quizEntity.addStringProperty("content");
        Property quizPhotoProperty = quizEntity.addStringProperty("photo").getProperty();
        quizEntity.addStringProperty("scripts");
        quizEntity.addBooleanProperty("isBattle");
        quizEntity.addLongProperty("created");
        quizEntity.addDoubleProperty("averageResult");
        quizEntity.addLongProperty("resultCount");
        quizEntity.addDoubleProperty("cityAverage");
        quizEntity.addDoubleProperty("cityTime");
        quizEntity.addDoubleProperty("cityCount");
        quizEntity.addBooleanProperty("userBattleDone");

        quizEntity.addToOne(category, quizCategoryProperty).setName("CategoryRef");
        quizEntity.addToOne(photoEntity, quizPhotoProperty).setName("PhotoRef");
        quizEntity.addToMany(quizRatesEntity, quizRatesQuizProperty).setName("RatesRef");
        quizEntity.addToMany(quizLatestResults, quizLatestResultQuizId).setName("LatestResultRef");
        quizEntity.addToMany(questionEntity, questionQuizId).setName("QuestionsRef");

        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema, "./app/src/main/java");
    }
}
