package com.wyz.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/18 10:01
 * @Description:
 */
public class TestServer {
	public static void main(String[] args) {
		// 定义什么什么组- -
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer()); // 指定处理器

		try {
			ChannelFuture sync = serverBootstrap.bind(8899).sync();// 指定端口
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 优雅关闭
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
