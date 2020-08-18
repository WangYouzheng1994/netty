package com.wyz.netty.secondexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 15:24
 * @Description: Netty服务器端
 */
public class MyServer {
	public static void main(String[] args) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();
		// handler 对应了bossGroup childHandler 对应了 workerGroup
		serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new MyServerInitializer());

		try {
			ChannelFuture sync = serverBootstrap.bind(8899).sync();
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
