package com.wyz.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 16:07
 * @Description: 客户端自定义 处理器
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress());
		System.out.println("client output: " + msg); // 服务器向客户端发送的数据
		ctx.writeAndFlush("from client" + LocalDateTime.now());
	}

	/**
	 * 连接后 的回调
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush("来自于客户端的问候~~~");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace(); // 打印
		ctx.close(); // 关闭连接
	}
}
