package com.sva.launcher;

import android.content.SharedPreferences;

/** Validated user-facing intervals for foreground content schedulers. */
final class SchedulePreferences {
    static final long MANUAL = 0L;
    static final long EVERY_LAUNCH = -1L;

    static final String SLIDESHOW = "scheduleSlideshowMs";
    static final String PLAYLIST = "schedulePlaylistMs";
    static final String GUIDE = "scheduleGuideMs";
    static final String CALENDAR = "scheduleCalendarMs";
    static final String GOOGLE_PHOTOS = "scheduleGooglePhotosMs";
    static final String WEATHER = "scheduleWeatherMs";

    static final long SECOND = 1000L;
    static final long MINUTE = 60L * SECOND;
    static final long HOUR = 60L * MINUTE;
    static final long DAY = 24L * HOUR;

    static final long[] SLIDESHOW_VALUES = {MANUAL, 5 * SECOND, 10 * SECOND, 30 * SECOND, MINUTE};
    static final long[] PLAYLIST_VALUES = {MANUAL, 6 * HOUR, 12 * HOUR, DAY, 3 * DAY, 7 * DAY};
    static final long[] GUIDE_VALUES = {MANUAL, HOUR, 3 * HOUR, 6 * HOUR, 12 * HOUR, DAY};
    static final long[] CALENDAR_VALUES = {MANUAL, HOUR, 3 * HOUR, 6 * HOUR, 12 * HOUR, DAY};
    static final long[] GOOGLE_PHOTOS_VALUES = {MANUAL, EVERY_LAUNCH, 6 * HOUR, 12 * HOUR, DAY};
    static final long[] WEATHER_VALUES = {MANUAL, EVERY_LAUNCH, 30 * MINUTE, HOUR, 3 * HOUR, 6 * HOUR};

    static final long DEFAULT_SLIDESHOW = 5 * SECOND;
    static final long DEFAULT_PLAYLIST = DAY;
    static final long DEFAULT_GUIDE = DAY;
    static final long DEFAULT_CALENDAR = 6 * HOUR;
    static final long DEFAULT_GOOGLE_PHOTOS = EVERY_LAUNCH;
    static final long DEFAULT_WEATHER = EVERY_LAUNCH;

    private SchedulePreferences() { }

    static long get(SharedPreferences preferences, String key, long[] values, long fallback) {
        return sanitize(preferences.getLong(key, fallback), values, fallback);
    }

    static long sanitize(long value, long[] values, long fallback) {
        for (long allowed : values) if (allowed == value) return value;
        return fallback;
    }

    static long next(long current, long[] values, long fallback) {
        long valid = sanitize(current, values, fallback);
        for (int i = 0; i < values.length; i++) {
            if (values[i] == valid) return values[(i + 1) % values.length];
        }
        return fallback;
    }

    static String label(long value, boolean slideshow, boolean supportsEveryLaunch) {
        if (value == MANUAL) return Lang.t(slideshow ? "Paused" : "Manual only").toString();
        if (supportsEveryLaunch && value == EVERY_LAUNCH) return Lang.t("Every launch").toString();
        // 英文有单复数，中文没有 —— 分开写，别指望 SUFFIX 规则去拼（" day" 会误伤 Today / All day）
        if (Lang.isChinese()) {
            if (value < MINUTE) return value / SECOND + " 秒";
            if (value < HOUR) return value / MINUTE + " 分钟";
            if (value < DAY) return value / HOUR + " 小时";
            return value / DAY + " 天";
        }
        if (value < MINUTE) return value / SECOND + " seconds";
        if (value < HOUR) return value / MINUTE + (value == MINUTE ? " minute" : " minutes");
        if (value < DAY) return value / HOUR + (value == HOUR ? " hour" : " hours");
        return value / DAY + (value == DAY ? " day" : " days");
    }
}
