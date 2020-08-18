package com.wyz.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 10:01
 * @Description:
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof HttpRequest) {
			HttpRequest httpRequest = (HttpRequest) msg;

			System.out.println("请求方法名" + httpRequest.method().name());

			URI uri = new URI(httpRequest.uri());
			if (StringUtils.equals("/favicon.ico", uri.getPath())) {
				System.out.println("请求favicon.ico");
				return;
			}
			System.out.println("执行channelRead0");

			ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
			DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

			ctx.writeAndFlush(response);
		}
	}

	/**
	 * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward
	 * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
	 * <p>
	 * Sub-classes may override this method to change behavior.
	 *
	 * @param ctx
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive");
		super.channelActive(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelRegistered");
		super.channelRegistered(ctx);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerAdded");
		super.handlerAdded(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive");
		super.channelInactive(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelUnregistered");
		super.channelUnregistered(ctx);
	}
}