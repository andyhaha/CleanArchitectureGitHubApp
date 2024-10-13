import androidx.compose.foundation.background
import androidx.compose.material3.SnackbarDuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShowSnackBar(message: String) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = message) {
        if (message.isNotEmpty()) {
            snackBarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    SnackbarHost(hostState = snackBarHostState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .padding(16.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}