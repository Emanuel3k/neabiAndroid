package br.edu.ifsc.neabiAndroid.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsc.neabiAndroid.data.remote.BaseURL.BASE_URL
import androidx.navigation.NavController
import br.edu.ifsc.neabiAndroid.R
import br.edu.ifsc.neabiAndroid.domain.model.Campus
import br.edu.ifsc.neabiAndroid.util.sizeLarge
import br.edu.ifsc.neabiAndroid.util.sizeMedium
import br.edu.ifsc.neabiAndroid.util.sizeSmall
import coil.compose.SubcomposeAsyncImage

@Composable
fun CampusCard(navController: NavController, campus: Campus) {
    Card(
        modifier = Modifier
            .padding(sizeMedium)
            .fillMaxWidth()
            .clickable {
                navController.navigate("campus/${campus.pk}")
            },
        elevation = sizeSmall,
        shape = RoundedCornerShape(sizeLarge)
    ) {
        Column {
            Spacer(modifier = Modifier.height(sizeSmall))
            Text(
                campus.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = sizeLarge)
            )
            Text(
                campus.institution.name,
                modifier = Modifier.padding(start = sizeLarge)
            )
            Spacer(modifier = Modifier.height(sizeSmall))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(campus.image.isBlank())
                    Image(painter = painterResource(id = R.drawable.default_campus_image),
                        contentDescription = "Imagem genérica")
                else
                    SubcomposeAsyncImage(
                        model = BASE_URL+campus.image,
                        loading = {
                            CircularProgressIndicator(modifier = Modifier.padding(70.dp))
                        },
                        contentDescription = "Imagem Câmpus",
                    )
                Button(
                    onClick = {
                        navController.navigate("campus/${campus.pk}")
                    },
                    modifier = Modifier.fillMaxSize(),
                    shape = MaterialTheme.shapes.medium.copy(
                        topEnd = CornerSize(0.dp),
                        topStart = CornerSize(0.dp)
                    )
                ) {
                    Text(
                        "Clique para mais informações sobre a instituição",
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}