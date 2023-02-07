package com.jeelp.platform.modules.bm02priv.viewobject;


import java.io.Serializable;

/**
 * @author Zheng Jie
 * @date 2018-12-20
 */
public class MenuMetaVo implements Serializable {

    private String title;

    private String icon;

    public MenuMetaVo(String title, String icon) {
        this.title=title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
