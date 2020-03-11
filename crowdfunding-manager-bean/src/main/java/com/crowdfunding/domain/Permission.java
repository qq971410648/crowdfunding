package com.crowdfunding.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应的数据表 t_permission
 */
public class Permission {
    private Integer id;

    private Integer pid;

    private String name;

    //ztree 节点的图标
    private String icon;

    private String url;

    //ztree 节点是否默认展开        true：展开 false：不展开
    private boolean open ;

    //节点的父子关系
    private List<Permission> children = new ArrayList<Permission>();

    //节点的checkbox 多选框
    private boolean checked;

    //节点的层级
    private int level;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", open=" + open +
                ", children=" + children +
                ", checked=" + checked +
                ", level=" + level +
                '}';
    }

    public Permission() {
    }

    public Permission(Integer id, Integer pid, String name, String icon, String url, boolean open, List<Permission> children, boolean checked, int level) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.open = open;
        this.children = children;
        this.checked = checked;
        this.level = level;
    }
}