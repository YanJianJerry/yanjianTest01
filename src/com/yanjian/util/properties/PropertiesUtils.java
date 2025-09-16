package com.yanjian.util.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties 配置文件读取工具类（支持多环境 + 本地路径）
 */
public final class PropertiesUtils {

    // 私有构造方法，防止实例化
    private PropertiesUtils() {
        throw new UnsupportedOperationException("这是一个工具类，不能被实例化");
    }

    // 配置文件缓存
    private static Properties properties;

    /**
     * 初始化配置文件
     *
     * @param env           当前环境，如 "dev", "prod"
     * @param localFilePath 可选参数，本地文件系统路径，如 "D:/config/config.properties"。如果为 null，则从 classpath 加载
     */
    public static void init(String env, String localFilePath) {
        if (properties != null) {
            // 已经初始化过，避免重复加载
            return;
        }

        properties = new Properties();
        InputStream input = null;

        if (localFilePath != null && !localFilePath.trim().isEmpty()) {
            // 优先从本地文件系统路径加载
            try {
                input = new FileInputStream(localFilePath);
                properties.load(input);
                System.out.println("已从本地文件加载配置: " + localFilePath);
            } catch (IOException e) {
                throw new RuntimeException("从本地文件加载配置失败: " + localFilePath, e);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException ignored) {}
                }
            }
        } else {
            // 从 classpath 加载（如 src/main/resources 下的文件）
            String fileName = "config-" + env + ".properties";
            try (InputStream classpathInput = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName)) {
                if (classpathInput == null) {
                    throw new IllegalArgumentException("无法找到配置文件: " + fileName + "，请检查 classpath 或环境参数");
                }
                properties.load(classpathInput);
                System.out.println("已从 classpath 加载配置: " + fileName);
            } catch (IOException e) {
                throw new RuntimeException("从 classpath 加载配置失败: " + fileName, e);
            }
        }
    }

    /**
     * 根据 key 获取配置值
     *
     * @param key 配置项的 key
     * @return 配置值，如果 key 不存在则返回 null
     */
    public static String getProperty(String key) {
        if (properties == null) {
            throw new IllegalStateException("请先调用 init 方法初始化配置文件");
        }
        return properties.getProperty(key);
    }

    /**
     * 根据 key 获取配置值，如果不存在则返回默认值
     *
     * @param key          配置项的 key
     * @param defaultValue 默认值
     * @return 配置值或默认值
     */
    public static String getProperty(String key, String defaultValue) {
        if (properties == null) {
            throw new IllegalStateException("请先调用 init 方法初始化配置文件");
        }
        return properties.getProperty(key, defaultValue);
    }
}