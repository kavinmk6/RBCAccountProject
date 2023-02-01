# RBCAccountProject
So, Basically the first screen 'MainActivity' on the app fetches the list of accounts from the AccountProvider Service and display it in the recyclerview.
When user selects any account from the recyclerview, it will redirect the user to second screen 'AccountDetailActivity'. This activity will show the Account information and list of all transactions made by that account.

To make the different API calls parallely, i have used viewmodel class 'AccountDetailsViewModel. I have used coroutines with async to do two API calls paralley and handled their sucess, error, progresbar laoding state by creating a simple Sealed class called Resource.

I have not used the repsoitory class, because as we don't have any requirement to handle local and remote data storage. I have not used DI, Room as per the apps requirement, that the app needs to be simple.

NOTE: THE getTransactions Service call and getAdditionalCreditCardTransactions always throwing error on first time. So i have placed an button to retry the service call, so that we can display the transactions in recyclerview.
While doing the API call, whenever an error occurs, i will display that errorMessage in a textview. -> Error handling

SCREENSHOTS:
<img width="413" alt="Screen Shot 2023-01-31 at 8 16 58 PM" src="https://user-images.githubusercontent.com/22996236/215922159-ce439e6b-e21d-4c13-965d-de1d51126cfa.png">
<img width="413" alt="Screen Shot 2023-01-31 at 8 17 10 PM" src="https://user-images.githubusercontent.com/22996236/215922167-17e19962-8ad1-4981-aa12-e05cae547ce6.png">
<img width="413" alt="Screen Shot 2023-01-31 at 8 17 36 PM" src="https://user-images.githubusercontent.com/22996236/215922175-e5511cd4-9d55-42ac-81a4-358c7291aa3e.png">


