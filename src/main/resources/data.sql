delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type)
		values ('FLTO', 'pszenna', 'WRAP');
insert into Ingredient (id, name, type)
		values ('COTO', 'kukurydziana', 'WRAP');
insert into Ingredient (id, name, type)
		values ('GRBF', 'mielona wolowina', 'PROTEIN');
insert into Ingredient (id, name, type)
		values ('CARN', 'kawalki miesa', 'PROTEIN');
insert into Ingredient (id, name, type)
		values ('TMTO', 'pomidory pokrojone w kostke', 'VEGGIES');
insert into Ingredient (id, name, type)
		values ('LETC', 'salata', 'VEGGIES');
insert into Ingredient (id, name, type)
		values ('CHED', 'cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
		values ('JACK', 'Monterey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
		values ('SLSA', 'pikantny sos pomidorowy', 'SAUCE');
insert into Ingredient (id, name, type)
		values ('SRCR', 'smietana', 'SAUCE');