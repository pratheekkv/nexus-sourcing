import csv

phone_models = [
    "Galaxy S1", "Galaxy S2", "Galaxy S3", "Galaxy S4", "Galaxy S5",
    "Galaxy S6", "Galaxy S7", "Galaxy S8", "Galaxy S9", "Galaxy S10",
    "Galaxy S20", "Galaxy S21", "Galaxy S22", "Galaxy S23", "Galaxy S24",
    "Galaxy S25", "Galaxy S26", "Galaxy S27", "Galaxy S28", "Galaxy S29",
    "Galaxy S30", "Pixel 1", "Pixel 2", "Pixel 3", "Pixel 4", "Pixel 5",
    "Pixel 6", "Pixel 7", "Pixel 8", "Pixel 9", "Pixel 10", "Pixel 11",
    "Pixel 12", "Pixel 13", "Pixel 14", "Pixel 15", "Pixel 16", "Pixel 17",
    "Pixel 18", "Pixel 19", "Pixel 20", "Xperia 1", "Xperia 2", "Xperia 3",
    "Xperia 4", "Xperia 5", "Xperia 6", "Xperia 7", "Xperia 8", "Xperia 9",
    "Xperia 10", "Xperia 11", "Xperia 12", "Xperia 13", "Xperia 14",
    "Xperia 15", "Xperia 16", "Xperia 17", "Xperia 18", "Xperia 19",
    "Xperia 20", "Redmi Note 1", "Redmi Note 2", "Redmi Note 3",
    "Redmi Note 4", "Redmi Note 5", "Redmi Note 6", "Redmi Note 7",
    "Redmi Note 8", "Redmi Note 9", "Redmi Note 10", "Redmi Note 11",
    "Redmi Note 12", "Redmi Note 13", "Redmi Note 14", "Redmi Note 15",
    "Redmi Note 16", "Redmi Note 17", "Redmi Note 18", "Redmi Note 19",
    "Redmi Note 20", "Nokia 1", "Nokia 2", "Nokia 3", "Nokia 4", "Nokia 5",
    "Nokia 6", "Nokia 7", "Nokia 8", "Nokia 9", "Nokia 10", "Nokia 11",
    "Nokia 12", "Nokia 13", "Nokia 14", "Nokia 15", "Nokia 16", "Nokia 17",
    "Nokia 18", "Nokia 19", "Nokia 20", "OnePlus 1", "OnePlus 2", "OnePlus 3",
    "OnePlus 4", "OnePlus 5", "OnePlus 6", "OnePlus 7", "OnePlus 8",
    "OnePlus 9", "OnePlus 10", "OnePlus 11", "OnePlus 12", "OnePlus 13",
    "OnePlus 14", "OnePlus 15", "OnePlus 16", "OnePlus 17", "OnePlus 18",
    "OnePlus 19", "OnePlus 20", "Moto G1", "Moto G2", "Moto G3", "Moto G4",
    "Moto G5", "Moto G6", "Moto G7", "Moto G8", "Moto G9", "Moto G10",
    "Moto G11", "Moto G12", "Moto G13", "Moto G14", "Moto G15", "Moto G16",
    "Moto G17", "Moto G18", "Moto G19", "Moto G20", "HTC One", "HTC Two",
    "HTC Three", "HTC Four", "HTC Five", "HTC Six", "HTC Seven", "HTC Eight",
    "HTC Nine", "HTC Ten", "HTC Eleven", "HTC Twelve", "HTC Thirteen",
    "HTC Fourteen", "HTC Fifteen", "HTC Sixteen", "HTC Seventeen",
    "HTC Eighteen", "HTC Nineteen", "HTC Twenty", "Huawei P1", "Huawei P2",
    "Huawei P3", "Huawei P4", "Huawei P5", "Huawei P6", "Huawei P7",
    "Huawei P8", "Huawei P9", "Huawei P10", "Huawei P11", "Huawei P12",
    "Huawei P13", "Huawei P14", "Huawei P15", "Huawei P16", "Huawei P17",
    "Huawei P18", "Huawei P19", "Huawei P20", "Honor X1", "Honor X2",
    "Honor X3", "Honor X4", "Honor X5", "Honor X6", "Honor X7", "Honor X8",
    "Honor X9", "Honor X10", "Honor X11", "Honor X12", "Honor X13",
    "Honor X14", "Honor X15", "Honor X16", "Honor X17", "Honor X18",
    "Honor X19", "Honor X20", "Oppo A1", "Oppo A2", "Oppo A3", "Oppo A4",
    "Oppo A5", "Oppo A6", "Oppo A7", "Oppo A8", "Oppo A9", "Oppo A10",
    "Oppo A11", "Oppo A12", "Oppo A13", "Oppo A14", "Oppo A15", "Oppo A16",
    "Oppo A17", "Oppo A18", "Oppo A19", "Oppo A20"
]


# Input CSV file
input_file = '/Users/I330275/Documents/GitHub/nexus-sourcing/db/data/com.sap.sourcing.db-Item.csv'

# Output CSV file
output_file = '/Users/I330275/Documents/GitHub/nexus-sourcing/db/data/com.sap.sourcing.db-Item1.csv'

# Open the input CSV file
with open(input_file, mode='r', newline='', encoding='utf-8') as infile:
    reader = csv.reader(infile)
    rows = list(reader)

# Replace "IPhone 15" with different phone models
counter = 0
for i in range(1, len(rows)):
    rows[i][2] = phone_models[counter % len(phone_models)]
    counter += 1

# Write to the output CSV file
with open(output_file, mode='w', newline='', encoding='utf-8') as outfile:
    writer = csv.writer(outfile)
    writer.writerows(rows)

print("CSV file updated successfully!")
