# java-code-generate

#### Description
java代码生成器，一键生成增、删、改、查等api及相关页面，项目基于spingboot+mybatis+mysql+ftl，前端css基于pear admin layui

代码生成

1.按需修改类Application中execute方法相关生成代码配置，包括数据库以及包名等，没有指定代码生成位置时默认直接生成在当前项目
2.在数据库中创建好需要生成代码的表，表信息尽量保持完整，特别是注释
3.运行main方法（此时只需执行execute方法即可）


效果查看

1.修改application.properties中数据库配置
2.以springboot方式启动项目
3.浏览器访问，查看效果。如果类名称是Banner，访问路径是http://localhost/banner/list.html

额外提醒

默认模版在template/default目录，按需修改或调整

