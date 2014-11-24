<?php
/*
 * Model学习测试
 *
 * @author:liangxifeng
 * @date: 2014-11-24
 */
namespace app\controllers\dir1;
use Yii;
use yii\web\Controller;

class TestModelController extends Controller
{
    //测试属性,ContactForm是在model中已经建立好的，并且属性名必须是对应数据表字段名
    public function actionAttr()
    {
        $model = new \app\models\ContactForm;
        //以对象形式使用
        $model->name = 'liangxifeng';
        var_dump($model->name);
        //以数组形式使用
        $model['name'] = 'zhangsan';
        var_dump($model['name']);exit;
    }
}
