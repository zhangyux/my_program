<?php
namespace app\controllers\dir1;
use Yii;
use yii\web\Controller;

class PostCommentController extends Controller
{
    public function actionIndex()
    {
         //重定向
         //return $this->redirect('/index.php?r=site/index');
         //察看数据库连接
         $db = \Yii::$app->db;
         //var_dump($db);
         //查看缓存
         $cache = \Yii::$app->cache;
         //var_dump($cache);exit;
         echo '<br>this is a test controller!';
         $name = $this->getName();
         var_dump($name);exit;

    }
    //私有方法必须在类内部调用，不能在路由规则内访问
    private function getName()
    {
        return 'my name is liangxifeng';
    }
}
