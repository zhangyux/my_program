package domain.util;

/**
 * 公共验证类
 *  @author : liangxifeng
 *  @date : 2018-01-25
 */
public class CommonCheck {
    /**
     * 验证名称基本信息(非空, 指定个数验证)
     * @param name 名字
     * @param nameNum 指定个数
     * @return 0: 名字为空, 1:验证通过, 2:超过指定个数
     */
    public static Integer checkNameBase(String name, Integer nameNum)
    {
        name = name.trim();
        //验证非空
        if(name == null || name.equals(""))
        {
            return 0;
        }
        //验证名称的个数
        if( name.length() > nameNum  )
        {
            return 2;
        }
        return 1;
    }
}
