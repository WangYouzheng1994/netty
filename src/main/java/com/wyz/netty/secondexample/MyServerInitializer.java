package com.wyz.netty.secondexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 15:28
 * @Description:
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
	/**
	 * 客户端和服务端连接后 就会创建channel  并且会调用这里。
	 */
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4)); // 解码
		pipeline.addLast(new LengthFieldPrepender(4)); // 编码器。
		pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8)); // 解码
		pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8)); // 编码
		pipeline.addLast(new MyServerHandler());
	}
}
