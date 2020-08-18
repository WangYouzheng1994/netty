package com.wyz.netty.thirdexample;

import com.wyz.netty.secondexample.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 19:31
 * @Description: 聊天室 客户端
 */
public class MyChatClient {
	public static void main(String[] args) {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());

			Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for (;;) {
				channel.writeAndFlush(br.readLine() + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
