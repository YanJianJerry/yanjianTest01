package com.tools.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RedisTest {
    public static void main(String[] args) {
        // 创建Jedis对象，参数为Redis服务器地址和端口
         Jedis jedis = null;
        try  {
            jedis = new Jedis("192.168.56.101", 6379);
            // 认证（如果Redis设置了密码）

             // 设置连接库
            jedis.select(3);

            // 测试连接
            System.out.println("连接成功: " + jedis.ping());

//            String key = "wms.a30:multicheck:flux.wms_test_01.so003426:map";
            String key = "WRF6.a30:multicheck:flux.wms_test_01.so003426:map";

            //获取键值
//            String value = getStringNew(jedis,key);
//            System.out.println(value);

//            JSONObject json = new JSONObject(getMapNew(jedis,key));
//            System.out.println(json.toJSONString());

            Map<String ,Object> map = getMapNew(jedis,key);
//            ((Map<String ,String>)map.get("WMS_TEST_001_DSHYMBCS001")).put("checkqty","1.0");
            JSONObject json = new JSONObject(map);
            System.out.println(json.toJSONString());

            //设置键值
//            Map<String,Object> qtyMap = new HashMap<>();
//            qtyMap.put("checkqty","10.0");
//            qtyMap.put("qty","100.0");
//            Map<String,Object> setMap = new HashMap<>();
//            setMap.put("WMS_TEST_001_DSHYMBCS002",qtyMap);
//            setMapNew(jedis,key,setMap);
            // 设置键值对
//            jedis.set(key,"呵呵哈哈哈");
//
//            System.out.println(jedis.get(key));


        }finally {
            if (jedis != null) {
                jedis.close();
                System.out.println("关闭成功: ");
            }
        }
    }

    public static String getStringNew(Jedis jedis,String key, int dbIndex) {

        Object var6;
        try {
            jedis.select(dbIndex);
            if (jedis.exists(key.getBytes())) {
                byte[] in = jedis.get(key.getBytes());
                String var7 = (String)unserialize(in);
                return var7;
            }

            var6 = null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return (String)var6;
    }

    public static String getStringNew(Jedis jedis,String key) {

        Object var6;
        try {
            if (jedis.exists(key.getBytes())) {
                byte[] in = jedis.get(key.getBytes());
                String var7 = (String)unserialize(in);
                return var7;
            }

            var6 = null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return (String)var6;
    }

    public static <T> void setStringNew(Jedis jedis,String key, String param, int dbIndex) {

        try {
            jedis.select(dbIndex);
            jedis.set(key.getBytes(), serialize(param));
        } catch (Exception var11) {
            System.out.println(var11.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

    }

    public static <T> void setStringNew(Jedis jedis,String key, String param) {

        try {
            jedis.set(key.getBytes(), serialize(param));
        } catch (Exception var11) {
            System.out.println(var11.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

    }

    /**
     * 获取redis的Map值
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> getMapNew(Jedis jedis,String key) {
        try{
            String bKey = key;
            if(!jedis.exists(bKey.getBytes())) {
                return null;
            }
            byte[] in = jedis.get(bKey.getBytes());
            Map<String,T> map = (Map<String, T>) unserialize(in);
            return map;
        }finally {
            // 注意：一定要记得 close 操作
            if (jedis != null) {
                // 把连接放回池里面
                jedis.close();
            }
        }
    }

    /**
     * 设置Map
     */
    public static <T> void setMapNew(Jedis jedis,String key, Map<String, T> map) {
        try{
            String bKey = key;
            jedis.set(bKey.getBytes(),serialize(map));
        }catch (Exception e) {
            // e.printStackTrace();
            // 20181214 zhusf 异常信息记录到日志文件中
            System.out.println(e.getMessage());
        }finally {
            // 注意：一定要记得 close 操作
            if (jedis != null) {
                // 把连接放回池里面
                jedis.close();
            }
        }
    }



    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Can't serialize null");
        } else {
            byte[] rv = null;
            ByteArrayOutputStream bos = null;
            ObjectOutputStream os = null;

            try {
                bos = new ByteArrayOutputStream();
                os = new ObjectOutputStream(bos);
                os.writeObject(value);
                os.close();
                bos.close();
                rv = bos.toByteArray();
            } catch (IOException var12) {
                System.out.println(var12.getMessage());
                throw new IllegalArgumentException("Non-serializable object", var12);
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }

                    if (bos != null) {
                        bos.close();
                    }
                } catch (Exception var11) {
                }

            }

            return rv;
        }
    }

    public static Object unserialize(byte[] bytes) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;

        try {
            if (bytes != null) {
                bis = new ByteArrayInputStream(bytes);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
                is.close();
                bis.close();
            }
        } catch (Exception var13) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (bis != null) {
                    bis.close();
                }
            } catch (Exception var12) {
            }

        }

        return rv;
    }
}
