package domain.util;

import domain.domain.Point;

/**
 * 验证坐标点是否在一条直线上
 * @author  liangxifeng 2018-01-11
 */
public class IsOnLine {
    public static boolean IsLine(Point p1, Point p2, Point p3){
        boolean flag=false;
        float k1=0.0f;//斜率
        float k2=0.0f;//斜率

        //1、两点共点（p1与p2共点，p1与p3共点，p2与p3共点）
        if((p1.getX()==p2.getX()&&p1.getY()==p2.getY())
                ||(p1.getX()==p3.getX()&&p1.getY()==p3.getY())
                ||(p2.getX()==p3.getX()&&p2.getY()==p3.getY())){
            flag=true;
            return flag;
        }

        //2、三点纵坐标相等，横坐标不相等
        if((p1.getY()==p2.getY())&&(p1.getY()==p3.getY())
                &&(p1.getX()!=p2.getX())&&(p1.getX()!=p3.getX())){
            flag=true;
            return flag;
        }
        //3、三点横坐标相等，且纵坐标不相等
        if((p1.getX()==p2.getX())&&(p1.getX()==p3.getX())
                &&(p1.getY()!=p2.getY())&&(p1.getY()!=p3.getY())){
            flag=true;
            return flag;
        }else{//横坐标不相等则不存在除数为0问题
            k1=(p3.getY()-p2.getY())/(p3.getX()-p2.getX());
            k2=(p1.getY()-p2.getY())/(p1.getX()-p2.getX());
            if(k1==k2){
                flag=true;
                return flag;
            }
        }

        //4、三点共点
//		if(p1.getX()==p2.getX()&&p1.getX()==p3.getX()
//				&&p1.getY()==p2.getY()&&p1.getY()==p3.getY()){
//			flag=true;
//		}
        //5、斜率相等（除数不能为0，且满足随机输入）
        //>>>>>中间点p2作为除数，则可以随机输入
//		float n=p3.getX()-p2.getX();
//		float m=p1.getX()-p2.getX();
//
//
//		if(n!=0&&m!=0){//除数不能为0
//			k1=(p3.getY()-p2.getY())/n;
//			k2=(p1.getY()-p2.getY())/m;
//			if(k1==k2){
//				flag=true;
//			}
//		}
        return flag;
    }
    //主方法
    @SuppressWarnings("static-access")
    public static void main(String args[]){
        //三点共点
        boolean bool=new IsOnLine().IsLine(new Point(0.0f,0.0f), new Point(0.0f,0.0f), new Point(0.0f,0.0f));
//		 bool=new IsOnLine().IsLine(new Point(0.0f,0.0f), new Point(0.0f,0.0f), new Point(1.0f,1.0f));
//		 bool=new IsOnLine().IsLine(new Point(1.0f,2.0f), new Point(2.0f,4.0f), new Point(4.0f,8.0f));
//		 bool=new IsOnLine().IsLine(new Point(2.0f,3.0f), new Point(2.0f,5.0f), new Point(2.0f,8.0f));
        bool=new IsOnLine().IsLine(new Point(1.0f,1.0f), new Point(1.0f,1.0f), new Point(1.0f,1.0f));

        //三点随机输入测试
        bool=new IsOnLine().IsLine(new Point(0.0f,2.0f), new Point(1.0f,1.0f), new Point(2.0f,0.0f));
        bool=new IsOnLine().IsLine(new Point(1.0f,1.0f), new Point(2.0f,0.0f), new Point(0.0f,2.0f));
        bool=new IsOnLine().IsLine(new Point(5.0f,3.0f), new Point(6.2f,4.0f), new Point(7.0f,5.0f));


        System.out.println("三点是否共线:"+bool);
    }

}
