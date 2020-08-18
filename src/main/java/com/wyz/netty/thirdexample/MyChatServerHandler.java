package com.wyz.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 18:46
 * @Description:
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
	/**
	 * 保存所有连接
 	 */
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		Channel channel = ctx.channel();

		channelGroup.forEach(ch -> {
			// 剔除发消息的 channel
			if (channel != ch) {
				// 就应该接收到发的消息。
				ch.writeAndFlush(channel.remoteAddress() + " 发送的消息：" + msg + "\n");
			} else {
				ch.writeAndFlush("【自己】 " + msg + "\n");
			}
		});
	}

	/**
	 * 连接建立 -- 服务器发出广播 通知所有人有人加入了。
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + " 加入\n");
		channelGroup.add(channel);
	}

	/**
	 * 连接断开 -- 服务器发出广播 通知所有人有人退出了。
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + " 离开\n");
		// 注意：channel 如果连接的时候 那么我们在上面显式的把 channel放到了集合中。
		// 如果这个channel 断开了。 实际上是否需要显式把channel移出不是必要的，因为 netty 会自动的把channel 移出 ChannelGroup
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress() + " 上线");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress() + " 下线");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
