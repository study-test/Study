#include <stdio.h>
#include <windows.h>
#include <string.h>
#define N 1000
struct worker_information
{
    int num;
 char name[10]; 
 int age;
 int worktime;
 char sex;
 char marrige;
 char tel[20];
 char grade;
 int wage;
 char tired;
 char addr[30];
}em[1000];        //定义一个结构体

void menu();
void input();
void save(int);
void del();
void add();
void search();
void search_name();
void search_worktime();
void search_grade();
void modify();  //定义各函数
void menu()   //菜单函数
{
 printf("        ******************职工信息管理系统****************\n");
 printf("           1。录入职工信息");
 printf("           2。查询职工信息\n");
 printf("           3。删除职工信息");
 printf("           4。添加职工信息\n");
 printf("           5。修改职工信息");
 printf("           6。退出\n");
 printf("\n");
    
}
int main()
{    
 char key[100]="123456";     //正确口令
 char s[100];
 int i=3;
 int n,flag;char a;
 for(;i>0;i--)
 {
   printf("请输入登录口令(初始化口令为123456):"); 
   scanf("%s",s);
   if(strcmp(s,key)==0)                
   {
     printf("你是合法用户!\n");
     system("pause");
     system("cls");
     break;
   }
  else
   {
     printf("你是非法用户!\n");
   }
  if(i!=1)
   printf("你还有%i次机会\n",i-1);
  if(i==1)
   return -1;
   system("pause");
   system("cls");
 }

 do
 {    
    menu();
    printf("请选择你需要操作的步骤(1--6):");
    scanf("%d",&n);
	system("cls");
    if(n>=1&&n<=6)
    {
    flag=1;
    break;
    }
    else
    {
   flag=0;
   printf("您输入有误，请重试...");
   system("pause");
   system("cls");
    }
 }
    while(flag==0);
    while(flag==1)
  {
  switch(n)
  {
    case 1:printf("               ◆◆◆录入职工信息◆◆◆\n");printf("\n");input();break;
    case 2:printf("              ◆◆◆按职工编号查询职工信息◆◆◆\n");printf("\n");search();break; 
    case 3:printf("              ◆◆◆删除职工信息◆◆◆\n");printf("\n");del();break;
    case 4:printf("              ◆◆◆添加职工信息◆◆◆\n");printf("\n");add();break;
    case 5:printf("               ◆◆◆修改职工信息◆◆◆\n");printf("\n");modify();break;
    case 6:exit(0);break;
    default :break;
  }
  getchar();
  printf("\n");
     printf("是否继续进行(y/n):");
     scanf("%c",&a);
  if(a=='y')
  {
     flag=1;
           system("cls");  //清屏
           menu(); //调用菜单函数
     printf("请再次选择你需要操作的步骤(1--6):");
        scanf("%d",&n);
     printf("\n");
  }
  
  else 
  exit(0);
 }
 return 0;
}
void input()   //录入函数
{
   int i,m;
      printf("请输入需要创建信息的职工人数(1--1000):");
   scanf("%d",&m);
   for (i=0;i<m;i++)
   {
    em[i].num=i+1;
    printf("职工编号:");
    printf("%d\n",em[i].num);
    printf("请输入姓名: ");
    scanf("%s",em[i].name);
    getchar();
    printf("请输入年龄: ");
    scanf("%d",&em[i].age);getchar();
    printf("请输入工龄: ");
    scanf("%d",&em[i].worktime);getchar();
    printf("请输入性别(w--女  m--男): ");
    scanf("%c",&em[i].sex);getchar();
    printf("请输入婚姻状况(y/n): ");
    scanf("%c",&em[i].marrige);getchar();
    printf("请输入电话: ");
    scanf("%s",em[i].tel);getchar();
    printf("请输入等级(1--5): ");
    scanf("%c",&em[i].grade);getchar();
        printf("请输入工资: ");
     scanf("%d",&em[i].wage);getchar();
	  printf("请输入是否在职(y/n): ");
     scanf("%c",&em[i].tired);getchar();
	 printf("请输入家庭地址: ");
	 scanf("%s",em[i].addr);getchar();
     printf("\n");
    if(em[i].tired=='n')                  //工资调整
	{
	   em[i].wage+=50;
	}
	else if(em[i].tired=='y')
	{
	if(em[i].grade=='1')
            em[i].wage+=20;
	else if(em[i].grade=='2')
            em[i].wage+=40;
	else if(em[i].grade=='3')
            em[i].wage+=60;
	else if(em[i].grade=='4')
            em[i].wage+=80;
	else if(em[i].grade=='5')
            em[i].wage+=100;
	}
   }
   
   printf("\n录入成功!\n");
      save(m);
}
void save(int m)  //保存文件函数
{
 int i;
 FILE*fp;
 if ((fp=fopen("worker_list.txt","w"))==NULL)  //创建文件并判断是否能打开
 {
  printf ("不能打开文件\n");
     exit(0);
 }
 if (fwrite(&em[i],sizeof(int),1,fp)!=1) 
 {
 	printf("写入文件错误\n");
 }
 for (i=0;i<m;i++) //将内存中职工的信息输出到磁盘文件中去
   if (fwrite(&em[i],sizeof(struct worker_information),1,fp)!=1)
   printf("文件读写错误\n");
   fclose(fp);
}
 int load()  //导入函数
{
     FILE*fp;
 int i=1;
   if((fp=fopen("worker_list.txt","r"))==NULL)
 {
  printf ("不能打开文件\n");
     exit(0);
 }
 else 
 {
  do 
  {
        fread(&em[i],sizeof(struct worker_information),1,fp);
     i++;
  }
  while(feof(fp)==0);
 }
 fclose(fp);
 return(i-1);
}
void display()  //浏览函数
{
 int i;
 int m=load();
 for(i=0;i<m;i++)  //m为输入部分的职工人数
 {  
	 printf("职工编号:%d\n",em[i].num);
	printf("姓名:%s\n",em[i].name);
printf("年龄:%d\n",em[i].age);
printf("工龄:%d\n",em[i].worktime);
printf("性别:%c\n",em[i].sex);
printf("婚姻状况:%c\n",em[i].marrige);
	 printf("电话:%s\n",em[i].tel);
	 printf("等级:%c\n",em[i].grade);
	printf("工资:%d\n",em[i].wage);
	printf("是否在职:%c\n",em[i].tired);
	printf("地址:%s\n",em[i].addr);
 }
}
void del()   //删除函数
{
  
    int m=load();
 int i,j,n,t,flag;
 char name[20];
 int num;
 int ch;
 printf("\n 原来的职工信息:\n");
    display();   //调用浏览函数
    printf("\n");
	scanf("%d",&ch);
	if(ch<1||ch>2)
{
	printf("输入错误,请重试...\n");
del();
}
	switch(ch)
{
case 1:printf("请输入要删除的职工的编号:");  scanf("%d",&num);break;

case 2:printf("请输入要删除的职工的姓名:");
 scanf("%s",name);break;
}
 for(flag=1,i=0;flag&&i<m;i++)
 {
  if(strcmp(em[i].name,name)==0||num==em[i].num)
  {
   printf("\n已找到此人，原始记录为：\n");
 printf("职工编号:%d\n",em[i].num);
	printf("姓名:%s\n",em[i].name);
printf("年龄:%d\n",em[i].age);
printf("工龄:%d\n",em[i].worktime);
printf("性别:%c\n",em[i].sex);
printf("婚姻状况:%c\n",em[i].marrige);
	 printf("电话:%s\n",em[i].tel);
	 printf("等级:%c\n",em[i].grade);
	printf("工资:%d\n",em[i].wage);
	printf("是否在职:%c\n",em[i].tired);
	 printf("地址:%s\n",em[i].addr);
            printf("\n是否删除此人信息?y/n\n");
   scanf("%d",&n);
            if(n=='y')   //如果删除，则其他的信息都往上移一行
   {
     for(j=i;j<m-1;j++)
     {
    strcpy(em[j].name,em[j+1].name);
                em[j].num=em[j+1].num;
                em[j].sex=em[j+1].sex;
                em[j].age=em[j+1].age;
                em[j].marrige=em[j+1].marrige;
    em[j].wage=em[j+1].wage;  
                strcpy(em[j].addr,em[j+1].addr);
                strcpy(em[j].tel,em[j+1].tel);
				em[j].worktime=em[j+1].worktime;
				em[j].grade=em[j+1].grade;
				em[j].tired=em[j+1].tired;
     }
    flag=0;
   }
  }
 }
 if(!flag)
    m=m-1;
 else
    printf("\n对不起，没有找到适配者!\n");
    printf("\n 浏览删除后的信息:\n");
    save(m);     //调用保存函数
    display();  //调用浏览函数
 printf("\n是否继续?y/n\n");
 scanf("%d",&t);getchar();
 switch(t)
 {
    case 'y':del();break;
    case 'n':break;
    default :break;
 }
}
void add()//添加函数
{
 FILE*fp;
 int n;
    int count=0;
    int i;
    int m=load();
    printf("\n 原来的职工信息:\n");
    display();   // 调用浏览函数
    printf("\n");
    fp=fopen("worker_list","a");
    printf("请输入想增加的职工数:");
 scanf("%d",&n);
    for (i=m;i<(m+n);i++)
  {em[i].num=i;
  printf("\n 正在输入新增加职工的信息\n");
  printf("职工编号:");
       printf("%d",em[i].num);
  printf("\n");
       printf("请输入姓名: ");
  scanf("%s",em[i].name);
  getchar();
  printf("请输入年龄: ");
  scanf("%d",&em[i].age);getchar();
    printf("请输入工龄: ");
  scanf("%d",&em[i].worktime);getchar();
  printf("请输入性别(w--女  m--男): ");
  scanf("%c",&em[i].sex);getchar();
  printf("请输入婚姻状况(y/n): ");
  scanf("%c",&em[i].marrige);getchar();
  printf("请输入电话: ");
  scanf("%ld",&em[i].tel);getchar();
  printf("请输入等级: ");
  scanf("%c",&em[i].grade);getchar();
        printf("请输入工资: ");
     scanf("%d",&em[i].wage);getchar();
	 printf("请输入是否在职: ");
     scanf("%c",&em[i].tired);getchar();
	 printf("请输入家庭地址: ");
	 scanf("%s",em[i].addr);getchar();
     printf("\n");

     count=count+1;
  printf("已增加的人数:\n");
     printf("%d\n",count);
 }
    printf("\n添加完毕!\n"); 
    m=m+count;
 printf("\n浏览后的所有职工信息:\n");
 printf("\n");
 save(m);
    display();
   fclose(fp);
}
void search()/*查询函数*/
{
    int t,flag;
 do
 {
    printf("\n1.按姓名查询 \n");
	printf("2.按工龄查询\n");
	printf("3.按级别查询\n");
	printf("4.退出\n");
    scanf("%d",&t);
	system("cls");
    if(t>=1&&t<=4)
    {
    flag=1;
    break;
    }
    else
    {
   flag=0;
   printf("您输入有误，请重试...");
    }
 }
    while(flag==0);
    while(flag==1)
 {
    switch(t)
    { 
     case 1:printf("按姓名查询\n");search_name();break;
     case 2:printf("按工龄查询\n");search_worktime();break;
     case 3:printf("按等级查询\n");search_grade();break;
     case 4:return menu();break;

    }
    
 }
}
void search_name()//查找函数——按姓名查询 
{ 
  char name[10]; 
  int i,t; 
  int m=load();
  printf("请输入您要查找的人的姓名：");
  scanf("%s",name);

  for(i=0;i<m;i++)
    if(strcmp(em[i].name,name)==0)
 {  
    printf("\n已找到此人，其记录为：\n");
    printf("姓名:%s\n",em[i].name); 
    printf("职工编号:%d\n",em[i].num);
    printf("年龄:%d\n",em[i].age);
    printf("工龄:%d\n",em[i].worktime);
printf("性别:%c\n",em[i].sex);
printf("婚姻状况:%c\n",em[i].marrige);
	 printf("电话:%s\n",em[i].tel);
	 printf("等级:%c\n",em[i].grade);
	printf("工资:%d\n",em[i].wage);
	printf("是否在职:%c\n",em[i].tired);
	 printf("地址:%s\n",em[i].addr);
    break;
 }
   if(i==m)  
    printf("\n对不起，没有找到适配者\n");
   printf("\n");
   printf("返回查询函数请按1,继续查询姓名请按2\n");
   scanf("%d",&t);
   	system("cls");
   switch(t)
   { 
   case 1:search();break;
      case 2:search_name();break;
   default:break;
   }
}

