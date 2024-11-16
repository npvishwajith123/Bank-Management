insert into Roles(roleName, description) values('ADMIN','System Administrator');
insert into Roles(roleName, description) values('EMPLOYEE','Bank Employees');
insert into Roles(roleName, description) values('ROLE_MANAGER','Manages Branch Operations');
insert into Roles(roleName, description) values('ROLE_CUSTOMER','Access to customer services');

insert into Users(username, password, roleId) values('np','test123',2);
insert into Users(username, password, roleId) values('np1','test123',3);
insert into Users(username, password, roleId) values('np2','test123',1);
insert into Users(username, password, roleId) values('np3','test123',4);

insert into Manager(name, branch, contactNumber) values('Jane M','Fed', '124-251-1252');
insert into Manager(name, branch, contactNumber) values('Michael S','Ted', '255-232-1252');

insert into Customer(name, contactNumber, address, managerId) values('AI Johnson','214-242-1221', '101 Oak St', 1);
insert into Customer(name, contactNumber, address, managerId) values('Sam Brown','252-211-1241', '102 Oak St', 1);
insert into Customer(name, contactNumber, address, managerId) values('Kelly Green','111-442-6767', '103 Oak St', 2);

insert into Account(accountNumber, balance, customerId) values(123456,1000.0,1);
insert into Account(accountNumber, balance, customerId) values(235235,5500.0,2);
insert into Account(accountNumber, balance, customerId) values(351123,750.0,3);

insert into CustomerRecords(transactionType, amount, transactionDate, accountId, managerId) values('Deposit',500.0,'2024-05-10',1,1);
insert into CustomerRecords(transactionType, amount, transactionDate, accountId, managerId) values('Withdrawal',300.0,'2024-07-10',1,1);
insert into CustomerRecords(transactionType, amount, transactionDate, accountId, managerId) values('Deposit',500.0,'2024-10-10',2,1);

insert into EmployeeRecords(name, role, managerId, contactNumber) values('John Cash','Cashier',1,'550-503-2321');
insert into EmployeeRecords(name, role, managerId, contactNumber) values('Alice Teller','Cashier',2,'123-241-2254');

insert into BankAdmin(name, email, phone) values('Bob A', 'admin@bob.com','2412-224-221');
insert into BankAdmin(name, email, phone) values('Mike S', 'admin@mike.com','123-425-1231');

