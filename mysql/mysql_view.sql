/*
 view_comment 视图，供前台查询所用，不允许添加、修改、删除
 引用表ecm_order_goods、ecm_order和virtual_order
 详细说明请看svn:3710/蓝景商城/分阶段/评论系统/商城评论系统数据结构.wps
 liangxifeng 2014-01-17
 */
CREATE VIEW ecm_view_order_comment
	(comment_id,goods_id,type,comment,comment_add_time,reply_content,reply_time,goods_count,note,order_add_time,user_name,user_id,goods_name) 
    AS SELECT ecm_order_goods.rec_id,
              ecm_order_goods.goods_id,
              0,
              ecm_order_goods.comment,
              FROM_UNIXTIME(ecm_order.add_time),
              0,0,
              ecm_order_goods.quantity,
              0,
              FROM_UNIXTIME(ecm_order.add_time),
              ecm_order.buyer_name,
              ecm_order.buyer_id,
              ecm_order_goods.goods_name 
    FROM ecm_order LEFT JOIN ecm_order_goods ON ecm_order.order_id=ecm_order_goods.order_id 
    WHERE ecm_order.status IN(20,30,40)   
    AND ecm_order_goods.is_valid=1 
    UNION ALL
	SELECT ecm_virtual_order.virtual_goods_id,
           ecm_virtual_order.goods_id,
           1,
           ecm_virtual_order.comment_content,
           ecm_virtual_order.comment_add_time,
           ecm_virtual_order.reply_content,
           ecm_virtual_order.reply_add_time,
           ecm_virtual_order.goods_count,
           ecm_virtual_order.note,
           ecm_virtual_order.add_time,
           ecm_virtual_order.virtual_user_name,
           ecm_virtual_order.virtual_user_id,
           ecm_virtual_order.goods_name 
    FROM ecm_virtual_order 
    WHERE ecm_virtual_order.check=1;
