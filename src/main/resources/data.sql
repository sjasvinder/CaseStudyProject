/*jasvin123*/
Insert into airline.users(name,email,password,phone_number,role) Select 'Jasvin','jasvin@jasvin.com','$2a$10$vGUkVsh6hIUoMkS4OTF9Y.QyTEv2UqFsoq3Q6OppCnyKOExgMlX82','1112223333','ADMIN'
where not exists (Select * from airline.users where email = 'jasvin@jasvin.com');