Douban API JAVA
=========

A Douban FM java API.
Douban并没有推出官方的FM API，这些实现都是通过抓包分析的。
基于前人的研究加上自己的一些探索使用JAVA进行实现。

Douban的接口是基于http和json的，我尝试在这个java实现API里面对json解析和网络通信进行封装。

主要分为三个模块：
1，用户登录
2，电台列表的获取
3，歌曲列表获取以及用户对歌曲的操作（加红心或者丢入垃圾桶）。

目前已经实现了1，2部分，3实现了部分功能。
开发完成之后再写写文档。

这是一些相关的分析：

https://github.com/zonyitoo/doubanfm-qt/wiki/%E8%B1%86%E7%93%A3FM-API
http://catx.me/2013/09/30/douban-fm-api/

使用过douban 推出的pc版本客户端都知道上面的电台很单一没有没动的，我分析了一下douban.fm其实是有
相关的接口提供电台的，我在这份API里面对这个接口进行了封装，可以获取到各种各样的电台。
这个接口是这样的：
地址：http://douban.fm/j/explore/genre
方法:Get
参数：
gid:类似于电台的分类id,这个可以在douban.fm首页源代码找到，目前我找到的有这些
335:摇滚
326:古典
327:爵士
337:民谣/乡村
331:流行
325:电子
328:原声配乐
332:轻音乐
334:说唱
330:雷鬼
329:拉丁
333:世界音乐
324:布鲁斯
336:放克/灵歌/R&amp;B

start:一般为0

limit一般为20
