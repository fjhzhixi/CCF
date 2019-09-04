# 2018年试题

## 201803

### 1.跳一跳

数据:

1. `lastState`为上一次跳跃状态,初始化为1
2. `lastScore`为上一次得分,初始化为0

逻辑:

```
state == 0 ==> quit
lastState == 1 && state == 1 ==> +1
lastState == 1 && state == 2 ==> +2
lastState == 2 && state == 1 ==> +1
lastState == 2 && state == 2 ==> +(lastScore + 2)
```

### 2.碰撞的小球

主要是对小球进行建模

### 3.URL 映射

**根据规则生成正则表达式**

java正则表达式的使用

### 4.棋局评估

博弈论的对抗搜索问题

