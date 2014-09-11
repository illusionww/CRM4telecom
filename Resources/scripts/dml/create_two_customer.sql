
  
MERGE INTO Customer C
Using (select 1 as customer_id from dual d)
ON ( C.customer_id = (select 1 as customer_id from dual d) )
WHEN MATCHED THEN 
    Update set  C.first_name = 'Ivan',
                C.last_name = 'Ivanov',
                C.email = 'ivanovivan@gmail.com',
                C.phone_number = '23356542143',
                C.balance = 2451,
                C.STREET = 'Lenina',
                C.building = 21,
                C.flat = 5,
                C.status = 'in proccess',
                C.Status_update_date = '25/10/2016 1:43:12'
                where C.customer_id < 2
    
WHEN NOT MATCHED THEN 
    Insert (C.customer_id,C.first_name,C.last_name,C.email,C.phone_number,C.balance,C.street,c.building,c.flat,c.status,C.Status_update_date)  
    Values (1,'Ivan','Ivanov','ivanovivan@gmail.com','23356542143' ,2451,'Lenina',21,5,'in process','25/10/2016 1:43:12');

MERGE INTO Customer C
Using (select 1 as customer_id from dual d)
ON ( C.customer_id = (select 2 as customer_id from dual d) )
WHEN MATCHED THEN 
    Update set  C.first_name = 'Petr',
                C.last_name = 'Petrov',
                C.email = 'petrov1232@mail.ru',
                C.phone_number = '24356524545',
                C.balance = 5000,
                C.STREET = 'Jukova',
                C.building = 1,
                C.flat = 24,
                C.status = 'in proccess',
                C.Status_update_date = '25/10/2016 1:43:12'
                
                where C.customer_id < 2
    
WHEN NOT MATCHED THEN 
    Insert (C.customer_id,C.first_name,C.last_name,C.email,C.phone_number,C.balance,C.street,c.building,c.flat,c.status,c.status_update_date)  
    Values (2,'Petr','Petrov','petrov1232@mail.ru','24356524545' ,5000,'Jukova',1,24,'in process','25/10/2016 1:43:12');

delete from Customer where customer_id> 2;

MERGE INTO balance_history b
USING ( select 1 from dual)
ON  ( B.CUSTOMER_ID = 1)
WHEN MATCHED THEN
    UPDATE SET
                B.BALANCE_DATE = '8/3/2010 23:11:45',
                B.AMOUNT = 322
WHEN NOT MATCHED THEN
    INSERT (B.CUSTOMER_ID,B.BALANCE_DATE,B.AMOUNT) values (1,'8/3/2010 23:11:45',322);
    
MERGE INTO balance_history b
USING ( select 1 from dual)
ON  ( B.CUSTOMER_ID = 2)
WHEN MATCHED THEN
    UPDATE SET
                B.BALANCE_DATE = '8/5/2015 23:11:45',
                B.AMOUNT = 21123
WHEN NOT MATCHED THEN
    INSERT (B.CUSTOMER_ID,B.BALANCE_DATE,B.AMOUNT) values (2,'8/5/2015 23:11:45',21123);
MERGE INTO static_ip s
USING (select 1 from dual)
ON ( S.IP = '10.0.0.1')
WHEN MATCHED THEN
    UPDATE SET
                S.CUSTOMER_ID = 1,
                S.STATUS = 'static',
                S.STATUS_COMMENT = 'all time'
WHEN NOT MATCHED THEN
    INSERT (S.IP,S.CUSTOMER_ID,S.STATUS,S.STATUS_COMMENT) values ('10.0.0.1',1,'static','all time'); 

MERGE INTO static_ip s
USING (select 1 from dual)
ON ( S.IP = '10.0.0.2')
WHEN MATCHED THEN
    UPDATE SET
                S.CUSTOMER_ID = 2,
                S.STATUS = 'static',
                S.STATUS_COMMENT = 'all time'
