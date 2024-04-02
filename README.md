# BankdataCodeTest

### Beskrivelse
Min besvarelse af opgaven stillet af Bankdata i optakt til jobsamtale.
Jeg har tilføjet Swagger/OpenAPI som kan bruges til at teste endpoints, hvis ikke Swagger interfacet
bruges, se beskrivelsen af [endpoints](#endpoints).

### Indledende
1. Klon projektet
    ``https://github.com/Bimmerlynge/BankdataCodeTest.git``
2. SDK: openJDK 22, language level 17
3. Kør DemoApplication
4. [Open Swagger UI](http://localhost:8080/swagger-ui/index.html#)

## Endpoints

[GetAllAccounts](#getallaccounts)

[CreateAccount](#createaccount)

[GetAccount](#getaccount)

[Deposit](#deposit)

[TransferMoney](#transfermoney)

[GetDKKToUSD](#getdkktousd)


### GetAllAccounts
**HTTP Method:** GET

**Endpoint:** '/accounts'

Description: 
Retrieves all accounts in the system

Example request:
````
http://localhost:8080/accounts
````

### CreateAccount

**HTTP Method:** POST

**Endpoint:** `/accounts`

#### Description
Creates a new account with the provided details.

#### Request Body
- **firstname** (string): The first name of the account holder.
- **lastname** (string): The last name of the account holder.
- **balance** (number): The initial balance of the account.

Request Body Example:

```json
{
    "firstname": "Flemming",
    "lastname": "Flemmingsen",
    "balance": 56
}
```

### GetAccount

**HTTP Method:** GET

**Endpoint:** `/accounts/{id}`

#### Description
Retrieves the account details for the specified account ID.

#### Path Parameters
- **id** (long): The unique identifier of the account to retrieve.

Request Example:
```
http://localhost:8080/accounts/123
```

#### Response Body
- **id** (long): The unique identifier of the account.
- **firstname** (string): The first name of the account holder.
- **lastname** (string): The last name of the account holder.
- **balance** (number): The balance of the account.

Response Body Example:

```json
{
    "id": 123,
    "firstname": "Flemming",
    "lastname": "Flemmingsen",
    "balance": 56
}
```

### Deposit

**HTTP Method:** PUT

**Endpoint:** `/accounts/{id}/deposit`

#### Description
Updates the balance of the specified account by depositing the provided amount. Returns updated account.

#### Path Parameters
- **id** (long): The unique identifier of the account to update.

#### Request Parameters
- **amount** (number): The amount to deposit into the account.

Request Example:

```http://localhost:8080/accounts/123/deposit?amount=20```

#### Response Body
- **id** (long): The unique identifier of the account.
- **firstname** (string): The first name of the account holder.
- **lastname** (string): The last name of the account holder.
- **balance** (number): The updated balance of the account after deposit.

Response Body Example:

```json
{
    "id": 123,
    "firstname": "Flemming",
    "lastname": "Flemmingsen",
    "balance": 106
}
```

### TransferMoney

**HTTP Method:** PUT

**Endpoint:** `/transfer`

#### Description
Transfers money from one account to another. Returns updated origin account.

#### Request Body
- **fromId** (long): The ID of the account from which money will be transferred.
- **toId** (long): The ID of the account to which money will be transferred.
- **amount** (number): The amount of money to transfer.

Request Body Example:

```json
{
    "fromId": 123,
    "toId": 456,
    "amount": 20
}
```

Response Body Example:
```json
{
  "id": 123,
  "firstname": "Flemming",
  "lastname": "Flemmingsen",
  "balance": 86
}
```

### GetDKKToUSD

**HTTP Method:** GET

**Endpoint:** `/dkk-to-usd`

#### Description
Calculates the exchange rate from Danish Krone (DKK) to US Dollar (USD) based on the provided amount.

#### Request Parameters
- **amount** (number): The amount in Danish Krone (DKK) for which the exchange rate will be calculated.

Request Example:
```
http://localhost:8080/dkk-to-usd?amount=100
```


#### Response Body
- **DKK** (number): The original amount in Danish Krone (DKK).
- **USD** (number): The equivalent amount in US Dollar (USD) based on the exchange rate.



Example Response:

```json
{
    "DKK": 100,
    "USD": 14.61
}
```