void search_worktime()//查找函数——按工龄查找 
{
   char worktime;
  int i, t;
  int m=load();
  printf("请输入您想要查找的工龄:\n");
  scanf("%d",worktime);
  for(i=0;i<m;i++) 
    if(worktime==em[i].worktime)
 {  
    printf("\n已找到此人，其记录为:\n");
 printf("职工编号:%d\n",em[i].num);
	printf("姓名:%s\n",em[i].name);
printf("年龄:%d\n",em[i].age);
printf("工龄:%d\n",em[i].worktime);
printf("性别:%c\n",em[i].sex);
printf("婚姻状况:%c\n",em[i].marrige);
	 printf("电话:%s\n",em[i].tel);
	 printf("等级:%c\n",em[i].grade);
	printf("工资:%d\n",em[i].wage);
	printf("是否在职:%c\n",em[i].tired);
	 printf("地址:%s\n",em[i].addr);
    break;
 }
    if(i==m)  
    printf("\n对不起，没有找到适配者\n");
   printf("\n");
   printf("返回查询函数请按1,继续查询工龄请按2\n");
   scanf("%d",&t);
   system("cls");
  switch(t)
  { 
   case 1:search();break;
      case 2:search_worktime();break;
   default :break;
  }
  
}
void search_grade()//查找函数——按等级查找 
{
	 char grade;
     int i,t;
  int m=load();
  printf("请输入您想要查找的等级:\n");
  scanf("%d",grade);
  for(i=0;i<m;i++) 
    if(grade==em[i].grade)
 {  
    printf("\n已找到此人，其记录为:\n");
 printf("职工编号:%d\n",em[i].num);
	printf("姓名:%s\n",em[i].name);
printf("年龄:%d\n",em[i].age);
printf("工龄:%d\n",em[i].worktime);
printf("性别:%c\n",em[i].sex);
printf("婚姻状况:%c\n",em[i].marrige);
	 printf("电话:%s\n",em[i].tel);
	 printf("等级:%c\n",em[i].grade);
	printf("工资:%d\n",em[i].wage);
	printf("是否在职:%c\n",em[i].tired);
	 printf("地址:%s\n",em[i].addr);
    break;
 }
    if(i==m)  
    printf("\n对不起，没有找到适配者\n");
   printf("\n");
   printf("返回查询函数请按1,继续查询等级请按2\n");
   scanf("%d",&t);
   system("cls");
  switch(t)
  { 
   case 1:search();break;
      case 2:search_grade();break;
   default :break;
  }
  
	  
} 
void modify() /*修改函数*/
{    int ch;
    int num;
 char name[10]; 
 int age;
 int worktime=0;
 char sex;
 char marrige=0;
 char tel[20];
 char grade=0;
 char tired=0;
 int wage=0;
 char addr[30];
    int b,c,i,n,t,flag; 
 int m=load(); /*导入文件内的信息*/
printf("1.职工编号修改     2.职工姓名修改\n");
printf("请输入修改方式:");scanf("%d",&ch);getchar();
if(ch<1||ch>2)
{
	printf("输入错误,请重试...\n");
modify();
}
switch(ch)
{
case 1:printf("请输入要修改的职工的编号:");  scanf("%d",&num);break;

case 2:printf("请输入要修改的职工的姓名:");
 scanf("%s",name);break;
}
 for(flag=1,i=0;flag&&i<m;i++)
 {
  if(strcmp(em[i].name,name)==0||num==em[i].num)
  {
   printf("\n已找到此人，原始记录为：\n");
 printf("职工编号:%d\n",em[i].num);
	printf("姓名:%s\n",em[i].name);
printf("年龄:%d\n",em[i].age);
printf("工龄:%d\n",em[i].worktime);
printf("性别:%c\n",em[i].sex);
printf("婚姻状况:%c\n",em[i].marrige);
	 printf("电话:%s\n",em[i].tel);
	 printf("等级:%c\n",em[i].grade);
	printf("工资:%d\n",em[i].wage);
	printf("是否在职:%c\n",em[i].tired);
	 printf("地址:%s\n",em[i].addr);
            printf("\n确实要修改此人信息请按1,不修改请按0\n");
   scanf("%d",&n);
   system("cls");
            if(n==1)
   {
               printf("\n需要进行修改的选项\n  1.姓名 2.性别 3.年龄 4.工龄 5.工资 6.住址 7.电话 8.等级 9.婚姻状况 10.是否在职 \n");  
               printf("请输入您想修改的选项:\n");  
               scanf("%d",&c);  
               if(c>10||c<1)  
         printf("\n选择错误，请重新选择!\n");  
   }
         flag=0;
  }
        
 }
    if(flag==1)
 printf("\n对不起，没有找到适配者!\n");  
    do  
 {
  switch(c)     
  {  
           case 1:printf("姓名改为: ");
                  scanf("%s",name);
                  strcpy(em[i].name,name); 
                  break;  
           case 2:printf("性别改为: ");
                  getchar();
                  scanf("%c",&sex);
                  em[i].sex=sex;
                  break;  
           case 3:printf("年龄改为: ");
                  scanf("%d",&age);
                  em[i].age=age;
                  break;  
           case 4:printf("工龄改为: ");
                  scanf("%d",worktime);
                  em[i].worktime=worktime; 
                  break;  
           case 5:printf("工资改为: ");
                  scanf("%d",wage);
                  break;  
           case 6:printf("住址改为: ");
                  scanf("%s",addr);
                  strcpy(em[i].addr,addr);
                  break;  
           case 7:printf("电话改为: ");
                  scanf("%s",tel);
                  strcpy(em[i].tel,tel); 
                  break;  
		   case 8:printf("等级改为:");
			      scanf("%c",grade);
			      em[i].grade=grade;
			      break;
		   case 9:printf("婚姻状况改为(y/n):");
			      scanf("%c",marrige);
			      em[i].marrige=marrige;
		   case 10:printf("是否在职改为(y/n):");
			       scanf("%c",tired);
			       em[i].tired=tired;
		  
  } 
        printf("\n");
  printf("\n是否确定所修改的信息y/n?\n"); 
        scanf("%d",&b);
   
 }  
   while(b=='n');
   printf("\n浏览修改后的所有职工信息:\n");
   printf("\n");
   save(m);
   display();
   printf("\n是否继续修改?y/n\n");
   scanf("%d",&t);getchar();
   switch(t)
 {
    case 'y':modify();break;
    case 'n':break;
    default :break;
 }
  
}
