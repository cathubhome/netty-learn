package com.maxus.netty.netty.chapter4.server;

import com.maxus.netty.netty.chapter4.protocol.response.MessageResponsePacket;
import com.maxus.netty.netty.chapter4.protocol.request.MessageRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created with IDEA
 * Author:catHome
 * Description:
 * Time:Create on 2018/10/15 13:05
 */
@Slf4j
@SuppressWarnings("all")
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        String message = messageRequestPacket.getMessage();
        log.debug("接收到客户端发来的消息：{} {}",message,new Date());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【"+message+"】");
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
