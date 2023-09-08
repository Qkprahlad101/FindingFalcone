package com.git.falcone.model.data.response

import com.google.gson.annotations.SerializedName

data class FoundQueenResponse(
   @SerializedName("planet_name") val planetName: String?  = null,
    @SerializedName("status") val status: String
)

val dummyQueenResponse = FoundQueenResponse("Lerbin", "success")