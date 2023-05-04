# ProductDemoProject
SQL database can must be added (mysql)
It can be imported from SQL file available with the prject.

## Proceudre
The fillowing procedure is created to be used in searching for item in the database (prepared statement)

<use classicmodels;
>
<DELIMITER $$
>
<CREATE PROCEDURE product_search (IN productID varchar (100))
>
<SELECT productCode, productName, productLine, productDescription from products where productCode = (productID)>
>
>$$
>
