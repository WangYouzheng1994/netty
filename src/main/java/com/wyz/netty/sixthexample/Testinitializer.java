package com.wyz.netty.sixthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYouzheng
 * @version 1.0
 * @since: 2020/8/18 23:07
 * @Description:
 */
public class Testinitializer extends ChannelInitializer<SocketChannel> {
    /**
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // netty对于protobuf 提供了四个处理器。
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        // protobuf 解码成对象。 参数指的是要转换的类的实例
        pipeline.addLast(new ProtobufDecoder(MyDataInfo.Person.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());

        // 自定义处理器
        pipeline.addLast(new TestServerHandler());
    }
}