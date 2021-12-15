package ru.netology.fmhandroid.repository.authRepository

import ru.netology.fmhandroid.api.AuthApi
import ru.netology.fmhandroid.auth.AppAuth
import ru.netology.fmhandroid.dto.AuthState
import ru.netology.fmhandroid.dto.LoginData
import ru.netology.fmhandroid.dto.RefreshRequest
import ru.netology.fmhandroid.utils.Utils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val appAuth: AppAuth
) : AuthRepository {
    override suspend fun login(login: String, password: String) =
        Utils.makeRequest(
            request = { authApi.getTokens(LoginData(login = login, password = password)) },
            onSuccess = { body -> appAuth.authState = body }
        )

    override suspend fun updateTokens(refreshToken: String): AuthState =
        Utils.makeRequest(
            request = { authApi.refreshTokens(RefreshRequest(refreshToken)) },
            onSuccess = { body ->
                appAuth.authState = body
                body
            }
        )
}