# rabbitMQ封装
RabbitMQ是一个开源的消息代理何队列服务器，用来通过普通协议在完全不同得我应用之间共享数据，RabbitMQ是使用Erlang语言编写的，是基于AMQP(Advanced Message Queuing Protocol,高级消息队列协议)协议的。
该项目主要对基础组件RabbitMQ进行封装，主要有以下实现关键点：
1、迅速消息的发送，确认消息的发送，延迟消息的发送。
2、消息的异步化、序列化。
3、连接池化，高性能的实现。
4、完备的补偿机制。
