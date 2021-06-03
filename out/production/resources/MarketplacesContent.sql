insert into client (name, address, login, password) VALUES 
('Dmitrii Askarov', 'Moscow, Sovietskaya st, 31', 'diman01', 'dsa@fsd221'),
('Nick Tompson', 'New York, Seven Ave, 1', 'tompNick', 'newyourker12'),
('Alex Borodin', 'San Diego, Funny st, 12', 'kingofworld', 'samseberegiser'),
('Ilya Astapov', 'Dreamcity, World st, 232', 'iluha007', '21dd12'),
('Demix Cool', 'Saratov, Shevchenko st, 23', 'kirillOpa', 'stranniiLogin'),
('Luk Skyfall', 'Minsk, Radjesh st, 94A', 'IamYourFather', '01starwars01'),
('Colman Freeman', 'San Francisco, Allcity st, 2', 'colman', 'coolboy666'),
('Some Cent', 'Dnepr, Koroleva st, 17', 'korol', 'qwe123')

insert into product (user_id, name, description) values
(5, 'Notebook ASUS', 'good almost new laptop'),
(3, 'TV Samsung', 'old tv but it works'),
(1, 'Traumatic pistol', 'pistol with 3 years of use'),
(6, 'broken glass', 'a glass from which I drank only coffee'),
(3, 'TV Sony', 'new tv, never used'),
(7, 'Sportcar GTR', 'after crash test')

insert into lot (product_id, start_price, stop_date) values
(1, 37000.12, '10.11.2033'),
(2, 112000.00, '22.08.2021'),
(3, 6500.00, '18.05.2021'),
(4, 320.50, '05.03.2023'),
(5, 75000.00, '12.07.2021'),
(6, 3200500.00, '11.03.2022')
