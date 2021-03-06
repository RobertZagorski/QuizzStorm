package pl.rzagorski.quizzstorm.model.database;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ANSWER".
 */
public class Answer {

    private Long id;
    private Long quiz;
    private Long order;
    private String text;
    private Boolean isCorrect;

    public Answer() {
    }

    public Answer(Long id) {
        this.id = id;
    }

    public Answer(Long id, Long quiz, Long order, String text, Boolean isCorrect) {
        this.id = id;
        this.quiz = quiz;
        this.order = order;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuiz() {
        return quiz;
    }

    public void setQuiz(Long quiz) {
        this.quiz = quiz;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

}
