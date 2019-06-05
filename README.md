# Hibernate-Mysql-EmpDept
Simple app en java usando hibernate y mysql para las tablas emp y dept

Posibilidad de:
* - Añadir empleados y departamentos
* - Borrar empleados y departamentos
* - Bscar por empno y deptno
* - Listar todos los empleados y departamentos
* - Control básico de errores al borrar, añadir, buscar un empleado/departamento no existente

## Creacion de las tablas: 
```
  create table dept (

    deptno decimal(2,0),

    dname  varchar(14),

    loc    varchar(13),

    constraint PK_DEPT primary key (deptno)

  );



  create table emp (

    empno    decimal(4,0),

    ename    varchar(10),

    job      varchar(9),

    mgr      decimal(4,0),

    hiredate date,

    sal      decimal(7,2),

    comm     decimal(7,2),

    deptno   decimal(2,0),

    constraint PK_EMP primary key (empno),

    constraint FK_EMP_DEPT foreign key (deptno) references dept (deptno),

    constraint FK_EMP_EMP foreign key (mgr) references emp (empno)

  );



  insert into dept values (10, 'ACCOUNTING', 'NEW YORK');

  insert into dept values (20, 'RESEARCH', 'DALLAS');

  insert into dept values (30, 'SALES', 'CHICAGO');

  insert into dept values (40, 'OPERATIONS', 'BOSTON');




  INSERT INTO emp VALUES

  (7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10),

  (7698,'BLAKE','MANAGER',7839,'1981-5-1',2850,NULL,30),

  (7782,'CLARK','MANAGER',7839,'1981-6-9',2450,NULL,10),

  (7566,'JONES','MANAGER',7839,'1981-4-2',2975,NULL,20),

  (7499,'ALLEN','SALESMAN',7698,'1981-2-20',1600,300,30),

  (7900,'JAMES','CLERK',7698,'1981-12-3',950,NULL,30),

  (7654,'MARTIN','SALESMAN',7698,'1981-9-28',1250,1400,30),

  (7844,'TURNER','SALESMAN',7698,'1981-9-8',1500,0,30),

  (7521,'WARD','SALESMAN',7698,'1981-2-22',1250,500,30),

  (7934,'MILLER','CLERK',7782,'1982-1-23',1300,NULL,10),

  (7788,'SCOTT','ANALYST',7566,'1987-7-13',3000,NULL,20),

  (7902,'FORD','ANALYST',7566,'1981-12-3',3000,NULL,20),

  (7876,'ADAMS','CLERK',7788,'1987-7-13',1100,NULL,20),

  (7369,'SMITH','CLERK',7902,'1980-12-17',800,NULL,20)
  ;
```
