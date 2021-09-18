package com.yevheniimakar.date.object;

public class DateObject {

    private long dateInMilliseconds;

    public DateObject() {}

    public DateObject(final long dateInMilliseconds) {
        this.dateInMilliseconds = dateInMilliseconds;
    }

    public long getDateInMilliseconds() {
        return this.dateInMilliseconds;
    }

    public void setDateInMilliseconds(final long dateInMilliseconds) {
        this.dateInMilliseconds = dateInMilliseconds;
    }

}
