package pattern.prototype;

import java.util.HashMap;
import java.util.Map;

class MonsterRegistry {
    private Map<String, Monster> prototypes = new HashMap<>();

    public void register(String key, Monster monster) {
        prototypes.put(key, monster);
    }

    public Monster create(String key) {
        Monster prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("未知怪物类型: " + key);
        }
        return prototype.clone(); // 返回克隆副本
    }
}