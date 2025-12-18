package com.franks.testtemi.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.franks.testtemi.R
import com.franks.testtemi.models.CategoriesItem
import com.franks.testtemi.models.ProductModel
import com.franks.testtemi.ui.theme.backgroundGrey
import com.franks.testtemi.ui.theme.bellOrange
import com.franks.testtemi.ui.theme.blueColor
import com.franks.testtemi.ui.theme.brownColor
import com.franks.testtemi.ui.theme.softColor
import com.franks.testtemi.ui.theme.textGreen
import com.franks.testtemi.ui.theme.textOrange
import com.franks.testtemi.ui.theme.violetColor
import com.franks.testtemi.ui.theme.yellow

@Preview
@Composable
fun HomeScreen(){

    Scaffold(
        Modifier, bottomBar = { BottomBar() }
    )
    {
        paddingValues ->
        Column (Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 30.dp)
            .verticalScroll(state = rememberScrollState())
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = textOrange)){
                            append("Hyper")
                        }
                        withStyle(SpanStyle(color = textGreen)) {
                            append("Mart")
                        }
                    },
                    Modifier, fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(Modifier.weight(1f))

                Row(
                    Modifier,
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Eng")
                    Box(
                        Modifier
                            .clip(CircleShape)
                            .background(backgroundGrey)
                            .padding(all = 5.dp)
                    ){
                        Icon(imageVector = Icons.Filled.Notifications, null, Modifier, tint = bellOrange )
                    }

                }


            }

            //Barra de busqueda
            SearchBox()

            //Carrusel

            Row(
                Modifier
                    .padding(top = 20.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                for(i in 0..2)
                Box(
                    Modifier
                        .padding(
                            start = if (i == 0) 20.dp else 0.dp,
                            end = if (i == 2) 20.dp else 0.dp
                        )
                        .height(180.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Image(
                        painter = painterResource(id = R.drawable.back_logo),
                        contentDescription = null
                    )

                    Column (
                        Modifier.padding(start = 20.dp),
                    ){
                        Text( text = "Happy Weekend")
                        Text( text = "50% Off",
                            Modifier,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }


            //Categorias
            ItemTitle("Categories")

            val categoriesList =
                listOf(
                    CategoriesItem("Groceries", img = R.drawable.groceries_icon, bgColor = textGreen),
                    CategoriesItem("Appliances", img = R.drawable.appliances_icon, bgColor = blueColor),
                    CategoriesItem("Furnitures", img = R.drawable.furniture_icon, bgColor = brownColor),
                    CategoriesItem("Fashion", img = R.drawable.fashion_icon, bgColor = violetColor),
                )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
            ){
                categoriesList.forEachIndexed{index, item ->
                    Box(
                        Modifier
                            .padding(start = if(index == 0 ) 20.dp else 0.dp )
                            .padding(end = if(index == categoriesList.size - 1) 20.dp else 0.dp)
                            .height(95.dp)
                            .width(90.dp)
                            .background(item.bgColor, shape = RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(painter = painterResource(item.img), null)
                            Text(text = item.title, Modifier
                                .padding(top = 5.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W600,
                                color = Color.White
                                )
                        }
                    }
                }
            }

            //Popular deals
            ItemTitle("Popular Deals")

            val products = listOf(
                ProductModel(img = R.drawable.product1, title = "Strawberries", price = "10$"),
                ProductModel(img = R.drawable.product2, title = "Fried Chips", price = "12$"),
                ProductModel(img = R.drawable.product3, title = "Modern Chair", price = "100$"),
                ProductModel(img = R.drawable.product4, title = "Washing Machine", price = "500$"),
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier.padding(top = 20.dp)
                    .padding(horizontal = 20.dp)
                    .height(550.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                itemsIndexed(items = products){index, product ->
                    Box(
                        Modifier.wrapContentSize()
                            .border(2.dp, color = backgroundGrey, shape = RoundedCornerShape(size = 12.dp))
                    ){
                        ProductItem(product)
                    }
                }
            }

            //Top marcas
            val brandsList =
                listOf(
                    R.drawable.brand1,
                    R.drawable.brand2,
                    R.drawable.brand3,
                    R.drawable.brand4
                )

            ItemTitle("Top Brands")

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(16.dp)

            ){
                brandsList.forEachIndexed{index, brand ->
                    Image(painter = painterResource(brand),
                        null,
                        Modifier
                            .padding(start =  if(index == 0) 20.dp else 0.dp)
                            .padding(end = if(index == brandsList.size - 1) 20.dp else 0.dp)
                            .size(width = 95.dp, height = 50.dp)
                    )
                }
            }

            //Marcas exclusivas
            ItemTitle("Exclusive Beauty Deals")

            val dealsList = listOf(
                R.drawable.deals1,
                R.drawable.deals2,
                R.drawable.deals3,
                R.drawable.deals4,
                R.drawable.deals5,
                R.drawable.deals6,

            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                Modifier
                    .height(300.dp)
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(items = dealsList){index, deal ->
                    Deals(deal)
                }
            }


            Spacer(Modifier.height(100.dp))

        }
    }
}

@Composable
fun Deals(deal: Int) {
    Column(
        Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Box(Modifier
            .background(color = backgroundGrey, shape = RoundedCornerShape(size = 16.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .padding(15.dp),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(deal), null,
                Modifier
                    .width(70.dp)
                    .height(45.dp)
            )
        }

        Box(
            Modifier
                .offset(y = -20.dp)
                .background(color = textGreen, CircleShape)
                .padding(all = 12.dp),
            contentAlignment = Alignment.Center
        ){
            Text("10% off", color = Color.White, fontSize = 10.sp,)
        }
    }
}

@Composable
fun ProductItem(product: ProductModel) {

    var heartClicked by remember { mutableStateOf(false) }
    var cartClicked by remember { mutableStateOf(false) }
    var cartCount by remember { mutableStateOf(1) }

    Column (
        Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box (
            Modifier
                .fillMaxWidth()
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }){
                heartClicked = !heartClicked
            },
            contentAlignment = Alignment.TopStart
        ){
            Image(
                painter = painterResource(
                    id = if(heartClicked) R.drawable.empty_heart
                    else R.drawable.filled_heart
                ),
                null
            )
        }

        Image(
            painter = painterResource(product.img),
                null,
                Modifier.padding(all = 12.dp)
                    .size(width = 90.dp, height = 95.dp)
        )

        Text(text = product.title, Modifier, fontSize = 14.sp)
        Row(Modifier.padding(top = 12.dp)){
            Text(text = product.price)
            Spacer(Modifier.weight(1f))
            Row{
                Text("4.8", Modifier, color = bellOrange)
                Icon(
                    imageVector = Icons.Filled.Star,
                    null,
                    Modifier.padding(start = 2.dp)
                        .size(size = 16.dp),
                    tint = yellow
                )
            }
        }

        if(!cartClicked){
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .border(width = 1.dp, color = yellow, shape = RoundedCornerShape(size = 12.dp))
                    .clickable(){
                        cartClicked = true
                    }
            ){
                Text("Add to Cart",
                    Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    color = yellow,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }else{
            Row(Modifier
                .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    Modifier
                        .size(36.dp)
                        .background(color = bellOrange, shape = RoundedCornerShape(size = 12.dp))
                        .clickable(){
                            if(cartCount > 1) cartCount--
                            if(cartCount == 1) cartClicked = false;
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text("-",
                        Modifier,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                }

                Spacer(Modifier.weight(1f))
                Text(cartCount.toString(), Modifier, fontSize = 20.sp, color = textGreen)
                Spacer(Modifier.weight(1f))


                Box(
                    Modifier
                        .size(36.dp)
                        .background(color = textGreen, shape = RoundedCornerShape(size = 12.dp))
                        .clickable(){
                            if(cartCount >= 1) cartCount++;
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text("+",
                        Modifier,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ItemTitle(title: String){
    Row (
        Modifier.fillMaxWidth()
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = title,
            Modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600
        )

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = Icons.Filled.ArrowForwardIos,
            contentDescription = null,
            Modifier.size(size = 24.dp)
        )
    }
}

@Composable
fun SearchBox(){
    var inputValue by remember { mutableStateOf(" ") }
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 20.dp)
            .background(backgroundGrey, shape = RoundedCornerShape(12.dp))

    ){
        Row(
            Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically

        ){
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                Modifier.padding(start = 15.dp),
                tint = softColor
            )

            TextField(
                value = inputValue,
                onValueChange = { it->
                    inputValue = it },
                Modifier,
                placeholder = {
                    Text("Search Anything...", color = softColor)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                )
            )

            Icon(
                imageVector = Icons.Filled.Mic,
                contentDescription = null,
                Modifier,
                tint = textGreen
            )
        }
    }
}


@Composable
fun BottomBar(){
    val bottomBarIcons = listOf(
        R.drawable.home_icon,
        R.drawable.categories_icon,
        R.drawable.wishlist_icon,
        R.drawable.profile_icon
    )

    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.BottomCenter
    ){
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(all = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(60.dp)
        ){
            bottomBarIcons.forEachIndexed{ index, icon ->
                Image(painter = painterResource(id = icon), null,
                    Modifier.size(24.dp))

                if(index == 1)
                    Spacer(Modifier.width(20.dp))
            }
        }

        Image(painter =  painterResource(id = R.drawable.center_tab), null)



    }
}

