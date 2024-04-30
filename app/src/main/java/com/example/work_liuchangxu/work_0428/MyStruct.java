package com.example.work_liuchangxu.work_0428;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MyStruct implements MultiItemEntity {

    // 游戏
    public static int TYPE_GAME = 0;
    // 正在加载
    public static final int TYPE_LOADING = -1;


    // 当前加载状态，默认为加载完成
    private static final int loadState = -2;
    // 加载完成
    public static final int LOADING_COMPLETE = -2;
    // 加载到底
    public static final int LOADING_END = 3;


    public int Type;

    private final int id;
    private final String gameName;

    private final String packageName;

    private final Long appId;

    private final String icon;

    private final String introduction;

    private final String brief;

    private final String versionName;

    private final String apkUrl;

    private final String tags;

    private final String score;

    private final String playNumFormat;

    public MyStruct(int type, int id, String gameName, String packageName, Long appId, String icon, String introduction, String brief, String versionName, String apkUrl, String tags, String score, String playNumFormat, String createTime) {
        Type = type;
        this.id = id;
        this.gameName = gameName;
        this.packageName = packageName;
        this.appId = appId;
        this.icon = icon;
        this.introduction = introduction;
        this.brief = brief;
        this.versionName = versionName;
        this.apkUrl = apkUrl;
        this.tags = tags;
        this.score = score;
        this.playNumFormat = playNumFormat;
        this.createTime = createTime;
    }

    public String getGamePackageName() {
        return packageName;
    }

    public Long getGameAppId() {
        return appId;
    }

    public String getGameIcon() {
        return icon;
    }

    public String getGameIntroduction() {
        return introduction;
    }

    public String getGameBrief() {
        return " | " + brief;
    }

    public String getGameGameVersionName() {
        return versionName;
    }

    public String getGameApkUrl() {
        return apkUrl;
    }

    public String getGameTags() {
        return " " + tags + " ";
    }

    public String getGameScore() {
        return "⭐" + score;
    }

    public String getGamePlayNumFormat() {
        return playNumFormat;
    }

    public String getGameCreateTime() {
        return createTime;
    }

    private final String createTime;

    public int getGameType() {
        return Type;
    }

    public String getGameName() {
        return gameName;
    }

    public int getGameId() {
        return id;
    }

    @Override
    public int getItemType() {
        return Type;
    }
}
