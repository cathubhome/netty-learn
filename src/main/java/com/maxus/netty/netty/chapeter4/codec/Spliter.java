package com.maxus.netty.netty.chapeter4.codec;

import com.maxus.netty.netty.chapeter4.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created with IDEA
 * Author:catHome
 * Description: 拆包器
 * Time:Create on 2018/10/15 16:00
 */
public class Spliter extends LengthFieldBasedFrameDecoder{

    /**
     * 长度域的偏移量（长度域相对整个数据包的偏移量，魔数+版本号+序列化算法+指令：4+1+1+1）
     */
    private static final int LENGTH_FIELD_OFFSET = 7;

    /**
     * 长度域的长度
     */
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter(){
        //参数Integer.MAX_VALUE是数据包的长度
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //判断当前发来的数据包是否满足自定义协议
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);

    }
}
