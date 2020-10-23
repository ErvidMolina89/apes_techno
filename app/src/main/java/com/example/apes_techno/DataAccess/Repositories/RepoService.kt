package com.example.apes_techno.DataAccess.Repositories

import android.content.Context
import com.example.apes_techno.DataAccess.Connection.Handler.Implementation.HandlerProxyRetrofitRx
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse

class RepoService (context: Context) {

    private val context : Context
    init {
        this.context = context
    }

    fun < T: IRepository>callService(objectResponse: BaseModel, objectSend: IRetrofitParcelable, service : Services, responder : T){
        HandlerProxyRetrofitRx(context)
            .withListenerAnswerObjects {
                responder.onSuccessResponse(it, service)
            }
            .withListenerAnswerListObjectcs {
                responder.onSuccessResponse(it, service)
            }
            .withListenerOfFailure { titulo, message ->
                val mess = MessageResponse()
                mess.Code = titulo
                mess.Message = message
                responder.onFailedResponse(mess, service)
            }
            .withMyClass(objectResponse::class.java)
            .withMyService(service)
            .withObjectSend(objectSend)
            .RunService()
    }
}