WHEN NOT MATCHED THEN
    INSERT (S.IP,S.CUSTOMER_ID,S.STATUS,S.STATUS_COMMENT) values ('10.0.0.2',2,'static','all time');
    
delete from static_ip where ip !='10.0.0.1' and ip != '10.0.0.2';

MERGE INTO static_ip_history s
USING (select 1 from dual)
ON ( s.ip = '10.0.0.1')
WHEN MATCHED THEN
    UPDATE SET
                S.CUSTOMER_ID =1,
                S.IP_COMMENT ='static',
                S.START_DATE = '11/7/2005 12:14:54',
                S.END_DATE = '11/2/2020 21:11:45'
WHEN NOT MATCHED THEN 
    insert (s.ip,S.CUSTOMER_ID,S.IP_COMMENT,S.START_DATE,S.END_DATE) values ( '10.0.0.1',1,'static','11/7/2005 12:14:54','11/2/2020 21:11:45');
                              
MERGE INTO static_ip_history s
USING (select 1 from dual)
ON ( s.ip = '10.0.0.2')
WHEN MATCHED THEN
    UPDATE SET
                S.CUSTOMER_ID =2,
                S.IP_COMMENT ='static',
                S.START_DATE = '12/8/2006 12:14:54',
                S.END_DATE = '12/3/2021 21:11:45'
WHEN NOT MATCHED THEN 
    insert (s.ip,S.CUSTOMER_ID,S.IP_COMMENT,S.START_DATE,S.END_DATE) values ( '10.0.0.2',2,'static','12/8/2006 12:14:54','12/3/2021 21:11:45');
   
delete from static_ip_history where ip !='10.0.0.1' and ip != '10.0.0.2';   

MERGE INTO phone_number p
USING (select 1 from dual ) 
ON ( p.phone_number = (select '915-12-45-76' from dual))
WHEN MATCHED THEN 
    UPDATE SET 
               P.STATUS_COMMENT = 'active customer',
               P.STATUS = 'active',
               p.customer_id = 1
WHEN NOT MATCHED THEN
    INSERT (P.PHONE_NUMBER,P.STATUS,P.CUSTOMER_ID,P.status_comment) values ('915-12-45-76','active',1,'active customer');              
     
MERGE INTO phone_number p
USING (select 1 from dual ) 
ON ( p.phone_number = (select '923-22-55-76' from dual))
WHEN MATCHED THEN 
    UPDATE SET 
               P.STATUS = 'active',
               P.STATUS_COMMENT = 'active',
               p.customer_id = 2
WHEN NOT MATCHED THEN
    INSERT (P.PHONE_NUMBER,P.STATUS,P.CUSTOMER_ID,P.STATUS_COMMENT) values ('923-22-55-76','active',2, 'active');              
    
delete from phone_number where phone_number != '923-22-55-76'  and phone_number !='915-12-45-76';

MERGE INTO phone_numbers_history p
USING (select 1 from dual ) 
ON ( p.phone_number = (select '915-12-45-76' from dual))
WHEN MATCHED THEN 
    UPDATE SET 
               P.history_COMMENT = 'active customer',
               P.start_date = '24/11/1999 12:43:13',
               p.customer_id = 1
WHEN NOT MATCHED THEN
    INSERT (P.PHONE_NUMBER,P.history_comment,P.CUSTOMER_ID,P.start_date) values ('915-12-45-76','active customer',1,'24/11/1999 12:43:13');    

MERGE INTO phone_numbers_history p
USING (select 1 from dual ) 
ON ( p.phone_number = (select '923-22-55-76' from dual))
WHEN MATCHED THEN 
    UPDATE SET 
               P.history_COMMENT = 'active customer',
               P.start_date = '24/11/1999 12:43:13',
               p.customer_id = 2
WHEN NOT MATCHED THEN
    INSERT (P.PHONE_NUMBER,P.history_comment,P.CUSTOMER_ID,P.start_date) values ('923-22-55-76','active customer',2,'24/11/1999 12:43:13');    

