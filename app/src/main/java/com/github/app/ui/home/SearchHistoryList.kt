import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.app.domain.model.SearchItem

@Composable
fun SearchHistoryList(
    historyItems: List<SearchItem> = emptyList(),
    onItemClick: (SearchItem) -> Unit = {},
    delete: (SearchItem) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        historyItems.forEach { item ->
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp)
                    .clickable {
                        onItemClick(item)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(start = 10.dp),
                    imageVector = Icons.Default.History,
                    contentDescription = "History Icon",
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.content, modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = "Right Icon",
                    modifier = Modifier.padding(end = 16.dp)
                        .clickable {
                            delete(item)
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearchHistoryList() {
    SearchHistoryList()
}