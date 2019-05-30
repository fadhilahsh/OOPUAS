CREATE TABLE users(
   id INT(5) AUTO_INCREMENT NOT NULL,
   username VARCHAR(30) UNIQUE NOT NULL,
   password VARCHAR(30),
   primary key (id)
);

CREATE TABLE fnb(
	idFnb INT(5) AUTO_INCREMENT NOT NULL,
	menu VARCHAR(30) UNIQUE NOT NULL,
	kategori VARCHAR(30) NOT NULL,
	harga INT(30),
	fotoMenu BLOB NOT NULL,
	primary key (idFnb)
);

CREATE TABLE flavour(
	idFlavour INT(5) AUTO_INCREMENT NOT NULL,
	rasa VARCHAR(30) UNIQUE NOT NULL,
	PRIMARY KEY (idFlavour)
	); 

CREATE TABLE pesan(
	idPesan INT(5) AUTO_INCREMENT NOT NULL,
	menu VARCHAR(30) UNIQUE NOT NULL,
	oAmount INT(10),
	oQuantity INT(10),
	PRIMARY KEY (idPesan),
	FOREIGN KEY (menu) REFERENCES fnb(menu) 
);

CREATE TABLE bill(
	idBill INT(5) AUTO_INCREMENT NOT NULL,
	menuTotal INT(5),
	id INT(5),
	PRIMARY KEY (idBill),
	FOREIGN KEY (id) REFERENCES users(id) 
);

INSERT INTO fnb (menu, kategori, harga, fotoMenu) VALUES
('Makaroni Kriuk', 'Makanan', 5000, LOAD_FILE('C:/xampp/htdocs/proyek_oop/fotoMenu/MakaroniKriuk.jpg')),
('Mie Kriuk', 'Makanan', 3000, LOAD_FILE('C:/xampp/htdocs/proyek_oop/fotoMenu/MieKriuk.jpg')),
('Lidi Kriuk', 'Makanan', 2000, LOAD_FILE('C:/xampp/htdocs/proyek_oop/fotoMenu/LidiKriuk.jpg')),
('Ice Float', 'Minuman', 7000, LOAD_FILE('C:/xampp/htdocs/proyek_oop/fotoMenu/IceFloat.jpg')),
('Milkshake', 'Minuman', 5000, LOAD_FILE('C:/xampp/htdocs/proyek_oop/fotoMenu/Milkshake.jpg')),
('Bubble Tea', 'Minuman', 10000, LOAD_FILE('C:/xampp/htdocs/proyek_oop/fotoMenu/BubbleTea.jpg'));

INSERT INTO users (username, password) VALUES
('dhea', '123');
