package com.example.bb.backend.data

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.example.bb.backend.*
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.Objects


// "[SHARED VIEW MODEL] ALLOWS ME TO SHARE DATA BETWEEN SCREENS,
//  BETTER SOLUTION THAN DB for now "
//      -juan A

@Parcelize
data class CategoryCard(var index: Int,var CategoryName:String, var Income:String) : Parcelable


//
//@Parcelize
//object CategoryHolder : Parceler<User>, Parcelable {
//    override fun create(parcel: Parcel): User {
//        TODO("Not yet implemented")
//    }
//
//    override fun User.write(parcel: Parcel, flags: Int) {
//        TODO("Not yet implemented")
//    }
//
//
//}
