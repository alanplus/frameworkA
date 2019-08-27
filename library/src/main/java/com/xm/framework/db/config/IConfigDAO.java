package com.xm.framework.db.config;

import com.xm.framework.db.base.IBaseDAO;

public interface IConfigDAO extends IBaseDAO<Config> {

    void remove(String key);

    Config getConfig(String key);

    /**
     * 参数为不需要清除的key
     * @param keys
     */
    void clear(String... keys);
}
