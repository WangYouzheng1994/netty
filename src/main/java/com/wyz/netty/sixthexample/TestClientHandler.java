package com.wyz.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/25 21:19
 * @Description:
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
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

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("张三 ").setAge(20).setAddress("北京").build();

		// 客户端发送消息到服务端。
		ctx.channel().writeAndFlush(person);
	}
}
