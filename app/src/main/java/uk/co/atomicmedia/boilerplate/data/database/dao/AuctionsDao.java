package uk.co.atomicmedia.boilerplate.data.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import uk.co.atomicmedia.boilerplate.data.entities.Auction;

/**
 * Interface for database access for selectedAuction Lot related operations.
 */
@Dao
public interface AuctionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Auction> auctions);

    @Query("SELECT * FROM auction")
    LiveData<List<Auction>> getAuctions();

    @Query("SELECT * FROM auction")
    List<Auction> getAllAuctions();

    @Query("DELETE FROM auction")
    void deleteAll();

    @Delete
    void delete(Auction auction);
}
