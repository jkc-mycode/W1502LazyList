package kr.ac.kumoh.s20201414.w1502lazylist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kr.ac.kumoh.s20201414.w1502lazylist.ui.theme.W1502LazyListTheme

class MainActivity : ComponentActivity() {
    data class Song(var title: String, var singer: String)
    private val songs = mutableStateListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //repeat(20){
            songs.add(Song("Ghost Town", "aaa"))
            songs.add(Song("Dangerously", "bbb"))
            songs.add(Song("Crazy", "ccc"))
        //}
        setContent {
            MyApp()
        }
    }
    @Composable
    fun MyApp() {
        W1502LazyListTheme {
            Column() {
                Button(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    onClick = {
                        songs.add(Song("It's my life", "ddd"))
                    }
                ) {
                    Text("추가")
                }
                List()
            }
        }
    }

    @Composable
    fun List() {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(songs)  { index, song ->
                SongItem(index, song)
            }
        }
    }

    @Composable
    fun SongItem(index: Int, song: Song) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color(255, 210, 210))
            .padding(8.dp)
            .clickable {
                Toast
                    .makeText(applicationContext, song.title, Toast.LENGTH_SHORT)
                    .show()
            }
        ) {
            AsyncImage(model = "https://picsum.photos/300/300?random=$index",
                contentDescription = "노래 앨범 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    //.clip(CircleShape)
                    .clip(RoundedCornerShape(percent = 10))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(song.title, fontSize = 30.sp)
                Text(song.singer, fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    W1502LazyListTheme {
//
//    }
}