delete from phone_numbers_history where phone_number != '923-22-55-76'  and phone_number !='915-12-45-76';
MERGE INTO MARKET m
USING (select 1 from dual)
ON ( M.MARKET_ID = (Select 1 from dual))
WHEN MATCHED THEN 
    UPDATE SET M.DESCRIPTION='bla bla',
               M.NAME = 'new market'
WHEN NOT MATCHED THEN          
      INSERT (M.MARKET_ID,M.NAME,M.DESCRIPTION) values (1,'bla bla','new market');

MERGE INTO MARKET m
USING (select 1 from dual)
ON ( M.MARKET_ID = (Select 2 from dual))
WHEN MATCHED THEN 
    UPDATE SET M.DESCRIPTION='aaa bbb',
               M.NAME = 'market'
WHEN NOT MATCHED THEN          
      INSERT (M.MARKET_ID,M.NAME,M.DESCRIPTION) values (2,'aaa bbb','market');

delete from market where market_id > 2;               
    
MERGE INTO MARKETS_CUSTOMERS c
Using (select 1 from dual)
ON (market_id = (select 1 from dual))
WHEN MATCHED THEN  
    UPDATE SET C.CUSTOMER_ID = 1,
               C.START_DATE = '12/4/2011 2:1:1',
               C.END_DATE = '14/5/2013 4:5:1'
WHEN NOT MATCHED THEN
    INSERT (C.MARKET_ID,C.START_DATE,C.END_DATE,C.CUSTOMER_ID) values (1,'12/4/2011 2:1:1','14/5/2013 4:5:1',1);              
      
MERGE INTO MARKETS_CUSTOMERS c
Using (select 1 from dual)
ON (market_id = (select 2 from dual))
WHEN MATCHED THEN  
    UPDATE SET C.CUSTOMER_ID = 2,
               C.START_DATE = '13/4/2011 2:1:1',
               C.END_DATE = '14/5/2015 4:5:1'
WHEN NOT MATCHED THEN
    INSERT (C.MARKET_ID,C.START_DATE,C.END_DATE,C.CUSTOMER_ID) values (2,'13/4/2011 2:1:1','14/5/2015 4:5:1',2);              
delete from markets_customers where market_id > 2;  

MERGE INTO Product p
USING (select 11 from dual)
ON ( P.PRODUCT_ID = (select 1 from dual))
WHEN MATCHED THEN
    UPDATE SET P.BASELINE_PRICE = 1245,
               P.SALES_PERIOD_START = '1/2/2012 3:4:5',
               P.DESCRIPTION = 'new product',
               P.SALES_PERIOD_END = '1/2/2015 12:2:4',
               P.NAME ='product one'
WHEN NOT MATCHED THEN
    INSERT (P.PRODUCT_ID,P.BASELINE_PRICE, P.SALES_PERIOD_START,P.DESCRIPTION,P.SALES_PERIOD_END,P.NAME) values(1,1245,'1/2/2012 3:4:5','new product','1/2/2015 12:2:4','product one');
   
MERGE INTO Product p
USING (select 11 from dual)
ON ( P.PRODUCT_ID = (select 2 from dual))
WHEN MATCHED THEN
    UPDATE SET P.BASELINE_PRICE = 55,
               P.SALES_PERIOD_START = '1/2/2012 3:4:5',
               P.DESCRIPTION = 'new product',
               P.NAME ='product two',
               P.SALES_PERIOD_END = '1/2/2019 2:2:2'
WHEN NOT MATCHED THEN
    INSERT (P.PRODUCT_ID,P.BASELINE_PRICE, P.SALES_PERIOD_START,P.DESCRIPTION,P.SALES_PERIOD_END,P.NAME) values(2,55,'1/2/2012 3:4:5','new product','1/2/2019 2:2:2','product two');
    
delete from product where product_id > 2;

MERGE INTO market_products m
USING (select 1 from dual)
ON ( M.MARKET_ID = (select 1 from dual))
WHEN MATCHED THEN 
    UPDATE SET M.PRICE = 111,
               M.PRODUCT_ID = 1,
               M.START_DATE = '1/2/2013 3:4:5',
               M.END_DATE  = '3/2/2016  9:8:7'
