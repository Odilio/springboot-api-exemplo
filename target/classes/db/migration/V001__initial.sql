
CREATE TABLE readjustments (
  id int NOT NULL AUTO_INCREMENT,
  minsalary float,
  maxsalary float,
  percent int,
  created_at TIMESTAMP,
  PRIMARY KEY (id)
) ;

INSERT INTO readjustments (id, minsalary, maxsalary, percent)
VALUES (1, 0.00 , 400.00, 15);

INSERT INTO readjustments (id, minsalary, maxsalary, percent)
VALUES (2, 400.01 , 800.00, 12);

INSERT INTO readjustments (id, minsalary, maxsalary, percent)
VALUES (3, 800.01 , 1200.00, 10);

INSERT INTO readjustments (id, minsalary, maxsalary, percent)
VALUES (4, 1200.01 , 2000.00, 7);

INSERT INTO readjustments (id, minsalary, maxsalary, percent)
VALUES (5, 2000.01 , 1000000.00, 4);