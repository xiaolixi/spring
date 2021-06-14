package com.example.sshcache.util;

import ch.ethz.ssh2.Connection;
import com.google.common.base.Joiner;
import com.google.common.cache.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SshConnectionUtil {

    private static LoadingCache<String,Connection> sshLoadingCache = null;
    static {
        sshLoadingCache = CacheBuilder.newBuilder()
                .expireAfterAccess(5,TimeUnit.MINUTES)
                .maximumSize(1000)
                .removalListener(new RemovalListener<String, Connection>(){
                    @Override
                    public void onRemoval(RemovalNotification<String, Connection> removalNotification) {
                        if(!Objects.isNull(removalNotification.getValue())){
                            removalNotification.getValue().close();
                            removalNotification.setValue(null);
                        }
                        String key = removalNotification.getKey();
                        System.out.println("ip：" + key.substring(0,key.indexOf(',')) + " removed");

                    }
                } )
                .build(new CacheLoader<String, Connection>() {
                    @Override
                    public Connection load(String key) {
                        //从SQL或者NoSql 获取对象
                        //Iterable<String> split = Splitter.on(',').split(key);
                        String[] split = key.split(",");
                        String username = split.length == 3 ? split[2] : "root";
                        String password = split[1];
                        String ip = split[0];


                        Connection connection = new Connection(ip, 22);
                        boolean b = false;
                        try {
                            connection.addConnectionMonitor(v -> {
                                System.out.println(v);
                            });
                            b = connection.authenticateWithPassword(username, password);
                            if(b) {
                                return connection;
                            }else {
                                return null;
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            if(!b){
                                connection.close();
                            }
                        }
                        return null;
                    }
        });
    }



    public static Connection getConnection(String ip,String password,String username){
        String join = Joiner.on(',').skipNulls().join(ip, password, username);
        try {
            Connection connection = sshLoadingCache.get(join);
            return connection;
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        Connection root = SshConnectionUtil.getConnection("192.168.11.110", "123456", "root");
        System.out.println(root);
    }

}
