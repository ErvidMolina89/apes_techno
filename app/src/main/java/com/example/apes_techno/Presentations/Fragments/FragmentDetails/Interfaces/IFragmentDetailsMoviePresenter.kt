package com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces

import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.Models.BaseModel

interface IFragmentDetailsMoviePresenter {
    fun callService(objectResponse: BaseModel, objectSend: IRetrofitParcelable, service: Services)
}