WHEN NOT MATCHED THEN 
    INSERT(M.MARKET_ID,M.PRODUCT_ID,M.PRICE,M.START_DATE,M.END_DATE) values (1,1,111,'1/2/2013 3:4:5','3/2/2016  9:8:7');

MERGE INTO market_products m
USING (select 1 from dual)
ON ( M.MARKET_ID = (select 2 from dual))
WHEN MATCHED THEN 
    UPDATE SET M.PRICE = 444,
               M.PRODUCT_ID = 2,
               M.START_DATE = '1/2/2013 3:4:5',
               M.END_DATE  = '3/2/2016  9:8:7'
WHEN NOT MATCHED THEN 
    INSERT(M.MARKET_ID,M.PRODUCT_ID,M.PRICE,M.START_DATE,M.END_DATE) values (2,2,444,'1/2/2013 3:4:5','3/2/2016  9:8:7');

delete from market_products where market_id > 2;    
    
MERGE INTO customer_products c
USING (select 1 from dual)
ON (C.CUSTOMER_ID = (select 1 from dual) )
WHEN MATCHED THEN 
    UPDATE SET C.PRICE = 111,
               C.PRODUCT_ID = 1,
               C.START_DATE = '3/4/2013 4:6:8',
               C.END_DATE = '4/5/2014 1:22:22'
WHEN NOT MATCHED THEN
    INSERT (C.customer_id,C.PRICE,C.PRODUCT_ID,C.START_DATE,C.END_DATE) values (1,111,1, '3/4/2013 4:6:8','4/5/2014 1:22:22');
    
MERGE INTO customer_products c
USING (select 1 from dual)
ON (C.CUSTOMER_ID = (select 2 from dual) )
WHEN MATCHED THEN 
    UPDATE SET C.PRICE = 55,
               C.PRODUCT_ID = 2,
               C.START_DATE = '3/4/2014 4:6:8',
               C.END_DATE = '4/5/2014 1:22:22'
WHEN NOT MATCHED THEN
    INSERT (C.customer_id,C.PRICE,C.PRODUCT_ID,C.START_DATE,C.END_DATE) values (2,55,2, '3/4/2014 4:6:8','4/5/2014 1:22:22');
 
 delete from customer_products where customer_id >2;   
               
MERGE INTO ORDERs o
USING (select 1 from dual)
ON ( o.order_id = (select 2 from dual))
WHEN MATCHED THEN
    UPDATE SET O.CUSTOMER_ID = 2,
               O.EMPLOYEE_ID =2,
               O.MANAGER_ID = 2,
               O.ORDER_DATE = '3/1/2014 2:1:1',
               O.ORDER_TYPE = 'simple',
               O.PRIORITY = 'high',
               O.PRODUCT_ID  = 2,
               O.STATUS = 'active',
               O.TECHNICAL_SUPPORT_FLAG = 'bbs',
               O.TYPE_COMMENT ='abbb'
WHEN NOT MATCHED THEN
    INSERT (O.ORDER_ID,O.CUSTOMER_ID,O.EMPLOYEE_ID,O.MANAGER_ID,O.ORDER_DATE,O.ORDER_TYPE,O.PRIORITY,O.PRODUCT_ID,O.STATUS,O.TECHNICAL_SUPPORT_FLAG,O.TYPE_COMMENT)
        values(2,2,2,2,'3/1/2014 2:1:1','simple','high',2,'active','bbs','abbb');      
      
MERGE INTO ORDERs o
USING (select 1 from dual)
ON ( o.order_id = (select 1 from dual))
WHEN MATCHED THEN
    UPDATE SET O.CUSTOMER_ID = 1,
               O.EMPLOYEE_ID =1,
               O.MANAGER_ID = 1,
               O.ORDER_DATE = '3/1/2014 2:1:1',
               O.ORDER_TYPE = 'simple',
               O.PRIORITY = 'low',
               O.PRODUCT_ID  = 1,
               O.STATUS = 'active',
               O.TECHNICAL_SUPPORT_FLAG = 'bs',
               O.TYPE_COMMENT ='bbb'
