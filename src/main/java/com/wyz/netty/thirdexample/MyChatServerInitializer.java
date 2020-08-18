package com.wyz.netty.thirdexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 18:41
 * @Description:
 */
public class MyChatServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
		pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
		pipeline.addLast(new MyChatServerHandler());
	}
}
