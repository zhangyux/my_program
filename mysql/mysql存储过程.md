### 一，语法：
```
create procedure 存储过程名字()   
(   
[in|out|inout] 参数 datatype   
)   
begin   
MySQL 语句;   
end;
```  

> MySQL 存储过程参数如果不显式指定“in”、“out”、“inout”，则默认为“in”。习惯上，对于是“in” 的参数，我们都不会显式指定。

### 二，规则

* 输入参数例子，我们选择了会话变量@x证明成功的将参数传入了改变量.

```
mysql> CREATE PROCEDURE p2(p INT) SET @x = p ;//  
Query OK, 0 rows affected (0.02 sec)  
   
mysql> CALL p2(123)//  
Query OK, 0 rows affected (0.00 sec)  
   
mysql> SELECT @x//  
+------+  
| @x   |  
+------+  
|  123 |  
+------+  
1 row in set (0.01 sec)  
```


* 输出参数的例子，我们选择会话变量@y去接收存储过程p3

```
mysql> CREATE PROCEDURE p3(OUT p INT)  
    -> SET p = -5;//  
Query OK, 0 rows affected (0.00 sec)  
   
mysql> CALL p3(@y)//  
Query OK, 0 rows affected (0.01 sec)  
   
mysql> SELECT @y//  
+------+  
| @y   |  
+------+  
|   -5 |  
+------+  
1 row in set (0.00 sec)  
```



* MySQL 存储过程名字后面的“()”是必须的，即使没有一个参数，也需要“()”

* MySQL 存储过程参数，不能在参数名称前加“@”，如：“@a int”。下面的创建存储过程语法在 MySQL 中是错误的（在 SQL Server 中是正确的）。 MySQL 存储过程中的变量，不需要在变量名字前加“@”，虽然 MySQL 客户端用户变量要加个“@”。
* MySQL 存储过程的参数不能指定默认值。
* 如果 MySQL 存储过程中包含多条 MySQL 语句，则需要 begin end 关键字。
* MySQL 存储过程中的每条语句的末尾，都要加上分号 “;”
* MySQL 存储过程中的注释：

```
/*   
这是个   
多行 MySQL 注释。   
*/ 
```
```  
declare c int; -- 这是单行 MySQL 注释 （注意 -- 后至少要有一个空格）   
if a is null then # 这也是个单行 MySQL 注释   
set a = 0;   
end if;   
...   
end; 
```

* 不能在 MySQL 存储过程中使用 “return” 关键字：

```
set c = a + b;   
select c as sum;   
/*   
return c; -- 不能在 MySQL 存储过程中使用。return 只能出现在函数中。   
*/   
end;  
```

* 调用 MySQL 存储过程时候，需要在过程名字后面加“()”，即使没有一个参数，也需要“()”

```
call pr_no_param();  
```

### 三，实例：
[代码实例](https://github.com/liangxifeng833/my_program/blob/master/mysql/procedure_demo.sql)
