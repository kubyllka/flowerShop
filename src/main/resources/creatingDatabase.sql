
CREATE TABLE flowers(
    flowerID int IDENTITY(1,1) PRIMARY KEY,
    flowerTypeID int NOT NULL,
    flowerColour nvarchar(70) NOT NULL,
    flowerLength FLOAT NOT NULL,
    flowerPrice FLOAT NOT NULL,
    fDateDelivery datetime NOT NULL default (getdate())
)

CREATE TABLE flowersType(
    ftypeID int PRIMARY KEY,
    nameFType nvarchar(70) NOT NULL,
)

--------Connection between flowerType and flowers.
----Primary key - ftypeID in flowersType
----Foreign key - flowerTypeID in flowers
ALTER TABLE flowers ADD CONSTRAINT flowers_and_types FOREIGN KEY (flowerTypeID)
    REFERENCES flowersType (ftypeID);


CREATE TABLE accessories
(
    accessoryID int IDENTITY(1,1) PRIMARY KEY,
    accessoryTypeID int NOT NULL,
    accessoryColour nvarchar(70) NOT NULL,
    accessoryPrice FLOAT NOT NULL,
    accessoryInfo nvarchar(250) NOT NULL
)

CREATE TABLE accessoriesType
(
    aTypeID int PRIMARY KEY ,
    nameAType nvarchar(70) NOT NULL,
)


--------Connection between accessoryType and accessories.
----Primary key - aTypeID in flowersType
----Foreign key - accessoryTypeID in flowers
ALTER TABLE accessories ADD CONSTRAINT accessories_and_types FOREIGN KEY (accessoryTypeID)
    REFERENCES accessoriesType (aTypeID);
