package pl.rzagorski.quizzstorm.model.database;

import java.util.List;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "QUIZ".
 */
public class Quiz {

    private Long id;
    private String buttonStart;
    private String shareTitle;
    private Long questions;
    private java.util.Date createdAt;
    private Boolean sponsored;
    private Long Category;
    private String type;
    private String title;
    private String content;
    private String photo;
    private String scripts;
    private Boolean isBattle;
    private Long created;
    private Double averageResult;
    private Long resultCount;
    private Double cityAverage;
    private Double cityTime;
    private Double cityCount;
    private Boolean userBattleDone;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient QuizDao myDao;

    private Category CategoryRef;
    private Long CategoryRef__resolvedKey;

    private Photo PhotoRef;
    private String PhotoRef__resolvedKey;

    private List<QuizRates> RatesRef;
    private List<QuizResult> LatestResultRef;
    private List<Question> QuestionsRef;

    public Quiz() {
    }

    public Quiz(Long id) {
        this.id = id;
    }

    public Quiz(Long id, String buttonStart, String shareTitle, Long questions, java.util.Date createdAt, Boolean sponsored, Long Category, String type, String title, String content, String photo, String scripts, Boolean isBattle, Long created, Double averageResult, Long resultCount, Double cityAverage, Double cityTime, Double cityCount, Boolean userBattleDone) {
        this.id = id;
        this.buttonStart = buttonStart;
        this.shareTitle = shareTitle;
        this.questions = questions;
        this.createdAt = createdAt;
        this.sponsored = sponsored;
        this.Category = Category;
        this.type = type;
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.scripts = scripts;
        this.isBattle = isBattle;
        this.created = created;
        this.averageResult = averageResult;
        this.resultCount = resultCount;
        this.cityAverage = cityAverage;
        this.cityTime = cityTime;
        this.cityCount = cityCount;
        this.userBattleDone = userBattleDone;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getQuizDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getButtonStart() {
        return buttonStart;
    }

    public void setButtonStart(String buttonStart) {
        this.buttonStart = buttonStart;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public Long getQuestions() {
        return questions;
    }

    public void setQuestions(Long questions) {
        this.questions = questions;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getSponsored() {
        return sponsored;
    }

    public void setSponsored(Boolean sponsored) {
        this.sponsored = sponsored;
    }

    public Long getCategory() {
        return Category;
    }

    public void setCategory(Long Category) {
        this.Category = Category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getScripts() {
        return scripts;
    }

    public void setScripts(String scripts) {
        this.scripts = scripts;
    }

    public Boolean getIsBattle() {
        return isBattle;
    }

    public void setIsBattle(Boolean isBattle) {
        this.isBattle = isBattle;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Double getAverageResult() {
        return averageResult;
    }

    public void setAverageResult(Double averageResult) {
        this.averageResult = averageResult;
    }

    public Long getResultCount() {
        return resultCount;
    }

    public void setResultCount(Long resultCount) {
        this.resultCount = resultCount;
    }

    public Double getCityAverage() {
        return cityAverage;
    }

    public void setCityAverage(Double cityAverage) {
        this.cityAverage = cityAverage;
    }

    public Double getCityTime() {
        return cityTime;
    }

    public void setCityTime(Double cityTime) {
        this.cityTime = cityTime;
    }

    public Double getCityCount() {
        return cityCount;
    }

    public void setCityCount(Double cityCount) {
        this.cityCount = cityCount;
    }

    public Boolean getUserBattleDone() {
        return userBattleDone;
    }

    public void setUserBattleDone(Boolean userBattleDone) {
        this.userBattleDone = userBattleDone;
    }

    /** To-one relationship, resolved on first access. */
    public Category getCategoryRef() {
        Long __key = this.Category;
        if (CategoryRef__resolvedKey == null || !CategoryRef__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoryDao targetDao = daoSession.getCategoryDao();
            Category CategoryRefNew = targetDao.load(__key);
            synchronized (this) {
                CategoryRef = CategoryRefNew;
            	CategoryRef__resolvedKey = __key;
            }
        }
        return CategoryRef;
    }

    public void setCategoryRef(Category CategoryRef) {
        synchronized (this) {
            this.CategoryRef = CategoryRef;
            Category = CategoryRef == null ? null : CategoryRef.getId();
            CategoryRef__resolvedKey = Category;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Photo getPhotoRef() {
        String __key = this.photo;
        if (PhotoRef__resolvedKey == null || PhotoRef__resolvedKey != __key) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PhotoDao targetDao = daoSession.getPhotoDao();
            Photo PhotoRefNew = targetDao.load(__key);
            synchronized (this) {
                PhotoRef = PhotoRefNew;
            	PhotoRef__resolvedKey = __key;
            }
        }
        return PhotoRef;
    }

    public void setPhotoRef(Photo PhotoRef) {
        synchronized (this) {
            this.PhotoRef = PhotoRef;
            photo = PhotoRef == null ? null : PhotoRef.getId();
            PhotoRef__resolvedKey = photo;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<QuizRates> getRatesRef() {
        if (RatesRef == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            QuizRatesDao targetDao = daoSession.getQuizRatesDao();
            List<QuizRates> RatesRefNew = targetDao._queryQuiz_RatesRef(id);
            synchronized (this) {
                if(RatesRef == null) {
                    RatesRef = RatesRefNew;
                }
            }
        }
        return RatesRef;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetRatesRef() {
        RatesRef = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<QuizResult> getLatestResultRef() {
        if (LatestResultRef == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            QuizResultDao targetDao = daoSession.getQuizResultDao();
            List<QuizResult> LatestResultRefNew = targetDao._queryQuiz_LatestResultRef(id);
            synchronized (this) {
                if(LatestResultRef == null) {
                    LatestResultRef = LatestResultRefNew;
                }
            }
        }
        return LatestResultRef;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetLatestResultRef() {
        LatestResultRef = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Question> getQuestionsRef() {
        if (QuestionsRef == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            QuestionDao targetDao = daoSession.getQuestionDao();
            List<Question> QuestionsRefNew = targetDao._queryQuiz_QuestionsRef(id);
            synchronized (this) {
                if(QuestionsRef == null) {
                    QuestionsRef = QuestionsRefNew;
                }
            }
        }
        return QuestionsRef;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetQuestionsRef() {
        QuestionsRef = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
