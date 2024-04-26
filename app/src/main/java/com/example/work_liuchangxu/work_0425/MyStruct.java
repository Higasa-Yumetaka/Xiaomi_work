package com.example.work_liuchangxu.work_0425;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MyStruct implements MultiItemEntity {

    public static int TYPE_TEXT = 0;
    public static int TYPE_IMAGE = 1;

    public int Type;
    public Object object;

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

    @Override
    public int getItemType() {
        return Type;
    }
}
