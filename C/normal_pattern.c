/*
 * C语言实现朴素模式匹配算法
 * date:2015-05-18
 */
#include<stdio.h>
void main(int argc, char *argv[])
{
    char s[] = "abcabcdefg"; 
    char t[] = "abcdefi"; 
    int sLen = sizeof(s)-1; 
    int tLen = sizeof(t)-1; 
    int i=0, j=0; 
    while(i<sLen  && j<tLen)
    {
        if(s[i] == t[j])
        {
            ++i; 
            ++j; 
        }else
        {
            i = i-j+1; 
            j=0; 
        }
    }
    if(j == tLen)
    {
        printf("匹配成功，t在s中的第一个位置是%d\n", i-j); 
    }else
    {
        printf("匹配失败\n"); 
    }
}
