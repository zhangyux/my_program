/*
 * 冒泡排序算法  
 * 2015-09-05 
 */
#include<stdio.h>
#include<malloc.h>
#define MAXSIZE 10 


//交换数组r[i] 和 r[j]的值
void swap(int *r, int i, int j)
{
    int tmp = r[i]; 
    r[i] = r[j]; 
    r[j] = tmp; 
}

/*
* 简单交换
*/
void bubbleSort_one(int *r, int len)
{
    int i, j; 
    for(i=0; i<len; i++)
    {
        for(j=i+1; j<len; j++)
        {
            if(r[i]>r[j])
            {
                swap(r, i, j); 
            }
        }
    }
}

/*
* 冒泡排序
*/
void bubbleSort_two(int *r, int len)
{
    int i, j; 
    for(i=0; i<len; i++)
    {
        for(j=len-2; j>=i; j--)
        {
            if(r[j]>r[j+1])
            {
                swap(r, j, j+1); 
            }
        }
    }
}

main(void)
{
    int list[] = {9, 1, 5, 8, 3, 7, 4, 6, 2}; 
    int len,  i; 
    //求数组长度
    len = sizeof(list)/sizeof(int); 
    bubbleSort_two(list, len); 
    for(i=0; i<len; i++)
    {
        printf("%d\n", list[i]); 
    }
}
