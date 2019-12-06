package com.zyct.ehome.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/6 15:55
 * Email 1945282561@qq.com
 */
public class RegionTree {
    private Long value;
    private String label;
    private List<RegionTree> children;

    public RegionTree() {
    }

    public RegionTree(Long value, String label) {
        this.value = value;
        this.label = label;
        this.children = new ArrayList<>();
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<RegionTree> getChildren() {
        return children;
    }

    public void setChildren(List<RegionTree> children) {
        this.children = children;
    }
}