WHEN NOT MATCHED THEN
    INSERT (O.ORDER_ID,O.CUSTOMER_ID,O.EMPLOYEE_ID,O.MANAGER_ID,O.ORDER_DATE,O.ORDER_TYPE,O.PRIORITY,O.PRODUCT_ID,O.STATUS,O.TECHNICAL_SUPPORT_FLAG,O.TYPE_COMMENT)
        values(1,1,1,1,'3/1/2014 2:1:1','simple','low',1,'active','bs','bbb');      

delete from orders where order_id > 2;

MERGE INTO Employee e
USING (select 11 from dual)
ON (E.EMPLOYEE_ID  = (select 1 from dual) )
WHEN MATCHED THEN
    UPDATE SET E.FIRST_NAME ='Alex',
               E.LAST_NAME = 'Petrov',
               E.JOB_DESCRIPTION ='wk',
               E.SCHEDULE = '1/4/2014 2:1:1'
WHEN NOT MATCHED THEN 
    INSERT (E.EMPLOYEE_ID,E.FIRST_NAME,E.LAST_NAME,E.JOB_DESCRIPTION,E.SCHEDULE) values(1,'Alex','Petrov','wk','1/4/2014 2:1:1');

MERGE INTO Employee e
USING (select 11 from dual)
ON (E.EMPLOYEE_ID  = (select 2 from dual) )
WHEN MATCHED THEN
    UPDATE SET E.FIRST_NAME ='Gosha',
               E.LAST_NAME = 'Osipov',
               E.JOB_DESCRIPTION ='bg',
               E.SCHEDULE = '1/4/2012 2:1:1'
WHEN NOT MATCHED THEN 
    INSERT (E.EMPLOYEE_ID,E.FIRST_NAME,E.LAST_NAME,E.JOB_DESCRIPTION,E.SCHEDULE) values(2,'Gosha','Osipov','bg','1/4/2012 2:1:1');
            
delete from employee where employee_id > 2;

MERGE INTO users u
USING (select 1 from dual)
ON ( u.login = 'empl1')
WHEN MATCHED THEN 
    UPDATE SET U.password = 'empl1',
               U.type = 'worker',
               U.employee_id = 1
WHEN NOT MATCHED THEN 
    INSERT (u.login,u.password,u.type,u.employee_id)  values('empl1','empl1','worker',1);

MERGE INTO users u
USING (select 1 from dual)
ON ( u.login = 'empl2')
WHEN MATCHED THEN 
    UPDATE SET U.password = 'empl2',
               U.type = 'worker',
               U.employee_id = 2
WHEN NOT MATCHED THEN 
    INSERT (u.login,u.password,u.type,u.employee_id)  values('empl2','empl2','worker',2);  

delete from users where login !='empl1' and login !='empl2';    

MERGE into equipment e
USING (select 1 from dual)
ON  (E.EQUIPMENT_ID = (select 1 from dual))
WHEN MATCHED THEN 
    UPDATE SET E.CUSTOMER_ID =1,
               E.DESCRIPTION ='new equip',
               E.NAME = 'eq 1',
               E.SERIAL_NUMBER = 23,
               E.STATUS = 'active'
WHEN NOT MATCHED THEN
    insert(E.EQUIPMENT_ID,E.CUSTOMER_ID,E.DESCRIPTION,E.NAME,E.SERIAL_NUMBER,E.STATUS) values(1,1,'new equip','eq 1',23,'active');
                    
MERGE into equipment e
USING (select 1 from dual)
ON  (E.EQUIPMENT_ID = (select 2 from dual))
WHEN MATCHED THEN 
    UPDATE SET E.CUSTOMER_ID =2,
               E.DESCRIPTION ='old equip',
               E.NAME = 'eq 2',
               E.SERIAL_NUMBER = 233,
               E.STATUS = 'active'
