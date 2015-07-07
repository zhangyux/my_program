<?php
/**
 * php操作redis类
 *
 * @author: liangxifeng
 * @date  : 2015-07-06
 **/
Class RedisLibrary
{
    //redis对象
    private $_RedisObj;
    public function __construct($configArray = array()) 
    { 
        if(!is_array($configArray))
        {
            echo "Redis construct paramter is not array!";exit;
        }
        $this->_init($configArray);
    }
    /**
     * 初始化连接Redis
     * @param  - array - $configArray:连接Redis的配置信息，
     *                   下标说明：host主机ip地址，port：端口, auth:授权密码
     * @return - object- redis连接对象
     **/
    private function _init($configArray = array())
    {
        if(empty($configArray['host'])) $configArray['host'] = '127.0.0.1';
        if(empty($configArray['port'])) $configArray['port'] = '6379';
        if(empty($configArray['auth'])) $configArray['auth'] = '123456';
        //实例化
        $this->_RedisObj = new Redis();
        //连接redis
        $a = $this->_RedisObj->connect($configArray['host'],$configArray['port']);
        //授权
        $this->_RedisObj->auth('123456');
    }

    /**
     * 设置值  构建一个字符串
     *
     * @param - string $key:     KEY名称
     * @param - string $value:   设置值
     * @param - intval $timeOut: 过期时间  0表示无过期时间
     * @return - boolean  true：成功 false：失败
     **/
    public function set($key, $value, $timeOut=0) 
    {
        $retRes = $this->_RedisObj->set($key, $value);
        if ($timeOut > 0) $this->_RedisObj->expire($key, $timeOut);
        return $retRes;
    }
    /**
     *  通过key获取数据
     *
     * @param - string $key: KEY名称
     * @return - 对应key值
     *
     **/
    public function get($key) 
    {
        $retRes = $this->_RedisObj->get($key);
        return $retRes;
    }
}

$redis = new RedisLibrary(array('host'=>'127.0.0.1','port'=>'6379','auth'=>123456));
$array = array('userid'=>1,'password'=>'123456');
$redis->set("cart1",json_encode($array),10);
$a = $redis->get('cart1');
var_dump($a);
?>
