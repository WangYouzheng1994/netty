package com.wyz.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/25 21:10
 * @Description:
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
	/**
	 * <strong>Please keep in mind that this method will be renamed to
	 * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
	 * <p>
	 * Is called for each message of type {@link I}.
	 *
	 * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
	 *            belongs to
	 * @param msg the message to handle
	 * @throws Exception is thrown if an error occurred
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
		// netty 已经帮我们把对象转换出来了~
		System.out.println(msg.getName());
		System.out.println(msg.getAge());
		System.out.println(msg.getAddress());
	}
}
