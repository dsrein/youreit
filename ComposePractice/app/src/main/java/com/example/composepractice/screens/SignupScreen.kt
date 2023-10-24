package com.example.composepractice.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.components.*
import com.example.composepractice.navigation.AppRouter
import com.example.composepractice.navigation.Screen

@Preview
@Composable
fun SignupScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            NormalTextComponent(
                text = "Create an Account",
                textStyle = headerTextStyle
            )
            Spacer(modifier = Modifier.height(20.dp))
            NormalTextFieldComponent("First Name", Icons.Outlined.Person)
            NormalTextFieldComponent("Last Name", Icons.Outlined.Person)
            NormalTextFieldComponent("Email", Icons.Outlined.Email)
            PasswordTextFieldComponent("Password")
            CheckBoxComponent(onTextSelected = {
                AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
            })
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent("Register")
            DividerTextComponent()
            ClickableLoginTextComponent(
                questionText = "Already have an account?",
                navigationText = "Login"
            ) {
                AppRouter.navigateTo(Screen.Login)
            }
        }
    }
}


@Composable
fun CheckBoxComponent(onTextSelected: (String) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkedState by remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState, onCheckedChange = {
            checkedState != checkedState
        })
        TermsAndConditions(onTextSelected)
    }
}


@Composable
fun TermsAndConditions(onTextSelected: (String) -> Unit) {
    val privacyPolicy = "Privacy Policy"
    val termsOfUse = "Terms of Use"
    val annotatedString = buildAnnotatedString {
        append("By continuing you accept our ")
        withStyle(style = SpanStyle(MaterialTheme.colors.primary)) {
            pushStringAnnotation(tag = privacyPolicy, annotation = privacyPolicy)
            append(privacyPolicy)
        }
        append(" and ")
        withStyle(style = SpanStyle(MaterialTheme.colors.primary)) {
            pushStringAnnotation(tag = termsOfUse, annotation = termsOfUse)
            append(termsOfUse)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.let { span ->
                if (span.item == termsOfUse || span.item == privacyPolicy) {
                    onTextSelected(span.item)
                }
            }
    })
}
