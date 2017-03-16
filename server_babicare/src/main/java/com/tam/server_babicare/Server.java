/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tam.server_babicare;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * @author TAM
 */
public class Server {
    public static void main(String[] args) throws InterruptedException{
        new Server(19191).run();
        System.out.println("Server runing . . .\nPort: 19191");
    }
    private final int port;

    public Server(int port) {
        this.port = port;
    }
    public void run()throws InterruptedException{
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap=new ServerBootstrap()
                    .group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());
            bootstrap.bind(port).sync().channel().closeFuture().sync();
            
        } catch (Exception e) {
        }
        finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
