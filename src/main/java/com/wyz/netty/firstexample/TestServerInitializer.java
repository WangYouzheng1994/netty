package com.wyz.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 10:01
 * @Description:
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
	/**
	 * This method will be called once the {@link Channel} was registered. After the method returns this instance
	 * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
	 *
	 * @param ch the {@link Channel} which was registered.
	 * @throws Exception is thrown if an error occurs. In that case it will be handled by
	 *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
	 *                   the {@link Channel}.
	 */
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// channel 注册之后就会调用这个方法 （这是个回调方法）
		ChannelPipeline pipeline = ch.pipeline();
		// HttpRequestDecoder 和 HttpResponseEncoder 客户端到服务器，服务器到客户端的 编码解码。
		pipeline.addLast("httpServerCodec", new HttpServerCodec());
		// 自定义的处理器
		pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
	}
}
