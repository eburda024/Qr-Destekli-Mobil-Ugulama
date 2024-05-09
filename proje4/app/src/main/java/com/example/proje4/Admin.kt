package com.example.proje4

data class Admin(
    var id: Int= adminId(),
    var name: String="",
    var pass: String ="",
){
    companion object{
        private var currentId = 0
        fun adminId():Int{
            return ++currentId
        }
    }

}

