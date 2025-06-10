package com.br.karen.composeshoes.di.modules

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.room.Room
import com.br.karen.composeshoes.R
import com.br.karen.composeshoes.dao.AppDao
import com.br.karen.composeshoes.database.AppDatabase
import com.br.karen.composeshoes.database.Migrations.MIGRATION_1_2
import com.br.karen.composeshoes.database.Migrations.MIGRATION_2_3
import com.br.karen.composeshoes.model.Product
import com.br.karen.composeshoes.repository.AppRepository
import com.br.karen.composeshoes.ui.viewmodel.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AppViewModel)
}

val repositoryModule = module {
    singleOf(::AppRepository)
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration(true)
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .build()
    }

    single { get<AppDatabase>().appDao() }

    single(createdAtStart = true) {
        val dao = get<AppDao>()

        CoroutineScope(Dispatchers.IO).launch {
            if (dao.getAllProducts().isEmpty()) {
                dao.saveProduct(
                    Product(
                        name = "Chuteira Nike Tiempo 10",
                        description = "Dê as boas-vindas à próxima geração do Air Max. Este modelo contemporâneo e" +
                                " deslumbrante homenageia o rico legado do Air Max com um cabedal em mesh multicamadas" +
                                " com estampa tátil e iconografia tradicional, detalhes foscos para um visual magnético" +
                                " e um inovador sistema de unidade Dynamic Air, projetado para fazer com que caminhar pareça" +
                                " deslizar no ar.",
                        price = 245.99,
                        image = R.drawable.image_tenis_1
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Nike Air Max Dn Essential",
                        description = "Dê as boas-vindas à próxima geração do Air Max. Este modelo contemporâneo e" +
                                " deslumbrante homenageia o rico legado do Air Max com um cabedal em mesh multicamadas" +
                                " com estampa tátil e iconografia tradicional, detalhes foscos para um visual magnético" +
                                " e um inovador sistema de unidade Dynamic Air, projetado para fazer com que caminhar pareça" +
                                " deslizar no ar.",
                        price = 699.00,
                        image = R.drawable.image_tenis_2
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Nike Air Max 2013",
                        description = "Dê as boas-vindas à próxima geração do Air Max. Este modelo contemporâneo e" +
                                " deslumbrante homenageia o rico legado do Air Max com um cabedal em mesh multicamadas" +
                                " com estampa tátil e iconografia tradicional, detalhes foscos para um visual magnético" +
                                " e um inovador sistema de unidade Dynamic Air, projetado para fazer com que caminhar pareça" +
                                " deslizar no ar.",
                        price = 920.00,
                        image = R.drawable.image_tenis_3
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Nike Air Zoom Upturn SC",
                        description = "Dê as boas-vindas à próxima geração do Air Max. Este modelo contemporâneo e" +
                                " deslumbrante homenageia o rico legado do Air Max com um cabedal em mesh multicamadas" +
                                " com estampa tátil e iconografia tradicional, detalhes foscos para um visual magnético" +
                                " e um inovador sistema de unidade Dynamic Air, projetado para fazer com que caminhar pareça" +
                                " deslizar no ar.",
                        price = 399.99,
                        image = R.drawable.image_tenis_4
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Produto sem imagem",
                        description = LoremIpsum(50).values.first(),
                        price = 250.99
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Produto com outra imagem",
                        description = LoremIpsum(20).values.first(),
                        price = 300.00,
                        image = R.drawable.calcado_feminino
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Produto Calçado teste",
                        description = LoremIpsum(150).values.first(),
                        price = 456.99,
                        image = R.drawable.calcado_feminino_2
                    )
                )
            }
        }
    }
}
