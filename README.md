# RBCAccountProject
So, Basically the first screen 'MainActivity' on the app fetches the list of accounts from the AccountProvider Service and display it in the recyclerview.
When user selects any account from the recyclerview, it will redirect the user to second screen 'AccountDetailActivity'. This activity will show the Account information and list of all transactions made by that account.
To make the different API calls, i have used viewmodel class 'AccountDetailsViewModel. I have used coroutines with async to do two API calls paralley and handled their sucess, error, progresbar laoding state by creating a simple Sealed class called Resource.

NOTE: THE getTransactions Service call and getAdditionalCreditCardTransactions always throwing error. So i couldn't have a chance to work with the success data. But i have created recyclerview and adapter class for listing transactions.
While doing the API call, whenever an error occurs, i will display that errorMessage in a textview.

SCREENSHOTS:
<img width="413" alt="Screen Shot 2023-01-29 at 11 57 20 PM" src="https://user-images.githubusercontent.com/22996236/215391208-32996fdf-8f25-4474-ac16-9b13e43e2a22.png">
<img width="413" alt="Screen Shot 2023-01-29 at 11 57 30 PM" src="https://user-images.githubusercontent.com/22996236/215391280-0937cadb-92d4-4881-8d90-5b7b4024c2f9.png">

