/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tam.server_babicare;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundByteHandlerAdapter;
import io.netty.channel.ChannelInboundMessageHandler;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

/**
 *
 * @author TAM
 */
class ClientHandler extends ChannelInboundMessageHandlerAdapter<String> {

    @Override
    public void messageReceived(ChannelHandlerContext arg0, String arg1) throws Exception {
        System.out.println(arg1);
    }
}
