package com.jeelp.platform.modules.bm02priv.viewobject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVo {

    public MenuVo() {
    }

    public MenuVo(String name, String path, Boolean hidden, String redirect, String component, Boolean alwaysShow, MenuMetaVo meta, List<MenuVo> children) {
        this.name = name;
        this.path = path;
        this.hidden = hidden;
        this.redirect = redirect;
        this.component = component;
        this.alwaysShow = alwaysShow;
        this.meta = meta;
        this.children = children;
    }

    private String name;

    private String path;

    private Boolean hidden = false;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMetaVo meta;

    private List<MenuVo> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Boolean getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public MenuMetaVo getMeta() {
        return meta;
    }

    public void setMeta(MenuMetaVo meta) {
        this.meta = meta;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }
}
