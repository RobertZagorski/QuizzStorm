package pl.rzagorski.quizzstorm.model.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import pl.rzagorski.quizzstorm.model.database.Photo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PHOTO".
*/
public class PhotoDao extends AbstractDao<Photo, String> {

    public static final String TABLENAME = "PHOTO";

    /**
     * Properties of entity Photo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property Author = new Property(1, String.class, "author", false, "AUTHOR");
        public final static Property Width = new Property(2, Long.class, "width", false, "WIDTH");
        public final static Property Height = new Property(3, Long.class, "height", false, "HEIGHT");
        public final static Property MediaId = new Property(4, String.class, "mediaId", false, "MEDIA_ID");
        public final static Property Source = new Property(5, String.class, "source", false, "SOURCE");
        public final static Property Title = new Property(6, String.class, "title", false, "TITLE");
        public final static Property Url = new Property(7, String.class, "url", false, "URL");
        public final static Property LocalUrl = new Property(8, String.class, "localUrl", false, "LOCAL_URL");
    };


    public PhotoDao(DaoConfig config) {
        super(config);
    }
    
    public PhotoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PHOTO\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"AUTHOR\" TEXT," + // 1: author
                "\"WIDTH\" INTEGER," + // 2: width
                "\"HEIGHT\" INTEGER," + // 3: height
                "\"MEDIA_ID\" TEXT," + // 4: mediaId
                "\"SOURCE\" TEXT," + // 5: source
                "\"TITLE\" TEXT," + // 6: title
                "\"URL\" TEXT," + // 7: url
                "\"LOCAL_URL\" TEXT);"); // 8: localUrl
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHOTO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Photo entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(2, author);
        }
 
        Long width = entity.getWidth();
        if (width != null) {
            stmt.bindLong(3, width);
        }
 
        Long height = entity.getHeight();
        if (height != null) {
            stmt.bindLong(4, height);
        }
 
        String mediaId = entity.getMediaId();
        if (mediaId != null) {
            stmt.bindString(5, mediaId);
        }
 
        String source = entity.getSource();
        if (source != null) {
            stmt.bindString(6, source);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(8, url);
        }
 
        String localUrl = entity.getLocalUrl();
        if (localUrl != null) {
            stmt.bindString(9, localUrl);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Photo readEntity(Cursor cursor, int offset) {
        Photo entity = new Photo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // author
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // width
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // height
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // mediaId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // source
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // title
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // url
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // localUrl
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Photo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setAuthor(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setWidth(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setHeight(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setMediaId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSource(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTitle(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUrl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLocalUrl(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(Photo entity, long rowId) {
        return entity.getId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(Photo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
