//package org.example.project
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//actual class Tablero {
//    @Composable
//    actual fun dibujarTablero() {
//        var puzzleSeleccionado by remember { mutableStateOf(R.drawable.mario) }
//        val logicPuzzle = remember(puzzleSeleccionado) { LogicPuzzle(puzzleSeleccionado) }
//        val currentImages = logicPuzzle.piezas.value.map { painterResource(id = it) }
//
//        var mostrarReferencia by remember { mutableStateOf(false) }
//        var mostrarDialogo by remember { mutableStateOf(false) }
//        var expandirMenu by remember { mutableStateOf(false) }
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Selección de Puzzle
//            Box {
//                Button(
//                    onClick = { expandirMenu = true },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA726))
//                ) {
//                    Text("Seleccionar Puzzle", fontSize = 16.sp, color = Color.White)
//                }
//
//                DropdownMenu(expanded = expandirMenu, onDismissRequest = { expandirMenu = false }) {
//                    listOf(
//                        "Mario" to R.drawable.mario,
//                        "Batman" to R.drawable.batman,
//                        "Números" to R.drawable.numeros,
//                        "Superman" to R.drawable.superman
//                    ).forEach { (nombre, id) ->
//                        DropdownMenuItem(onClick = {
//                            puzzleSeleccionado = id
//                            expandirMenu = false
//                        }) {
//                            Text(nombre, fontSize = 16.sp)
//                        }
//                    }
//                }
//            }
//
//            // Botón para mezclar piezas
//            Button(
//                onClick = {
//                    logicPuzzle.mezclarPiezas()
//                    mostrarDialogo = false
//                },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3F51B5))
//            ) {
//                Text("Mezclar piezas", fontSize = 18.sp, color = Color.White)
//            }
//
//            // Botón para mostrar referencia
//            Button(
//                onClick = { mostrarReferencia = !mostrarReferencia },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009688))
//            ) {
//                Text(
//                    if (mostrarReferencia) "Ocultar referencia" else "Mostrar referencia",
//                    fontSize = 18.sp, color = Color.White
//                )
//            }
//
//            // Mostrar la imagen de referencia
//            if (mostrarReferencia) {
//                Image(
//                    painter = painterResource(id = puzzleSeleccionado),
//                    contentDescription = "Imagen de referencia",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp)
//                        .padding(bottom = 16.dp)
//                )
//            }
//
//            // Tablero de piezas
//            LazyVerticalGrid(columns = GridCells.Fixed(4), contentPadding = PaddingValues(4.dp)) {
//                items(currentImages.size) { index ->
//                    Image(
//                        painter = currentImages[index],
//                        contentDescription = null,
//                        modifier = Modifier
//                            .padding(4.dp)
//                            .fillMaxWidth()
//                            .aspectRatio(1f)
//                            .clip(RoundedCornerShape(8.dp))
//                            .clickable {
//                                logicPuzzle.seleccionarPieza(index)
//                                if (logicPuzzle.yaGano()) {
//                                    mostrarDialogo = true
//                                }
//                            }
//                    )
//                }
//            }
//        }
//
//        // Mostrar el cuadro de diálogo de victoria si el usuario gana
//        if (mostrarDialogo) {
//            VictoryDialog(onDismiss = { mostrarDialogo = false })
//        }
//    }
//
//    @Composable
//    fun VictoryDialog(onDismiss: () -> Unit) {
//        AlertDialog(
//            onDismissRequest = onDismiss,
//            title = {
//                Text(
//                    text = "¡Felicidades!",
//                    fontSize = 22.sp,
//                    color = Color(0xFF4CAF50)
//                )
//            },
//            text = { Text(text = "Has completado el rompecabezas.", fontSize = 18.sp) },
//            confirmButton = {
//                Button(
//                    onClick = onDismiss,
//                    shape = RoundedCornerShape(8.dp),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF673AB7))
//                ) {
//                    Text("Aceptar", color = Color.White)
//                }
//            }
//        )
//    }
//}


