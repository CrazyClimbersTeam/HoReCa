package com.jeapie;

import com.jeapie.util.json.JSONArray;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class, builds Jeapie API events
 */
class EventBuilder {

    private EventBuilder() {
    }

    private static Long getUnixTime() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * Builds open api event
     *
     * @param date open date
     */
    /*public static Map<String, Object> buildOpenEvent(Date date) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("type", "open");
        res.put("time", getUnixTime());

        return res;
    }*/

    /**
     * Builds register GCM token api event
     *
     * @param token GCM token
     */
    public static Map<String, Object> buildTokenEvent(String token) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("token", token);
        res.put("time", getUnixTime());

        return res;
    }

    /**
     * Builds push api event
     *
     * @param mId  m_id field from push message
     * @param date push date
     */
    public static Map<String, Object> buildPushEvent(String mId, Date date) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("type", "push");
        res.put("m_id", mId);
        res.put("time", getUnixTime());

        return res;
    }

    /**
     * Builds close session api event
     *
     * @param duration session duration in seconds
     */
    public static Map<String, Object> buildSessionEvent(long duration) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("session", duration);
        res.put("open", getUnixTime() - duration);
        res.put("time", getUnixTime());

        return res;
    }

    /**
     * Builds tags added api event
     *
     * @param tags list of tags
     */
    public static Map<String, Object> buildTagsEvent(List<String> tags) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("tags", new JSONArray(tags));

        return res;
    }

    public static Map<String, Object> buildAddTagEvent(String tag) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("add_tag", tag);

        return res;
    }

    public static Map<String, Object> buildRemoveTagEvent(String tag) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("remove_tag", tag);

        return res;
    }

    public static Map<String, Object> buildRemoveTagsEvent() {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("remove_tags", "true");

        return res;
    }

    public static Map<String, Object> buildAliasEvent(String alias) {
        Map<String, Object> res = new HashMap<String, Object>();

        res.put("alias", alias);

        return res;
    }
}
