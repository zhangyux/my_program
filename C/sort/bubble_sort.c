/*
 * 冒泡排序算法  
 * 2015-09-05 
 */
#include<stdio.h>
/*
 * 最基础的顺序查找
 */

#define MAXSIZE 10 
typedef struct
{
    int r[MAXSIZE+1];  //存放要排序的数组
    int length;        //记录顺序表长度
}sqList; 

//交换Ｌ中数组r[i] 和 r[j]的值
void swap(sqList *L, i, j)
{
    int tmp = L->r[i]; 
    L->r[j] = L->r[i]; 
    L->r[j] = tmp; 
}
main(void)
{
}
