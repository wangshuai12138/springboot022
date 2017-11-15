package org.qf.action;

import org.qf.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class RedisAction {

    //得到自定义配置的内容
    //@Value中要对应配置文件中的 key
    @Value("${java1712}")
    private String java1712;

    @RequestMapping("/java")
    public String java(){

        return java1712;
    }


    //注入服务层接口
    @Autowired
    private RedisService redisService;

    @RequestMapping("/set")
    public String set(String key,String value){

        redisService.set(key,value);
        return "OK";
    }

    @RequestMapping("/get")
    public Object get(String key){

        return redisService.get(key);
    }

    @RequestMapping("/lPush")
    public String  lPush(String key,String value){

        redisService.lPush(key,value);
        return "OK";
    }

    @RequestMapping("/rPop")
    public Object rPop(String key){

        return redisService.rPop(key);
    }

    @RequestMapping("/rPush")
    public String rPush(String key,String value){

        redisService.rPush(key,value);
        return "OK";
    }

    @RequestMapping("/lPop")
    public Object lPop(String key){

        return redisService.lPop(key);
    }

    @RequestMapping("/setTime")
    public String setTime(String key,String value,Long second){

        redisService.setTime(key,value,second);
        return "OK";
    }

    //原子递增
    @RequestMapping("/incr")
    public Long incr(String key){

        return  redisService.incr(key);
    }

    //原子递减
    @RequestMapping("/decr")
    public Long decr(String key){

        return redisService.decr(key);
    }

    //单独设置有效时间
    @RequestMapping("/expire")
    public String expire(String key,int second){

        redisService.expire(key,second);
        return "OK";
    }

    //查看剩余过期时间
    @RequestMapping("/ttl")
    public Long ttl(String key){

        return redisService.ttl(key);
    }

    //hash
    @RequestMapping("/hashSet")
    @ResponseBody
    public Map<String,String> hashSet(String key, String item, String value){

        //后面的泛型最好写上，防止线上的jdk版本过低
        Map<String ,String> map = new HashMap<String, String>();

        redisService.hset(key,item,value);

        map.put("code","OK");

        //返回响应码
        //OK  代表响应成功
        return map;

    }
    @RequestMapping("/hashGet")
    @ResponseBody
    public Map<String,String> hahGet(String key,String item){

        Map<String,String> map = new HashMap<String, String>();
        String hGet = redisService.hget(key,item).toString();
        if (hGet!=null && hGet!="")
        {
            map.put("code","OK");
            map.put("data",hGet);

        }else {
            map.put("code","error");
            map.put("data","");
        }

        return map;
    }

    @RequestMapping("/hashDel")
    public String hashDel(String key,String item){

        redisService.hdel(key,item);
        return "OK";
    }

}
