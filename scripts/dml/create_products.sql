 MERGE INTO PRODUCT p
 USING (select 1 from dual)
 ON ( P.PRODUCT_ID = 12101)
 WHEN MATCHED THEN
    UPDATE SET p.name = 'IPTV-Standard',
               p.description = '���������� ������� �����, �������� 8 �������',
               p.sales_period_start = '25/12/2010 00:00:00',
               p.sales_period_end = NULL,
               p.baseline_price = 0
WHEN NOT MATCHED THEN 
    INSERT (p.product_id,P.NAME ,P.DESCRIPTION,P.SALES_PERIOD_START,P.sales_period_end,P.BASELINE_PRICE)
    values(12101,'IPTV-Standard','���������� ������� �����, �������� 8 �������','25/12/2010 00:00:00',NULL,0);

 MERGE INTO PRODUCT p
 USING (select 1 from dual)
 ON ( P.PRODUCT_ID = 12102)
 WHEN MATCHED THEN
    UPDATE SET p.name = 'IPTV-11Plus',
               p.description = '� �������� 8 ������� ����������� ��� 11',
               p.sales_period_start = '14/03/2011 00:00:00',
               p.sales_period_end = NULL,
               p.baseline_price = 20
WHEN NOT MATCHED THEN 
    INSERT (p.product_id,P.NAME ,P.DESCRIPTION,P.SALES_PERIOD_START,P.sales_period_end,P.BASELINE_PRICE)
    values(12102,'IPTV-11Plus','� �������� 8 ������� ����������� ��� 11','14/03/2011 00:00:00',NULL,20);
      
MERGE INTO PRODUCT p
 USING (select 1 from dual)
 ON ( P.PRODUCT_ID = 12103)
 WHEN MATCHED THEN
    UPDATE SET  p.name = 'IPTV-Sport',
                p.description = '8 �������� ������� + 6 ����������',
                p.sales_period_start = '04/06/2012 00:00:00',
                p.sales_period_end = NULL,
                p.baseline_price = 100
WHEN NOT MATCHED THEN 
    INSERT (p.product_id,P.NAME ,P.DESCRIPTION,P.SALES_PERIOD_START,P.sales_period_end,P.BASELINE_PRICE)
    values(12103,'IPTV-Sport','8 �������� ������� + 6 ����������','04/06/2012 00:00:00',NULL,100);
    
MERGE INTO PRODUCT p
 USING (select 1 from dual)
 ON ( P.PRODUCT_ID = 12104)
 WHEN MATCHED THEN
    UPDATE SET  p.name = 'IPoE-Unlim100',
                p.description = 'IP over Ethernet, �������� �� 100 ����/�',
                p.sales_period_start = '08/08/2010 00:00:00',
                p.sales_period_end = NULL,
                p.baseline_price = 1000
WHEN NOT MATCHED THEN 
    INSERT (p.product_id,P.NAME ,P.DESCRIPTION,P.SALES_PERIOD_START,P.sales_period_end,P.BASELINE_PRICE)
    values(12104,'IPoE-Unlim100','IP over Ethernet, �������� �� 100 ����/�','08/08/2010 00:00:00',NULL,1000);
    
MERGE INTO PRODUCT p
 USING (select 1 from dual)
 ON ( P.PRODUCT_ID = 12105)
 WHEN MATCHED THEN
    UPDATE SET  p.name = 'IPoE-Unlim60',
                p.description = 'IP over Ethernet, �������� �� 60 ����/�',
                p.sales_period_start = '11/04/2010 00:00:00',
                p.sales_period_end = NULL,
                p.baseline_price = 600
WHEN NOT MATCHED THEN 
    INSERT (p.product_id,P.NAME ,P.DESCRIPTION,P.SALES_PERIOD_START,P.sales_period_end,P.BASELINE_PRICE)
    values(12105,'IPoE-Unlim60','IP over Ethernet, �������� �� 60 ����/�','11/04/2010 00:00:00',NULL,600);    
               
MERGE INTO PRODUCT p
 USING (select 1 from dual)
 ON ( P.PRODUCT_ID = 12106)
 WHEN MATCHED THEN
    UPDATE SET  p.name = 'IPoE-Basic80',
                p.description = 'IP over Ethernet, �������� �� 80 ����/�, ����� 300 �� - ����������� ��������',
                p.sales_period_start = '20/05/2010 00:00:00',
                p.sales_period_end = NULL,
                p.baseline_price = 400
WHEN NOT MATCHED THEN 
    INSERT (p.product_id,P.NAME ,P.DESCRIPTION,P.SALES_PERIOD_START,P.sales_period_end,P.BASELINE_PRICE)
    values(12106,'IPoE-Basic80','IP over Ethernet, �������� �� 80 ����/�, ����� 300 �� - ����������� ��������','20/05/2010 00:00:00',NULL,400);    