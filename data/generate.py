import csv
import random

# Define headers and the list of item IDs
headers = ["ITEM_ID", "ID", "DATATYPE", "VALUE"]

ids = ["QTYAVAILABLE", "PRICE", "QUANTITY"]

item_ids = ["b35dc927-a018-446a-b963-90f651e9ab22",
"d60d8e45-7c5f-4781-872c-9fee17a6672f",
"745627fb-7138-40be-9c0e-efe19bb12327",
"dbcda365-5549-4214-b853-bdb51fdb81fe",
"26c746bd-8996-4d4c-b3be-218ad93166e5",
"3a4f4e69-155f-4d65-a4c9-0d6d0acc9b5f",
"7c928f0e-6876-4a0b-81a8-3552f7b4d3e8",
"76efb97d-9683-4e61-b3ec-e60cb8526de5",
"9f47ecd4-4339-4777-a69c-10edfd42bd02",
"a0a569af-f995-4694-b99d-141fa3f581b5",
"4286100b-5351-4800-a7b3-da1954efd1bd",
"a8f11362-594f-4ddb-8831-27c1de533c63",
"e5819876-5442-4210-b8f3-6e9f5c04489b",
"9bd2a54e-f12e-475f-ab5a-e80215f6d9ac",
"66399f73-2b19-43a0-84db-9c6c56dc7a95",
"06895a40-b023-4d1e-9f4e-5ea666a0b2e6",
"ea976152-81eb-4c15-b13e-ce6cdf434c2c",
"d5559c76-7ba3-49ad-87fb-f14463e48dae",
"8310b79c-88bf-4691-a470-2a3827ccaa83",
"0da866bf-b320-49c2-ae5e-c772ec335faf",
"ae5539a8-754d-4707-af14-2155d0b3b07e",
"a28979cc-49b6-4ded-814e-4fa2829d8fff",
"34936bf7-49f0-43e1-84d5-e9cd3dc0735f",
"9da019d1-eb2d-4553-82ff-0122a1c760d5",
"c5921fc3-975f-451a-bc85-a146d9ee0398",
"6b6729a0-1cb4-4c7b-bb61-dc8d56941786",
"d42bdad8-3662-46d3-b947-ae2230b4944e",
"32cd1217-69e5-41ad-ab3f-f58dac832d27",
"9c325766-b71b-4684-b5a4-c61964868f93",
"7e30e864-3007-4fcf-95b1-f08be4917e95",
"79592733-d881-4b74-ad5c-a321dc280743",
"f6eefde8-267d-4f4f-b8d6-6ebef77c89e8",
"21b4d397-480b-4f2a-ad09-8638f70871a0",
"c8f1ed8f-97a2-4ca1-84e5-7f620f89bbbf",
"0d3851f1-a329-42ac-a0e5-df8ac5f49e1d",
"ff43aaa2-217e-423e-bea4-846bbe2426f4",
"05d91213-16d1-473f-82c9-532d9f31646b",
"772b9fb7-e2a9-4107-aa10-7a182cdf324e",
"4dcaa663-410b-4fb2-9247-94dd8740a902",
"441c53e4-76c1-4c26-a287-8eea49e40869",
"ce2011e8-3a11-4df0-b76c-f51f0f969057",
"e4ef5837-445d-4ec2-ae8a-ba6cc9d62f33",
"cf4b612e-8900-44fe-9a24-c4897596bb39",
"064ef7c8-5dc0-4a33-a175-4d6e46b85807",
"be79cbb4-b7a2-4108-ae5e-83c5d0b58a57",
"0373ba0e-a764-4b09-96c0-56f38585b7cc",
"8a92454f-9ea6-4686-aea2-3f2f696cd604",
"5bf2512e-9a76-4400-a018-38cfb3569084",
"547f85cf-6498-4c0a-8200-2922f8fc2fad",
"16364059-c6e8-4f95-b0bc-6bcd7f298f72",
"91ca952e-1c49-4014-8275-65c4c93f1aa8",
"0b7852f8-ca1b-42a6-b3e3-d0a4aab1727e",
"aea82e5c-5d5d-4d69-a715-68dfc4de68c9",
"a3edd587-0a19-4250-b20b-47bdeba7528d",
"fbdd80a6-de46-4d20-a564-3987c98e124f",
"fb95863a-3ce2-411a-9d81-e147e5dc2d96",
"1aee8730-c371-4c97-84d3-7cbb553ff140",
"67c1ff39-9bf1-46fd-b6e3-d698fe309f47",
"06ac5895-8801-4383-ab7a-ec52f43d6cd6",
"79d4f1eb-5255-477c-9f5c-21a5a064e462",
"4614e85c-b93d-489b-951a-6c0484c17ffe",
"d098e5d2-ab2c-46d2-8f3a-4a21c7e6cb6a",
"a019fb08-b148-4297-a1c2-2b40adf06b2a",
"529f6e1d-9aea-4f67-b386-7d1a9d067b78",
"bdfa97ec-ac10-448e-b9eb-ab65396a34b5",
"6b29e0b1-954c-40fe-ab3a-5d1abfe4f231",
"0fa7fc91-2fb0-4e6b-98be-22728bd91550",
"28fb38b1-c8dc-4dae-8749-7d24c0991856",
"59ba5adb-d4ae-453d-9935-3a51318258a6",
"c37b54e1-39c9-42de-b761-c4e15ceb5760",
"20754c76-8f38-41fe-a63a-0ddb1d44ddb1",
"bbd1afb9-2fb3-4b76-b605-6a67ce02b457",
"8fd4688e-9ece-4416-a995-743500bc9198",
"4c86d43c-f8cd-44aa-a076-294ba859f3c4",
"1fd788e1-1546-4493-9e17-c1f0dcc6e7df",
"fc3a10fe-f2b5-4803-8561-2833c446dc2a",
"5c23faaa-dd01-45ba-874a-7d23ea10dcdb",
"c16cc031-c1bc-441b-b639-1dc88c44f4ac",
"b4a494b8-6bdf-4e36-b401-8656015c045a",
"3da03afe-ff01-4b39-9d5b-73931b581516",
"a8ee57f9-de9e-4e39-b683-4d735f3dc10a",
"081942a8-53e3-49c3-a198-14dd2fc57e91",
"8ef72a0e-1357-4e7a-a8b6-01d0b90cf7c4",
"bd33d0b3-a4aa-450e-a039-9cb3f03b0882",
"ed82cf8b-e167-414a-8d5c-b451112ecabc",
"c75ab4d7-dab9-430c-9a72-ec5d2c1ab37a",
"edf9a877-7d67-4f41-bd13-d67d007aea4e",
"23f1b96c-b0a2-4e67-8436-57c23594efa4",
"9ac6104a-f897-4f99-95c2-281c8659614b",
"575ebf8b-4e21-4b1d-898a-f87e019472d7",
"fa1edc8d-2d09-4f6b-8b72-0d36bfebefeb",
"1b67fd2c-2a3a-47d3-9be6-d7ab80861172",
"d80597f2-d166-46ef-9b3c-4fd0ecc2509d",
"d7a5e9a0-8243-4077-b84a-817a44825d1b",
"7d1c76fe-cd1f-4ea2-bd69-aff677b23fdf",
"867a835b-9853-4a1e-8253-c62adfce7eeb",
"37d1d54d-0bcd-45e6-a8e6-1f00638a7758",
"9039c15a-985f-457e-ad58-e10f672e03d0",
"68802a16-9d2c-43b9-a86e-4a43a396ed84",
"c736286b-5a38-42bd-b84d-6efa99b3fd28",
"2f577294-9db8-4b2d-ac35-3f1d8742fbf2",
"6dba3c3e-ce7f-44b0-8ab2-10a107413473",
"33347e13-05ca-4872-82a7-3762ca521129",
"ba7c2305-2e5e-4bb6-8327-33558b905927",
"95b1c14a-cc68-481a-8f0b-913735978573",
"b14b8db0-52b8-47ca-9132-5f9fd00f40a9",
"977d8f0f-b5d9-4799-ab7f-b7a8d1092486",
"370b971c-eba0-43a2-bc0d-36dfe8a3077f",
"eaea4488-07ad-40fc-ad62-d07cc71002c6",
"94842ef8-9d6a-4120-9ddb-395dadbb91df",
"4aeb7892-2bf0-47a4-8432-20e74f3eb5b8",
"d0968d1d-fde9-4ca7-93c4-057fe9e1d27c",
"4616c171-b1de-42ed-8936-4597827bf886",
"723e451d-555d-484c-9a35-943dfc8b507c",
"fec90657-49e6-4e73-a723-be33db5f29ae",
"69cbbf07-0550-42c7-9327-2b9003de7cd3",
"14c6f2a5-6924-4970-9845-c086ce4f6d4c",
"cc364d6a-95a3-4062-9fad-123f810f061c",
"765f5b30-1a95-4921-b196-90d342f36018",
"b7dc956c-c8f2-4aaa-b141-ca2b6b85a3a6",
"d0045cf0-9efc-4142-848b-2e584ff7e066",
"fdd7ed1f-59ce-4e70-835d-7d9891cfbfb3",
"6498fcf6-1aad-4314-b7c8-f0af2da664e0",
"4e103504-a55c-406a-98b1-d6bf1a73c38c",
"348b9145-86cc-4939-a9e8-2417bc5948ac",
"59b2fa23-a12e-4b5b-88f5-7065df51d373",
"dd0f9460-5a71-45c5-9b9a-b97ac5066b17",
"6cf24ec1-024d-4e4e-aa17-2fc0200df0b2",
"7f0ecc0f-f67a-413e-9781-470f91a738d9",
"0956a10c-ab50-4262-8fcc-c444c5716cbe",
"03fc81fc-c269-4518-920d-714fc95ca215",
"af98ced1-a924-4d87-8a8e-435f3bacba4e",
"6df96f6b-41a9-4bb9-ad94-3c14cf5efbb6",
"ff2b151c-bdf1-4aea-b279-3cf4887329d8",
"b5c54021-1fa0-45ac-824a-f1e24db38ac8",
"11beba3a-017d-4150-9170-c9fd367b4fec",
"788d7e56-21eb-4791-a3f1-aa968fd8aa4f",
"340a0fc5-c7ed-4eb4-8fcb-7f576276bc22",
"a563abf4-1c62-4998-b782-e6f7a7eed8e8",
"b75ff59b-4feb-41da-a4cb-3a0a0eac94a2",
"2351bd0f-6fc1-476e-96d2-0db2c5076f1b",
"921c5211-cc16-44b5-bdaa-aa2448caa706",
"11b63db8-4b93-4dbc-91e6-e70b79335479",
"b323c628-a892-4fd1-b8c9-11b1f5bb286f",
"d8602b46-29b2-4f49-9953-54b5909e700e",
"584828ab-ba97-4a91-ae30-d903062e9639",
"ba671547-9ace-451f-b082-b183b9b1ac23",
"8f3fda3e-aba9-49d4-9f4e-6eb3bc3f5470",
"43f7b45e-ec04-49ea-b406-ff375957871c",
"94ab00ac-a5d7-4c12-9e43-7f55ef5fb92e",
"f9e9b53d-d892-4a05-b306-6a0b7d4dedd7",
"d0bf130e-5d7b-483b-b30b-41a67000fd7a",
"e3f870c3-255d-46b5-80c3-4e152b4d034f",
"46bad13c-e9a4-4779-a8fb-2318fb000722",
"f4fa8979-c0b1-4393-b6bb-0112da5fd7de",
"f97306ed-c11c-4de7-9056-d0ca3920fc2b",
"b3451f52-9711-4ac8-9fcc-3830a0f49684",
"787415df-3900-462f-908d-416c5479a250",
"68c9137e-32c5-4f6b-bf2f-ae180c7447fe",
"133de4b9-5b68-4709-95fd-8e55c67437e5",
"c44a7bea-6cec-4bb2-aa3a-c970b1770bc0",
"9b82dae0-39ab-4010-a920-8aec2a5556af",
"6b8f1ebb-8ebc-403d-9f33-a50a8ea14116",
"886a2599-84a8-4285-9168-578140b9a3d2",
"c8ffa88e-991a-4e8c-bed1-bf4b7c4ef945",
"0960bbde-92ea-4c55-9218-5d58118a0ce5",
"439ba77d-4df8-4488-a6e2-909f17c0afea",
"2bc1eee7-d4df-4f33-84a5-fbeb64c11504",
"7da9d12d-9ae1-4571-9f98-7343da454a50",
"c20e1ddc-5ba7-49cc-8f9a-164b4affa1e5",
"579dc622-6274-49cd-bc14-744aa2e9692a",
"37123ec2-f262-49f8-a083-f7ffb9b9f74e",
"475bb785-9f56-4dd2-bc5a-bf305fe06cad",
"3617cb48-4cb7-4b11-9f26-607329baf44d",
"e76971b3-202d-4553-a9fb-efd882ea2da7",
"c0d60208-6669-41ff-843e-59c85e3e3d3c",
"26946d29-5b60-44ef-82dd-76725a9319f4",
"12450c50-82fd-4972-91f8-54f4913c1c24",
"20512bcb-4446-4cfe-ba2f-5656bba200b9",
"4cdeed19-4b64-4b40-9844-93241f4ef314",
"aa577bcb-d80c-4228-8350-1a0b1c039e00",
"9f95da61-cf8a-4075-bcb2-a1de021d6ef5",
"72bf726f-84f1-4afc-85c3-652198cf31ab",
"577f26b3-ac26-4b05-bc2d-687ba005c437",
"30945f38-881d-4be9-a136-28ca6b64eab6",
"a5174f90-5828-4994-aa39-979929ea913d",
"82ba8086-6dff-4693-a8ee-0ca7f78d5efa",
"201d19a6-30ba-4ae4-9113-771e179de20c",
"76ff3b2f-f5e1-46e8-8c42-25d85a3df532",
"b67c8cbe-e92e-4967-bd5e-e5adb75e7f2b",
"53850526-cef8-4dde-805f-b9f5e6368129",
"9bdfc666-7e32-4a4e-9d33-89cfd6fb2210",
"538bd539-3000-4e98-b00f-76436f92f0f0",
"00a185cd-5e0c-4fdb-b4df-625106010af2",
"cc0ef14c-e3a4-4dbc-8a29-84e50eb4810e",
"ba2ced34-0769-4328-9c87-1712800e7d1a",
"35c60808-4a01-488b-9a61-8833afc593fb",
"4604c31d-9f4d-41cf-8f85-75674c31dbb1",
"040311fc-a47d-475a-9b1e-59653193a08d",
"2ce43ebd-1068-4fb3-8719-f3d3ecc1754b"]

# Possible datatypes
datatypes = ["STRING", "INTEGER", "FLOAT", "BOOLEAN"]

# Function to generate a random value based on datatype
def generate_value(datatype):
    if datatype == "STRING":
        return ''.join(random.choices('ABCDEFGHIJKLMNOPQRSTUVWXYZ', k=5))
    elif datatype == "INTEGER":
        return random.randint(1, 100)
    elif datatype == "FLOAT":
        return round(random.uniform(1.0, 100.0), 2)
    elif datatype == "BOOLEAN":
        return random.choice(["true", "false"])

# Generate CSV data
with open('output.csv', mode='w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(headers)

    for item_id in item_ids:
        for id_value in ids:
            if id_value == "PRICE":
                datatype = "FLOAT"
                value = generate_value(datatype)
            elif id_value == "QUANTITY":
                datatype = "INTEGER"
                value = generate_value(datatype)
            elif id_value == "QTYAVAILABLE":
                datatype = "BOOLEAN"
                value = generate_value(datatype)

            writer.writerow([item_id, id_value, datatype, value])

print("CSV file generated successfully.")
