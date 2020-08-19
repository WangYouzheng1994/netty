package com.wyz.netty.fourthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/19 10:54
 * @Description:
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new ChunkedWriteHandler()); // 块，写
		pipeline.addLast(new HttpObjectAggregator(123));
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws")); // 指定了contenxtPath

		// 自定义处理器
		pipeline.addLast(new TextWebSocketFrameHandler());
	}
}