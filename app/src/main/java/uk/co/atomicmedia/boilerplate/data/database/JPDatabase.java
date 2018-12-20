package uk.co.atomicmedia.boilerplate.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import uk.co.atomicmedia.boilerplate.data.database.dao.AuctionsDao;
import uk.co.atomicmedia.boilerplate.data.entities.Auction;

/**
 * Singleton SQLite database
 */
@Database(entities = {Auction.class}, version = 1, exportSchema = false)
public abstract class JPDatabase extends RoomDatabase {

    private static JPDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "app_database.db";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    abstract public AuctionsDao auctionsDao();

    public void runTransactionInNewThread(Runnable runnable){
        AsyncTask.execute(() -> runInTransaction(runnable));
    }

    public static JPDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (JPDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static JPDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, JPDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        JPDatabase database = JPDatabase.getInstance(appContext);
                        database.setDatabaseCreated();
                    }
                })
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }


    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
