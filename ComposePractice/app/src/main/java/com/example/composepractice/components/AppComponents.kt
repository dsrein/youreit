package com.example.composepractice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.ui.theme.BackgroundColor
import com.example.composepractice.ui.theme.TextColor

val normalTextStyle = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    textAlign = TextAlign.Center,
)

val headerTextStyle = TextStyle(
    fontSize = 30.sp,
    fontWeight = FontWeight.Bold,
    fontStyle = FontStyle.Normal,
    textAlign = TextAlign.Center,
)

@Composable
fun NormalTextComponent(text: String, textStyle: TextStyle = normalTextStyle) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = textStyle,
        color = TextColor
    )
}

@Composable
fun NormalTextFieldComponent(labelString: String, img: ImageVector) {
    var textValue by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        value = textValue,
        onValueChange = {
            textValue = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            focusedLabelColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.primary,
            backgroundColor = BackgroundColor,
        ),
        keyboardOptions = KeyboardOptions.Default,
        label = {
            Text(text = labelString)
        },
        leadingIcon = {
            Icon(imageVector = img, contentDescription = null)
        })
}

@Composable
fun PasswordTextFieldComponent(labelString: String) {
    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        value = password,
        onValueChange = {
            password = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            focusedLabelColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.primary,
            backgroundColor = BackgroundColor,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = {
            Text(text = labelString)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = null)
        },
        trailingIcon = {
            val img = if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
            val description = if (passwordVisible) {
                "Hide Password"
            } else {
                "Show Password"
            }
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                Icon(imageVector = img, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun ButtonComponent(text: String) {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colors.secondary,
                        MaterialTheme.colors.primary
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center) {
            Text(text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White)
        }
    }
}

@Composable
fun DividerTextComponent() {
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        ) {
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp)
        Text(text = "or", fontSize = 18.sp, color = TextColor, modifier = Modifier.padding(8.dp))
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        color = Color.Gray,
        thickness = 1.dp)
    }
}
