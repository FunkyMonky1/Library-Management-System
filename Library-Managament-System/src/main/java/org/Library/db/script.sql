
CREATE TABLE Books(
    idBooks INT AUTO_INCREMENT PRIMARY KEY,
    titel VARCHAR(45) NOT NULL UNIQUE,
    autor VARCHAR(45) NOT NULL,
    erscheinungsjahr YEAR NOT NULL,
    available TINYINT(1) DEFAULT 1
);

CREATE TABLE Kunde(
    idkunde INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    adresse VARCHAR(45) NOT NULL
);

INSERT INTO Books (titel, autor, erscheinungsjahr, available) VALUES
('Der Herr der Ringe', 'J.R.R. Tolkien', 1954, 1),
('Harry Potter und der Stein der Weisen', 'J.K. Rowling', 1997, 1),
('1984', 'George Orwell', 1949, 1),
('Die Verwandlung', 'Franz Kafka', 1915, 1),
('Pride and Prejudice', 'Jane Austen', 1901, 1),
('Der Kleine Prinz', 'Antoine de Saint-Exupéry', 1943, 1),
('To Kill a Mockingbird', 'Harper Lee', 1960, 1),
('Moby Dick', 'Herman Melville', 1901, 1),
('Die Unendliche Geschichte', 'Michael Ende', 1979, 1),
('Der Alchemist', 'Paulo Coelho', 1988, 1);





    

    

    