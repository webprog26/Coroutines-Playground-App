package com.webprog26.coroutinesplaygroundapp.users

import com.webprog26.coroutinesplaygroundapp.users.data_source.DataProvider
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Address
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Company
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.Geo
import com.webprog26.coroutinesplaygroundapp.users.data_source.data_model.User

class FakeLocalDataSuccessProvider : DataProvider {

    override suspend fun getUsersList(): List<User> {
        return USERS_LIST
    }

    companion object {

        val USERS_LIST = listOf(
            User(
                1, "Leanne Graham", "Bret", "Sincere@april.biz",
                Address(
                    "Kulas Light", "Apt. 556", "Gwenborough", "92998-3874",
                    Geo(-37.3159, 81.1496)
                ), "1-770-736-8031 x56442", "hildegard.org",
                Company(
                    "Romaguera-Crona",
                    "Multi-layered client-server neural-net",
                    "harness real-time e-markets"
                )
            ),
            User(
                2, "Ervin Howell", "Antonette", "Shanna@melissa.tv",
                Address(
                    "Victor Plains", "Suite 879", "Wisokyburgh", "90566-7771",
                    Geo(-43.9509, -34.4618)
                ), "010-692-6593 x09125", "anastasia.net",
                Company(
                    "Deckow-Crist",
                    "Proactive didactic contingency",
                    "synergize scalable supply-chains"
                )
            ),
            User(
                3, "Clementine Bauch", "Samantha", "Nathan@yesenia.net",
                Address(
                    "Douglas Extension", "Suite 847", "McKenziehaven", "59590-4157",
                    Geo(-68.6102, -47.0653)
                ), "1-463-123-4447", "ramiro.info",
                Company(
                    "Romaguera-Jacobson",
                    "Face to face bifurcated interface",
                    "e-enable strategic applications"
                )
            )

        )
    }
}