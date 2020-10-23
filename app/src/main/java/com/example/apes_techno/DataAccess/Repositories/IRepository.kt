package com.example.apes_techno.DataAccess.Repositories

import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.Models.MessageResponse

interface IRepository {
    fun onSuccessResponse(objectResponse: Any?, services: Services)
    fun onFailedResponse(response: MessageResponse, services: Services)
}