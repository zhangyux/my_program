/*
 * 快速排序算法  
 * 2015-09-22 
 */
#include<stdio.h>
#include<malloc.h>

#define MAXSIZE 10 
#define TRUE 1
#define FALSE 0
typedef int status; 


//交换数组r[i] 和 r[j]的值
void swap(int *r, int i, int j)
{
    int tmp = r[i]; 
    r[i] = r[j]; 
    r[j] = tmp; 
}

//将枢轴值到正确位置，目的是将呆排序ｒ的比pivot小的放到pivot左边，比pivot大的放到piovt右边，并返回pivot所在位置
int Partition(int *r, int low, int heigh)
{
    int pivot = r[low]; 
    while(low < heigh)
    {
        //将比枢轴小的记录放到pivot的左侧
        if(low<heigh && r[heigh] >= pivot )
        {
            heigh--; 
        }
        swap(r, low, heigh); 

        //将比枢轴大的记录放到pivot的右边
        if(low<heigh && r[low] <= pivot)
        {
            low++; 
        }
        swap(r, low, heigh); 
    }
    //返回枢轴所在位置
    return low; 
}

/*
* 递归实现快速排序
* @param r呆排序序列
* @param low r中数组的最低下标
* @param heigh r中数组的最高下标
*/
void quick_sort(int *r, int low, int heigh)
{
    //选择枢轴点, 目的是将呆排序ｒ的比pivot小的放到pivot左边，比pivot大的放到piovt右边
    int pivot; 
    if(low<heigh)
    {
        pivot = Partition(r, low, heigh);  //将r一分为二, 并算出枢轴值pivot
        quick_sort(r, low, pivot-1);       //将比数轴值小的子序列递归排序
        quick_sort(r, pivot+1, heigh);     //将比数轴值大的子序列递归排序
    }
}
main(void)
{
    int list[] = {9, 1, 5, 8, 3, 7, 4, 6, 2}; 
    int len,  i; 
    //求数组长度
    len = sizeof(list)/sizeof(int); 
    quick_sort(list, 0, len); 
    for(i=0; i<len; i++)
    {
        printf("%d\n", list[i]); 
    }
}
