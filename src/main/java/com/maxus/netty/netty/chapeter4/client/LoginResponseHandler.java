package com.maxus.netty.netty.chapeter4.client;

import com.maxus.netty.netty.chapeter4.protocol.request.LoginRequestPacket;
import com.maxus.netty.netty.chapeter4.protocol.response.LoginResponsePacket;
import com.maxus.netty.netty.chapeter4.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IDEA
 * Author:catHome
 * Description:
 * Time:Create on 2018/10/14 15:56
 */
@Slf4j
@SuppressWarnings("all")
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            log.debug("{}: 客户端登录成功", new Date());
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        } else {
            log.debug("{}: 客户端登录失败，原因：{}", new Date(), loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("{} : 客户端登录", new Date());

        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("cat");
        loginRequestPacket.setPassword("cathome");

        //写数据
        ctx.channel().writeAndFlush(loginRequestPacket);


    }




    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("{} oh my god，客户端连接关闭",new Date());
    }
}
