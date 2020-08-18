package com.wyz.netty.fourthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYouzheng
 * @version 1.0
 * @since: 2020/8/18 23:07
 * @Description:
 */
public class MyServerinitializer extends ChannelInitializer<SocketChannel> {
    /**
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 空闲状态检测处理器， 读空闲5秒， 写空闲 7 秒， 读写空闲 10秒。
        pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandler());
    }
}