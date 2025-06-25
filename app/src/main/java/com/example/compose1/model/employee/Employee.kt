package com.example.compose1.model.employee

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(var imageUrl: String,
                    var name: String,
                    var title: String) : Parcelable
