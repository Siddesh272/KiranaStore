create table Customer(cId int PRIMARY KEY, cName varchar(200), cEmail varchar(200), cPassword varchar(200), cAddress varchar(200), cContact varchar(200), cIsActive boolean);

create table Product(pId int PRIMARY KEY, pName varchar(200), pPrice double, pQuantity int, pIsReserved boolean, pCId int, FOREIGN KEY(pCId) REFERENCES Customer(cId));

create table Transactions(tId int PRIMARY KEY, tTotalAmount double, tNoOfItems int, tCId int, FOREIGN KEY(tCId) REFERENCES Customer(cId), tPId int, FOREIGN KEY(tPId) REFERENCES Product(pId));

create table Admin(aId int PRIMARY KEY, aPassword varchar(200), tCId int, FOREIGN KEY(tCId) REFERENCES Customer(cId), tPId int, FOREIGN KEY(tPId) REFERENCES Product(pId), aTId int, FOREIGN KEY(aTId) REFERENCES Transactions(tId));

select * from admin;
select * from customer