package no.oda.shop.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import no.oda.shop.data.Product
import no.oda.shop.data.ShopApi
import no.oda.shop.ui.formatOre
import no.oda.shop.ui.theme.ShopColors
import no.oda.shop.ui.theme.ShopTheme

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = viewModel { ProductListViewModel(ShopApi.create()) },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductListScreen(uiState = uiState, onEvent = viewModel::onEvent)
}

@Composable
fun ProductListScreen(
    uiState: ProductListUiState,
    onEvent: (ProductListEvent) -> Unit,
) {
    // TODO: The cart, and a way to get to it from here.
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .windowInsetsTopHeight(WindowInsets.statusBars)
                    .background(MaterialTheme.colorScheme.background),
            )
        },
    ) { innerPadding ->
        val layoutDirection = LocalLayoutDirection.current
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            contentPadding = PaddingValues(
                start = innerPadding.calculateStartPadding(layoutDirection) + 16.dp,
                top = innerPadding.calculateTopPadding() + 16.dp,
                end = innerPadding.calculateEndPadding(layoutDirection) + 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 16.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(uiState.products, key = { it.id }) { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
private fun ProductCard(product: Product) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp),
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(ShopColors.White),
        )
        Spacer(Modifier.height(12.dp))
        // Fixed line counts keep the prices level across a row, whatever the title's length.
        Text(
            text = product.title,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = product.subtitle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            minLines = 3,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = formatOre(product.priceOre),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        // TODO: A button that puts the product in the cart.
    }
}

@Preview
@Composable
private fun ProductListScreenPreview() {
    ShopTheme {
        ProductListScreen(
            uiState = ProductListUiState(
                products = listOf(
                    Product("bananer", "Bananer i Klase", "5-6 stk, Costa Rica / Colombia", "", "", 2690),
                    Product("klementiner", "Klementiner", "Spania / Marokko, 1 kg", "", "", 4990),
                ),
            ),
            onEvent = {},
        )
    }
}
