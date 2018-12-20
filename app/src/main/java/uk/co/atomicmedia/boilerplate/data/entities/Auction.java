
package uk.co.atomicmedia.boilerplate.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import uk.co.atomicmedia.boilerplate.util.DateUtils;

@Entity(tableName = "auction")
public class Auction {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @ColumnInfo(name = "start_date_time")
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;

    @ColumnInfo(name = "end_date_time")
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @NonNull
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("startDateTime", startDateTime)
                .append("endDateTime", endDateTime)
                .toString();
    }


    public String getStartDay(){
        return DateUtils.parseAndFormatDate(startDateTime, DateUtils.FORMAT_AUCTION_LIST_DATE);
    }
    public String getEndDay(){
        return DateUtils.parseAndFormatDate(endDateTime, DateUtils.FORMAT_AUCTION_LIST_DATE);
    }
    public String getStartTime(){
        return DateUtils.parseAndFormatDate(startDateTime, DateUtils.FORMAT_AUCTION_LIST_TIME);
    }
    public String getEndTime(){
        return DateUtils.parseAndFormatDate(endDateTime, DateUtils.FORMAT_AUCTION_LIST_TIME);
    }

    public boolean getIsEndingToday(){
        return DateUtils.isSameDay(endDateTime, DateUtils.getTodayInApiFormat());
    }

    public boolean getIsMultiDay(){
        return !DateUtils.isSameDay(startDateTime, endDateTime);
    }
}
