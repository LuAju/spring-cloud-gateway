# spring-cloud-gateway
gateway组件的学习


https://blog.csdn.net/qq_41948178/article/details/109391999



添加了认证和鉴权的功能
认证功能即是简单地判断token有无，鉴权功能通过数据库查询获取具体的可访问权限跟当前的对比
为了提高效率，可以使用redis做存储