package com.xm.framework.db;


import com.xm.framework.db.config.Config;
import com.xm.framework.db.config.ConfigDAOImpl;
import com.xm.framework.db.config.IConfigDAO;
import com.xm.framework.tools.StringTools;


/**
 * Automatically the code generate by Alan
 * Date:2019-08-27
 */
public class ConfigManager {


    private IConfigDAO sp;

    private static ConfigManager instance;

    private ConfigManager() {
        sp = new ConfigDAOImpl();
    }

    public synchronized static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getString(String key, String defValue) {
        Config config = sp.getConfig(key);
        return config == null ? defValue : config.value;
    }


    public int getInt(String key, int defValue) {
        Config config = sp.getConfig(key);
        return config == null ? defValue : StringTools.getInt(config.value, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        Config config = sp.getConfig(key);
        return null == config ? defValue : StringTools.getBoolean(config.value, defValue);
    }

    public long getLong(String key, long defValue) {
        Config config = sp.getConfig(key);
        return null == config ? defValue : StringTools.getLong(config.value, defValue);
    }

    public void setValue(String key, String val) {
        sp.replace(new Config(key, val));
    }

    public void setValue(String key, int val) {
        sp.replace(new Config(key, String.valueOf(val)));
    }

    public void setValue(String key, boolean val) {
        sp.replace(new Config(key, String.valueOf(val)));
    }

    public void setValue(String key, long val) {
        sp.replace(new Config(key, String.valueOf(val)));
    }


    public void remove(String key) {
        sp.remove(key);
    }

    public boolean contains(String key) {
        return sp.getConfig(key) != null;
    }

    public void clear(String... noNeedClearKeys) {
        sp.clear(noNeedClearKeys);
    }
}

