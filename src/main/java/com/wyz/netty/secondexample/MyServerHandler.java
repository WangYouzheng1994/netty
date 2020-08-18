package com.wyz.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 15:35
 * @Description: 自定义处理器 handler
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
	/**
	 * 服务器端接收到消息后的处理
	 *
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + ", " + msg);
		ctx.channel().writeAndFlush("from server: " + UUID.randomUUID());
	}

	/**
	 * 异常处理
	 *
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace(); // 打印
		ctx.close(); // 关闭连接
		// super.exceptionCaught(ctx, cause);
	}
}
