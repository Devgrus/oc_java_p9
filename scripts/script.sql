CREATE TABLE patient (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  family VARCHAR(255) NOT NULL,
  given VARCHAR(255) NOT NULL,
  dob DATE,
  sex VARCHAR(50) NOT NULL,
  address VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,

  PRIMARY KEY (id)
);

INSERT INTO patient(id, family, given, dob, sex, address, phone)
VALUES
  (1, 'ferguson', 'lucas', '1968-06-22', 'M', '2 Warren Street', '387-866-1399'),
  (2, 'rees', 'pippa', '1952-09-27', 'F', '745 West Valley Farms Drive', '628-423-0993'),
  (3, 'arnold', 'edward', '1952-11-11', 'M', '599 East Garden Ave', '123-727-2779'),
  (4, 'sharp', 'anthony', '1946-11-26', 'M', '894 Hall Street', '451-761-8383'),
  (5, 'ince', 'wendy', '1958-06-29', 'F', '4 Southampton Road', '802-911-9975'),
  (6, 'ross', 'tracey', '1949-12-07', 'F', '40 Sulphur Springs Dr', '131-396-5049'),
  (7, 'wilson', 'claire', '1966-12-31', 'F', '12 Cobblestone St', '300-452-1091'),
  (8, 'buckland', 'max', '1945-06-24', 'M', '193 Vale St', '833-534-0864'),
  (9, 'clark', 'natalie', '1964-06-18', 'F', '12 Beechwood Road', '241-467-9197'),
  (10, 'bailey', 'piers', '1959-06-28', 'M', '1202 Bumble Dr', '747-815-0557');