//
//package org.example.project
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.expandVertically
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.shrinkVertically
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material.icons.filled.PlayArrow
//import androidx.compose.material.icons.filled.Refresh
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//
//actual class Tablero {
//    @Composable
//    actual fun dibujarTablero() {
//        // Define color scheme
//        val primaryColor = Color(0xFFFFD54F)
//        val secondaryColor = Color(0xFFFF5252)
//        val accentColor = Color(0xFFFFD54F)
//        val backgroundGradient = Brush.verticalGradient(
//            colors = listOf(
//                Color(0xFFF5F5F5),
//                Color(0xFFE0E0E0)
//            )
//        )
//
//        var puzzleSeleccionado by remember { mutableStateOf(R.drawable.mario) }
//        val logicPuzzle = remember(puzzleSeleccionado) { LogicPuzzle(puzzleSeleccionado) }
//        val currentImages = logicPuzzle.piezas.value.map { painterResource(id = it) }
//
//        var mostrarReferencia by remember { mutableStateOf(false) }
//        var mostrarDialogo by remember { mutableStateOf(false) }
//        var expandirMenu by remember { mutableStateOf(false) }
//
//        // Animation properties
//        val rotationState by animateFloatAsState(
//            targetValue = if (expandirMenu) 180f else 0f,
//            animationSpec = tween(300)
//        )
//
//        var lastClickedIndex by remember { mutableStateOf(-1) }
//        val pieceScale = remember { mutableStateListOf<Float>().apply {
//            repeat(16) { add(1f) }
//        }}
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(brush = backgroundGradient)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                Text(
//                    "Puzzle Game",
//                    fontSize = 28.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = primaryColor,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//
//                // Selector con apariencia mejorada
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    elevation = 4.dp,
//                    shape = RoundedCornerShape(12.dp),
//                    backgroundColor = Color.White
//                ) {
//                    Column {
//                        Box {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .clickable { expandirMenu = !expandirMenu }
//                                    .padding(16.dp),
//                                horizontalArrangement = Arrangement.SpaceBetween,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Text(
//                                    getPuzzleName(puzzleSeleccionado),
//                                    fontSize = 16.sp,
//                                    fontWeight = FontWeight.Medium
//                                )
//                                Icon(
//                                    Icons.Default.KeyboardArrowDown,
//                                    contentDescription = "Expandir",
//                                    modifier = Modifier.rotate(rotationState),
//                                    tint = primaryColor
//                                )
//                            }
//
//                            DropdownMenu(
//                                expanded = expandirMenu,
//                                onDismissRequest = { expandirMenu = false },
//                                modifier = Modifier
//                                    .fillMaxWidth(0.9f)
//                                    .background(Color.White)
//                            ) {
//                                listOf(
//                                    "Mario" to R.drawable.mario,
//                                    "Batman" to R.drawable.batman,
//                                    "Números" to R.drawable.numeros,
//                                    "Superman" to R.drawable.superman
//                                ).forEach { (nombre, id) ->
//                                    DropdownMenuItem(
//                                        onClick = {
//                                            puzzleSeleccionado = id
//                                            expandirMenu = false
//                                        }
//                                    ) {
//                                        Text(nombre, fontSize = 16.sp)
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//                // Fila de botones
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    // Botón para mezclar piezas
//                    Button(
//                        onClick = {
//                            logicPuzzle.mezclarPiezas()
//                            mostrarDialogo = false
//                        },
//                        modifier = Modifier.weight(1f),
//                        shape = RoundedCornerShape(12.dp),
//                        colors = ButtonDefaults.buttonColors(backgroundColor = secondaryColor)
//                    ) {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.spacedBy(4.dp)
//                        ) {
//                            Icon(Icons.Default.Refresh, contentDescription = "Mezclar")
//                            Text("Mezclar", fontSize = 16.sp, color = Color.White)
//                        }
//                    }
//
//                    // Botón para mostrar/ocultar referencia
//                    Button(
//                        onClick = { mostrarReferencia = !mostrarReferencia },
//                        modifier = Modifier.weight(1f),
//                        shape = RoundedCornerShape(12.dp),
//                        colors = ButtonDefaults.buttonColors(backgroundColor = accentColor)
//                    ) {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.spacedBy(4.dp)
//                        ) {
//                            Icon(
//                                if (mostrarReferencia) Icons.Default.PlayArrow else Icons.Default.Star,
//                                contentDescription = "Mostrar referencia",
//                                tint = Color.Black
//                            )
//                            Text(
//                                if (mostrarReferencia) "Ocultar" else "Ver imagen",
//                                fontSize = 16.sp,
//                                color = Color.Black
//                            )
//                        }
//                    }
//                }
//
//                // Imagen de referencia con animación
//                AnimatedVisibility(
//                    visible = mostrarReferencia,
//                    enter = fadeIn(animationSpec = tween(300)) + expandVertically(),
//                    exit = fadeOut(animationSpec = tween(300)) + shrinkVertically()
//                ) {
//                    Card(
//                        elevation = 4.dp,
//                        shape = RoundedCornerShape(16.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(200.dp)
//                            .padding(vertical = 8.dp)
//                    ) {
//                        Image(
//                            painter = painterResource(id = puzzleSeleccionado),
//                            contentDescription = "Imagen de referencia",
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.fillMaxSize()
//                        )
//                    }
//                }
//
//                // Tablero de piezas con animaciones
//                Card(
//                    elevation = 4.dp,
//                    shape = RoundedCornerShape(16.dp),
//                    backgroundColor = Color.White,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                ) {
//                    LazyVerticalGrid(
//                        columns = GridCells.Fixed(4),
//                        contentPadding = PaddingValues(12.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp),
//                        horizontalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        items(currentImages.size) { index ->
//                            val scale = remember(index) {
//                                mutableStateOf(1f)
//                            }
//
//                            LaunchedEffect(key1 = lastClickedIndex) {
//                                if (lastClickedIndex == index) {
//                                    scale.value = 0.9f
//                                    kotlinx.coroutines.delay(100)
//                                    scale.value = 1f
//                                }
//                            }
//
//                            Image(
//                                painter = currentImages[index],
//                                contentDescription = null,
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .aspectRatio(1f)
//                                    .scale(scale.value)
//                                    .clip(RoundedCornerShape(12.dp))
//                                    .clickable {
//                                        lastClickedIndex = index
//                                        logicPuzzle.seleccionarPieza(index)
//                                        if (logicPuzzle.yaGano()) {
//                                            mostrarDialogo = true
//                                        }
//                                    }
//                            )
//                        }
//                    }
//                }
//            }
//        }
//
//        // Cuadro de victoria mejorado
//        if (mostrarDialogo) {
//            VictoryDialog(
//                onDismiss = { mostrarDialogo = false },
//                onNewGame = {
//                    logicPuzzle.mezclarPiezas()
//                    mostrarDialogo = false
//                }
//            )
//        }
//    }
//
//    @Composable
//    fun VictoryDialog(onDismiss: () -> Unit, onNewGame: () -> Unit) {
//        Dialog(onDismissRequest = onDismiss) {
//            Card(
//                shape = RoundedCornerShape(16.dp),
//                backgroundColor = Color.White,
//                elevation = 8.dp
//            ) {
//                Column(
//                    modifier = Modifier
//                        .padding(24.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    Text(
//                        text = "¡Felicidades!",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF4CAF50)
//                    )
//
//                    Text(
//                        text = "Has completado el rompecabezas con éxito.",
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(vertical = 8.dp)
//                    )
//
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Button(
//                            onClick = onNewGame,
//                            modifier = Modifier.weight(1f),
//                            shape = RoundedCornerShape(8.dp),
//                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
//                        ) {
//                            Text("Nuevo juego", color = Color.White)
//                        }
//
//                        OutlinedButton(
//                            onClick = onDismiss,
//                            modifier = Modifier.weight(1f),
//                            shape = RoundedCornerShape(8.dp)
//                        ) {
//                            Text("Cerrar")
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private fun getPuzzleName(drawableId: Int): String {
//        return when (drawableId) {
//            R.drawable.mario -> "Mario"
//            R.drawable.batman -> "Batman"
//            R.drawable.numeros -> "Números"
//            R.drawable.superman -> "Superman"
//            else -> "Seleccionar Puzzle"
//        }
//    }
//}

























package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

actual class Tablero {
    @Composable
    actual fun dibujarTablero() {
        // Colores de la paleta Big Machine
        val redColor = Color(0xFFBD2A2E)      // Big-Machine-1
        val darkGrayColor = Color(0xFF3B3936) // Big-Machine-2
        val lightGrayColor = Color(0xFFB2BEBF) // Big-Machine-3
        val mediumGrayColor = Color(0xFF889C9B) // Big-Machine-4
        val tealColor = Color(0xFF486966)     // Big-Machine-5

        var puzzleSeleccionado by remember { mutableStateOf(R.drawable.mario) }
        val logicPuzzle = remember(puzzleSeleccionado) { LogicPuzzle(puzzleSeleccionado) }
        val currentImages = logicPuzzle.piezas.value.map { painterResource(id = it) }

        var mostrarReferencia by remember { mutableStateOf(false) }
        var mostrarDialogo by remember { mutableStateOf(false) }
        var expandirMenu by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightGrayColor)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Título
            Text(
                "MI PUZZLE GAME",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = redColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Selección de Puzzle
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Button(
                    onClick = { expandirMenu = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = tealColor),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "ELEGIR PUZZLE: ${getPuzzleName(puzzleSeleccionado)}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Abrir menú",
                            tint = Color.White
                        )
                    }
                }

                DropdownMenu(
                    expanded = expandirMenu,
                    onDismissRequest = { expandirMenu = false },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(mediumGrayColor)
                ) {
                    listOf(
                        "Mario" to R.drawable.mario,
                        "Batman" to R.drawable.batman,
                        "Números" to R.drawable.numeros,
                        "Superman" to R.drawable.superman
                    ).forEach { (nombre, id) ->
                        DropdownMenuItem(
                            onClick = {
                                puzzleSeleccionado = id
                                expandirMenu = false
                            },
                            modifier = Modifier.background(
                                if (puzzleSeleccionado == id) lightGrayColor else mediumGrayColor
                            )
                        ) {
                            Text(
                                nombre,
                                fontSize = 16.sp,
                                color = darkGrayColor,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Botón para mezclar piezas
            Button(
                onClick = {
                    logicPuzzle.mezclarPiezas()
                    mostrarDialogo = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = redColor)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Mezclar",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "MEZCLAR PIEZAS!!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Botón para mostrar referencia
            Button(
                onClick = { mostrarReferencia = !mostrarReferencia },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = darkGrayColor)
            ) {
                Text(
                    if (mostrarReferencia) "ESCONDER IMAGEN" else "VER IMAGEN COMPLETA",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Mostrar la imagen de referencia
            if (mostrarReferencia) {
                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = mediumGrayColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    Image(
                        painter = painterResource(id = puzzleSeleccionado),
                        contentDescription = "Imagen de referencia",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }
            }

            // Tablero de piezas
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = mediumGrayColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(currentImages.size) { index ->
                        Image(
                            painter = currentImages[index],
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .border(2.dp, redColor, RoundedCornerShape(8.dp))
                                .clickable {
                                    logicPuzzle.seleccionarPieza(index)
                                    if (logicPuzzle.yaGano()) {
                                        mostrarDialogo = true
                                    }
                                }
                        )
                    }
                }
            }
        }

        // Mostrar el cuadro de diálogo de victoria
        if (mostrarDialogo) {
            Dialog(onDismissRequest = { mostrarDialogo = false }) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = lightGrayColor,
                    elevation = 8.dp,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "¡¡¡GANASTE!!!",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = redColor
                        )

                        Text(
                            text = "Completaste el puzzle :D",
                            fontSize = 18.sp,
                            color = darkGrayColor,
                            textAlign = TextAlign.Center
                        )

                        Button(
                            onClick = {
                                logicPuzzle.mezclarPiezas()
                                mostrarDialogo = false
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = redColor),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("JUGAR DE NUEVO!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }

                        Button(
                            onClick = { mostrarDialogo = false },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = darkGrayColor),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("CERRAR",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getPuzzleName(drawableId: Int): String {
        return when (drawableId) {
            R.drawable.mario -> "Mario"
            R.drawable.batman -> "Batman"
            R.drawable.numeros -> "Números"
            R.drawable.superman -> "Superman"
            else -> "???"
        }
    }
}