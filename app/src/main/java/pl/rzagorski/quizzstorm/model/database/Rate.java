package pl.rzagorski.quizzstorm.model.database;

import java.util.List;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "RATE".
 */
public class Rate {

    private Long id;
    private Long scoreFrom;
    private Long scoreTo;
    private String content;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient RateDao myDao;

    private List<QuizRates> RateRef;

    public Rate() {
    }

    public Rate(Long id) {
        this.id = id;
    }

    public Rate(Long id, Long scoreFrom, Long scoreTo, String content) {
        this.id = id;
        this.scoreFrom = scoreFrom;
        this.scoreTo = scoreTo;
        this.content = content;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRateDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScoreFrom() {
        return scoreFrom;
    }

    public void setScoreFrom(Long scoreFrom) {
        this.scoreFrom = scoreFrom;
    }

    public Long getScoreTo() {
        return scoreTo;
    }

    public void setScoreTo(Long scoreTo) {
        this.scoreTo = scoreTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<QuizRates> getRateRef() {
        if (RateRef == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            QuizRatesDao targetDao = daoSession.getQuizRatesDao();
            List<QuizRates> RateRefNew = targetDao._queryRate_RateRef(id);
            synchronized (this) {
                if(RateRef == null) {
                    RateRef = RateRefNew;
                }
            }
        }
        return RateRef;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetRateRef() {
        RateRef = null;
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
