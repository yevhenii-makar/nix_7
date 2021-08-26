package com.yevheniimakar.date.object;

public class CustomDateObject {

    private long dateInMilliseconds;

    public CustomDateObject() {}

    public CustomDateObject(final long dateInMilliseconds) {
        this.dateInMilliseconds = dateInMilliseconds;
    }

    public long getDateInMilliseconds() {
        return this.dateInMilliseconds;
    }

    public void setDateInMilliseconds(final long dateInMilliseconds) {
        this.dateInMilliseconds = dateInMilliseconds;
    }

}
