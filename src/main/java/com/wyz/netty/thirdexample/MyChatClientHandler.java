package com.wyz.netty.thirdexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 19:33
 * @Description: 自定义 客户端处理器
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

	}
}
