import pandas as pd
import mysql.connector
from mysql.connector import Error

db_config = {
    "host": "localhost",
    "port": 3306,
    "user": "root",
    "password": "admin",
    "database": "deltadrive",
}
csv_file = "delta-drive.csv"
df = pd.read_csv(csv_file)

try:
    connection = mysql.connector.connect(**db_config)

    if connection.is_connected():
        cursor = connection.cursor()

        # Define the table schema
        create_table_query = """
        CREATE TABLE IF NOT EXISTS vehicle (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            brand VARCHAR(255),
            first_name VARCHAR(255),
            last_name VARCHAR(255),
            current_position POINT NOT NULL ,
            start_price DOUBLE,
            price_per_km DOUBLE,
            state INT 
        )
        """
        cursor.execute(create_table_query)

        connection.commit()
        i  = 0
        for _, row in df.iterrows():
            i+=1
            if i >= 20: break
            brand = row["brand"]
            firstName = row["firstName"]
            lastName = row["lastName"]
            latitude = row["latitude"]
            longitude = row["longitude"]
            start_price = float(row["startPrice"].replace("EUR", ""))
            price_per_km = float(row["pricePerKM"].replace("EUR", ""))

            insert_query = (
                "INSERT INTO vehicle (brand, first_name, last_name, current_position, start_price, price_per_km, state) "
                "VALUES (%s, %s, %s, ST_GeomFromText('POINT(%s %s)'), %s, %s, %s)"
            )
            data = (brand, firstName, lastName, latitude, longitude, start_price, price_per_km,0)

            cursor.execute(insert_query, data)
            connection.commit()

        cursor.close()

except Error as e:
    print("Error:", e)

finally:
    if connection.is_connected():
        connection.close()

print("Data imported into MySQL.")
