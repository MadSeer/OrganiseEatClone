package com.example.organiseeatclone.core

import com.mymind.core.base.BaseHTTPRequest

class RecipeAPI(
) :BaseHTTPRequest() {
    override val apikey: String = "6Vb6UQwnXAr2clEVCVLCNw==apexCaZIkYh9yrsn"
    override var url: String = "https://api.api-ninjas.com/v1/recipe?query="
    override val gettingFieldName: String = ""
}