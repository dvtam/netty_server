/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tam.server_babicare;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

/**
 *
 * @author TAM
 */
class ServerHandler extends ChannelInboundMessageHandlerAdapter<String> {
public static final ChannelGroup channels=new DefaultChannelGroup();
    public ServerHandler() {
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming=ctx.channel();
        for (Channel channel : channels) {
            channel.write("SERVER: "+incoming.remoteAddress()+"has joined!\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming=ctx.channel();
        for (Channel channel : channels) {
            channel.write("SERVER: "+incoming.remoteAddress()+"has left!\n");
        }
        channels.remove(ctx.channel());
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel incoming=ctx.channel();
        for (Channel channel : channels) {
            if (channel!=incoming) {
                channel.write(""+incoming.remoteAddress()+": "+msg+"\n"); 
            }
        }
    }
    
}
