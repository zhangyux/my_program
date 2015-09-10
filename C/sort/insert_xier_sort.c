/*
 * 直接插入+希尔排序算法  
 * 2015-09-10 
 */
#include<stdio.h>
#include<malloc.h>


/*
* 简单选择排序
*/
void insert_sort(int *r, int len)
{
    int i, j, tmp; 
    //下标从1开始，默认下标0（一个元素）已经排好序（升序）
    for(i=1; i<=len; i++)
    {
        if(r[i-1] > r[i])
        {
            //将较小值放入到tmp临时哨兵变量中
            tmp = r[i]; 
            //我比你小，你就做到后面位置去，空出你的位置, 并且空座位一直向前移动
            for(j=i-1; r[j]>tmp; j--)
            {
                r[j+1] = r[j]; 
            }
            //最终确定空座位的合理位置，然后我坐上去
            r[j+1] = tmp; 
        }
    }
}

main(void)
{
    int list[] = {9, 1, 5, 8, 3, 7, 4, 6, 2}; 
    int len,  i; 
    //求数组长度
    len = sizeof(list)/sizeof(int); 
    insert_sort(list, len); 
    for(i=0; i<len; i++)
    {
        printf("%d\n", list[i]); 
    }
}
