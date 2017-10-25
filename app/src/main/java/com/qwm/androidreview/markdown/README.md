# Markdown使用说明

Android平台下的原生Markdown解析器

地址：

[https://github.com/zzhoujay/Markdown](https://github.com/zzhoujay/Markdown)

----


# MarkDown

> Android平台的原生Markdown解析器，已整合进 [RichText](https://github.com/zzhoujay/RichText)

* 由markdown文本直接转换为Spanned，快捷高效
* 不依赖特定控件，低侵入性
* 遵循 Github Flavored Markdown 标准

### 效果展示

![效果图](image/img1.jpg)

### 使用

```java
Markdown.fromMarkdown(text,imageGetter,textView);
```
**注意：** 此方法需要在textView的Measure完成后调用，因为需要获取textView的宽高

例子：
```java
textView.post(new Runnable() {
     @Override
     public void run() {
     Spanned spanned = MarkDown.fromMarkdown(stream, new Html.ImageGetter() {
           @Override
           public Drawable getDrawable(String source) {
                 Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                 drawable.setBounds(0, 0, 400, 400);
                 return drawable;
           }
     }, textView);
     textView.setText(spanned);
}
```

### 自定义样式

在可以你的主题里加入 `markdownStyle` 来自定义样式，如果没有指定样式会使用自带的亮色主题。

例子：
```xml
<style name="你的主题">
    <!--<item name="markdownStyle">@style/Markdown</item>--> <!-- 暗色主题 -->
	<item name="markdownStyle">@style/Markdown.Light</item> <!-- 亮色主题 -->
</style>
```

### 在RichText中使用

[RichText](https://github.com/zzhoujay/RichText) 包含了一些对图片和其它东西的处理，使用更简单
```java
RichText.fromMarkdown(markdown).into(textView);
```

### Use in Gradle

`compile 'com.zzhoujay.markdown:markdown:1.0.5'`

