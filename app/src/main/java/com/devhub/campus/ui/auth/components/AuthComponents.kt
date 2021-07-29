package com.devhub.campus.ui.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.material.TextField
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import com.devhub.campus.R

@Composable
fun BigButton(
    onPressed: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onPressed,
        modifier = modifier
            .padding(24.dp)
            .height(48.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White
        )
    ) {
        content
    }
}

@Composable
fun Header(
    style: TextStyle = TextStyle(),
    text: String,
    smallText: String? = null
) {
    Column() {
        Text(
            text,
            style = style,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            if(!smallText.isNullOrEmpty()) smallText else "",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun DropDownList() {
    DropdownMenu(
        expanded = false,
        onDismissRequest = { /*TODO*/ }
    ) {

    }
}

@Composable
fun UserTextInput(
    text: String,
    onValueChanged: (String) -> Unit,
    labelText: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Surface {
        Box{
            TextField(
                value = text,
                onValueChange = onValueChanged,
                modifier = Modifier.fillMaxWidth(),
                // textStyle = MaterialTheme.typography.subtitle1,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                label = { Text(labelText) },
                textStyle = MaterialTheme.typography.subtitle1,
                visualTransformation = visualTransformation,
            )
        }

        /* TODO: requires some more styling */
    }
}