WHEN NOT MATCHED THEN
    insert(E.EQUIPMENT_ID,E.CUSTOMER_ID,E.DESCRIPTION,E.NAME,E.SERIAL_NUMBER,E.STATUS) values(2,2,'old equip','eq 2',233,'active');

delete from equipment where equipment_id > 2;

MERGE  INTO equipment_history e
Using (select 1 from dual)
ON (E.EQUIPMENT_ID  = 1)
WHEN MATCHED THEN
    UPDATE SET E.CUSTOMER_ID =1,
               E.END_DATE = '2/2/2016 2:11:11',
               E.EQUIPMENT_COMMENT = 'some text',
               E.START_DATE = '2/4/2015 2:1:1',
               E.STATUS = 'active'
WHEN NOT MATCHED THEN
    INSERT ( E.EQUIPMENT_ID,E.CUSTOMER_ID,E.END_DATE,E.EQUIPMENT_COMMENT,E.START_DATE,E.STATUS) 
        values (1,1,'2/2/2016 2:11:11','some text', '2/4/2015 2:1:1','active');               

MERGE  INTO equipment_history e
Using (select 1 from dual)
ON (E.EQUIPMENT_ID  = 2)
WHEN MATCHED THEN
    UPDATE SET E.CUSTOMER_ID =2,
               E.END_DATE = '2/2/2026 2:11:11',
               E.EQUIPMENT_COMMENT = '111some text',
               E.START_DATE = '2/4/2015 2:1:1',
               E.STATUS = 'active'
WHEN NOT MATCHED THEN
    INSERT ( E.EQUIPMENT_ID,E.CUSTOMER_ID,E.END_DATE,E.EQUIPMENT_COMMENT,E.START_DATE,E.STATUS) 
        values (2,2,'2/2/2026 2:11:11','111some text', '2/4/2015 2:1:1','active');    
                   
delete equipment_history where equipment_id > 2;

MERGE INTO ORDER_processing o
USING(select 1 from dual)
ON ( O.ORDER_ID = (select 1 from dual))
WHEN MATCHED THEN 
    UPDATE SET O.DESCRIPTION = 'new order',
               O.EMPLOYEE_ID = 1,
               O.END_DATE= '4/5/2015 2:2:2',
               O.END_DATE_HARD = '5/5/2015 2:22:22',
               O.EQUIPMENT_ID = 1,
               O.START_DATE = '2/4/2015 2:1:1',
               O.STEP_NAME = 'active'
WHEN NOT MATCHED THEN
    INSERT (O.ORDER_ID,O.DESCRIPTION,O.EMPLOYEE_ID,O.END_DATE,O.END_DATE_HARD,O.EQUIPMENT_ID,O.START_DATE,O.STEP_NAME) 
        values(1,'new orderd',1,'4/5/2015 2:2:2' ,'5/5/2015 2:22:22',1,'2/4/2015 2:1:1','active'); 
        
MERGE INTO ORDER_processing o
USING(select 1 from dual)
ON ( O.ORDER_ID = (select 2 from dual))
WHEN MATCHED THEN 
    UPDATE SET O.DESCRIPTION = 'new order',
               O.EMPLOYEE_ID = 2,
               O.END_DATE= '4/5/2015 2:2:2',
               O.END_DATE_HARD = '5/5/2015 2:22:22',
               O.EQUIPMENT_ID = 2,
               O.START_DATE = '2/4/2015 2:1:1',
               O.STEP_NAME = 'active'
WHEN NOT MATCHED THEN
    INSERT (O.ORDER_ID,O.DESCRIPTION,O.EMPLOYEE_ID,O.END_DATE,O.END_DATE_HARD,O.EQUIPMENT_ID,O.START_DATE,O.STEP_NAME) 
        values(2,'new orderd',2,'4/5/2015 2:2:2' ,'5/5/2015 2:22:22',2,'2/4/2015 2:1:1','active');
     
delete from order_processing where order_id> 2;
