CREATE DATABASE cashin;
USE cashin;
CREATE TABLE barang(
	id_produk INT PRIMARY KEY NOT NULL,
    nama_barang VARCHAR(100),
    kategori VARCHAR(50),
    harga INT,
    stok INT
);

INSERT INTO barang VALUES(100, "Keripik Kentang", "Cemilan", 15000, 20);
INSERT INTO barang VALUES(101, "Pasta Gigi", "Toiletries", 25000, 10);
INSERT INTO barang VALUES(102, "Pulpen", "Alat Tulis", 5000, 30);

CREATE TABLE user(
	id_user INT PRIMARY KEY NOT NULL,
    username VARCHAR(20),
    password VARCHAR(20),
    tipe INT
);

INSERT INTO user VALUES(1, "admin", "admin", 1);
INSERT INTO user VALUES(2, "supervisor", "supervisor", 2);
INSERT INTO user VALUES(3, "kasir", "kasir", 3);

CREATE TABLE transaksi(
	id_transaksi INT PRIMARY KEY NOT NULL,
    tanggal DATE,
    total INT
);

INSERT INTO transaksi VALUES(1000, "2022-03-01", 15000);
INSERT INTO transaksi VALUES(1001, "2022-10-03", 25000);
INSERT INTO transaksi VALUES(1002, "2022-04-07", 5000);

CREATE TABLE transaksi_detail(
	id_detailtrx INT NOT NULL,
    id_transaksi INT,
    id_barang INT,
    nama VARCHAR(100),
    harga INT,
    kuantitas INT,
    subtotal INT,
    PRIMARY KEY (id_detailtrx),
    CONSTRAINT fk_trx_id FOREIGN KEY (id_transaksi) REFERENCES transaksi(id_transaksi),
    CONSTRAINT fk_barang_id FOREIGN KEY (id_barang) REFERENCES barang(id_produk)
);

INSERT INTO transaksi_detail VALUES(10001, 1000, 100, "Keripik Kentang", 15000, 1, 15000);
INSERT INTO transaksi_detail VALUES(10011, 1001, 101, "Pasta Gigi", 25000, 1, 25000);
INSERT INTO transaksi_detail VALUES(10021, 1002, 102, "Pulpen", 5000, 1, 5000);
