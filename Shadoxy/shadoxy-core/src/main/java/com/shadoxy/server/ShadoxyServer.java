package com.shadoxy.server;

import com.shadoxy.config.ShadoxyConfig;
import com.shadoxy.logging.ShadoxyLogger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;

public class ShadoxyServer {
    private static final ShadoxyLogger logger = ShadoxyLogger.getLogger(ShadoxyServer.class);

    private final ShadoxyConfig config;

    public ShadoxyServer(ShadoxyConfig config){
        this.config = config;
    }

    public void start() throws InterruptedException {
        /*int numberThread = config.getServerConfig().getThreads();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(numberThread);

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup).channel(NioSctpServerChannel.class).childHandler();

        bootstrap.bind(config.getServerConfig().getServerPort()).sync();*/
    }
}
