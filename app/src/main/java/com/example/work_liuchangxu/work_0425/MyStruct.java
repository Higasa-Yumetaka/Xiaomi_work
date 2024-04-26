package com.example.work_liuchangxu.work_0425;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MyStruct implements MultiItemEntity {

    public static int TYPE_TEXT = 0;
    public static int TYPE_IMAGE = 1;
    // 当前加载状态，默认为加载完成
    private static final int loadState = 2;
    // 正在加载
    public static final int TYPE_LOADING = -1;
    // 加载完成
    public static final int LOADING_COMPLETE = 2;
    // 加载到底
    public static final int LOADING_END = 3;
    public int Type;
    public Object object;
    public boolean stared;

    public MyStruct(int type, Object object) {
        Type = type;
        this.object = object;
    }

    public int getType() {
        return Type;
    }

    public Object getObject() {
        return object;
    }

    public void setType(int type) {
        Type = type;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isStared() {
        return stared;
    }

    public void setStared(boolean stared) {
        this.stared = stared;
    }

    @Override
    public int getItemType() {
        return Type;
    }
}
