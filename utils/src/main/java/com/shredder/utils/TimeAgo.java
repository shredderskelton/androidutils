package com.shredder.utils;

import android.content.res.Resources;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class TimeAgo {

    private final Resources r;
    String prefixAgo;
    String suffixAgo;
    String prefixUntil;
    String suffixUntil;

    public TimeAgo(Resources r) {
        this.r = r;
        setStrings();
    }

    private void setStrings() {
        prefixAgo = r.getString(R.string.time_ago_prefix);
        suffixAgo = r.getString(R.string.time_ago_suffix);
        prefixUntil = r.getString(R.string.time_until_prefix);
        suffixUntil = r.getString(R.string.time_until_suffix);
    }

    public String timeAgo(long millis) {
        long diff = new Date().getTime() - millis;

        double seconds = Math.abs(diff) / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
        double years = days / 365;

        String words;

        if (seconds < 45) {
            words = r.getString(R.string.time_ago_seconds, Math.round(seconds));
        } else if (seconds < 90) {
            words = r.getString(R.string.time_ago_minute);
        } else if (minutes < 45) {
            words = r.getString(R.string.time_ago_minutes, Math.round(minutes));
        } else if (minutes < 90) {
            words = r.getString(R.string.time_ago_hour);
        } else if (hours < 24) {
            words = r.getString(R.string.time_ago_hours, Math.round(hours));
        } else if (hours < 42) {
            words = r.getString(R.string.time_ago_day);
        } else if (days < 30) {
            words = r.getString(R.string.time_ago_days, Math.round(days));
        } else if (days < 45) {
            words = r.getString(R.string.time_ago_month);
        } else if (days < 365) {
            words = r.getString(R.string.time_ago_months, Math.round(days / 30));
        } else if (years < 1.5) {
            words = r.getString(R.string.time_ago_year);
        } else {
            words = r.getString(R.string.time_ago_years, Math.round(years));
        }

        String prefix;
        String suffix;

        if (diff >= 0) {
            prefix = prefixAgo;
            suffix = suffixAgo;
        } else {
            prefix = prefixUntil;
            suffix = suffixUntil;
        }

        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isEmpty(prefix)) {
            sb.append(prefix).append(" ");
        }

        sb.append(words);

        if (!StringUtils.isEmpty(prefix)) {
            sb.append(" ").append(suffix);
        }
        return sb.toString().trim();
    }
}
