<?php
namespace app\controllers\dir1;
use Yii;
use yii\web\Controller;

class PostCommentController extends Controller
{
    public function actionIndex()
    {
         //察看数据库连接
         $db = \Yii::$app->db;
         var_dump($db);
         echo '<br>';
         //查看缓存
         $cache = \Yii::$app->cache;
         var_dump($cache);exit;
         echo '<br>this is a test controller!';

    }
}
