/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tam.server_babicare;
import io.netty.channel.Channel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author TAM
 */
public class Client {
     public static void main(String[] args) throws Exception{
        new Client("192.168.1.183", 19191).run();
    }
    private  final String host;
    private  final int port;
   
    public Client(String host, int port) {
        this.host=host;
        this.port=port;
    }
    public void run()throws Exception{
        EventLoopGroup group= new NioEventLoopGroup();
        try {
            Bootstrap bootstrap=new Bootstrap()
                    .group(group).channel(NioSctpChannel.class)
                    .handler(new ClientInitializer());
            Channel channel=bootstrap.connect(host,port).sync().channel();
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
            while (true) {                
                channel.write(in.readLine()+"\r\n");
            }
        } catch (Exception e) {
        }
        finally{
            group.shutdownGracefully();
        }
    }
}
