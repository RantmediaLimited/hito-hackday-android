package com.rantmedia.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rantmedia.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class OldDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
//                PopulateUI()
            }
        }
    }
}

//@Preview
//@Composable
//fun PopulateUI() {
//    Column(modifier = Modifier.padding(20.dp)) {
//        ShowImage()
//        Spacer(Modifier.preferredHeight(26.dp))
//        ShowUserList()
//    }
//}
//
//@Composable
//fun ShowImage() {
//    val (shape, setShape) = state<Shape> { CircleShape }
//    Image(asset = imageResource(id = R.drawable.ewg_logo), modifier = Modifier
//        .size(256.dp)
//        .padding(16.dp)
//        .drawShadow(8.dp, shape)
//        .drawBorder(6.dp, MaterialTheme.colors.primary, shape)
//        .drawBorder(12.dp, MaterialTheme.colors.secondary, shape)
//        .drawBorder(18.dp, MaterialTheme.colors.background, shape)
//        .ripple(color = MaterialTheme.colors.onSurface)
//        .clickable {
//            setShape(
//                if (shape == CircleShape)
//                    CutCornerShape(topLeft = 32.dp, bottomRight = 32.dp)
//                else
//                    CircleShape
//            )
//        }
//    )
//}
//
//@Composable
//private fun ShowUserList() {
//    UserList(getUsersLD())
//}
//
//@Composable
//private fun UserList(usersLD: LiveData<List<User>>) {
//    val items: List<User> by usersLD.observeAsState(emptyList())
//    AdapterList(data = items) { item ->
//        UserItem(name = item.name)
//    }
//}
//
//@Composable
//fun UserItem(name: String) {
//    Text(text = name)
//}
//
//
//private fun getUsersLD(): LiveData<List<User>> {
//    val listOfUsers = mutableListOf<User>()
//    listOfUsers.add(User("User One", "email1@gmail.com"))
//    listOfUsers.add(User("User Two", "email2@gmail.com"))
//    listOfUsers.add(User("User Three", "email3@gmail.com"))
//    listOfUsers.add(User("User 4", "email4@gmail.com"))
//    listOfUsers.add(User("User 5", "email5@gmail.com"))
//    val usersLD = MutableLiveData<List<User>>()
//    usersLD.value = listOfUsers
//    return usersLD
//}
//
//data class User(val name: String = "", val email: String = "")