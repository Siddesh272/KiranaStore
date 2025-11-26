create table Customer(cId int PRIMARY KEY, cName varchar(200), cEmail varchar(200), cPassword varchar(200), cAddress varchar(200), cContact varchar(200), cIsActive boolean);

CREATE TABLE PRODUCT (
    pId INT PRIMARY KEY,
    pName VARCHAR(255) NOT NULL,
    pPrice DOUBLE NOT NULL,
    pQuantity INT NOT NULL,
    pIsReserved BOOLEAN,
    pCId INT, -- Corresponds to CustomerId in your setCustomerId(rs.getInt(6))
    pImage BLOB,
    FOREIGN KEY (pCId) REFERENCES CUSTOMER(cId)
);
create table Transactions(tId int PRIMARY KEY, tTotalAmount double, tNoOfItems int, tCId int, FOREIGN KEY(tCId) REFERENCES Customer(cId), tPId int, FOREIGN KEY(tPId) REFERENCES Product(pId));

create table Admin(aId int PRIMARY KEY, aPassword varchar(200), tCId int, FOREIGN KEY(tCId) REFERENCES Customer(cId), tPId int, FOREIGN KEY(tPId) REFERENCES Product(pId), aTId int, FOREIGN KEY(aTId) REFERENCES Transactions(tId));

insert into Admin(aId, aPassword) values(2798176, 'Simple@123');
DROP TABLE Admin;

DROP TABLE Transactions;

DROP TABLE PRODUCT;

DROP TABLE Customer;
select * from admin;
select * from customer
select * from product;



