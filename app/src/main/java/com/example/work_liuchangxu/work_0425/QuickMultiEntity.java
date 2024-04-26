package com.example.work_liuchangxu.work_0425;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class QuickMultiEntity implements MultiItemEntity {
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;

    public int type;
    public Object data;
    public boolean star;

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public QuickMultiEntity(int type, Object data) {
        this.type = type;
        this.data = data;
        this.star = false;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
