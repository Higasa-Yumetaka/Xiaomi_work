# 0422 作业说明

## 作业内容
```
1. 搭建App首页，一个Activity 有多个fragment，点击底部fragment切换fragment，Fragment只显示一个文本即可
2. 使用ViewPager实现Fragment左右滑动
```

## 说明
```
上面的ViewPager实现了左右滑动切换内部的Fragment
底部的按钮其实也是Fragment加上背景，其中内嵌了一个match_parent的TextView，通过TextView的onClick()实现了切换Fragment
```