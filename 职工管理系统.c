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
}em[1000];        //����һ���ṹ��

void menu();
void input();
void save(int);
void del();
void add();
void search();
void search_name();
void search_worktime();
void search_grade();
void modify();  //���������
void menu()   //�˵�����
{
 printf("        ******************ְ����Ϣ����ϵͳ****************\n");
 printf("           1��¼��ְ����Ϣ");
 printf("           2����ѯְ����Ϣ\n");
 printf("           3��ɾ��ְ����Ϣ");
 printf("           4�����ְ����Ϣ\n");
 printf("           5���޸�ְ����Ϣ");
 printf("           6���˳�\n");
 printf("\n");
    
}
int main()
{    
 char key[100]="123456";     //��ȷ����
 char s[100];
 int i=3;
 int n,flag;char a;
 for(;i>0;i--)
 {
   printf("�������¼����(��ʼ������Ϊ123456):"); 
   scanf("%s",s);
   if(strcmp(s,key)==0)                
   {
     printf("���ǺϷ��û�!\n");
     system("pause");
     system("cls");
     break;
   }
  else
   {
     printf("���ǷǷ��û�!\n");
   }
  if(i!=1)
   printf("�㻹��%i�λ���\n",i-1);
  if(i==1)
   return -1;
   system("pause");
   system("cls");
 }

 do
 {    
    menu();
    printf("��ѡ������Ҫ�����Ĳ���(1--6):");
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
   printf("����������������...");
   system("pause");
   system("cls");
    }
 }
    while(flag==0);
    while(flag==1)
  {
  switch(n)
  {
    case 1:printf("               ������¼��ְ����Ϣ������\n");printf("\n");input();break;
    case 2:printf("              ��������ְ����Ų�ѯְ����Ϣ������\n");printf("\n");search();break; 
    case 3:printf("              ������ɾ��ְ����Ϣ������\n");printf("\n");del();break;
    case 4:printf("              ���������ְ����Ϣ������\n");printf("\n");add();break;
    case 5:printf("               �������޸�ְ����Ϣ������\n");printf("\n");modify();break;
    case 6:exit(0);break;
    default :break;
  }
  getchar();
  printf("\n");
     printf("�Ƿ��������(y/n):");
     scanf("%c",&a);
  if(a=='y')
  {
     flag=1;
           system("cls");  //����
           menu(); //���ò˵�����
     printf("���ٴ�ѡ������Ҫ�����Ĳ���(1--6):");
        scanf("%d",&n);
     printf("\n");
  }
  
  else 
  exit(0);
 }
 return 0;
}
void input()   //¼�뺯��
{
   int i,m;
      printf("��������Ҫ������Ϣ��ְ������(1--1000):");
   scanf("%d",&m);
   for (i=0;i<m;i++)
   {
    em[i].num=i+1;
    printf("ְ�����:");
    printf("%d\n",em[i].num);
    printf("����������: ");
    scanf("%s",em[i].name);
    getchar();
    printf("����������: ");
    scanf("%d",&em[i].age);getchar();
    printf("�����빤��: ");
    scanf("%d",&em[i].worktime);getchar();
    printf("�������Ա�(w--Ů  m--��): ");
    scanf("%c",&em[i].sex);getchar();
    printf("���������״��(y/n): ");
    scanf("%c",&em[i].marrige);getchar();
    printf("������绰: ");
    scanf("%s",em[i].tel);getchar();
    printf("������ȼ�(1--5): ");
    scanf("%c",&em[i].grade);getchar();
        printf("�����빤��: ");
     scanf("%d",&em[i].wage);getchar();
	  printf("�������Ƿ���ְ(y/n): ");
     scanf("%c",&em[i].tired);getchar();
	 printf("�������ͥ��ַ: ");
	 scanf("%s",em[i].addr);getchar();
     printf("\n");
    if(em[i].tired=='n')                  //���ʵ���
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
   
   printf("\n¼��ɹ�!\n");
      save(m);
}
void save(int m)  //�����ļ�����
{
 int i;
 FILE*fp;
 if ((fp=fopen("worker_list.txt","w"))==NULL)  //�����ļ����ж��Ƿ��ܴ�
 {
  printf ("���ܴ��ļ�\n");
     exit(0);
 }
 if (fwrite(&em[i],sizeof(int),1,fp)!=1) 
 {
 	printf("д���ļ�����\n");
 }
 for (i=0;i<m;i++) //���ڴ���ְ������Ϣ����������ļ���ȥ
   if (fwrite(&em[i],sizeof(struct worker_information),1,fp)!=1)
   printf("�ļ���д����\n");
   fclose(fp);
}
 int load()  //���뺯��
{
     FILE*fp;
 int i=1;
   if((fp=fopen("worker_list.txt","r"))==NULL)
 {
  printf ("���ܴ��ļ�\n");
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
void display()  //�������
{
 int i;
 int m=load();
 for(i=0;i<m;i++)  //mΪ���벿�ֵ�ְ������
 {  
	 printf("ְ�����:%d\n",em[i].num);
	printf("����:%s\n",em[i].name);
printf("����:%d\n",em[i].age);
printf("����:%d\n",em[i].worktime);
printf("�Ա�:%c\n",em[i].sex);
printf("����״��:%c\n",em[i].marrige);
	 printf("�绰:%s\n",em[i].tel);
	 printf("�ȼ�:%c\n",em[i].grade);
	printf("����:%d\n",em[i].wage);
	printf("�Ƿ���ְ:%c\n",em[i].tired);
	printf("��ַ:%s\n",em[i].addr);
 }
}
void del()   //ɾ������
{
  
    int m=load();
 int i,j,n,t,flag;
 char name[20];
 int num;
 int ch;
 printf("\n ԭ����ְ����Ϣ:\n");
    display();   //�����������
    printf("\n");
	scanf("%d",&ch);
	if(ch<1||ch>2)
{
	printf("�������,������...\n");
del();
}
	switch(ch)
{
case 1:printf("������Ҫɾ����ְ���ı��:");  scanf("%d",&num);break;

case 2:printf("������Ҫɾ����ְ��������:");
 scanf("%s",name);break;
}
 for(flag=1,i=0;flag&&i<m;i++)
 {
  if(strcmp(em[i].name,name)==0||num==em[i].num)
  {
   printf("\n���ҵ����ˣ�ԭʼ��¼Ϊ��\n");
 printf("ְ�����:%d\n",em[i].num);
	printf("����:%s\n",em[i].name);
printf("����:%d\n",em[i].age);
printf("����:%d\n",em[i].worktime);
printf("�Ա�:%c\n",em[i].sex);
printf("����״��:%c\n",em[i].marrige);
	 printf("�绰:%s\n",em[i].tel);
	 printf("�ȼ�:%c\n",em[i].grade);
	printf("����:%d\n",em[i].wage);
	printf("�Ƿ���ְ:%c\n",em[i].tired);
	 printf("��ַ:%s\n",em[i].addr);
            printf("\n�Ƿ�ɾ��������Ϣ?y/n\n");
   scanf("%d",&n);
            if(n=='y')   //���ɾ��������������Ϣ��������һ��
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
    printf("\n�Բ���û���ҵ�������!\n");
    printf("\n ���ɾ�������Ϣ:\n");
    save(m);     //���ñ��溯��
    display();  //�����������
 printf("\n�Ƿ����?y/n\n");
 scanf("%d",&t);getchar();
 switch(t)
 {
    case 'y':del();break;
    case 'n':break;
    default :break;
 }
}
void add()//��Ӻ���
{
 FILE*fp;
 int n;
    int count=0;
    int i;
    int m=load();
    printf("\n ԭ����ְ����Ϣ:\n");
    display();   // �����������
    printf("\n");
    fp=fopen("worker_list","a");
    printf("�����������ӵ�ְ����:");
 scanf("%d",&n);
    for (i=m;i<(m+n);i++)
  {em[i].num=i;
  printf("\n ��������������ְ������Ϣ\n");
  printf("ְ�����:");
       printf("%d",em[i].num);
  printf("\n");
       printf("����������: ");
  scanf("%s",em[i].name);
  getchar();
  printf("����������: ");
  scanf("%d",&em[i].age);getchar();
    printf("�����빤��: ");
  scanf("%d",&em[i].worktime);getchar();
  printf("�������Ա�(w--Ů  m--��): ");
  scanf("%c",&em[i].sex);getchar();
  printf("���������״��(y/n): ");
  scanf("%c",&em[i].marrige);getchar();
  printf("������绰: ");
  scanf("%ld",&em[i].tel);getchar();
  printf("������ȼ�: ");
  scanf("%c",&em[i].grade);getchar();
        printf("�����빤��: ");
     scanf("%d",&em[i].wage);getchar();
	 printf("�������Ƿ���ְ: ");
     scanf("%c",&em[i].tired);getchar();
	 printf("�������ͥ��ַ: ");
	 scanf("%s",em[i].addr);getchar();
     printf("\n");

     count=count+1;
  printf("�����ӵ�����:\n");
     printf("%d\n",count);
 }
    printf("\n������!\n"); 
    m=m+count;
 printf("\n����������ְ����Ϣ:\n");
 printf("\n");
 save(m);
    display();
   fclose(fp);
}
void search()/*��ѯ����*/
{
    int t,flag;
 do
 {
    printf("\n1.��������ѯ \n");
	printf("2.�������ѯ\n");
	printf("3.�������ѯ\n");
	printf("4.�˳�\n");
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
   printf("����������������...");
    }
 }
    while(flag==0);
    while(flag==1)
 {
    switch(t)
    { 
     case 1:printf("��������ѯ\n");search_name();break;
     case 2:printf("�������ѯ\n");search_worktime();break;
     case 3:printf("���ȼ���ѯ\n");search_grade();break;
     case 4:return menu();break;

    }
    
 }
}
void search_name()//���Һ���������������ѯ 
{ 
  char name[10]; 
  int i,t; 
  int m=load();
  printf("��������Ҫ���ҵ��˵�������");
  scanf("%s",name);

  for(i=0;i<m;i++)
    if(strcmp(em[i].name,name)==0)
 {  
    printf("\n���ҵ����ˣ����¼Ϊ��\n");
    printf("����:%s\n",em[i].name); 
    printf("ְ�����:%d\n",em[i].num);
    printf("����:%d\n",em[i].age);
    printf("����:%d\n",em[i].worktime);
printf("�Ա�:%c\n",em[i].sex);
printf("����״��:%c\n",em[i].marrige);
	 printf("�绰:%s\n",em[i].tel);
	 printf("�ȼ�:%c\n",em[i].grade);
	printf("����:%d\n",em[i].wage);
	printf("�Ƿ���ְ:%c\n",em[i].tired);
	 printf("��ַ:%s\n",em[i].addr);
    break;
 }
   if(i==m)  
    printf("\n�Բ���û���ҵ�������\n");
   printf("\n");
   printf("���ز�ѯ�����밴1,������ѯ�����밴2\n");
   scanf("%d",&t);
   	system("cls");
   switch(t)
   { 
   case 1:search();break;
      case 2:search_name();break;
   default:break;
   }
}

void search_worktime()//���Һ���������������� 
{
   char worktime;
  int i, t;
  int m=load();
  printf("����������Ҫ���ҵĹ���:\n");
  scanf("%d",worktime);
  for(i=0;i<m;i++) 
    if(worktime==em[i].worktime)
 {  
    printf("\n���ҵ����ˣ����¼Ϊ:\n");
 printf("ְ�����:%d\n",em[i].num);
	printf("����:%s\n",em[i].name);
printf("����:%d\n",em[i].age);
printf("����:%d\n",em[i].worktime);
printf("�Ա�:%c\n",em[i].sex);
printf("����״��:%c\n",em[i].marrige);
	 printf("�绰:%s\n",em[i].tel);
	 printf("�ȼ�:%c\n",em[i].grade);
	printf("����:%d\n",em[i].wage);
	printf("�Ƿ���ְ:%c\n",em[i].tired);
	 printf("��ַ:%s\n",em[i].addr);
    break;
 }
    if(i==m)  
    printf("\n�Բ���û���ҵ�������\n");
   printf("\n");
   printf("���ز�ѯ�����밴1,������ѯ�����밴2\n");
   scanf("%d",&t);
   system("cls");
  switch(t)
  { 
   case 1:search();break;
      case 2:search_worktime();break;
   default :break;
  }
  
}
void search_grade()//���Һ����������ȼ����� 
{
	 char grade;
     int i,t;
  int m=load();
  printf("����������Ҫ���ҵĵȼ�:\n");
  scanf("%d",grade);
  for(i=0;i<m;i++) 
    if(grade==em[i].grade)
 {  
    printf("\n���ҵ����ˣ����¼Ϊ:\n");
 printf("ְ�����:%d\n",em[i].num);
	printf("����:%s\n",em[i].name);
printf("����:%d\n",em[i].age);
printf("����:%d\n",em[i].worktime);
printf("�Ա�:%c\n",em[i].sex);
printf("����״��:%c\n",em[i].marrige);
	 printf("�绰:%s\n",em[i].tel);
	 printf("�ȼ�:%c\n",em[i].grade);
	printf("����:%d\n",em[i].wage);
	printf("�Ƿ���ְ:%c\n",em[i].tired);
	 printf("��ַ:%s\n",em[i].addr);
    break;
 }
    if(i==m)  
    printf("\n�Բ���û���ҵ�������\n");
   printf("\n");
   printf("���ز�ѯ�����밴1,������ѯ�ȼ��밴2\n");
   scanf("%d",&t);
   system("cls");
  switch(t)
  { 
   case 1:search();break;
      case 2:search_grade();break;
   default :break;
  }
  
	  
} 
void modify() /*�޸ĺ���*/
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
 int m=load(); /*�����ļ��ڵ���Ϣ*/
printf("1.ְ������޸�     2.ְ�������޸�\n");
printf("�������޸ķ�ʽ:");scanf("%d",&ch);getchar();
if(ch<1||ch>2)
{
	printf("�������,������...\n");
modify();
}
switch(ch)
{
case 1:printf("������Ҫ�޸ĵ�ְ���ı��:");  scanf("%d",&num);break;

case 2:printf("������Ҫ�޸ĵ�ְ��������:");
 scanf("%s",name);break;
}
 for(flag=1,i=0;flag&&i<m;i++)
 {
  if(strcmp(em[i].name,name)==0||num==em[i].num)
  {
   printf("\n���ҵ����ˣ�ԭʼ��¼Ϊ��\n");
 printf("ְ�����:%d\n",em[i].num);
	printf("����:%s\n",em[i].name);
printf("����:%d\n",em[i].age);
printf("����:%d\n",em[i].worktime);
printf("�Ա�:%c\n",em[i].sex);
printf("����״��:%c\n",em[i].marrige);
	 printf("�绰:%s\n",em[i].tel);
	 printf("�ȼ�:%c\n",em[i].grade);
	printf("����:%d\n",em[i].wage);
	printf("�Ƿ���ְ:%c\n",em[i].tired);
	 printf("��ַ:%s\n",em[i].addr);
            printf("\nȷʵҪ�޸Ĵ�����Ϣ�밴1,���޸��밴0\n");
   scanf("%d",&n);
   system("cls");
            if(n==1)
   {
               printf("\n��Ҫ�����޸ĵ�ѡ��\n  1.���� 2.�Ա� 3.���� 4.���� 5.���� 6.סַ 7.�绰 8.�ȼ� 9.����״�� 10.�Ƿ���ְ \n");  
               printf("�����������޸ĵ�ѡ��:\n");  
               scanf("%d",&c);  
               if(c>10||c<1)  
         printf("\nѡ�����������ѡ��!\n");  
   }
         flag=0;
  }
        
 }
    if(flag==1)
 printf("\n�Բ���û���ҵ�������!\n");  
    do  
 {
  switch(c)     
  {  
           case 1:printf("������Ϊ: ");
                  scanf("%s",name);
                  strcpy(em[i].name,name); 
                  break;  
           case 2:printf("�Ա��Ϊ: ");
                  getchar();
                  scanf("%c",&sex);
                  em[i].sex=sex;
                  break;  
           case 3:printf("�����Ϊ: ");
                  scanf("%d",&age);
                  em[i].age=age;
                  break;  
           case 4:printf("�����Ϊ: ");
                  scanf("%d",worktime);
                  em[i].worktime=worktime; 
                  break;  
           case 5:printf("���ʸ�Ϊ: ");
                  scanf("%d",wage);
                  break;  
           case 6:printf("סַ��Ϊ: ");
                  scanf("%s",addr);
                  strcpy(em[i].addr,addr);
                  break;  
           case 7:printf("�绰��Ϊ: ");
                  scanf("%s",tel);
                  strcpy(em[i].tel,tel); 
                  break;  
		   case 8:printf("�ȼ���Ϊ:");
			      scanf("%c",grade);
			      em[i].grade=grade;
			      break;
		   case 9:printf("����״����Ϊ(y/n):");
			      scanf("%c",marrige);
			      em[i].marrige=marrige;
		   case 10:printf("�Ƿ���ְ��Ϊ(y/n):");
			       scanf("%c",tired);
			       em[i].tired=tired;
		  
  } 
        printf("\n");
  printf("\n�Ƿ�ȷ�����޸ĵ���Ϣy/n?\n"); 
        scanf("%d",&b);
   
 }  
   while(b=='n');
   printf("\n����޸ĺ������ְ����Ϣ:\n");
   printf("\n");
   save(m);
   display();
   printf("\n�Ƿ�����޸�?y/n\n");
   scanf("%d",&t);getchar();
   switch(t)
 {
    case 'y':modify();break;
    case 'n':break;
    default :break;
 }
  
}
